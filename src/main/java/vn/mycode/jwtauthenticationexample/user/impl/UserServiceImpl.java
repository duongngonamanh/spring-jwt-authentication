package vn.mycode.jwtauthenticationexample.user.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.mycode.jwtauthenticationexample.user.IUserService;
import vn.mycode.jwtauthenticationexample.user.dto.UserDto;
import vn.mycode.jwtauthenticationexample.user.entity.User;
import vn.mycode.jwtauthenticationexample.user.repository.IRelUsersRoleRepository;
import vn.mycode.jwtauthenticationexample.user.repository.IUserRepository;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IRelUsersRoleRepository relUsersRoleRepository;

    public UserServiceImpl(IUserRepository userRepository, IRelUsersRoleRepository relUsersRoleRepository) {
        this.userRepository = userRepository;
        this.relUsersRoleRepository = relUsersRoleRepository;
    }

    @Override
    public UserDto login(String username, String password) throws Exception {
        UserDto output = new UserDto();
        User user = userRepository.findByUsernameLike(username);
        //Check password
        if (password.equals(user.getPassword())) {
            List<String> roles = relUsersRoleRepository.findRole_NameByUser_UsernameLike(username);
            output.setUsername(user.getUsername());
            output.setRoles(roles);
        }
        return output;
    }

    @Override
    public UserDetails findByUsernameLike(String username) throws IOException {
        User user = userRepository.findByUsernameLike(username);
        List<String> roles = relUsersRoleRepository.findRole_NameByUser_UsernameLike(username);
        Collection<GrantedAuthority> authorities = new HashSet<>();
        for (String role: roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}

package vn.mycode.jwtauthenticationexample.user;

import org.springframework.security.core.userdetails.UserDetails;
import vn.mycode.jwtauthenticationexample.user.dto.UserDto;

import java.io.IOException;

public interface IUserService {
    UserDto login(String username, String password) throws Exception;
    UserDetails findByUsernameLike(String username) throws IOException;
}

package vn.mycode.jwtauthenticationexample.auth.impl;

import org.springframework.stereotype.Service;
import vn.mycode.jwtauthenticationexample.auth.IAuthService;
import vn.mycode.jwtauthenticationexample.auth.dto.RequestLoginDto;
import vn.mycode.jwtauthenticationexample.auth.dto.ResponseLoginDto;
import vn.mycode.jwtauthenticationexample.user.IUserService;
import vn.mycode.jwtauthenticationexample.user.dto.UserDto;
import vn.mycode.jwtauthenticationexample.util.JwtProvider;

@Service
public class AuthServiceImpl implements IAuthService {
    private IUserService userService;
    private JwtProvider jwtProvider;

    public AuthServiceImpl(
            IUserService userService,
            JwtProvider jwtProvider
    ) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public ResponseLoginDto login(RequestLoginDto requestLoginDto) throws Exception{
        ResponseLoginDto output = new ResponseLoginDto();
        UserDto userDto = userService.login(requestLoginDto.getUsername(), requestLoginDto.getPassword());

        String accessToken = jwtProvider.generateToken(userDto);

        output.setAccessToken(accessToken);
        output.setTokenType("Bearer");

        return output;
    }

}

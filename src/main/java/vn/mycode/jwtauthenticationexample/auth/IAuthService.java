package vn.mycode.jwtauthenticationexample.auth;

import vn.mycode.jwtauthenticationexample.auth.dto.RequestLoginDto;
import vn.mycode.jwtauthenticationexample.auth.dto.ResponseLoginDto;

public interface IAuthService {
    ResponseLoginDto login(RequestLoginDto requestLoginDto) throws Exception;
}

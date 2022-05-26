package vn.mycode.jwtauthenticationexample.auth.dto;

import lombok.Data;

@Data
public class ResponseLoginDto {
    private String accessToken;
    private String tokenType = "Bearer";
}

package vn.mycode.jwtauthenticationexample.auth.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponseDto {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}

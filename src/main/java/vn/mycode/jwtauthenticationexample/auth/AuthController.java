package vn.mycode.jwtauthenticationexample.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.mycode.jwtauthenticationexample.auth.dto.ErrorResponseDto;
import vn.mycode.jwtauthenticationexample.auth.dto.RequestLoginDto;
import vn.mycode.jwtauthenticationexample.auth.dto.ResponseLoginDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestLoginDto requestLoginDto, HttpServletRequest request) {
        try {
            ResponseLoginDto responseLoginDto = authService.login(requestLoginDto);
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(responseLoginDto);
        }
        catch (Exception e) {
            ErrorResponseDto errorResponseDto = new ErrorResponseDto();

            errorResponseDto.setTimestamp(new Date());
            errorResponseDto.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            errorResponseDto.setError("Unauthorized");
            errorResponseDto.setMessage("Username or password incorrect!");
            errorResponseDto.setPath(request.getRequestURI());

            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(errorResponseDto);
        }
    }
}

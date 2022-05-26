package vn.mycode.jwtauthenticationexample;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestController {
    @GetMapping("/auth")
    @PreAuthorize("isAuthenticated()")
    public String auth() {
        return "Function auth";
    }

    @GetMapping("/admin-auth")
    @PreAuthorize("hasRole('ADMIN')") //maping with 'ROLE_ADMIN' in db
    public String adminAuth() {
        return "Function adminAuth";
    }

    @GetMapping("/authority-auth")
    @PreAuthorize("hasAuthority('authorityAuth_TestController')") //maping with 'authorityAuth_TestController" in db
    public String authorityAuth_TestController() {
        return "Function authorityAuth";
    }
}

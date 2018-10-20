package aua.projects.engineering.controller;

import aua.projects.engineering.authentication.AuthProvider;
import aua.projects.engineering.beans.response.ResponseResult;
import aua.projects.engineering.beans.response.ResponseStatus;
import aua.projects.engineering.dto.UserDto;
import aua.projects.engineering.service.EmergencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthProvider authProvider;
    @Autowired
    private EmergencyService emergencyService;
    private Logger LOG = LoggerFactory.getLogger(getClass());

    private static class LoginRequest{
        public String username;
        public String password;

    }
    @PostMapping("/login")
    ResponseResult login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        try {
            boolean authenticated = authProvider.checkUserCredentials(loginRequest.username, loginRequest.password);
            if(authenticated){
                response.addHeader(HttpHeaders.AUTHORIZATION, authProvider.createToken(loginRequest.username));
                return ResponseResult.ok();
            }
            return ResponseResult.error(ResponseStatus.AUTHENTICATION_FAILED);
        }catch (Throwable t){
            LOG.error("Login failed. ", t);
            return ResponseResult.error();
        }
    }
}

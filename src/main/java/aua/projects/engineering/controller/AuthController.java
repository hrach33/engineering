package aua.projects.engineering.controller;

import aua.projects.engineering.authentication.AuthProvider;
import aua.projects.engineering.beans.response.ResponseResult;
import aua.projects.engineering.beans.response.ResponseStatus;
import aua.projects.engineering.dto.TeamDto;
import aua.projects.engineering.dto.UserDto;
import aua.projects.engineering.service.EmergencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
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

    private static class LoginResponse {
        public String jwt;
        public long teamId;
        public long userId;
    }
    @PostMapping("/login")
    ResponseResult login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        try {
            boolean authenticated = authProvider.checkUserCredentials(loginRequest.username, loginRequest.password);
            if(authenticated){
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.jwt = authProvider.createToken(loginRequest.username);
                loginResponse.userId = emergencyService.getUserByUsername(loginRequest.username).getId();
                TeamDto teamDto = emergencyService.getTeamByUsername(loginRequest.username);
                if(teamDto != null)
                    loginResponse.teamId = teamDto.getId();
                return ResponseResult.ok(loginResponse);
            }
            return ResponseResult.error(ResponseStatus.AUTHENTICATION_FAILED);
        }catch (Throwable t){
            LOG.error("Login failed. ", t);
            return ResponseResult.error();
        }
    }
}

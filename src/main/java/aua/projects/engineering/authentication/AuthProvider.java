package aua.projects.engineering.authentication;

import aua.projects.engineering.dto.UserDto;
import aua.projects.engineering.service.EmergencyService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Date;

@Component
public class AuthProvider {

    @Value("${secret.key}")
    private String key;
    @Value("${jwt.expiration.miliseconds}")
    private long expirationTime;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmergencyService emergencyService;

    private Algorithm algorithm;
    private Logger LOG = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void init(){
        algorithm = Algorithm.HMAC256(key);
    }

    public boolean verifyToken(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception){
            LOG.error("Authentication failed ", exception);
            return false;
        }
    }

    public String createToken(String username){
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            String token = JWT.create().withClaim("userName", username).withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                    .withIssuer("auth0")
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            return null;
        }

    }

    public boolean checkUserCredentials(String username, String password){
//        UserDto userDto = emergencyService.getUserByUserName(username);
        UserDto userDto = null;
        if(userDto != null){
            return passwordEncoder.matches(password, userDto.getPassword());
        }
        return false;
    }

    public String hashPassword(String password){
        return passwordEncoder.encode(password);
    }
}

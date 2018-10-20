package aua.projects.engineering.controller;

import aua.projects.engineering.beans.response.ResponseResult;
import aua.projects.engineering.dto.UserDto;
import aua.projects.engineering.service.EmergencyService;
import aua.projects.engineering.service.EmergencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/emergency")
public class EmergencyController {

    @Autowired
    private EmergencyService emergencyService;
    private Logger LOG = LoggerFactory.getLogger(getClass());


    @GetMapping("/getAllUsers")
    ResponseResult getAllUsers(){
        try {
            List<UserDto> userDtos = emergencyService.getAllUsers();
            return ResponseResult.ok(userDtos);
        }catch (Throwable t){
            LOG.error("Get all users failed. ", t);
            return ResponseResult.error();
        }
    }

    @GetMapping("/getUserById/{id}")
    ResponseResult getUserById(@PathVariable("id") Long id){
        try {
            UserDto userDto = emergencyService.getUserById(id);
            return ResponseResult.ok(userDto);
        }catch (Throwable t){
            LOG.error("Get user by id failed. ", t);
            return ResponseResult.error();
        }
    }

    @PostMapping("/insertUser")
    ResponseResult inserUser(@RequestBody UserDto userDto){
        try {
            emergencyService.insertUser(userDto);
            return ResponseResult.ok();
        }catch (Throwable t){
            LOG.error("Insert user failed. ", t);
            return ResponseResult.error();
        }
    }
}

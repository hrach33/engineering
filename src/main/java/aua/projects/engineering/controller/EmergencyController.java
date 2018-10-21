package aua.projects.engineering.controller;

import aua.projects.engineering.beans.response.ResponseResult;
import aua.projects.engineering.dto.TaskDto;
import aua.projects.engineering.dto.UserDto;
import aua.projects.engineering.dto.TeamDto;
import aua.projects.engineering.service.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@CrossOrigin
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

    @GetMapping("/getUserByUsername/{username}")
    ResponseResult getUserByUsername(@PathVariable("username") String username){
        try {
            UserDto userDto = emergencyService.getUserByUsername(username);
            return ResponseResult.ok(userDto);
        }catch (Throwable t){
            LOG.error("Get user by username failed. ", t);
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

    @PostMapping("/updateUser")
    ResponseResult updateUser(@RequestBody UserDto userDto) {
        try {
            emergencyService.updateUser(userDto);
            return ResponseResult.ok();
        } catch (Throwable t) {
            LOG.error("update user failed. ", t);
            return ResponseResult.error();
        }
    }

    @GetMapping("/getAllTeams")
    ResponseResult getAllTeams(){
        try {
            List<TeamDto> teamDtos = emergencyService.getAllTeams();
            return ResponseResult.ok(teamDtos);
        }catch (Throwable t){
            LOG.error("Get all teams failed. ", t);
            return ResponseResult.error();
        }
    }

    @GetMapping("/getTeamById/{id}")
    ResponseResult getTeamById(@PathVariable("id") Long id){
        try {
            TeamDto teamDto = emergencyService.getTeamById(id);
            return ResponseResult.ok(teamDto);
        }catch (Throwable t){
            LOG.error("Get team by id failed. ", t);
            return ResponseResult.error();
        }
    }

    @PostMapping("/insertTeam")
    ResponseResult inserTeam(@RequestBody TeamDto teamDto){
        try {
            emergencyService.insertTeam(teamDto);
            return ResponseResult.ok();
        }catch (Throwable t){
            LOG.error("Insert team failed. ", t);
            return ResponseResult.error();
        }
    }

    @PostMapping("/updateTeam")
    ResponseResult updateTeam(@RequestBody TeamDto teamDto) {
        try {
            emergencyService.updateTeam(teamDto);
            return ResponseResult.ok();
        } catch (Throwable t) {
            LOG.error("update user failed. ", t);
            return ResponseResult.error();
        }
    }




    @GetMapping("/getAllTasks")
    ResponseResult getAllTasks(){
        try {
            List<TaskDto> taskDtos = emergencyService.getAllTasks();
            return ResponseResult.ok(taskDtos);
        }catch (Throwable t){
            LOG.error("Get all tasks failed. ", t);
            return ResponseResult.error();
        }
    }

    @GetMapping("/getTaskById/{id}")
    ResponseResult getTaskById(@PathVariable("id") Long id){
        try {
            TaskDto taskDto = emergencyService.getTaskById(id);
            return ResponseResult.ok(taskDto);
        }catch (Throwable t){
            LOG.error("Get task by id failed. ", t);
            return ResponseResult.error();
        }
    }

    @PostMapping("/insertTask")
    ResponseResult inserTask(@RequestBody TaskDto taskDto){
        try {
            emergencyService.insertTask(taskDto);
            return ResponseResult.ok();
        }catch (Throwable t){
            LOG.error("Insert task failed. ", t);
            return ResponseResult.error();
        }
    }

    @PostMapping("/updateTask")
    ResponseResult updateTask(@RequestBody TaskDto taskDto) {
        try {
            emergencyService.updateTask(taskDto);
            return ResponseResult.ok();
        } catch (Throwable t) {
            LOG.error("update task failed. ", t);
            return ResponseResult.error();
        }
    }

}

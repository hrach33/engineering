package aua.projects.engineering.controller;

import aua.projects.engineering.beans.response.ResponseResult;
import aua.projects.engineering.beans.response.ResponseStatus;
import aua.projects.engineering.dto.*;
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

    private static class CountRequest {
        public SearchFilter searchFilter;
    }

    @RequestMapping(value = "/countUsers", method = RequestMethod.POST)
    public ResponseResult countUsers(@RequestBody CountRequest request) {
        try {
            return ResponseResult.ok(emergencyService.getSearchCount(request.searchFilter));
        } catch (Throwable throwable) {
            LOG.error("get count failed ", throwable);
            return ResponseResult.error(ResponseStatus.GENERAL_ERROR);
        }
    }

    private static class SearchReportsRequest {
        public SearchFilter searchFilter;
        public PageInfo pageInfo;
        public OrderInfoList orderInfoList;
    }

    @RequestMapping(value = "/searchUsers", method = RequestMethod.POST)
    public ResponseResult countUsers(@RequestBody SearchReportsRequest searchReportsRequest) {
        try {
            return ResponseResult.ok(emergencyService.search(searchReportsRequest.searchFilter, searchReportsRequest.pageInfo, searchReportsRequest.orderInfoList));
        } catch (Throwable throwable) {
            LOG.error("search failed ", throwable);
            return ResponseResult.error(ResponseStatus.GENERAL_ERROR);
        }
    }

    @RequestMapping(value = "/countTeams", method = RequestMethod.POST)
    public ResponseResult countTeams(@RequestBody CountRequest request) {
        try {
            return ResponseResult.ok(emergencyService.getTeamSearchCount(request.searchFilter));
        } catch (Throwable throwable) {
            LOG.error("get count failed ", throwable);
            return ResponseResult.error(ResponseStatus.GENERAL_ERROR);
        }
    }

    @RequestMapping(value = "/searchTeams", method = RequestMethod.POST)
    public ResponseResult countTeams(@RequestBody SearchReportsRequest searchReportsRequest) {
        try {
            return ResponseResult.ok(emergencyService.searchTeam(searchReportsRequest.searchFilter, searchReportsRequest.pageInfo, searchReportsRequest.orderInfoList));
        } catch (Throwable throwable) {
            LOG.error("search failed ", throwable);
            return ResponseResult.error(ResponseStatus.GENERAL_ERROR);
        }
    }
}

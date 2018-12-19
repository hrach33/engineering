package aua.projects.engineering.service;

import aua.projects.engineering.dto.*;

import java.util.List;

public interface EmergencyService {
    List<UserDto> getAllUsers();
    List<UserDto> getUsersByTeamId(long teamId);
    void deleteUser(long id);
    UserDto getUserById(long id);
    UserDto getUserByUsername(String username);
    void insertUser(UserDto userDto);
    void updateUser(UserDto userDto);
    int getSearchCount(SearchFilter searchUsersFilter);
    List<UserDto> search(SearchFilter searchUsersFilter, PageInfo pageInfo, OrderInfoList orderInfoList);
    int getTeamSearchCount(SearchFilter searchFilter);
    List<TeamDto> searchTeam(SearchFilter searchFilter, PageInfo pageInfo, OrderInfoList orderInfoList);
    List<TeamDto> getAllTeams();
    TeamDto getTeamById(long id);
    void insertTeam(TeamDto teamDto);
    void updateTeam(TeamDto teamDto);
    boolean checkUsernameTaken(String username);
    TeamDto getTeamByUsername(String username);
    List<TaskDto> getTasksByTEamID(long teamID);
    void takeTask(long taskId);
    void finishTask(long taskId, String status);
    void deleteTeam(long id);
    int getTaskCount();
    List<TaskDto> searchTasks(PageInfo pageInfo);
    void addTask(TaskDto taskDto);

}

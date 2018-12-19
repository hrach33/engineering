package aua.projects.engineering.dao;

import aua.projects.engineering.dto.*;

import java.util.List;

public interface EmergencyDao {
    List<UserDto> getAllUsers();
    UserDto getUserById(long id);
    UserDto getUserByUsername(String username);
    void insertUser(UserDto userDto);
    void updateUser(UserDto userDto);
    void deleteUser(long id);
    void deleteUserFromTeam(long id);
    int getSearchCount(SearchFilter searchFilter);
    List<UserDto> search(SearchFilter searchFilter, PageInfo pageInfo, OrderInfoList orderInfoList);
    int getTeamSearchCount(SearchFilter searchFilter);
    List<TeamDto> searchTeam(SearchFilter searchFilter, PageInfo pageInfo, OrderInfoList orderInfoList);
    List<TeamDto> getAllTeams();
    TeamDto getTeamById(long id);
    void insertTeam(TeamDto teamDto);
    void updateTeam(TeamDto teamDto);
    TeamDto getTeamByUserId(String username);
    List<TaskDto> getTasksByTEamID(long teamID);
    void changeTaskStatus(long taskId, String status);
    void changeTaskState(long taskId, String state);
    List<UserDto> getUsersByTeamId(long teamId);
    void insertUserTeam(long userId, long teamId);
    void deleteTeamUsers(long teamID);
    void deleteTeam(long id);
    int getTaskCount();
    List<TaskDto> searchTasks(PageInfo pageInfo);
    int addTask(TaskDto taskDto);
    void addTeamTask(long teamId, long taskId);

}

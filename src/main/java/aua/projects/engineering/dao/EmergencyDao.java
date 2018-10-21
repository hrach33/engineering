package aua.projects.engineering.dao;

import aua.projects.engineering.dto.TaskDto;
import aua.projects.engineering.dto.TeamDto;
import aua.projects.engineering.dto.UserDto;

import java.util.List;

public interface EmergencyDao {
    List<UserDto> getAllUsers();
    UserDto getUserById(long id);
    UserDto getUserByUsername(String username);
    void insertUser(UserDto userDto);
    void updateUser(UserDto userDto);

    List<TeamDto> getAllTeams();
    TeamDto getTeamById(long id);
    void insertTeam(TeamDto teamDto);
    void updateTeam(TeamDto teamDto);

    List<TaskDto> getAllTasks();
    TaskDto getTaskById(long id);
    void insertTask(TaskDto taskDto);
    void updateTask(TaskDto taskDto);

}

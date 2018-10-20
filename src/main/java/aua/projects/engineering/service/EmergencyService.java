package aua.projects.engineering.service;

import aua.projects.engineering.dto.TeamDto;
import aua.projects.engineering.dto.UserDto;

import java.util.List;

public interface EmergencyService {
    List<UserDto> getAllUsers();
    UserDto getUserById(long id);
    void insertUser(UserDto userDto);
    void updateUser(UserDto userDto);

    List<TeamDto> getAllTeams();
    TeamDto getTeamById(long id);
    void insertTeam(TeamDto teamDto);
    void updateTeam(TeamDto teamDto);
}

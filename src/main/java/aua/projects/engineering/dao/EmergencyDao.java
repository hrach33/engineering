package aua.projects.engineering.dao;

import aua.projects.engineering.dto.*;

import java.util.List;

public interface EmergencyDao {
    List<UserDto> getAllUsers();
    UserDto getUserById(long id);
    UserDto getUserByUsername(String username);
    void insertUser(UserDto userDto);
    void updateUser(UserDto userDto);
    int getSearchCount(SearchFilter searchFilter);
    List<UserDto> search(SearchFilter searchFilter, PageInfo pageInfo, OrderInfoList orderInfoList);
    int getTeamSearchCount(SearchFilter searchFilter);
    List<TeamDto> searchTeam(SearchFilter searchFilter, PageInfo pageInfo, OrderInfoList orderInfoList);
    List<TeamDto> getAllTeams();
    TeamDto getTeamById(long id);
    void insertTeam(TeamDto teamDto);
    void updateTeam(TeamDto teamDto);

}

package aua.projects.engineering.service;

import aua.projects.engineering.dao.EmergencyDao;
import aua.projects.engineering.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("emergencyService")
public class EmergencyServiceImpl implements EmergencyService {

    @Autowired
    private EmergencyDao emergencyDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUsers() {
        return emergencyDao.getAllUsers();
    }

    @Override
    public List<UserDto> getUsersByTeamId(long teamId) {
        return emergencyDao.getUsersByTeamId(teamId);
    }

    @Override
    public void deleteUser(long id) {
        emergencyDao.deleteUser(id);
        emergencyDao.deleteUserFromTeam(id);
    }

    @Override
    public UserDto getUserById(long id) {
        return emergencyDao.getUserById(id);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        return emergencyDao.getUserByUsername(username);
    }

    @Override
    public void insertUser(UserDto userDto) {
        if (userDto != null) {
            if (userDto.getId() == 0) {
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                emergencyDao.insertUser(userDto);
            } else {
                if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
                } else {
                    UserDto temp = emergencyDao.getUserById(userDto.getId());
                    userDto.setPassword(temp.getPassword());
                }
                emergencyDao.updateUser(userDto);
            }
        }
    }

    @Override
    public void updateUser(UserDto userDto) {
        emergencyDao.updateUser(userDto);
    }

    @Override
    public int getSearchCount(SearchFilter searchUsersFilter) {
        return emergencyDao.getSearchCount(searchUsersFilter);
    }

    @Override
    public List<UserDto> search(SearchFilter searchUsersFilter, PageInfo pageInfo, OrderInfoList orderInfoList) {
        return emergencyDao.search(searchUsersFilter, pageInfo, orderInfoList);
    }

    @Override
    public int getTeamSearchCount(SearchFilter searchFilter) {
        return emergencyDao.getTeamSearchCount(searchFilter);
    }

    @Override
    public List<TeamDto> searchTeam(SearchFilter searchFilter, PageInfo pageInfo, OrderInfoList orderInfoList) {
        List<TeamDto> teams = emergencyDao.searchTeam(searchFilter, pageInfo, orderInfoList);
        for (TeamDto teamDto : teams) {
            teamDto.setUsers(getUsersByTeamId(teamDto.getId()));
        }
        return teams;
    }

    @Override
    public List<TeamDto> getAllTeams() {
        return emergencyDao.getAllTeams();
    }

    @Override
    public TeamDto getTeamById(long id) {
        return emergencyDao.getTeamById(id);
    }

    @Override
    public void insertTeam(TeamDto teamDto) {
        if (teamDto != null) {
            if (teamDto.getId() == 0) {
                emergencyDao.insertTeam(teamDto);
            } else {
                emergencyDao.updateTeam(teamDto);
            }
            List<UserDto> users = teamDto.getUsers();
            if (users != null) {
                emergencyDao.deleteTeamUsers(teamDto.getId());
                for (UserDto userDto : users) {
                    emergencyDao.insertUserTeam(userDto.getId(), teamDto.getId());
                }
            }
        }

    }

    @Override
    public void updateTeam(TeamDto teamDto) {
        emergencyDao.updateTeam(teamDto);
    }

    @Override
    public boolean checkUsernameTaken(String username) {
        return getUserByUsername(username) != null;
    }

    @Override
    public TeamDto getTeamByUsername(String username) {
        return emergencyDao.getTeamByUserId(username);
    }

    @Override
    public List<TaskDto> getTasksByTEamID(long teamID) {
        return emergencyDao.getTasksByTEamID(teamID);
    }

    @Override
    public void takeTask(long taskId) {
        emergencyDao.changeTaskState(taskId, "in_progress");
    }

    @Override
    public void finishTask(long taskId, String status) {
        emergencyDao.changeTaskState(taskId, "finished");
        emergencyDao.changeTaskStatus(taskId, status);
    }

    @Override
    public void deleteTeam(long id) {
        emergencyDao.deleteTeam(id);
    }

    @Override
    public int getTaskCount() {
        return emergencyDao.getTaskCount();
    }

    @Override
    public List<TaskDto> searchTasks(PageInfo pageInfo) {
        return emergencyDao.searchTasks(pageInfo);
    }

    @Override
    public void addTask(TaskDto taskDto) {
        int id = emergencyDao.addTask(taskDto);
        emergencyDao.addTeamTask(taskDto.getTeamId(), id);
    }

}

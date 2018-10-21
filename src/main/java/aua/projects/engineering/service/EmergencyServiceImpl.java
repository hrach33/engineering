package aua.projects.engineering.service;

import aua.projects.engineering.dao.EmergencyDao;
import aua.projects.engineering.dto.TaskDto;
import aua.projects.engineering.dto.TeamDto;
import aua.projects.engineering.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("emergencyService")
public class EmergencyServiceImpl implements EmergencyService {

    @Autowired
    private EmergencyDao emergencyDao;

    @Override
    public List<UserDto> getAllUsers() { return emergencyDao.getAllUsers();}

    @Override
    public UserDto getUserById(long id) { return emergencyDao.getUserById(id); }

    @Override
    public UserDto getUserByUsername(String username) {
        return emergencyDao.getUserByUsername(username);
    }

    @Override
    public void insertUser(UserDto userDto) { emergencyDao.insertUser(userDto);}

    @Override
    public void updateUser(UserDto userDto) { emergencyDao.updateUser(userDto);}

    @Override
    public List<TeamDto> getAllTeams() { return emergencyDao.getAllTeams();}

    @Override
    public TeamDto getTeamById(long id) { return emergencyDao.getTeamById(id);}

    @Override
    public void insertTeam(TeamDto teamDto) { emergencyDao.insertTeam(teamDto); }

    @Override
    public void updateTeam(TeamDto teamDto) { emergencyDao.updateTeam(teamDto);}

    @Override
    public List<TaskDto> getAllTasks() { return emergencyDao.getAllTasks();}

    @Override
    public TaskDto getTaskById(long id) { return emergencyDao.getTaskById(id);}

    @Override
    public void insertTask(TaskDto taskDto) { emergencyDao.insertTask(taskDto); }

    @Override
    public void updateTask(TaskDto taskDto) { emergencyDao.updateTask(taskDto);}

    @Override
    public boolean checkUsernameTaken(String username) { return getUserByUsername(username) != null;}
}

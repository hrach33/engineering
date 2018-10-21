package aua.projects.engineering.dao;

import aua.projects.engineering.dto.TaskDto;
import aua.projects.engineering.dto.TeamDto;
import aua.projects.engineering.dto.UserDto;
import aua.projects.engineering.dto.mapper.TaskDtoMapper;
import aua.projects.engineering.dto.mapper.UserDtoMapper;
import aua.projects.engineering.dto.mapper.TeamDtoMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("emergencyDao")
public class EmergencyDaoImpl extends BaseDao implements EmergencyDao {


    private static final String GET_ALL_USERS = "select * from user";
    @Override
    public List<UserDto> getAllUsers() {
        return this.getJdbcTemplate().query(GET_ALL_USERS, new UserDtoMapper());
    }

    private static final String GET_USER_BY_ID = "select * from user where id = ? ";
    @Override
    public UserDto getUserById(long id) {
        try {
            return this.getJdbcTemplate().queryForObject(GET_USER_BY_ID, new Object[]{id}, new UserDtoMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    private static final String GET_USER_BY_USERNAME = "select * from user where user_name = ? ";
    @Override
    public UserDto getUserByUsername(String username) {
        try {
            return this.getJdbcTemplate().queryForObject(GET_USER_BY_USERNAME, new Object[]{username}, new UserDtoMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    private static final String INSERT_USER = "insert into user " +
            "(user_name, first_name, last_name, password, speciality, birth_date, gender, status) " +
            " VALUES (?,?,?,?,?,?,?,?)";
    @Override
    public void insertUser(UserDto userDto) {
        this.getJdbcTemplate().update(INSERT_USER, new Object[]{userDto.getUserName(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPassword(),
                userDto.getSpeciality(),
                userDto.getBirthDate(),
                userDto.getGender(),
                userDto.getStatus()});
    }

    private static final String UPDATE_USER = "update user set" +
            "(user_name, first_name, last_name, speciality, birth_date, gender, status) " +
            " VALUES (?,?,?,?,?,?,?) where id = ? ";
    @Override
    public void updateUser(UserDto userDto) {
        this.getJdbcTemplate().update(UPDATE_USER, new Object[]{userDto.getUserName(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getSpeciality(),
                userDto.getBirthDate(),
                userDto.getGender(),
                userDto.getStatus(),
                userDto.getId()});
    }


    private static final String GET_ALL_TEAMS = "select * from team";
    @Override
    public List<TeamDto> getAllTeams() {
        return this.getJdbcTemplate().query(GET_ALL_TEAMS, new TeamDtoMapper());
    }



    private static final String GET_TEAM_BY_ID = "select * from team where id = ? ";
    @Override
    public TeamDto getTeamById(long id) {
        try {
            return this.getJdbcTemplate().queryForObject(GET_TEAM_BY_ID, new Object[]{id}, new TeamDtoMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }


    private static final String INSERT_TEAM = "insert into team "+
            "(name, speciality, capacity, leaderId, status)" +
            " VALUES (?,?,?,?,?)";
    @Override
    public void insertTeam(TeamDto teamDto) {
        this.getJdbcTemplate().update(INSERT_TEAM, new Object[]{teamDto.getName(),
        teamDto.getSpeciality(),
        teamDto.getCapacity(),
        teamDto.getLeaderId(),
        teamDto.getStatus()});
    }


    private static final String UPDATE_TEAM = "update team set" +
            "(name, speciality, capacity, leaderId, status) " +
            " VALUES (?,?,?,?,?) where id = ? ";
    @Override
    public void updateTeam(TeamDto teamDto) {
        this.getJdbcTemplate().update(UPDATE_TEAM, new Object[]{teamDto.getName(),
                teamDto.getSpeciality(),
                teamDto.getCapacity(),
                teamDto.getLeaderId(),
                teamDto.getStatus()});
    }


    private static final String GET_ALL_TASKS = "select * from task";
    @Override
    public List<TaskDto> getAllTasks() {
        return this.getJdbcTemplate().query(GET_ALL_TASKS, new TaskDtoMapper());
    }



    private static final String GET_TASK_BY_ID = "select * from task where id = ? ";
    @Override
    public TaskDto getTaskById(long id) {
        try {
            return this.getJdbcTemplate().queryForObject(GET_TASK_BY_ID, new Object[]{id}, new TaskDtoMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }


    private static final String INSERT_TASK = "insert into task "+
            "(type, destination, description, status, level)" +
            " VALUES (?,?,?,?,?)";
    @Override
    public void insertTask(TaskDto taskDto) {
        this.getJdbcTemplate().update(INSERT_TASK, new Object[]{ taskDto.getType(),
                taskDto.getDestination(),
                taskDto.getDescription(),
                taskDto.getStatus(),
                taskDto.getLevel()});
    }



    private static final String UPDATE_TASK = "update task set" +
            "(type, destination, description, status, level) " +
            " VALUES (?,?,?,?,?) where id = ? ";
    @Override
    public void updateTask(TaskDto taskDto) {
        this.getJdbcTemplate().update(UPDATE_TASK, new Object[]{taskDto.getType(),
                taskDto.getDestination(),
                taskDto.getDescription(),
                taskDto.getStatus(),
                taskDto.getLevel()});
    }
}
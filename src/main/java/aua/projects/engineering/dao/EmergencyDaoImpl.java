package aua.projects.engineering.dao;

import aua.projects.engineering.dto.TeamDto;
import aua.projects.engineering.dto.UserDto;
import aua.projects.engineering.dto.mapper.UserDtoMapper;
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

    @Override
    public void updateUser(UserDto userDto) {

    }

    @Override
    public List<TeamDto> getAllTeams() {
        return null;
    }

    @Override
    public TeamDto getTeamById(long id) {
        return null;
    }

    @Override
    public void insertTeam(TeamDto teamDto) {

    }

    @Override
    public void updateTeam(TeamDto teamDto) {

    }
}

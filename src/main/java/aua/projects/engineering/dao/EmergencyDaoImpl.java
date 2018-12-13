package aua.projects.engineering.dao;

import aua.projects.engineering.dto.*;
import aua.projects.engineering.dto.mapper.UserDtoMapper;
import aua.projects.engineering.dto.mapper.TeamDtoMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository("emergencyDao")
public class EmergencyDaoImpl extends BaseDao implements EmergencyDao {


    private static final String GET_ALL_USERS = "select * from user";
    private static final List<String> columnValues = new ArrayList();

    @PostConstruct
    public void init(){
        columnValues.add("user_name");
        columnValues.add("name");
        columnValues.add("speciality");
        columnValues.add("status");
        columnValues.add("team_status");
    }
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

    @Override
    public int getSearchCount(SearchFilter searchFilter) {
        List<Object> filterValues = new ArrayList();
        StringBuffer query = new StringBuffer(1024);
        query.append("SELECT count(*) from user ");
        generateQuery(query, searchFilter, null, null, filterValues);
        return this.getJdbcTemplate().queryForObject(query.toString(), filterValues.toArray(), Integer.class);
    }

    @Override
    public List<UserDto> search(SearchFilter searchFilter, PageInfo pageInfo, OrderInfoList orderInfoList) {
        List<Object> filterValues = new ArrayList();
        StringBuffer query = new StringBuffer(1024);
        query.append("SELECT * from user ");
        generateQuery(query, searchFilter, pageInfo, orderInfoList, filterValues);
        return this.getJdbcTemplate().query(query.toString(), filterValues.toArray(), new UserDtoMapper());

    }

    @Override
    public int getTeamSearchCount(SearchFilter searchFilter) {
        List<Object> filterValues = new ArrayList();
        StringBuffer query = new StringBuffer(1024);
        query.append("SELECT count(*) from team ");
        generateQuery(query, searchFilter, null, null, filterValues);
        return this.getJdbcTemplate().queryForObject(query.toString(), filterValues.toArray(), Integer.class);
    }

    @Override
    public List<TeamDto> searchTeam(SearchFilter searchFilter, PageInfo pageInfo, OrderInfoList orderInfoList) {
        List<Object> filterValues = new ArrayList();
        StringBuffer query = new StringBuffer(1024);
        query.append("SELECT * from team t left join user u on t.leader_id = u.id ");
        generateQuery(query, searchFilter, pageInfo, orderInfoList, filterValues);
        return this.getJdbcTemplate().query(query.toString(), filterValues.toArray(), new TeamDtoMapper());
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
    private void generateQuery(StringBuffer query, SearchFilter searchUsersFilter, PageInfo page, OrderInfoList order,
                               List<Object> filterValues) {
        if (searchUsersFilter != null) {

            if (searchUsersFilter.getSearchParams() != null && searchUsersFilter.getSearchParams().size() != 0) {
                query.append(" where 1=1 ");
                Map<String, String> params = searchUsersFilter.getSearchParams();
                for (String key : params.keySet()) {
                    if(params.get(key) != null && !params.get(key).isEmpty() && columnValues.contains(key)) {
                            query.append(" and  " + key + " = ? ");
                            filterValues.add(params.get(key));
                    }
                }
            }

            if (order != null && order.getOrderMap() != null) {
                query.append(" ORDER BY ");
                Iterator<Map.Entry<String, Boolean>> iterator = order.getOrderMap().entrySet().iterator();
                Map.Entry<String, Boolean> entry;
                if (iterator.hasNext()) {
                    entry = iterator.next();
                    query.append(entry.getKey() + (entry.getValue() ? " ASC " : " DESC "));
                }
                while (iterator.hasNext()) {
                    query.append(" , ");
                    entry = iterator.next();
                    query.append(entry.getKey() + (entry.getValue() ? " ASC " : " DESC "));
                }
            }

            if (page != null) {
                query.append(" LIMIT ").append(page.getSize()).append(" OFFSET ").append(page.getSize() * page.getIndex());
            }
        }
    }

}

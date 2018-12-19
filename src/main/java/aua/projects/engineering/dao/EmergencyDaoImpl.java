package aua.projects.engineering.dao;

import aua.projects.engineering.dto.*;
import aua.projects.engineering.dto.mapper.TaskDtoMapper;
import aua.projects.engineering.dto.mapper.UserDtoMapper;
import aua.projects.engineering.dto.mapper.TeamDtoMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

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

    private static final String UPDATE_USER = "update user set " +
            "user_name = ?, first_name = ?, last_name = ?, speciality = ?, birth_date = ?, gender = ?, status = ?, password = ?" +
            " where id = ? ";
    @Override
    public void updateUser(UserDto userDto) {
        this.getJdbcTemplate().update(UPDATE_USER, new Object[]{
                userDto.getUserName(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getSpeciality(),
                userDto.getBirthDate(),
                userDto.getGender(),
                userDto.getStatus(),
                userDto.getPassword(),
                userDto.getId()});
    }

    private static final String DELETE_USER = "update user set status = 'deleted' where id = ? ";
    @Override
    public void deleteUser(long id) {
        this.getJdbcTemplate().update(DELETE_USER, new Object[]{id});
    }

    private static final String DELETE_USER_FROM_TEAM = "delete from user_team where user_id = ? ";

    @Override
    public void deleteUserFromTeam(long id) {
        this.getJdbcTemplate().update(DELETE_USER_FROM_TEAM, new Object[]{id});

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


    private static final String UPDATE_TEAM = "update team set " +
            " name = ?, speciality = ?, capacity = ?, team_status = ? " +
            "  where id = ? ";
    @Override
    public void updateTeam(TeamDto teamDto) {
        this.getJdbcTemplate().update(UPDATE_TEAM, new Object[]{teamDto.getName(),
                teamDto.getSpeciality(),
                teamDto.getCapacity(),
                teamDto.getStatus(),
                teamDto.getId()});
    }

    private static final String GET_TEAM_BY_USER_ID = "select t.* from user u inner join user_team ut on u.id = ut.user_id inner join team t on ut.team_id = t.id where u.user_name = ? ";
    @Override
    public TeamDto getTeamByUserId(String username) {
    try {
        return this.getJdbcTemplate().queryForObject(GET_TEAM_BY_USER_ID, new Object[]{username}, new TeamDtoMapper());
    } catch (EmptyResultDataAccessException e){
        return null;
    }
    }

    private static final String GET_TASKS_BY_TEAM_ID = "select t.*, tt.status as state from team_task tt inner join task t on tt.taskId = t.id where tt.teamId = ? ";
    @Override
    public List<TaskDto> getTasksByTEamID(long teamID) {
        return this.getJdbcTemplate().query(GET_TASKS_BY_TEAM_ID, new Object[]{teamID}, new TaskDtoMapper());
    }

    private static final String CHANGE_TASK_STATUS = "update task set status = ? where id = ? ";
    @Override
    public void changeTaskStatus(long taskId, String status) {
        this.getJdbcTemplate().update(CHANGE_TASK_STATUS, new Object[]{status, taskId});
    }

    private static final String CHANGE_TASK_STATE = "update team_task set status = ? where taskId = ? ";

    @Override
    public void changeTaskState(long taskId, String state) {
        this.getJdbcTemplate().update(CHANGE_TASK_STATE, new Object[]{state, taskId});

    }

    private static final String GET_USERS_BY_TEAM = "select u.* from user u inner join user_team t on u.id = t.user_id where t.team_id = ? ";
    @Override
    public List<UserDto> getUsersByTeamId(long teamId) {
        return this.getJdbcTemplate().query(GET_USERS_BY_TEAM, new Object[]{teamId}, new UserDtoMapper());
    }

    private static final String INSERT_USER_TEAM = "insert into user_team (user_id, team_id) values(?,?) ";
    @Override
    public void insertUserTeam(long userId, long teamId) {
        this.getJdbcTemplate().update(INSERT_USER_TEAM, new Object[]{userId, teamId});
    }

    private static final String DELETE_FROM_TEAM = " delete from user_team where team_id = ? ";
    @Override
    public void deleteTeamUsers(long teamID) {
        this.getJdbcTemplate().update(DELETE_FROM_TEAM, new Object[]{teamID});
    }

    private final String DELETE_TEAM = "update team set team_status = 'deleted' where id = ? ";
    @Override
    public void deleteTeam(long id) {
        this.getJdbcTemplate().update(DELETE_TEAM, new Object[]{id});
    }

    private static final String COUNT_TASKS = "select count(*) from task ";
    @Override
    public int getTaskCount() {
        return this.getJdbcTemplate().queryForObject(COUNT_TASKS, Integer.class);
    }

    private static final String SEARCH_TASKS = "select t.*, ttt.id as team_id, ttt.name as team_name, tt.status as state from task t inner join team_task tt on t.id = tt.taskId inner join team ttt on tt.teamId = ttt.id ";
    @Override
    public List<TaskDto> searchTasks(PageInfo pageInfo) {
        StringBuffer query = new StringBuffer(1024);
        query.append(SEARCH_TASKS);
        OrderInfoList orderInfoList = new OrderInfoList();
        Map<String, Boolean> orderMap = new HashMap<>();
        orderMap.put("t.id", false);
        orderInfoList.setOrderMap(orderMap);


        if (orderInfoList != null && orderInfoList.getOrderMap() != null) {
            query.append(" ORDER BY ");
            Iterator<Map.Entry<String, Boolean>> iterator = orderInfoList.getOrderMap().entrySet().iterator();
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

        if (pageInfo != null) {
            query.append(" LIMIT ").append(pageInfo.getSize()).append(" OFFSET ").append(pageInfo.getSize() * pageInfo.getIndex());
        }
        return this.getJdbcTemplate().query(query.toString(), new TaskDtoMapper());
    }

    private static final String ADD_TASK = "insert into task (type, destination, description, level) values(:type,:destination,:description,:level) ";
    @Override
    public int addTask(TaskDto taskDto) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("type", taskDto.getType())
                .addValue("destination", taskDto.getDestination())
                .addValue("description", taskDto.getDescription())
                .addValue("level", taskDto.getLevel());
        this.getParameterJdbcTemplate().update(ADD_TASK, parameters, holder);
        return holder.getKey().intValue();
    }

    private static final String ADD_TEAM_TASK = "insert into team_task (teamId, taskId, status) values(?,?,'pending') ";
    @Override
    public void addTeamTask(long teamId, long taskId) {
        this.getJdbcTemplate().update(ADD_TEAM_TASK, new Object[]{teamId, taskId});
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

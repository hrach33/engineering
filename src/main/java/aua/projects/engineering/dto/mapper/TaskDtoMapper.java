package aua.projects.engineering.dto.mapper;

import aua.projects.engineering.dto.TaskDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TaskDtoMapper implements RowMapper<TaskDto> {
    @Override
    public TaskDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        TaskDto entity = new TaskDto();
        ResultSetMetaData metadata = resultSet.getMetaData();
        int colCount = metadata.getColumnCount();
        for (int col = 1; col <= colCount; ++col) {
            String label = metadata.getColumnLabel(col);
            switch (label) {
                case "id":
                    entity.setId(resultSet.getLong(col));
                    break;
                case "type":
                    entity.setType(resultSet.getString(col));
                    break;
                case "destination":
                    entity.setDestination(resultSet.getString(col));
                    break;
                case "description":
                    entity.setDescription(resultSet.getString(col));
                    break;
                case "status":
                    entity.setStatus(resultSet.getString(col));
                    break;
                case "level":
                    entity.setLevel(resultSet.getInt(col));
                    break;
                case "state":
                    entity.setState(resultSet.getString(col));
                    break;
                case "team_id":
                    entity.setTeamId(resultSet.getLong(col));
                    break;
                case "team_name":
                    entity.setTeamName(resultSet.getString(col));
                    break;
            }

        }
        return entity;
    }
}

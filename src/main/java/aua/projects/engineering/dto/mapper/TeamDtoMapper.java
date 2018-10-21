package aua.projects.engineering.dto.mapper;

import aua.projects.engineering.dto.TeamDto;
import aua.projects.engineering.dto.UserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class TeamDtoMapper implements RowMapper<TeamDto> {
    @Override
    public TeamDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        TeamDto entity = new TeamDto();
        ResultSetMetaData metadata = resultSet.getMetaData();
        int colCount = metadata.getColumnCount();
        for (int col = 1; col <= colCount; ++col) {
            String label = metadata.getColumnLabel(col);
            switch (label) {
                case "id":
                    entity.setId(resultSet.getLong(col));
                    break;
                case "name":
                    entity.setName(resultSet.getString(col));
                    break;
                case "speciality":
                    entity.setSpeciality(resultSet.getString(col));
                    break;
                case "capacity":
                    entity.setCapacity(resultSet.getLong(col));
                    break;
                case "leader_id":
                    entity.setLeaderId(resultSet.getLong(col));
                    break;
                case "team_status":
                    entity.setStatus(resultSet.getString(col));
                    break;
                case "user_name":
                    entity.setLeaderName(resultSet.getString(col));
                    break;
            }

        }
        return entity;
    }
}


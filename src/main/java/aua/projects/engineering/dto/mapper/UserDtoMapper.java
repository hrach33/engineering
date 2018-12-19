package aua.projects.engineering.dto.mapper;

import aua.projects.engineering.dto.UserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class UserDtoMapper implements RowMapper<UserDto> {
    @Override
    public UserDto mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        UserDto entity = new UserDto();
        ResultSetMetaData metadata = resultSet.getMetaData();
        int colCount = metadata.getColumnCount();
        for (int col = 1; col <= colCount; ++col) {
            String label = metadata.getColumnLabel(col);
            switch (label) {
                case "id":
                    entity.setId(resultSet.getLong(col));
                    break;
                case "user_name":
                    entity.setUserName(resultSet.getString(col));
                    break;
                case "first_name":
                    entity.setFirstName(resultSet.getString(col));
                    break;
                case "last_name":
                    entity.setLastName(resultSet.getString(col));
                    break;
                case "password":
                    entity.setPassword(resultSet.getString(col));
                    break;
                case "speciality":
                    entity.setSpeciality(resultSet.getString(col));
                    break;
                case "birth_date":
                    entity.setBirthDate(resultSet.getString(col));
                    break;
                case "gender":
                    entity.setGender(resultSet.getString(col));
                    break;
                case "status":
                    entity.setStatus(resultSet.getString(col));
                    break;
            }

        }
        return entity;
    }
}
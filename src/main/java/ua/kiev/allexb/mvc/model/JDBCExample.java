package ua.kiev.allexb.mvc.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JDBCExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCExample.class);

    @Autowired
    DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        LOGGER.info("JDBCExample postConstruct is called. datasource = " + dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //JDBC TEMPLATE INSERT EXAMPLE
    public boolean insertLog(DBLog log) {
        LOGGER.info("JDBCExample: log(final String log) is called");
        final String INSERT_SQL = "INSERT INTO LOG (LOGSTRING) VALUES (?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);
                preparedStatement.setString(1, log.getLogString());
                return preparedStatement;
            }
        });
        return true;
    }

    //JDBC TEMPLATE SELECT EXAMPLE
    public List<DBLog> queryAllLogs() {
        LOGGER.info("JDBCExample: queryAllLogs() is called");
        final String QUERY_SQL = "SELECT * FROM LOG ORDER BY IDLOG";
        List<DBLog> dbLogList = this.jdbcTemplate.query(QUERY_SQL, new RowMapper<DBLog>() {
            public DBLog mapRow(ResultSet resulSet, int rowNum) throws SQLException {
                LOGGER.info("Getting log: "+ rowNum + " content: " + resulSet.getString("LOGSTRING"));
                DBLog dbLog = new DBLog();
                dbLog.setIdLog(resulSet.getInt("IDLOG"));
                dbLog.setLogString(resulSet.getString("LOGSTRING"));
                return dbLog;
            }
        });
        return dbLogList;
    }

    public List<User> queryAllUsers() {
        LOGGER.info("JDBCExample: queryAllUsers is called");
        final String QUERY_SQL = "SELECT * FROM USER ORDER BY IDUSER";
        List<User> userList = this.jdbcTemplate.query(QUERY_SQL, new RowMapper<User>() {
            public User mapRow(ResultSet resulSet, int rowNum) throws SQLException {
                User user = new User();
                user.setIdUser(resulSet.getInt("IDUSER"));
                user.setUsername(resulSet.getString("USERNAME"));
                user.setPassword(resulSet.getString("PASSWORD"));
                user.setEnabled(resulSet.getBoolean("ENABLED"));
                return user;
            }
        });
        return userList;
    }

    //JDBC TEMPLATE DELETE EXAMPLE
    public boolean deleteUSER(int iduser) {
        LOGGER.info("JDBCExample: deleteUSER called");
        final String DELETE_SQL = "DELETE FROM USER WHERE IDUSER LIKE ?";
        int result = jdbcTemplate.update(DELETE_SQL,new Object[]{iduser});
        LOGGER.info("r" + result);
        if (result > 0) {
            LOGGER.info("User is deleted: " + iduser);
            return true;
        } else {
            return false;
        }
    }

    //JDBC TEMPLATE UPDATE EXAMPLE
    public boolean updateUserEnable(User u, boolean enable)  {
        LOGGER.info("JDBCExample: updateUserEnable called");
        final String UPDATE_SQL = "UPDATE USER SET ENABLED = ? WHERE USERNAME = ?";
        int result = jdbcTemplate.update(UPDATE_SQL,new Object[]{enable, u.getUsername()});
        if (result > 0) {
            LOGGER.info("User is updated: " + u.getUsername());
            return true;
        } else {
            return false;
        }
    }

}

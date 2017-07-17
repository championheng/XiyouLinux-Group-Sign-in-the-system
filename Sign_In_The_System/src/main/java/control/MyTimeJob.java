package control;

import model.DBUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hg_yi on 17-7-15.
 */
public class MyTimeJob implements Job {
    public void execute(JobExecutionContext argv) throws JobExecutionException {
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE student_message SET status = 'false'");
            statement.execute();

            statement = connection.prepareStatement("TRUNCATE student_SignIN");
            statement.execute();

            ResultSet resultSet = null;

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
import model.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

/**
 * Created by hg_yi on 17-7-15.
 */
public class DataBase {
    public static void main(String[] args) {
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT studentId FROM student_message WHERE studentId = '04151040'");
            ResultSet resultSet = statement.executeQuery();

            out.println(resultSet.getString(3));

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

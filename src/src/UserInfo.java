package src;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by svenskjefel on 2017-02-03.
 */
public class UserInfo {
    public final String user_name;

    public UserInfo(ResultSet rs) throws SQLException {
        this.user_name = rs.getString("user_name");
    }
}

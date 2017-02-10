package src;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by svenskjefel on 2017-02-03.
 */
public class ScheduleInfo {
    public final String theater_name;

    public ScheduleInfo(ResultSet rs) throws SQLException{
        this.theater_name = rs.getString("theater_name");
    }
}

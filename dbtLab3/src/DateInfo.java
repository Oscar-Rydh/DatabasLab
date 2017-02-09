package src;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by svenskjefel on 2017-02-03.
 */
public class DateInfo {
    public final String date;

    public DateInfo(ResultSet rs) throws SQLException {
        this.date = rs.getString("date");
    }
}

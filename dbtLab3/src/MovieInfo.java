package src;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by svenskjefel on 2017-02-03.
 */
public class MovieInfo {
    public final String move_name;
    public final String date;

    public MovieInfo(ResultSet rs) throws SQLException {
        this.move_name = rs.getString("movie_name");
        this.date = rs.getString("date");
    }

}

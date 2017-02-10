package src;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by svenskjefel on 2017-02-03.
 */
public class IndividualMovieInfo {
    public final String move_name;

    public IndividualMovieInfo(ResultSet rs) throws SQLException {
        this.move_name = rs.getString("movie_name");
    }

}

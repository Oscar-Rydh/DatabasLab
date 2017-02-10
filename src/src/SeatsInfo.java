package src;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by svenskjefel on 2017-02-03.
 */
public class SeatsInfo {
    public final int nbr_of_seats;

    public SeatsInfo(ResultSet rs) throws SQLException {
        this.nbr_of_seats = rs.getInt("nbr_of_seats");
    }
}

package src;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by svenskjefel on 2017-02-03.
 */
public class ReservationCount {
    public final int tot_reservations;

    public ReservationCount(ResultSet rs) throws SQLException{
        this.tot_reservations = rs.getInt("count()");
    }
}

package src;

import java.sql.*;
import java.util.*;

/**
 * Database is a class that specifies the interface to the movie
 * database. Uses JDBC.
 */
public class Database {

    /**
     * The database connection.
     */
    private Connection conn;

    /**
     * Create the database interface object. Connection to the
     * database is performed later.
     */
    public Database() {
        conn = null;
    }

    /**
     * Open a connection to the database, using the specified user
     * name and password.
     */
    public boolean openConnection(String filename) {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Close the connection to the database.
     */
    public void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if the connection to the database has been established
     * 
     * @return true if the connection has been established
     */
    public boolean isConnected() {
        return conn != null;
    }

    /* --- insert own code here --- */

    /**
     * Checks users database if the requested login exists
     * @param user_name
     * @return A user_name
     */
    public List<UserInfo> getUserForLogin(String user_name) {
        List<UserInfo> found = new LinkedList<>();
        try {
            String sql = "SELECT user_name \n" +
                    "FROM users \n" +
                    "WHERE user_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user_name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                found.add(new UserInfo(rs));
            }
            return found;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    //Returns All information for all movies (NOT USED)
    public List<MovieInfo> getAllMovieInfo() {
        List<MovieInfo> found = new LinkedList<>();
        try {
            String sql = "SELECT * \n" +
                    "FROM movie_performances";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                found.add(new MovieInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    //Returns all the available movie performances
    public List<IndividualMovieInfo> getIndividualMovieInfo() {
        List<IndividualMovieInfo> found = new LinkedList<>();
        try {
            String sql = "SELECT DISTINCT movie_name \n" +
                    "FROM movie_performances";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                found.add(new IndividualMovieInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }
    
    //Returns a movie based on the search query.
    public List<MovieInfo> searchForMovie(String movie_name){
        List<MovieInfo> found = new LinkedList<>();
        try {
            String sql = "SELECT movie_name \n" +
                    "FROM movie_performances \n"+
                    "WHERE movie_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie_name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                found.add(new MovieInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    //Returns all the dates for a specified movie
    public List<DateInfo> getMovieDates(String movie_name){
        List<DateInfo> found = new LinkedList<>();
        try {
            String sql = "SELECT date \n" +
                    "FROM movie_performances \n"+
                    "WHERE movie_name = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie_name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                found.add(new DateInfo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }

    
    //Returns the theater for a movie performance
    public ScheduleInfo getScheduleInfo(String movie_name, String date){
    	ScheduleInfo info = null;
        try {
            String sql = "SELECT theater_name \n" +
                    "FROM showsin \n"+
                    "WHERE movie_name = ? \n" +
                    "AND date = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, movie_name);
            ps.setString(2, date);
            ResultSet rs = ps.executeQuery();	
            info = new ScheduleInfo(rs);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    public int getFreeSeatsInfo(String theater, String movie_name, String date){
        int tot = 0;
        try {
            String sql = "SELECT nbr_of_seats \n" +
                    "FROM theaters \n"+
                    "WHERE theater_name = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, theater);
            ResultSet rsTotSeats = ps.executeQuery();
            SeatsInfo seatsInfo = new SeatsInfo(rsTotSeats);
            sql = "SELECT count() \n" + 
            		"FROM reservations \n" +
            		"Where movie_name = ? AND date = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, movie_name);
            ps.setString(2, date);
            ResultSet rsTotReserv = ps.executeQuery();
            ReservationCount reservationCount = new ReservationCount(rsTotReserv);
            tot = seatsInfo.nbr_of_seats - reservationCount.tot_reservations; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tot;
    }
    
    //Makes a reservation if seats are a available
    public void makeReservation(String user_name, String theater, String movie_name, String date){
    	int currentAvailableSeats = getFreeSeatsInfo(theater, movie_name, date);
    	if (currentAvailableSeats > 0){
    		try {
    			String sql = "INSERT INTO reservations(user_name, movie_name, date) \n" +
    					"VALUES(?,?,?)";
    			PreparedStatement ps = conn.prepareStatement(sql);
    			ps.setString(1, user_name);
    			ps.setString(2, movie_name);
    			ps.setString(3, date);
    			ps.executeUpdate();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }

}

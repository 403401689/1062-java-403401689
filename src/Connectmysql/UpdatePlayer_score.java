package Connectmysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


public class UpdatePlayer_score {

	public static void main(int frequency) {
		String Splayer_id = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			// handle the error
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/player_list?"
					+ "user=root&password=conansmart&serverTimezone=UTC&useSSL=false");

			Statement stmt = conn.createStatement();
			
			//取的現在時間
			SimpleDateFormat nowdate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String sdate = nowdate.format(new java.util.Date());
			System.out.println(sdate);
			
			String sql = "select * from player order by player_id desc limit 1;";
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				 Splayer_id = result.getString(1);
			}
			int Iplayer_id = Integer.valueOf(Splayer_id);

							
			//跟新score
			PreparedStatement sql_score = conn.prepareStatement("UPDATE player SET score = ? WHERE player_id = ?;");
			sql_score.setInt(2, Iplayer_id);
			sql_score.setInt(1, frequency);
			sql_score.executeUpdate();
					
			//跟新endtime
			PreparedStatement sql_endtime = conn.prepareStatement("UPDATE end_time SET end_time = ? WHERE player_id = ?;");
			sql_endtime.setInt(2, Iplayer_id);
			sql_endtime.setString(1, sdate);
			sql_endtime.executeUpdate();
			
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}
	

}

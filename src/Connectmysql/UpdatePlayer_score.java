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
		String player_id = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			// handle the error
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/player_list?"
					+ "user=root&password=0000&serverTimezone=UTC&useSSL=false");

			Statement stmt = conn.createStatement();
			
			//活得當前player_id
			String sql = "select * from player order by player_id desc limit 1;";
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				 player_id = result.getString(1);
			}
			int intValue = Integer.valueOf(player_id);

							
			//跟新資料庫的score
			PreparedStatement sql_starttime = conn.prepareStatement("UPDATE player SET score = ? WHERE player_id = ?;");
			sql_starttime.setInt(2, intValue);
			sql_starttime.setInt(1, frequency);
			sql_starttime.executeUpdate();
			
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}
	

}

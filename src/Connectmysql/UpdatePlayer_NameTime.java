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


public class UpdatePlayer_NameTime {

	public static void main(String playername) {
		
		String Splayer_id = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			// handle the error
		}

		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/g14?"
					+ "user=root&password=0000&serverTimezone=UTC&useSSL=false");

			Statement stmt = conn.createStatement();
					
			//取的現在時間
			SimpleDateFormat nowdate = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String sdate = nowdate.format(new java.util.Date());
			System.out.println(sdate);
			
			//新增Player_name,Start_time到資料庫中
			PreparedStatement sql_starttime = conn.prepareStatement("INSERT INTO player SET player_name=?, start_time = ? ;");
			sql_starttime.setString(1, playername);
			sql_starttime.setString(2, sdate);
			sql_starttime.executeUpdate();
			
			String sql = "select * from player order by player_id desc limit 1;";
			ResultSet result = stmt.executeQuery(sql);
			while (result.next()) {
				 Splayer_id = result.getString(1);
			}
			int Iplayer_id = Integer.valueOf(Splayer_id);
			
			//新增End_time到資料庫中
			PreparedStatement sql_endid = conn.prepareStatement("INSERT INTO end_time SET player_id = ?;");
			sql_endid.setInt(1,Iplayer_id );
			sql_endid.executeUpdate();
			
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}
	

}

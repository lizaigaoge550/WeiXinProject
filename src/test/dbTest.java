package test;

import java.sql.*;

import domain.JobHunting;

public class dbTest {
	private static String url = "jdbc:mysql://localhost:3306/myDB";
	
	public static Connection getConnection(String url)
	{
		try 
		{
			Connection conn = DriverManager.getConnection(url, "root", "wsnw2000");
			return conn;
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("can not link database");
			e.printStackTrace();
			System.exit(-100);
		}
		return null;	
	}
	
	 public static void queryDataBase(Connection conn)
	 {
		try 
		{
			String sql = "select * from job_hunting";
			PreparedStatement psta = conn.prepareStatement(sql);
			ResultSet rs = psta.executeQuery();
			while(rs.next())
			{
				System.out.println("hh");
			}
		} 
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	
	
	 public static void addDataBase(JobHunting record, Connection conn) throws SQLException
	 {
		 long curTime = System.currentTimeMillis();
		 Timestamp startTime = new Timestamp(curTime);
		 int top = record.getTop();
		
		String sql = "insert into job_hunting(wx_id, title, category, phone, text, image, top, type, start_time,"
				+ "view_number, comments) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement psta = conn.prepareStatement(sql);
		psta.setString(1, record.getUserId());
		psta.setInt(3, record.getCategory());
		psta.setString(4, record.getPhone());
		psta.setString(5, record.getText());
		psta.setInt(7, record.getTop());
		psta.setInt(8, record.getType());
		psta.setTimestamp(9, record.getCreateTime());
		psta.setInt(10, record.getViewNumber());
		psta.setString(11, record.getComments().toString());
		psta.execute(sql);
		
	 }
	 
	public static void main(String[] args)
	{
		
			
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("successful load driver");
		
		Connection conn = getConnection(url);
		//query
		queryDataBase(conn);
//		Timestamp t = new Timestamp(System.currentTimeMillis());
//		System.out.println(t);
//		long curTime = System.currentTimeMillis();
//		long newTime = curTime + 24*3600*1000;
//		Timestamp newT = new Timestamp(newTime);
//		System.out.println(newT);
	}
}

package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.jdbc.TxQueryRunner;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();
	public boolean insert(String openid, String userid)
	{
		String sql = "insert into user(userid, openid) values(?,?)";
		Object[] params = {userid, openid};
		try {
			qr.update(sql, params);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}

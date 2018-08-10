package service;

import dao.UserDao;

public class UserService {
	UserDao userDao = new UserDao();
	public boolean InsertUserTable(String openid, String userid)
	{
		return userDao.insert(openid, userid);
	}
}

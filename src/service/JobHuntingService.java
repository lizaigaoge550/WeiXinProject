package service;

import dao.JobHuntingDao;
import domain.JobHunting;
import domain.PageBean;

public class JobHuntingService 
{

	JobHuntingDao jobHuntingDao = new JobHuntingDao();
	
	public boolean AddRecord(JobHunting record)
	{
		return jobHuntingDao.add(record);
	}	
	
	public JobHunting UpdateRecord(int id)
	{
		return jobHuntingDao.update(id);
	}
	
	public boolean UpdateRecord(int id, String wx_id, int top, int type, int grade)
	{
		return jobHuntingDao.update(id, wx_id, top, type, grade);
	}
	
	public PageBean<JobHunting> GetPublishMessages(int pc, int pr, int type)
	{
		return jobHuntingDao.list(pc, pr, type);
	}
	
	public PageBean<JobHunting> GetHumanCheckInfo(int pc, int pr)
	{
		return jobHuntingDao.list(pc, pr);
	}
	
	
	public PageBean<JobHunting> GetPublishMessages(int pc, int pr, String wx_id)
	{
		return jobHuntingDao.list(pc, pr, wx_id);
	}
	
	public boolean QueryUser(String wx_id)
	{
		return jobHuntingDao.query(wx_id);
	}
	
	public JobHunting CheckDetailsInfo(int id)
	{
		return jobHuntingDao.check_details_info(id);
	}
	
	public JobHunting QueryRecord(int id)
	{
		return jobHuntingDao.query(id);
	}
	
	public boolean DeleteMessage(int id)
	{
		return jobHuntingDao.delete(id);
	}

	public boolean QueryAdam(String username, String passwd)
	{
		return jobHuntingDao.query(username, passwd);
	}
	
	public boolean InsertToHumanCheckTable(int id)
	{
		return jobHuntingDao.insert_to_human_check_table(id);
	}
	
	public boolean ModifyTelephone(String wx_id, String telephone)
	{
		return jobHuntingDao.modify_telephone_number(wx_id, telephone);
	}
	
	public boolean NotPass(int id)
	{
		return jobHuntingDao.not_pass(id);
	}

	public boolean Pass(int id)
	{
		return jobHuntingDao.pass(id);
	}
}


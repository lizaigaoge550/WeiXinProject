package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;

import java.util.ArrayList;
import java.util.List;
import cn.itcast.jdbc.TxQueryRunner;
import domain.JobHunting;
import domain.PageBean;
import utils.Helper;
import utils.*;
public class JobHuntingDao {
	private QueryRunner qr = new TxQueryRunner();
	
	private String addSql = "insert into job_hunting(wxId, title, category, phone, text, miniText, image, top"
			+ ", type, createTime, viewNumber, comments, grade, state, name, gender) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private String addSql_1 = "insert into job_hunting_human(wxId, title, category, phone, text, miniText, image, top"
			+ ", type, createTime, viewNumber, comments, grade, state, name, gender) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private String countSql = "select count(*) from job_hunting";
	
	private JiebaSegmenter segmenter = new JiebaSegmenter();
	
	private boolean IsValid(String text)

	{
		List<SegToken> segtokens = segmenter.process(text,SegMode.SEARCH);
		List<String> tokens = new ArrayList<String>();
		for(SegToken segtoken:segtokens)
		{
			tokens.add(segtoken.word);
		}
		//加载关键词库
		List<String> dictionary = Helper.LoadingDictionary();
		for(String token : tokens)
		{
			if(dictionary.contains(token))
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean delete(int id)
	{
		String sql = "delete from job_hunting where id=?";
		Object[] params = {id};
		try {
			qr.update(sql, params);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean add(JobHunting record)
	{
		String text = record.getText();
		String miniText = text;
		if(text.contains("。"))
		{
			miniText = text.split("。")[0];
		}
		else if(text.contains("."))
		{
			miniText = text.split(".")[0];
		}
		else if(text.length() > 20)
		{
			miniText = text.substring(0, 20);
		}
			
		record.setMiniText(miniText);
		 	
		try {
			
			Number number = (Number)qr.query(countSql, new ScalarHandler<>());
			record.setId(number.intValue()+1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if (IsValid(text))
		{
			record.setState(1);
		}
		else
		{
			record.setState(0);
				
		}
		try {
			Object[] params = {record.getWxId(), record.getTitle(), record.getCategory(),
					record.getPhone(), text, miniText, record.getImage(), record.getTop(),record.getType(),
					record.getCreateTime(), record.getViewNumber(), record.getComments(),record.getGrade(),record.getState(),
					record.getName(), record.getGender() };	
			qr.update(addSql, params);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	//某用户查看全文的时候的更新
	public JobHunting update(int id)
	{
		try 
		{
            String sql = "select * from job_hunting where id=?";
            Object[] params_1 = {id};
            List<JobHunting> records =  qr.query(sql , new BeanListHandler<>(JobHunting.class),params_1);
            assert records.size() == 1 : "update record != 1";
            JobHunting record = records.get(0);
            int grade = record.getGrade();
            int top = record.getTop();
            grade = Helper.UpdateGrade(top, grade);
            //找到该用户发布的所有记录
            String wx_id = record.getWxId();
            sql = "update job_hunting set grade=? where wxId=?";
            Object[] params_2 = {grade, wx_id};
            qr.update(sql, params_2);
            return record;
           
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
	}
	
	//管理员修改某记录的时候的更新
	public boolean update(int id, String wx_id, int top, int type, int grade)
	{
		try {
			String sql = "update job_hunting set top = ?, type = ?, grade = ? where id=? and wxId=?";
			Object[] params_1 = {top, type, grade, id, wx_id};
			qr.update(sql , params_1);
			sql = "update job_hunting set grade=? where wxId=?";
			Object[] params_2 = {grade, wx_id};
			qr.update(sql, params_2);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public PageBean<JobHunting> list(int pc, int pr) //pc是当前的页数，pr是每页的记录数
	{
		try
		{
			PageBean<JobHunting> pb = new PageBean();
			pb.setPc(pc);
			pb.setPr(pr);
		
			Number number = (Number) qr.query(countSql, new ScalarHandler<>());
			int tr = number.intValue();
			pb.setTr(tr);
			String sql = "select * from job_hunting where grade > 0 and state = 1 order by top desc,createTime asc limit ?,?";
			Object[] params = {(pc-1)*pr, pr};
			List<JobHunting> beanList = qr.query(sql, new BeanListHandler<>(JobHunting.class), params);
			pb.setBeanList(beanList);
			return pb;
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	//列出指定wx_id的发布记录
	public PageBean<JobHunting> list(int pc, int pr, String wx_id) //pc是当前的页数，pr是每页的记录数
	{
		try
		{
			PageBean<JobHunting> pb = new PageBean();
			pb.setPc(pc);
			pb.setPr(pr);
		
			String sql = "select count(*) from job_hunting where wxId = ?";
			Object[] params_1 = {wx_id};
			Number number = (Number) qr.query(sql, new ScalarHandler<>(), params_1);
			int tr = number.intValue();
			pb.setTr(tr);
			sql = "select * from job_hunting where wxId=? order by createTime limit ?,?";
			Object[] params_2 = {wx_id, (pc-1)*pr, pr};
			List<JobHunting> beanList = qr.query(sql, new BeanListHandler<>(JobHunting.class), params_2);
			pb.setBeanList(beanList);
			return pb;
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	//查询指定wx_id的发布记录(管理员)
	public boolean query(String wx_id)
	{
		String sql = "select * from job_hunting where wxId=?";
		Object[] params = {wx_id};
		try {
			List<JobHunting> beanList = qr.query(sql, new BeanListHandler<>(JobHunting.class), params);
			if (beanList.size() == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	//用于管理员登录时候的查询
	public boolean query(String usrname, String passwd)
	{
		String sql = "select * from administrator where username=? and password=?";
		Object[] params = {usrname, passwd};
		try {
			List<JobHunting> beanList = qr.query(sql, new BeanListHandler<>(JobHunting.class), params);
			if (beanList.size() == 0)
			{
				return false;
			}
			else
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//查看某条record：用于管理员编辑record的时候，根据id返回该record
	public JobHunting query(int id)
	{
		String sql = "select * from job_hunting where id=?";
		Object[] params = {id};
		try {
			List<JobHunting> beanList = qr.query(sql, new BeanListHandler<>(JobHunting.class), params);
			assert beanList.size() == 1;
			return beanList.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//查询某条记录的详细信息(管理员这时不用对grade进行更新)
	public JobHunting check_details_info(int id)
	{
		String sql = "select * from job_hunting where id =?";
		Object[] params = {id};
		try {
			List<JobHunting> beanList = qr.query(sql, new BeanListHandler<>(JobHunting.class), params);
			assert beanList.size() == 1;
			return beanList.get(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean insert_to_human_check_table(int id)
	{
		String sql = "select * from job_hunting where id =?";
		Object[] params = {id};
		try {
			List<JobHunting> beanList = qr.query(sql, new BeanListHandler<>(JobHunting.class), params);
			assert beanList.size() == 1;
			JobHunting record = beanList.get(0);
			Object[] params_1 = {record.getWxId(), record.getTitle(), record.getCategory(),
					record.getPhone(), record.getText(), record.getMiniText(), record.getImage(), record.getTop(),record.getType(),
					record.getCreateTime(), record.getViewNumber(), record.getComments(),record.getGrade(),record.getState()
					,record.getName(), record.getGender()};	
			qr.update(addSql_1, params_1);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean modify_telephone_number(String wx_id, String telephone)
	{
		String sql = "update job_hunting set telephone=? where wxId=?";
		Object[] params = {wx_id, telephone};
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

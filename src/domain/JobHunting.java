package domain;

import java.sql.Timestamp;
import java.util.List;

import org.json.JSONArray;

//wx_id, title, category, phone, text, image, top, type, start_time, end_time, view_number, comments
public class JobHunting {
	private int id;
	private String wxId;
	private String name;
	private int gender;
	private String title;
	private int category;
	private String phone;
	private String text;
	private String image;
	private int top; //置顶
	private int type; //全职 or 兼职
	private Timestamp createTime ;
	private int viewNumber;
	private String comments;
	private int grade;
	private String miniText;
	private int state;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setGender(int gender)
	{
		this.gender = gender;
	}
	public int getGender()
	{
		return gender;
	}
	public int getState()
	{
		return state;
	}
	public void setState(int state)
	{
		this.state = state;
	}
	public String getWxId()
	{
		return wxId;
	}
	public void setWxId(String wxId)
	{
		this.wxId = wxId;
	}
	public int getGrade()
	{
		return grade;
	}
	public void setGrade(int grade)
	{
		this.grade = grade;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public int getCategory()
	{
		return category;
	}
	public void setCategory(int category)
	{
		this.category = category;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getText()
	{
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	public String getImage()
	{
		return image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}
	public int getTop()
	{
		return top;
	}
	public void setTop(int top)
	{
		this.top = top;
	}
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}
	public Timestamp getCreateTime()
	{
		return createTime;
	}
	public void setCreateTime(Timestamp t)
	{
		this.createTime = t;
	}
	public int getViewNumber()
	{
		return viewNumber;
	}
	
	public void setViewNumber(int view_number)
	{
		this.viewNumber = view_number;
	}
	public String getComments()
	{
		return comments;
	}
	public void setComments(String comments)
	{
		this.comments = comments;
	}
	public String getMiniText()
	{
		return miniText;
	}
	public void setMiniText(String mini_text)
	{
		this.miniText = mini_text;
	}


}	

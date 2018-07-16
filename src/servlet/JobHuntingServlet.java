package servlet;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import servlet.BaseServlet;
import domain.JobHunting;
import domain.PageBean;
import service.JobHuntingService;
import utils.Constant;

public class JobHuntingServlet extends BaseServlet 
{
	private JobHuntingService jobHuntingService = new JobHuntingService();
	
	//添加发布记录
	public void AddPublishMessage(HttpServletRequest request, HttpServletResponse response)
	{
		JobHunting record = new JobHunting();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		try
		{
			List<FileItem> items = sfu.parseRequest(request);
			System.out.println(items.size());
			for (int i = 0; i < items.size(); i++) 
			{
				FileItem item = items.get(i);
				if(!item.isFormField())
				{
					//String path = Paths.get(request.getContextPath(),"imageSave").toString();
					String path = Constant.imageSavePath;
					System.out.println("path : " + path);
					//获得文件名
					String fileName = UUID.randomUUID().toString() + ".jpg";
					System.out.println("fileName : " + fileName);
					File file = new File(path+"\\"+fileName);
					if(!file.exists())
					{
						item.write(file);
						//设置保存路径
						record.setImage(fileName);
					}
				}
				else
				{
					String name = item.getFieldName();
					System.out.println("name : " + name);
					String value = item.getString();
					if (name.equals("wx_id"))
					{
						System.out.println("name : " + name);
						record.setWxId(value);
					}
					else if(name.equals("title"))
					{
						record.setTitle(value);
					}
					else if(name.equals("category"))
					{
						record.setCategory(Integer.parseInt(value));
					}
					else if(name.equals("phone"))
					{
						record.setPhone(value);
					}
					else if(name.equals("description"))
					{
						record.setText(value);
					}
					else if(name.equals("type"))
					{
						record.setType(Integer.parseInt(value));
					}
					else if(name.equals("top"))
					{
						record.setType(Integer.parseInt(value));
					}
					else if(name.equals("name"))
					{
						record.setName(value);
					}
					else if(name.equals("gender"))
					{
						record.setGender(Integer.parseInt(value));
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//获取当前时间
		record.setCreateTime(new Timestamp(System.currentTimeMillis()));
		//设置积分
		record.setGrade(Constant.initGrade);
		//设置访问量
		record.setViewNumber(Constant.initViewNumber);
		//设置评论
		record.setComments(Constant.initComments);
		if( jobHuntingService.AddRecord(record))
		{
			System.out.println("add successful");
			request.setAttribute("state",1);
		}
		else
		{
			request.setAttribute("state", 0);
		}
	}
	
	//当有人查看某条记录的详细信息的时候，去更新信息。
	public void UpdatePublishMessage(HttpServletRequest request, HttpServletResponse response)
	{
		int id = Integer.parseInt(request.getParameter("id"));
		JobHunting record = jobHuntingService.UpdateRecord(id);
		request.setAttribute("record", record);
		request.setAttribute("state", 1);
	}

	//按照一定的排序方式列出发布记录
	public void PublishMessagesList(HttpServletRequest request, HttpServletResponse response)
	{
		int pc = getPc(request);
		int pr = 2;
		PageBean<JobHunting> pb = jobHuntingService.GetPublishMessages(pc, pr);
		pb.setUrl(getUrl(request));
		request.setAttribute("pb", pb);
		request.setAttribute("state", 1);
	}
	
	//管理员, 用户删除信息
	public void DeleteMessage(HttpServletRequest request, HttpServletResponse response)
	{
		int id = Integer.parseInt(request.getParameter("id"));
		if(jobHuntingService.DeleteMessage(id))
		{
			request.setAttribute("state", 1);
		}
		else
		{
			request.setAttribute("state", 0);
		}
		
	}
	
	private int getPc(HttpServletRequest request)
	{
		String value = request.getParameter("pc");
		if(value == null || value.trim().isEmpty())
		{
			return 1;
		}
		return Integer.parseInt(value);
	}

	private String getUrl(HttpServletRequest request)
	{
		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String queryString = request.getQueryString();
		
		if(queryString.contains("&pc="))
		{
			int index =queryString.lastIndexOf("&pc=");
			queryString = queryString.substring(0, index);
		}
		return contextPath + servletPath + "?" + queryString;
	}

	//用户登录
	public void UserLogin(HttpServletRequest request, HttpServletResponse response)
	{
		String wx_id = request.getParameter("wx_id");
		System.out.println("wx_id " + wx_id);
		boolean isExist = jobHuntingService.QueryUser(wx_id);
		System.out.println("Exist........ " + isExist);
		HttpSession session = request.getSession();
		if(isExist)
		{
			System.out.println("user exist...");
			session.setAttribute("wx_id", wx_id);
		}
		
	}
	
	public void AdamLogin(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("usrname");
		String passwd = request.getParameter("password");
		boolean isExist = jobHuntingService.QueryAdam(id, passwd);
		System.out.println("Exist........ " + isExist);
		HttpSession session = request.getSession();
		if(isExist)
		{
			System.out.println("user exist...");
			session.setAttribute("id", id);
		}
		
	}
	 
	//用户查看自己发布的信息
	public void ShowUserInfo(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		if(session.getAttribute("wx_id") != null)
		{
			String wx_id = (String) session.getAttribute("wx_id");
			int pc = getPc(request);
			int pr = 10;
			PageBean<JobHunting> pb = jobHuntingService.GetPublishMessages(pc, pr, wx_id);
			pb.setUrl(getUrl(request));
			request.setAttribute("pb", pb);
			request.setAttribute("state", 1);
		}
		else
		{
			System.out.println("session is not exist");
		}
	}

	//管理员权限：根据wx_id 查询记录
	public String QueryBasedOnWxId(HttpServletRequest request, HttpServletResponse response)
	{
		String wx_id = request.getParameter("wx_id");
		int pc = getPc(request);
		int pr = 10;
		PageBean<JobHunting> pb = jobHuntingService.GetPublishMessages(pc, pr, wx_id);
		pb.setUrl(getUrl(request));
		request.setAttribute("pb", pb);
		return "/modify.jsp";
		
	}

	//管理员权限, 修改某一条发布的信息
	public String ModifyMessage(HttpServletRequest request, HttpServletResponse response)
	{
		int id = Integer.parseInt(request.getParameter("id"));
		int top = Integer.parseInt(request.getParameter("top"));
		int type = Integer.parseInt(request.getParameter("type"));
		int grade = Integer.parseInt(request.getParameter("grade"));
		String wx_id = request.getParameter("wx_id");
		if(jobHuntingService.UpdateRecord(id, wx_id, top, type, grade))
		{
			request.setAttribute("msg", "修改成功");
		}
		else
		{
			request.setAttribute("msg", "修改失败");
		}
		return "/msg.jsp";
	}

	//管理员查看某一条发布的详细信息
	public String CheckPublishMessage(HttpServletRequest request, HttpServletResponse response)
	{
		int id = Integer.parseInt(request.getParameter("id"));
		JobHunting record = jobHuntingService.CheckDetailsInfo(id);
		request.setAttribute("record", record);
		return "/show_details.jsp";
	}

	//管理员编辑某一条记录
	public String EditMessage(HttpServletRequest request, HttpServletResponse response)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			JobHunting record = jobHuntingService.QueryRecord(id);
			if(record != null)
			{
				request.setAttribute("record", record);
				return "/edit.jsp";
			}
			else
			{
				request.setAttribute("msg", "edit error");
				return "/msg.jsp";
			}
			
		}

	public void RequestHumanCheck(HttpServletRequest request, HttpServletResponse response)
	{
		int id = Integer.parseInt(request.getParameter("id"));
		if (jobHuntingService.InsertToHumanCheckTable(id))
		{
			request.setAttribute("state", 1);
		}
		else
		{
			request.setAttribute("state", 0);
		}
	}

	public void ModifyTelephoneNumber(HttpServletRequest request, HttpServletResponse response)
	{
		String wx_id = request.getParameter("wx_id");
		String telephone = request.getParameter("telephone");
		if(jobHuntingService.ModifyTelephone(wx_id, telephone))
		{
			request.setAttribute("state", 1);
		}
		else
		{
			request.setAttribute("state", 0);
		}
	}
}

package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JobHuntingServlet extends BaseServlet 
{
	private JobHuntingService jobHuntingService = new JobHuntingService();
	
	//添加发布记录带图片的
	public void AddPublishMessagePic(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		JobHunting record = new JobHunting();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		HttpSession sess = request.getSession();
		String wx_id = sess.getAttribute("wx_id").toString();
		record.setWxId(wx_id);
		try
		{
			List<FileItem> items = sfu.parseRequest(request);
			System.out.println(items.size());
			for (int i = 0; i < items.size(); i++) 
			{
				FileItem item = items.get(i);
				if(item.isFormField())
				{
					String name = item.getFieldName();
					System.out.println("name : " + name);
					String value = item.getString();
					if(name.equals("title"))
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
						record.setTop(Integer.parseInt(value));
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
				else
				{
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
		Map<String, Object> resJson = new HashMap();
		if( jobHuntingService.AddRecord(record))
		{
			System.out.println("add successful");
			resJson.put("state", 1);
		}
		else
		{
			resJson.put("state",0);
		}
		response.getWriter().write(JSON.toJSONString(resJson));
	}
	
	//添加发布记录不带图片
	public void AddPublishMessage(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		JobHunting record = new JobHunting();
		HttpSession sess = request.getSession();
		String wx_id = sess.getAttribute("wx_id").toString();
		record.setWxId(wx_id);
		record.setTitle(request.getParameter("title"));
		record.setCategory(Integer.parseInt(request.getParameter("category")));
		record.setPhone(request.getParameter("phone"));
		record.setText(request.getParameter("text"));
		record.setType(Integer.parseInt(request.getParameter("type")));
		record.setTop(Integer.parseInt(request.getParameter("top")));
		record.setName(request.getParameter("name"));
		record.setGender(Integer.parseInt(request.getParameter("gender")));
		//设置图片
		record.setImage("");
		//获取当前时间
		record.setCreateTime(new Timestamp(System.currentTimeMillis()));
		//设置积分
		record.setGrade(Constant.initGrade);
		//设置访问量
		record.setViewNumber(Constant.initViewNumber);
		//设置评论
		record.setComments(Constant.initComments);
		Map<String, Object> resJson = new HashMap();
		if( jobHuntingService.AddRecord(record))
		{
			System.out.println("add successful");
			resJson.put("state", 1);
		}
		else
		{
			resJson.put("state",0);
		}
		response.getWriter().write(JSON.toJSONString(resJson));
	}

	//当有人查看某条记录的详细信息的时候，去更新信息。
	public void UpdatePublishMessage(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		JobHunting record = jobHuntingService.UpdateRecord(id);
		Map<String, Object> resJson = new HashMap();
		if(record != null)
		{
			resJson.put("state",1);
			resJson.put("record",record);
		}
		else
		{
			resJson.put("state",0);
		}
		String jsonString = JSON.toJSONString(resJson);
		response.getWriter().write(jsonString);
	}
	
	//按照一定的排序方式列出发布记录
	public void PublishMessagesList(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int pc = getPc(request);
		int pr = 2;
		PageBean<JobHunting> pb = jobHuntingService.GetPublishMessages(pc, pr);
		pb.setUrl(getUrl(request));
		Map<String, Object> resJson = new HashMap();
		if(pb != null)
		{
			System.out.println("publish successful " + pb.getBeanList().size());
			resJson.put("state",1);
			resJson.put("records",pb);
		}
		else
		{
			resJson.put("state",0);
		
		}
		response.getWriter().write(JSON.toJSONString(resJson));
	}
	
	//管理员, 用户删除信息
	public void DeleteMessage(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		Map<String, Object> jsonString = new HashMap();
		if(jobHuntingService.DeleteMessage(id))
		{
			jsonString.put("state",1);
		}
		else
		{
			jsonString.put("state",0);
		}
		response.getWriter().write(JSON.toJSONString(jsonString));
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
	public void UserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String appid = request.getParameter("appid");
		String secret = request.getParameter("appsecret");
		String code = request.getParameter("code");
        String grant_type = "authorization_code";
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        String params = "appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=" + grant_type;
        String data = GetOpenId(requestUrl , params);
        JSONObject  json = JSONObject.parseObject(data);
        Map<String, Object> jsonString = new HashMap();
        HttpSession sess = request.getSession();
        if(json.containsKey("openid"))
        {
        	//用户的唯一标识（openid）
            String openid =String.valueOf(json.get("openid"));
            //System.out.println(Openid);
            //设置全局id
            sess.setAttribute("wx_id", openid);
            jsonString.put("state", 1);
        }
        else
        {
        	jsonString.put("state", 0);
        }
        response.getWriter().write(JSON.toJSONString(jsonString));
	}
	
	//发送请求
	public String GetOpenId(String url, String param)
	{
		 String result = "";
	        BufferedReader in = null;
	        try {
	            String urlNameString = url + "?" + param;
	            //System.out.println(urlNameString);
	            URL realUrl = new URL(urlNameString);
	            // 打开和URL之间的连接
	            URLConnection connection = realUrl.openConnection();
	            // 设置通用的请求属性
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 建立实际的连接
	            connection.connect();
	            // 获取所有响应头字段
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // 遍历所有的响应头字段
	            // 定义 BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送GET请求出现异常！" + e);
	            e.printStackTrace();
	        }
	        // 使用finally块来关闭输入流
	        finally {
	            try {
	                if (in != null) {
	                    in.close();
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        return result;
	}
	
	public void AdamLogin(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String id = request.getParameter("usrname");
		String passwd = request.getParameter("password");
		Map<String, Object> resJson = new HashMap();
		boolean isExist = jobHuntingService.QueryAdam(id, passwd);
		System.out.println("Exist........ " + isExist);
		HttpSession session = request.getSession();
		if(isExist)
		{
			System.out.println("user exist...");
			resJson.put("state",1);
			session.setAttribute("id", id);
		}
		else
		{
			resJson.put("state", 0);
		}
		response.getWriter().write(JSON.toJSONString(resJson));
	}
	 
	//用户查看自己发布的信息
	public void ShowUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Map<String, Object> jsonString = new HashMap();
		HttpSession session = request.getSession();
		if(session.getAttribute("wx_id") != null)
		{
			
			String wx_id = (String) session.getAttribute("wx_id");
			int pc = getPc(request);
			int pr = 10;
			PageBean<JobHunting> pb = jobHuntingService.GetPublishMessages(pc, pr, wx_id);
			pb.setUrl(getUrl(request));
			//request.setAttribute("pb", pb);
			//request.setAttribute("state", 1);
			if(pb != null)
			{
				jsonString.put("records",pb);
				jsonString.put("state",1);
			}
			else
			{
				jsonString.put("state", 0);
			}
		}
		else
		{
			jsonString.put("state", -1);
		}
		response.getWriter().write(JSON.toJSONString(jsonString));
	}

	//管理员权限：根据wx_id 查询记录
	public void QueryBasedOnWxId(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String wx_id = request.getParameter("wx_id");
		int pc = getPc(request);
		int pr = 10;
		PageBean<JobHunting> pb = jobHuntingService.GetPublishMessages(pc, pr, wx_id);
		pb.setUrl(getUrl(request));
		Map<String, Object> resJson = new HashMap();
		if(pb != null)
		{
			resJson.put("state",1);
			resJson.put("records",pb);
		}
		else
		{
			resJson.put("state",0);
		}
		response.getWriter().write(JSON.toJSONString(resJson));
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
	public void EditMessage(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
			int id = Integer.parseInt(request.getParameter("id"));
			JobHunting record = jobHuntingService.QueryRecord(id);
			Map<String, Object> resJson = new HashMap();
			if(record != null)
			{
				resJson.put("state", 1);
				resJson.put("record",record);
			}
			else
			{
				resJson.put("state",0);
			}
			
			response.getWriter().write(JSON.toJSONString(resJson));
	}

	//管理员查看记录列表,按viewNumber排序
	
	//管理员审查job_hunting_表
	public void HumanCheck(HttpServletRequest request, HttpServletResponse response)
	{
		
	}
	
	public void RequestHumanCheck(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		Map<String, Object> jsonString = new HashMap();
		if (jobHuntingService.InsertToHumanCheckTable(id))
		{
			jsonString.put("state", 1);
		}
		else
		{
			jsonString.put("state", 0);
		}
		response.getWriter().write(JSON.toJSONString(jsonString));
	}

	public void ModifyTelephoneNumber(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession sess = request.getSession();
		String wx_id = (String) sess.getAttribute("wx_id");
		String telephone = request.getParameter("telephone");
		Map<String, Object> jsonString = new HashMap();
		if(jobHuntingService.ModifyTelephone(wx_id, telephone))
		{
			jsonString.put("state", 1);
		}
		else
		{
			jsonString.put("state", 0);
		}
		response.getWriter().write(JSON.toJSONString(jsonString));
	}
}

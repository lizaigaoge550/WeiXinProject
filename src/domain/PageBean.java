package domain;

import java.util.List;

public class PageBean <Object>
{
	private int pc; //current page
	private int tp; //total page
	private int pr; //page records
	private int tr; //total records
	private List<Object> beanList; //
	private String url;
	
	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public int getPc()
	{
		return pc;
	}
	
	public void setPc(int pc)
	{
		this.pc = pc;
	}
	
	public void setTp()
	{
		int tp = tr / pr;
		this.tp = tr % pr == 0 ? tp : tp+1;
	}
	public int getTp()
	{
		return this.tp;
	}
	
	 public void setTp(int tp)
	 {
		 this.tp = tp;
	 }
	
	public int getTr()
	{
		return tr;
	}
	
	public void setTr(int tr)
	{
		this.tr = tr;
	}
	
	public int getPr()
	{
		return pr;
	}
	
	public void setPr(int pr)
	{
		this.pr = pr;
	}
	public List<Object> getBeanList()
	{
		return beanList;
	}
	
	public void setBeanList(List<Object> beanList)
	{
		this.beanList = beanList;
	}
}

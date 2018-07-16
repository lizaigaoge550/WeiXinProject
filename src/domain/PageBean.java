package domain;

import java.util.List;

public class PageBean <Object>
{
	private int pc; //current page
	private int tp; //total page
	private int pr; //page records
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
	
	public int getTp()
	{
		int tr = tp / pr;
		return tp % pr == 0 ? tr : tr+1;
	}

	public int getTr()
	{
		return tp;
	}
	
	public void setTr(int tp)
	{
		this.tp = tp;
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

/**
 * 
 */
package com.eshop.utils;

import java.util.List;

/**
 * yy 2015 2015年11月19日 上午9:29:07
 *
 */
public class Page<T> {
	
	@Override
	public String toString() {
		return "Page [list=" + list + ", totalpage=" + totalpage
				+ ", totalrecord=" + totalrecord + ", pagesize=" + pagesize
				+ ", pagenum=" + pagenum + ", startindex=" + startindex
				+ ", startPage=" + startPage + ", endPage=" + endPage
				+ ", url=" + url + "]";
	}


	private List<T> list;
	private int totalpage;
	private int totalrecord;
	private  int pagesize;
	
	private int pagenum;//用户想看的页数
	private int startindex;//数据取值点
	
	private int startPage;//记住jsp页面显示的起始代码
	
	private int endPage;//记住jsp页面的结束页码
	
	private String url;//记录处理分页的servlet
	
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Page(int totalrecord,int pagesize,int pagenum ) {
		this.totalrecord=totalrecord;
		this.pagesize=pagesize;
		
		if (this.totalrecord%this.pagesize==0) {
			this.totalpage=this.totalrecord/this.pagesize;
			
		}else {
			
			this.totalpage=this.totalrecord/this.pagesize+1;
		}
		this.pagenum=pagenum;//1
		this.startindex=(this.pagenum-1)*this.pagesize;
		
		//根据用户想看的页pagenum,算出jsp页面的起始页面和结束页码
		if (this.totalpage<=10) {
			this.startPage=1;
			this.endPage=this.totalpage;
			
		}else {
			this.startPage=this.pagenum-4;
			this.endPage=this.pagenum+5;
			if (this.startPage<1) {
				this.startPage=1;
				this.endPage=10;
				
			}
			if (this.endPage>this.totalpage) {
				this.endPage=totalpage;
				this.startPage=this.totalpage-9;
				
			}
		}
		
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}


	public int getTotalpage() {
		return totalpage;
	}


	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}


	public int getTotalrecord() {
		return totalrecord;
	}


	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
	}


	public int getPagesize() {
		return pagesize;
	}


	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}


	public int getPagenum() {
		return pagenum;
	}


	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}


	public int getStartindex() {
		return startindex;
	}


	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}


}

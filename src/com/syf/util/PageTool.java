package com.syf.util;

public class PageTool {

	private int pageNo;//当前是第几页
	private int pageCount;//一共有几页
	public static int pageSize=15;//每页有15条数据
	
	//获取当前页
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	//获取下一页
	public int getNextPage(){
		if(pageNo<pageCount){
			return pageNo+1;
		}
		return pageCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	// 页码的大小：表中记录的行数/页码的大小
		// count代表的是表中的记录数
	public void setPageCount(int count) {
		if(count % pageSize==0){
		this.pageCount = count / pageSize;
	}else{
		this.pageCount = count / pageSize + 1;
	}
	}
	
}

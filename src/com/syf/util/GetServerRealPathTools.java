package com.syf.util;

import java.io.File;

public class GetServerRealPathTools {
	/**
	 * ��ȡ��Ŀ�ڷ������е���ʵ·���Ĺ�����
	 * 
	 * ������web��Ŀ�У���ȡ��Ŀʵ��·������ѷ�ʽ����windows��linuxϵͳ�¾����ʹ��
	 * 
	 */
public static String getRootPath() {
	
	String classPath = GetServerRealPathTools.class.getClassLoader().getResource("/").getPath();
	System.out.println("classPath---"+classPath);
	String rootPath = "";
	//windows��
	if("\\".equals(File.separator)){
//		System.out.println("windows");
	rootPath = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
	rootPath = rootPath.replace("/", "\\");
	}
	//linux��
	if("/".equals(File.separator)){
//		System.out.println("linux");
	rootPath = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
	rootPath = rootPath.replace("\\", "/");
	}
	return rootPath;
	}
public static void main(String[] args) {
	
	String url=GetServerRealPathTools.getRootPath();
	System.out.println("url="+url);
	
}
}

package com.syf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Date;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

/***
 * 文件上传例子   resource code encoding is utf-8
 * <br>主要为了android客户端实现功能   代码写的乱   请大家见谅
 * @author spring sky
 * Email:vipa1888@163.com
 * QQ:84095105
 *
 */
public class FileUpload extends ActionSupport {

	//private String savePath="/upload/";
	/**这里的名字和html的名字必须对称*/
	//private File img;
	/**要上传的文件类型*/
	//private String imgContentType;
	/**文件的名称*/
	//private String imgFileName;
	
	//private String orderId;
	/**
	 * 指定的上传类型   zip 和   图片格式的文件
	 */
	private static final String[] types = { "application/x-zip-compressed",
			"ZIP", "image/pjpeg","image/x-png","application/octet-stream" };  //"application/octet-stream; charset=utf-8",

	/***
	 * 判断文件的类型是否为指定的文件类型
	 * @return
	 */
	public boolean filterType(String imgContentType) {
		boolean isFileType = false;
		String fileType = imgContentType;
		System.out.println(fileType);
		for (String type : types) {
			if (type.equals(fileType)) {
				isFileType = true;
				break;
			}
		}
		return isFileType;
	}
 
	/**
	 * 取得文件夹大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public long getFileSize(File f) throws Exception {
		return f.length();
	}

	public String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 上传文件操作
	 * 
	 * @return
	 * @throws Exception
	 */
	private String projectName="Startup_0.1_Alpha";        //  你项目的名称

	//获取当前项目的绝对路径
	  public String getPorjectPath(){
	   String nowpath;             //当前tomcat的bin目录的路径 如 D:\java\software\apache-tomcat-6.0.14\bin
	   String tempdir;
	  // nowpath=System.getProperty("user.dir");
	   // String path =getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	   nowpath=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
	   tempdir=nowpath.replace("/WEB-INF/classes/", "/");  //把bin 文件夹变到 webapps文件里面 
	 //  tempdir+="\\"+projectName;  //拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro 
	   return tempdir;  
	  }
	  
	public String upload(String imgFileName,File img,String imgContentType) throws Exception {
		//以时间+图片名的格式来命名图片名，防止重名 
		Date date = new Date();
		String newName = date.getTime()+imgFileName;
			
		String ct  =  ServletActionContext.getRequest().getHeader("Content-Type");
		String result = "unknow error";
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		if (!filterType(imgContentType)) {
			ServletActionContext.getRequest().setAttribute("typeError",
					"您要上传的文件类型不正确");
			result = "error:" + imgContentType+ " type not upload file type";
		} else {
			System.out.println("当前文件大小为："
					+ FormetFileSize(getFileSize(img)));
			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				// 保存文件那一个路径
			    String classPath = GetServerRealPathTools.class.getClassLoader().getResource("/").getPath();
				String aa=classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
				
				fos = new FileOutputStream(aa+"/upload"+"/"
						+ newName);
				//Windows下+"\\"  
				//Linux下+"/"
				
				fis = new FileInputStream(img);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				result = "upload file success !";
			} catch (Exception e) {
				result = "upload file failed ! ";
				e.printStackTrace();
				return "failed";
			} finally {
				fos.close();
				fis.close();
			}
		}
		//此处返回的是服务器上的图片地址，相对路径
 		String url="http://139.129.25.106/Startup_0.1_Alpha/upload/" 
				+ newName;
 		
    	//String url="http://localhost:8080/Startup_0.1_Alpha/upload/" + newName;
		
		out.print(result);
		return url;
	}
}

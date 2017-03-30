package com.syf.action.web;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syf.model.Project;
import com.syf.model.ProjectType;
import com.syf.service.ProjectService;
import com.syf.service.ProjectTypeService;

public class ProjectTypeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public ProjectTypeServlet() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		if ("toProjectList".equals(op)) {
			toProjectList(request, response);
		}else if("toProInfo".equals(op)){
			toProInfo(request, response);
		}
	}
	private void toProInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		System.out.println("=====进入1号方法");
		int id=Integer.valueOf(idStr);
		System.out.println("ID="+id);
		ProjectService projectService = new ProjectService();
		List<Project> projectInfo = projectService.findAllById(id);
		
		request.setAttribute("projectInfo", projectInfo);
	    request.getRequestDispatcher("project_list.jsp").forward(request, response);
	}
	private void toProjectList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		ProjectTypeService projectTypeService=new ProjectTypeService();
		List<ProjectType> projectList=projectTypeService.findAll();
		request.setAttribute("projectList", projectList);
		request.getRequestDispatcher("project_list.jsp").forward(request, response);
		
	}
	
	
}

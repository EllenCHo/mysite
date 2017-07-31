package com.javaex.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

/**
 * Servlet implementation class GuestBookServlet
 */
@WebServlet("/gb")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		
		if("insert".equals(actionName)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(cal.getTime());
			
			GuestBookVo vo = new GuestBookVo(name, password, content, date);
			GuestBookDao dao = new GuestBookDao();
			
			dao.insert(vo);
			
			response.sendRedirect("/mysite/gb");
			
		} else if("deleteform".equals(actionName)){
			//String no = (String)request.getAttribute("no");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			//request.setAttribute("no", no);
			rd.forward(request, response);
			
		} else if("delete".equals(actionName)) {
			int no = Integer.parseInt((String)request.getParameter("no").trim());		//no 앞뒤로 null값이 있을 수 있음
			String password = request.getParameter("password");
			
			GuestBookDao dao = new GuestBookDao();
			dao.delete(no, password);
			
			response.sendRedirect("/mysite/gb");
			
		} else {
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookVo> list = dao.getList();
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
			request.setAttribute("list", list);
			rd.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

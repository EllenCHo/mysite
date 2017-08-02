package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/bs")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		
		if("read".equals(actionName)) {
			int boardNo = Integer.parseInt(request.getParameter("no"));
			
			BoardDao dao = new BoardDao();
			BoardVo vo = dao.read(boardNo);
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/board/read.jsp");
			request.setAttribute("vo", vo);
			rd.forward(request, response);
			
		} else {
			BoardDao dao = new BoardDao();
			List<BoardVo> list = dao.getlist();
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/board/list.jsp");
			request.setAttribute("list", list);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

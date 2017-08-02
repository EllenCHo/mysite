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
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/bs")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");

		if("search".equals(actionName)) {
			String kwd = request.getParameter("kwd");
			
			BoardDao dao = new BoardDao();
			List<BoardVo> list = dao.search(kwd);
			
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/board/list.jsp");
			request.setAttribute("list", list);
			request.setAttribute("kwd", kwd);
			rd.forward(request, response);
			
		} else if("modify".equals(actionName)) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			BoardDao dao = new BoardDao();
			dao.update(boardNo, title, content);
			
			String path = "/mysite/bs?a=read&no=" + boardNo;
			response.sendRedirect(path);
			
		} else if("modifyform".equals(actionName)) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			BoardDao dao = new BoardDao();
			BoardVo vo = dao.read(boardNo);
			vo.setContent(vo.getContent().replace("<br/>", "\n"));

			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/board/modifyform.jsp");
			request.setAttribute("vo", vo);
			rd.forward(request, response);
			
		} else if ("delete".equals(actionName)) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
	
			BoardDao dao = new BoardDao();
			dao.delete(boardNo);
			
			response.sendRedirect("/mysite/bs");

		} else if ("write".equals(actionName)) {
			int userNo = Integer.parseInt(request.getParameter("userNo"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardDao dao = new BoardDao();
			int boardNo = dao.insert(userNo, title, content);

			String path = "/mysite/bs?a=read&no=" + boardNo;
			response.sendRedirect(path);
			
		} else if ("writeform".equals(actionName)) {
			WebUtil.forward(request, response, "WEB-INF/views/board/writeform.jsp");

		} else if ("read".equals(actionName)) {
			int boardNo = Integer.parseInt(request.getParameter("no"));

			BoardDao dao = new BoardDao();
			BoardVo vo = dao.read(boardNo);
			dao.count(boardNo);

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.javaex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String actionName = request.getParameter("a");

		if ("joinform".equals(actionName)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/joinform.jsp");
			// 개발 영역으로 들어가는 것이므로 밑의 주소와 다름
			rd.forward(request, response);
		} else if ("join".equals(actionName)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String gender = request.getParameter("gender");

			UserVo vo = new UserVo(name, email, password, gender);
			UserDao dao = new UserDao();

			dao.insert(vo);

			// insert가 결과값을 저장하므로 1이상 리턴할 경우 db에 성공적으로 저장했으므로 if-else문으로 회원가입 성공과 실패를 나누는
			// 방법이 필요할 듯
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/joinsuccess.jsp");
			// 리다이렉트로 못하는 이유는 joinsuccess.jsp로 직접적으로 들어갈 수없기 때문이다.
			// else if문으로 joinsuccess.jsp으로 포워딩시킬수있지만 결국에는 두번 일을 하는 것이므로 join에서 바로 포워딩시킨다.
			rd.forward(request, response);

		} else if ("loginform".equals(actionName)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
			rd.forward(request, response);
		} else if ("login".equals(actionName)) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			UserDao dao = new UserDao();
			UserVo vo = dao.getUser(email, password);

			if (vo == null) { // 로그인에 실패했을 경우
				System.out.println("실패");
				response.sendRedirect("/mysite/user?a=loginform&result=fail");

				/*
				 * request.setAttribute("result", "fail"); RequestDispatcher rd =
				 * request.getRequestDispatcher("/WEB-INF/views/user/loginform.jsp");
				 * rd.forward(request, response);
				 */

			} else {
				System.out.println("성공");
				HttpSession session = request.getSession(true);
				session.setAttribute("authUser", vo); // 사용자의 세션id에 authUser는 그냥 별명(인증된 유저)으로 vo를 실어줌

				response.sendRedirect("/mysite/main");

				return; // 여기서 끝 -> 여기서는 안하면 오류남
			}

		} else if ("logout".equals(actionName)) {
			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();

			response.sendRedirect("/mysite/main");

		} else {
			response.sendRedirect("/mysite/main"); // 사용자가 다시 요청하는 것이므로 사용자가 작성하는 것과 같이 작성
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

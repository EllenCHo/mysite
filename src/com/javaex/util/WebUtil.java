package com.javaex.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {			//매번 쓰기 귀찮으니 메소드로 하나 정의해서 사용, 매번 클래스 new하기 싫으니 static으로 사용
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		response.sendRedirect(url);
	}

}

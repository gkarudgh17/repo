package com.namoosori.shop.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -6737978632833768209L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		CustomerRepository repository = CustomerRepository.getInstance();
		
		ServletContext context = getServletContext();
		String userId = context.getInitParameter("adminId");
		String password = context.getInitParameter("adminPassword");
		// 요청파라미터
		req.setCharacterEncoding("utf-8");
		String paramLoginId = req.getParameter("loginId");
		String paramPassword = req.getParameter("password");

		Cookie cookie = new Cookie("loginId", paramLoginId);
		cookie.setMaxAge(-1);
		Cookie cookie2 = new Cookie("passWord", paramPassword);
		cookie2.setMaxAge(-1);
		
		System.out.println("아이디:"+userId);
		System.out.println("비밀번호:"+password);
		System.out.println("입력한아이디:"+paramLoginId);
		System.out.println("입력한비밀번호:"+paramPassword);
		HttpSession session = req.getSession();
		if (userId.equals(paramLoginId) && password.equals(paramPassword)) {
			// 로그인성공
			session.setAttribute("loginId", userId);// 로그인아이디를 세션에 저장
			session.setAttribute("password", password);
			resp.sendRedirect("main.xhtml");
			resp.addCookie(null);
			resp.addCookie(null);
		} else {
			// 로그인실패
			resp.addCookie(cookie);
			resp.addCookie(cookie2);
			resp.sendRedirect("login.xhtml");
		}

	}
}

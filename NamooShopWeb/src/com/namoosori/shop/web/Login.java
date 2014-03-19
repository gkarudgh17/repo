package com.namoosori.shop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login.xhtml")
public class Login extends HttpServlet {

	private static final long serialVersionUID = -7237889679575088633L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html; charset=utf-8");
		PrintWriter writer = resp.getWriter();

		String login = "";
		String loginid = "";
		String password = "";

		Cookie[] cookies = req.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if ("loginId".equals(cookie.getName())) {
					loginid = cookie.getValue();
				}
			}
		}
		Cookie[] cookies2 = req.getCookies();
		if (cookies2 != null && cookies2.length > 0) {
			for (Cookie cookie2 : cookies2) {
				if ("passWord".equals(cookie2.getName())) {
					password = cookie2.getValue();
				}
			}
		}
		
		System.out.println(loginid);
		System.out.println(password);
		
		if(loginid.length()>0){
			login = "로그인실패";
		}
		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>로그인</title>");
		writer.println("<link href='./login.css' rel='stylesheet' type='text/css' />");
		writer.println("</head>");
		writer.println("<body>");
		writer.println(login);
		writer.println("<form action = \"login.do\" method=\"post\">");
		writer.println("<table>");
		writer.println("<tr>");
		writer.println("<th class='can'>로그인ID</th>");
		writer.println("<th><input type=\"text\" name=\"loginId\" value='"+loginid+"' /></th><br/>");
		writer.println("</tr>");

		writer.println("<tr>");
		writer.println("<th class='can'>패스워드</th>");
		writer.println("<th><input type=\"password\" name=\"password\" value='"+password+"'/></th><br/>");
		writer.println("</tr>");
		writer.println("</table>");
		writer.println("<input id='login' type=\"submit\" value=\"로그인\"/>");
		writer.println("</form>");
		writer.println("</body>");
		writer.println("</html>");

	}

}

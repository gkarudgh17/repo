package com.namoosori.shop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/complete.xhtml")
public class Complete extends HttpServlet {

	private static final long serialVersionUID = -3051439526195997271L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.removeAttribute("loginId");
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter writer = resp.getWriter();
		
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>완료</title>");
		writer.println("<link href='./css/complete.css' rel='stylesheet' type='text/css' />");
		writer.println("</head>");
		writer.println("<body>");
		
		writer.println("<h1>상품주문이 완료되었습니다</h1>");
		writer.println("<form action='main.xhtml' method='post'>");
		writer.println("<input id='ddd' type='submit' value='확인'/>");
		
		writer.println("</form>");
		writer.println("</body>");
		writer.println("</html>");
	}

}

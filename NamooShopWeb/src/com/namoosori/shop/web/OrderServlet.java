package com.namoosori.shop.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/order.do")
public class OrderServlet extends HttpServlet{

	private static final long serialVersionUID = -1588822978316313012L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 
		req.setCharacterEncoding("utf-8");
		String[] products = req.getParameterValues("products");
		System.out.println(products);
		if(products != null && products.length > 0) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("order.xhtml");
			dispatcher.forward(req, resp);
		} else {
			//
			resp.sendRedirect("main.xhtml");
		}
	}
	
	

}
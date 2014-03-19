package com.namoosori.shop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoosori.namooshop.domain.Product;
import com.namoosori.namooshop.service.facade.ProductService;
import com.namoosori.namooshop.service.factory.NamooShopServiceFactory;

@WebServlet("/confirm.xhtml")
public class Comfirm extends HttpServlet {

	private static final long serialVersionUID = -7237889679575088633L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
//
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NamooShopServiceFactory factory = NamooShopServiceFactory.getInstance();
		ProductService service = factory.getProductService();
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter writer = resp.getWriter();
		
		String pay = req.getParameter("pay");
		String address = req.getParameter("address");
		
		/*if(pay != null | address != null) {
		 *오더에 책리스트 목록줘야함
			RequestDispatcher dispatcher = req.getRequestDispatcher("order.xhtml");
			dispatcher.forward(req, resp);
		}*/
		System.out.println(pay);
		System.out.println(address);
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>Insert title here</title>");
		writer.println("<link href='./order.css' rel='stylesheet' type='text/css' />");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>주문 상품</h1>");
		//테이블
		writer.println("<table>");
		
		String[] products = req.getParameterValues("products");
		int price = 0;
		for (String productName : products) {
			Product product = service.getProduct(Integer.parseInt(productName));
			writer.println("<tr>");
			writer.println("<td class='books1'>" + product.getSerialNo() + "</td>");
			writer.println("<td class='books2'>" + product.getName() + "</td>");
			writer.println("<td class='books3'>" + product.getPrice() + "</td>");
			writer.println("</tr>");
			price += product.getPrice();
		}
		writer.println("</table>");
		writer.println("<h2>주문금액:" + price + "</h2>");
		
		
		writer.println("<h1>주문정보 확인</h1>");
		writer.println("<table id='tableaaa'>");
		writer.println("<tr>");
		writer.println("<th class='table1'>결제방법</th>");
		writer.println("<th class='table2'>"+pay+"<br/>");
		writer.println("</tr>");
		writer.println("<tr>");
		writer.println("<th class='table1'>배송지 주소</th>");
		writer.println("<th class='table2'>"+address+"</th>");
		writer.println("</tr>");
		writer.println("</table>");
		writer.println("<a href='javascript:history.back()'>수정하기</a>");
		writer.println("<form action='complete.xhtml' method='post'>");
		writer.println("<input type='submit' id='sin' value='신청완료'/>");
		writer.println("</form>");
		writer.println("</body>");
		writer.println("</html>");
		
	}

}

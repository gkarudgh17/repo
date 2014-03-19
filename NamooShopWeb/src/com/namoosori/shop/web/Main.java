package com.namoosori.shop.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namoosori.namooshop.domain.Product;
import com.namoosori.namooshop.service.facade.ProductService;
import com.namoosori.namooshop.service.factory.NamooShopServiceFactory;

@WebServlet("/main.xhtml")
public class Main extends HttpServlet {

	private static final long serialVersionUID = -5441914164986289841L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
		NamooShopServiceFactory factory = NamooShopServiceFactory.getInstance();
		ProductService service = factory.getProductService();
		List<Product> products = service.getAllProducts();

		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter writer = resp.getWriter();

		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>Insert title here</title>");
		writer.println("<link href='./booklist.css' rel='stylesheet' type='text/css' />");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>상품목록</h1>");

		writer.println("<form action='order.do' method='post'>");
		writer.println("<table>");
		writer.println("<thead>");
		writer.println("<tr>");
		writer.println("<th></th>");
		writer.println("<th class='books1'>상품번호</th>");
		writer.println("<th class='books2'>상품명</th>");
		writer.println("<th class='books4'>가격</th>");
		writer.println("<th class='books4'>평점</th>");
		writer.println("</tr>");

		writer.println("</thead>");
		writer.println("<tbody>");

		for (Product product : products) {
			writer.println("<tr>");
			writer.println("<td class='books'><input type='checkbox' name='products' value='"
					+ product.getSerialNo() + "'/></td>");
			writer.println("<td class='books1'>" + product.getSerialNo()
					+ "</td>");
			writer.println("<td class='books2'>" + product.getName() + "</td>");
			writer.println("<td class='books3'>" + product.getPrice() + "</td>");
			writer.println("<td class='books4'>" + product.getLike() + "</td>");
			writer.println("</tr>");
		}

		writer.println("</tbody>");
		writer.println("</table>");
		writer.println("<input type='submit' id='jumun' value='주문하기'/>");
		writer.println("</form>");
		writer.println("</body>");
		writer.println("</html>");
	}

}

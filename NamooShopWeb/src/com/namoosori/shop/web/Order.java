package com.namoosori.shop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.namoosori.namooshop.domain.Product;
import com.namoosori.namooshop.service.facade.ProductService;
import com.namoosori.namooshop.service.factory.NamooShopServiceFactory;

@WebServlet("/order.xhtml")
public class Order extends HttpServlet {

	private static final long serialVersionUID = -7237889679575088633L;

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

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter writer = resp.getWriter();
		String[] products = req.getParameterValues("products");

		// 요청파라미터
		HttpSession session = req.getSession();
		String paramLoginId = (String) session.getAttribute("loginId");
		String paramPassword = (String) session.getAttribute("password");
		System.out.println("아이디:" + paramLoginId);
		System.out.println("비밀번호:" + paramPassword);
		if (paramLoginId == null | paramPassword == null) {
			// 로그인이 아니면
			resp.sendRedirect("login.xhtml");
		}

		writer.println("[" + paramLoginId + "]" + "님이 로그인되었습니다.");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<title>Insert title here</title>");
		writer.println("<link href='./order.css' rel='stylesheet' type='text/css' />");
		writer.println("</head>");
		writer.println("<body>");
		writer.println("<h1>주문 상품</h1>");
		// 테이블
		writer.println("<form action='confirm.xhtml' method='post'>");
		writer.println("<table>");
		writer.println("<thead>");
		writer.println("<tr>");
		writer.println("<th class='books1'>상품번호</th>");
		writer.println("<th class='books2'>상품명</th>");
		writer.println("<th class='books3'>가격</th>");
		writer.println("</tr>");
		writer.println("</thead>");

		int price = 0;
		System.out.println("주문한책목록" + products);
		for (String productName : products) {
			Product product = service.getProduct(Integer.parseInt(productName));
			writer.println("<tr>");
			writer.println("<input name='products' style='display:none' value='"
					+ product.getSerialNo() + "'>");
			writer.println("<td class='books1'>" + product.getSerialNo()
					+ "</td>");
			writer.println("<td class='books2'>" + product.getName() + "</td>");
			writer.println("<td class='books3'>" + product.getPrice() + "</td>");
			writer.println("</tr>");
			price += product.getPrice();
		}
		writer.println("</table>");
		writer.println("<h2>주문금액:" + price + "</h2>");

		writer.println("<h1>주문정보 입력</h1>");
		writer.println("<table >");
		writer.println("<tr>");
		writer.println("<th class='table1'>결제방법</th>");
		writer.println("<th class='table2'><input type='radio' name='pay' value='신용카드'/>신용카드<input type='radio' name='pay' value='실시간이체'/>실시간이체</th><br/>");
		writer.println("</tr>");
		writer.println("<tr>");
		writer.println("<th class='table1'>배송지 주소</th>");
		writer.println("<th class='table2'><input type='text' style='width:350px' name='address' /></th>");
		writer.println("</tr>");
		writer.println("</table>");
		writer.println("<input type='submit' id='jumun' value='주문하기'/>");
		writer.println("</form>");

		writer.println("</body>");
		writer.println("</html>");

	}

}

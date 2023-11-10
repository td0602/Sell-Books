package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.BookOrderDAOImpl;
import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
import com.entity.Book_Order;
import com.entity.Cart;

@WebServlet(urlPatterns= {"/order"})
public class OrderServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("username");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String address = req.getParameter("address");
			String landmark = req.getParameter("landmark");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String pincode = req.getParameter("pincode");
			String paymentType = req.getParameter("payment");
			
			String fullAdd = address+","+landmark+","+city+","+state+","+pincode;
			//System.out.println(name+" "+email+" "+phno+" "+fullAdd+" "+paymentType);
			
			CartDAOImpl daoImpl = new CartDAOImpl(DBConnect.getConnection());
			List<Cart> blist = daoImpl.getBookByUser(id);

			BookOrderDAOImpl daoImpl2 = new BookOrderDAOImpl(DBConnect.getConnection());
			
			if(blist.isEmpty()) {
				session.setAttribute("failedMsg", "Add Item");
				resp.sendRedirect("checkout.jsp");
			} else {
				Book_Order order = null;
				ArrayList<Book_Order> orderList = new ArrayList<>();
				
				Random r = new Random();
				for(Cart c: blist) {
					order = new Book_Order();
					order.setOrderId("BOOK-ORD-00"+r.nextInt(1000));
					order.setUserName(name);
					order.setEmail(email);
					order.setPhno(phno);
					order.setFulladd(fullAdd);
					order.setBookName(c.getBookName());
					order.setAuthor(c.getAuthor());
					order.setPrice(c.getPrice()+"");
					order.setPaymentType(paymentType);
					
					orderList.add(order);
				}
				
				if("noselect".equals(paymentType)) {
					session.setAttribute("failedMsg", "Choose Payment Method");
					resp.sendRedirect("checkout.jsp");
				} else {
					boolean f = daoImpl2.saveOrder(orderList);
					if(f) {
						resp.sendRedirect("order_success.jsp");
					} else {
						session.setAttribute("failedMsg", "Your Order Failed");
						resp.sendRedirect("checkout.jsp");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

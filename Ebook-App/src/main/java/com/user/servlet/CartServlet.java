package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.BookDAOImpl;
import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
import com.entity.Cart;

@WebServlet(urlPatterns= {"/cart"})
public class CartServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			int bid = Integer.parseInt(req.getParameter("bid"));
			int uid = Integer.parseInt(req.getParameter("uid"));
			BookDAOImpl daoImpl = new BookDAOImpl(DBConnect.getConnection());
			BookDtls bookDtls = daoImpl.getBookById(bid);
			
			Cart cart = new Cart();
			cart.setBid(bid);
			cart.setUid(uid);
			cart.setBookName(bookDtls.getBookName());
			cart.setAuthor(bookDtls.getAuthor());
			cart.setPrice(bookDtls.getPrice());
			cart.setTotalPrice(bookDtls.getPrice());
			
			CartDAOImpl dao2 = new CartDAOImpl(DBConnect.getConnection());
			boolean f = dao2.addCart(cart);
			
			HttpSession session = req.getSession();
			if(f) {
				session.setAttribute("addCart", "Book Added to cart");
				resp.sendRedirect("all_new_book.jsp");
				
			} else {
				session.setAttribute("failed", "Something Wrong on server");
				resp.sendRedirect("all_new_book.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

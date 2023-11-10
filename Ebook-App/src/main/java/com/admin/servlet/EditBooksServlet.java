package com.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.BookDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;

@WebServlet(urlPatterns= {"/editbooks"})
public class EditBooksServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String bookName = req.getParameter("bname");
			String author = req.getParameter("author");
			Double price = Double.parseDouble(req.getParameter("price"));
			String status = req.getParameter("bstatus");
		
			BookDtls bookDtls = new BookDtls();
			bookDtls.setBookId(id);
			bookDtls.setBookName(bookName);
			bookDtls.setAuthor(author);
			bookDtls.setPrice(price);
			bookDtls.setStatus(status);
			
			BookDAOImpl daoImpl = new BookDAOImpl(DBConnect.getConnection());
			boolean f = daoImpl.updateBooks(bookDtls);
			
			HttpSession session = req.getSession();
			
			if(f) {
				session.setAttribute("succMsg", "Book Update Successfuly..");
				resp.sendRedirect("admin/all_books.jsp");
			} else {
				session.setAttribute("failedMsg", "Something wrong on server..");
				resp.sendRedirect("admin/all_books.jsp");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

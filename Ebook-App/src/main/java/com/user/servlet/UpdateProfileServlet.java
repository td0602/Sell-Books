package com.user.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDAOImpl;
import com.DB.DBConnect;
import com.entity.User;

@WebServlet(urlPatterns= {"/update_profile"})
public class UpdateProfileServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("fname");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String password = req.getParameter("password");
			
			User user = new User();
			user.setId(id);
			user.setName(name);
			user.setEmail(email);
			user.setPhno(phno);
			
			HttpSession session = req.getSession();
			UserDAOImpl daoImpl = new UserDAOImpl(DBConnect.getConnection());
			boolean f = daoImpl.checkPassword(id, password);
			
			if(f) {
				boolean f2 = daoImpl.updateProfile(user);
				if(f2) {
					session.setAttribute("succMsg", "Profile Update Successfully...");
					resp.sendRedirect("edit_profile.jsp");
				} else {
					session.setAttribute("failedMsg", "Something wrong on server...");
					resp.sendRedirect("edit_profile.jsp");
				}
			} else {
				session.setAttribute("failedMsg", "Your Password is Incorrect...");
				resp.sendRedirect("edit_profile.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Book_Order;

public class BookOrderDAOImpl implements BookOrderDAO{

	private Connection connection;
	
	

	public BookOrderDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}



	public BookOrderDAOImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean saveOrder(List<Book_Order> blist) {
		boolean f = false;
		try {
			String sql = "insert into book_order(order_id, user_name, email, address, phone, book_name, author, price, payment) values(?,?,?,?,?,?,?,?,?)";
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(sql);
			
			for(Book_Order b: blist) {
				statement.setString(1, b.getOrderId());
				statement.setString(2, b.getUserName());
				statement.setString(3, b.getEmail());
				statement.setString(4, b.getFulladd());
				statement.setString(5, b.getPhno());
				statement.setString(6, b.getBookName());
				statement.setString(7, b.getAuthor());
				statement.setString(8, b.getPrice());
				statement.setString(9, b.getPaymentType());
				statement.addBatch();
			}
			
			int[] count = statement.executeBatch();
			connection.commit();
			f = true;
			connection.setAutoCommit(true  );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}



	@Override
	public List<Book_Order> getBook(String email) {
		
		List<Book_Order> list = new ArrayList<>();
		Book_Order book_Order = null;
		try {
			String sql = "select *from book_order where email=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				book_Order = new Book_Order();
				book_Order.setId(resultSet.getInt(1));
				book_Order.setOrderId(resultSet.getString(2));
				book_Order.setUserName(resultSet.getString(3));
				book_Order.setEmail(resultSet.getString(4));
				book_Order.setFulladd(resultSet.getString(5));
				book_Order.setPhno(resultSet.getString(6));
				book_Order.setBookName(resultSet.getString(7));
				book_Order.setAuthor(resultSet.getString(8));
				book_Order.setPrice(resultSet.getString(9));
				book_Order.setPaymentType(resultSet.getNString(10));
				
				list.add(book_Order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public List<Book_Order> getAllOrder() {
		List<Book_Order> list = new ArrayList<>();
		Book_Order book_Order = null;
		try {
			String sql = "select *from book_order";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				book_Order = new Book_Order();
				book_Order.setId(resultSet.getInt(1));
				book_Order.setOrderId(resultSet.getString(2));
				book_Order.setUserName(resultSet.getString(3));
				book_Order.setEmail(resultSet.getString(4));
				book_Order.setFulladd(resultSet.getString(5));
				book_Order.setPhno(resultSet.getString(6));
				book_Order.setBookName(resultSet.getString(7));
				book_Order.setAuthor(resultSet.getString(8));
				book_Order.setPrice(resultSet.getString(9));
				book_Order.setPaymentType(resultSet.getNString(10));
				
				list.add(book_Order);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}

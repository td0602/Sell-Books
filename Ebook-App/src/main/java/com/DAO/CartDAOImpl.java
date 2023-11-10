package com.DAO;

import java.lang.annotation.Retention;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.BookDtls;
import com.entity.Cart;

public class CartDAOImpl implements CartDAO{
	
	private Connection connection;
	
	public CartDAOImpl(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public boolean addCart(Cart cart) {
		boolean f = false;
		try {
			String sql = "insert into cart(bid, uid, bookname, author, price, total_price) values(?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, cart.getBid());
			statement.setInt(2, cart.getUid());
			statement.setString(3, cart.getBookName());
			statement.setString(4, cart.getAuthor());
			statement.setDouble(5, cart.getPrice());
			statement.setDouble(6, cart.getTotalPrice());
			
			int i = statement.executeUpdate();
			if(i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public List<Cart>  getBookByUser(int userId) {
		List<Cart> list = new ArrayList<>();
		Cart cart = null;
		double totalPrice = 0;
		try {
			String sql ="select *from cart where uid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, userId);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				cart = new Cart();
				cart.setCid(resultSet.getInt(1));
				cart.setBid(resultSet.getInt(2));
				cart.setUid(resultSet.getInt(3));
				cart.setBookName(resultSet.getString(4));
				cart.setAuthor(resultSet.getString(5));
				cart.setPrice(resultSet.getDouble(6));
				
				totalPrice += resultSet.getDouble(7);
				cart.setTotalPrice(totalPrice);
				
				list.add(cart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean deleteBook(int bid, int uid, int cid) {
		boolean f = false;
		try {
			String sql = "delete from cart where bid=? and uid=? and cid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, bid);
			statement.setInt(2, uid);
			statement.setInt(3, cid);
			int i = statement.executeUpdate();
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
}

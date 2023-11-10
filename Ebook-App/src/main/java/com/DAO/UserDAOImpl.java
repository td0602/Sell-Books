package com.DAO;

import java.security.spec.PSSParameterSpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDAOImpl implements  UserDAO{
	private Connection connection;

	public UserDAOImpl(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public boolean userRegister(User us) {
		boolean f = false;
		
		try {
			String sql = "insert into user(name, email, phno, password) values(?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, us.getName());
			statement.setString(2, us.getEmail());
			statement.setString(3, us.getPhno());
			statement.setString(4, us.getPassword());
			
			int i = statement.executeUpdate();
			if(i==1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public User login(String email, String password) {
		
		User user = null;
		try {
			String sql="select *from user where email=? and password=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet resultset = statement.executeQuery();
			while(resultset.next()) {
				user = new User();
				user.setId(resultset.getInt(1));
				user.setName(resultset.getString(2));
				user.setEmail(resultset.getString(3));
				user.setPhno(resultset.getString(4));
				user.setPassword(resultset.getString(5));
				user.setAddress(resultset.getString(6));
				user.setLandmark(resultset.getString(7));
				user.setCity(resultset.getString(8));
				user.setState(resultset.getString(9));
				user.setPincode(resultset.getString(10));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean checkPassword(int id, String ps) {
		boolean f = false;
		
		try {
			String sql = "select *from user where id=? and password=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2, ps);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	@Override
	public boolean updateProfile(User user) {
		boolean f = false;
		
		try {
			String sql = "update user set name=?, email=?, phno=? where id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPhno());
			statement.setInt(4, user.getId());
			
			int i = statement.executeUpdate();
			if(i==1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public boolean checkUer(String em) {
		boolean f = true;
		try {
			String sql = "select *from user where email=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, em);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				f = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
}

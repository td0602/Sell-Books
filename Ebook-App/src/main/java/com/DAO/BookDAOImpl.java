package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.BookDtls;

import javafx.util.converter.ShortStringConverter;

public class BookDAOImpl implements BookDAO{

	private Connection connection;
	
	
	public BookDAOImpl(Connection connection) {
		super();
		this.connection = connection;
	}


	@Override
	public boolean addBook(BookDtls bookDtls) {
		boolean f = false;
		try {
			String sql = "insert into book_dtls(bookname, author, price, bookCategory, status, photo, email) values(?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, bookDtls.getBookName());
			statement.setString(2, bookDtls.getAuthor());
			statement.setDouble(3, bookDtls.getPrice());
			statement.setString(4, bookDtls.getBookCategory());
			statement.setString(5, bookDtls.getStatus());
			statement.setString(6, bookDtls.getPhotoName());
			statement.setString(7, bookDtls.getEmail());
		
			int i = statement.executeUpdate();
			if(i == 1) {
				f = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return f;
	}


	@Override
	public List<BookDtls> getAllBooks() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls bookDtls = null;
		
		try {
			String sql = "select *from book_dtls";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(resultSet.getInt(1));
				bookDtls.setBookName(resultSet.getString(2));
				bookDtls.setAuthor(resultSet.getString(3));
				bookDtls.setPrice(resultSet.getDouble(4));
				bookDtls.setBookCategory(resultSet.getString(5));
				bookDtls.setStatus(resultSet.getString(6));
				bookDtls.setPhotoName(resultSet.getString(7));
				bookDtls.setEmail(resultSet.getString(8));
				
				list.add(bookDtls);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}


	@Override
	public BookDtls getBookById(int Id) {
		BookDtls bookDtls = null;
		try {
			String sql = "select *from book_dtls where bookId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, Id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				bookDtls = new BookDtls();
				bookDtls.setBookId(resultSet.getInt(1));
				bookDtls.setBookName(resultSet.getString(2));
				bookDtls.setAuthor(resultSet.getString(3));
				bookDtls.setPrice(resultSet.getDouble(4));
				bookDtls.setBookCategory(resultSet.getString(5));
				bookDtls.setStatus(resultSet.getString(6));
				bookDtls.setPhotoName(resultSet.getString(7));
				bookDtls.setEmail(resultSet.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookDtls;
	}


	@Override
	public boolean updateBooks(BookDtls bookDtls) {
		boolean f = false;
		try {
			String sql = "update book_dtls set bookname=?, author=?, price=?, status=? where bookId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, bookDtls.getBookName());
			statement.setString(2, bookDtls.getAuthor());
			statement.setDouble(3, bookDtls.getPrice());
			statement.setString(4, bookDtls.getStatus());
			statement.setInt(5, bookDtls.getBookId());
			
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
	public boolean deleteBooks(int id) {
		boolean f = false;
		try {
			String sql = "delete from book_dtls where bookId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
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
	public List<BookDtls> getNewBook() {
		
		List<BookDtls> list = new ArrayList<>();
		BookDtls bookDtls = null;
		try {
			String sql = "select *from book_dtls where bookCategory=? and status=? order by bookId desc";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "New");
			statement.setString(2, "Active");
			ResultSet resultSet = statement.executeQuery();
			int i = 1;
			while(resultSet.next() && i <= 4) {
				 bookDtls = new BookDtls();
				 bookDtls.setBookId(resultSet.getInt(1));
				 bookDtls.setBookName(resultSet.getString(2));
				 bookDtls.setAuthor(resultSet.getString(3));
				 bookDtls.setPrice(resultSet.getDouble(4));
				 bookDtls.setBookCategory(resultSet.getString(5));
				 bookDtls.setStatus(resultSet.getString(6));
				 bookDtls.setPhotoName(resultSet.getString(7));
				 bookDtls.setEmail(resultSet.getString(8));
				 
				 list.add(bookDtls);
				 i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<BookDtls> getRecentBook() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls bookDtls = null;
		try {
			String sql = "select *from book_dtls where status=? order by bookId desc";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "Active");
			ResultSet resultSet = statement.executeQuery();
			int i = 1;
			while(resultSet.next() && i <= 4) {
				 bookDtls = new BookDtls();
				 bookDtls.setBookId(resultSet.getInt(1));
				 bookDtls.setBookName(resultSet.getString(2));
				 bookDtls.setAuthor(resultSet.getString(3));
				 bookDtls.setPrice(resultSet.getDouble(4));
				 bookDtls.setBookCategory(resultSet.getString(5));
				 bookDtls.setStatus(resultSet.getString(6));
				 bookDtls.setPhotoName(resultSet.getString(7));
				 bookDtls.setEmail(resultSet.getString(8));
				 
				 list.add(bookDtls);
				 i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<BookDtls> getOldBooks() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls bookDtls = null;
		try {
			String sql = "select *from book_dtls where bookCategory=? and status=? order by bookId desc";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "Old");
			statement.setString(2, "Active");
			ResultSet resultSet = statement.executeQuery();
			int i = 1;
			while(resultSet.next() && i <= 4) {
				 bookDtls = new BookDtls();
				 bookDtls.setBookId(resultSet.getInt(1));
				 bookDtls.setBookName(resultSet.getString(2));
				 bookDtls.setAuthor(resultSet.getString(3));
				 bookDtls.setPrice(resultSet.getDouble(4));
				 bookDtls.setBookCategory(resultSet.getString(5));
				 bookDtls.setStatus(resultSet.getString(6));
				 bookDtls.setPhotoName(resultSet.getString(7));
				 bookDtls.setEmail(resultSet.getString(8));
				 
				 list.add(bookDtls);
				 i++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<BookDtls> getAllRecentBook() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls bookDtls = null;
		try {
			String sql = "select *from book_dtls where status=? order by bookId desc";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "Active");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				 bookDtls = new BookDtls();
				 bookDtls.setBookId(resultSet.getInt(1));
				 bookDtls.setBookName(resultSet.getString(2));
				 bookDtls.setAuthor(resultSet.getString(3));
				 bookDtls.setPrice(resultSet.getDouble(4));
				 bookDtls.setBookCategory(resultSet.getString(5));
				 bookDtls.setStatus(resultSet.getString(6));
				 bookDtls.setPhotoName(resultSet.getString(7));
				 bookDtls.setEmail(resultSet.getString(8));
				 
				 list.add(bookDtls);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<BookDtls> getAllNewBook() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls bookDtls = null;
		try {
			String sql = "select *from book_dtls where bookCategory=? and status=? order by bookId desc";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "New");
			statement.setString(2, "Active");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				 bookDtls = new BookDtls();
				 bookDtls.setBookId(resultSet.getInt(1));
				 bookDtls.setBookName(resultSet.getString(2));
				 bookDtls.setAuthor(resultSet.getString(3));
				 bookDtls.setPrice(resultSet.getDouble(4));
				 bookDtls.setBookCategory(resultSet.getString(5));
				 bookDtls.setStatus(resultSet.getString(6));
				 bookDtls.setPhotoName(resultSet.getString(7));
				 bookDtls.setEmail(resultSet.getString(8));
				 
				 list.add(bookDtls);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<BookDtls> getAllOldBook() {
		List<BookDtls> list = new ArrayList<>();
		BookDtls bookDtls = null;
		try {
			String sql = "select *from book_dtls where bookCategory=? and status=? order by bookId desc";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "Old");
			statement.setString(2, "Active");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				 bookDtls = new BookDtls();
				 bookDtls.setBookId(resultSet.getInt(1));
				 bookDtls.setBookName(resultSet.getString(2));
				 bookDtls.setAuthor(resultSet.getString(3));
				 bookDtls.setPrice(resultSet.getDouble(4));
				 bookDtls.setBookCategory(resultSet.getString(5));
				 bookDtls.setStatus(resultSet.getString(6));
				 bookDtls.setPhotoName(resultSet.getString(7));
				 bookDtls.setEmail(resultSet.getString(8));
				 
				 list.add(bookDtls);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public List<BookDtls> getBookByOld(String email, String cate) {
		List<BookDtls> list = new ArrayList<>();
		BookDtls bookDtls = null;
		
		try {
			String sql = "select *from book_dtls where email=? and bookCategory=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, cate);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				bookDtls = new BookDtls();
				 bookDtls.setBookId(resultSet.getInt(1));
				 bookDtls.setBookName(resultSet.getString(2));
				 bookDtls.setAuthor(resultSet.getString(3));
				 bookDtls.setPrice(resultSet.getDouble(4));
				 bookDtls.setBookCategory(resultSet.getString(5));
				 bookDtls.setStatus(resultSet.getString(6));
				 bookDtls.setPhotoName(resultSet.getString(7));
				 bookDtls.setEmail(resultSet.getString(8));
				 
				 list.add(bookDtls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public boolean oldBookDelete(String email, String cat, int bid) {
		boolean f = false;
		
		try {
			String sql = "delete from book_dtls where email=? and bookCategory=? and bookId=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			statement.setString(2, cat);
			statement.setInt(3, bid);
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
	public List<BookDtls> getBookBySearch(String ch) {
		List<BookDtls> list = new ArrayList<>();
		BookDtls bookDtls = null;
		
		try {
			String sql = "select *from book_dtls where bookname like ? or author like ? or bookCategory like ? and status=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "%"+ch+"%");
			statement.setString(2, "%"+ch+"%");
			statement.setString(3, "%"+ch+"%");
			statement.setString(4, "Active");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				bookDtls = new BookDtls();
				 bookDtls.setBookId(resultSet.getInt(1));
				 bookDtls.setBookName(resultSet.getString(2));
				 bookDtls.setAuthor(resultSet.getString(3));
				 bookDtls.setPrice(resultSet.getDouble(4));
				 bookDtls.setBookCategory(resultSet.getString(5));
				 bookDtls.setStatus(resultSet.getString(6));
				 bookDtls.setPhotoName(resultSet.getString(7));
				 bookDtls.setEmail(resultSet.getString(8));
				 
				 list.add(bookDtls);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}

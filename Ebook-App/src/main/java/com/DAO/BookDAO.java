package com.DAO;

import java.util.List;

import com.entity.BookDtls;

public interface BookDAO {

	public boolean addBook(BookDtls bookDtls);
	public List<BookDtls> getAllBooks();
	public BookDtls getBookById(int Id);
	public boolean updateBooks(BookDtls bookDtls);
	public boolean deleteBooks(int id);
	public List<BookDtls> getNewBook();
	public List<BookDtls> getRecentBook();
	public List<BookDtls> getOldBooks();
	public List<BookDtls> getAllRecentBook();
	public List<BookDtls> getAllNewBook();
	public List<BookDtls> getAllOldBook();
	public List<BookDtls> getBookByOld(String email, String cate);
	public boolean oldBookDelete(String email, String cat, int bid);
	public List<BookDtls> getBookBySearch(String ch);
}

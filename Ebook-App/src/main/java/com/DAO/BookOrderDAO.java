package com.DAO;

import java.util.List;

import com.entity.Book_Order;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

public interface BookOrderDAO {
	
	public boolean saveOrder(List<Book_Order> blist);
	public List<Book_Order> getBook(String email);
	public List<Book_Order> getAllOrder();
}

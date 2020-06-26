package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.VocaDAO.VocaDTO;

public abstract class DAO<T> {
	//protected는 상속받은 클래스에서만 접근가능.
		protected Connection con;
		protected java.sql.Statement st = null;
		protected ResultSet rs = null;
		public DAO() {
			try {

				con = null;
				con = DriverManager.getConnection("jdbc:mysql://localhost/eng_voca?useUnicode=true&characterEncoding=UTF-8","root", "apmsetup");
				
				st = null;
				st = con.createStatement();

			} catch (SQLException sqex) {
				System.out.println("SQLException: " + sqex.getMessage());
				System.out.println("SQLState: " + sqex.getSQLState());

			}
		}
		
		//구현은 각 xxxDAO 클래스에서 한다.
		public abstract void create(T sql);
		public abstract T update(String sql);
		public abstract ArrayList<T> read(String sql);
		public abstract void delete(String sql);
}

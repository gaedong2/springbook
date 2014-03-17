package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import springbook.user.domain.User;

public abstract class UserDao {

	public void add(User user) throws ClassNotFoundException , SQLException{
		
		
		
		System.out.println("DB 커넥션");
		Connection c  = getConnection();
		
		PreparedStatement ps = c.prepareStatement(
				 "insert into users(id,name,password) values(?,?,?)");
				
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	    			
	}
	
	public User get(String id) throws ClassNotFoundException,SQLException{
		
		Connection c  = getConnection();
		
		PreparedStatement ps = c.prepareStatement(
				 "select * from users where id = ?");
		
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		User user = new User();
		
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		return user;
		
	}
	
	abstract protected Connection getConnection() throws ClassNotFoundException, SQLException ;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new NUserDao();

		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");

		System.out.println("프로그램 진입");
		
		
		dao.add(user);
			
		System.out.println(user.getId() + " 등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
		System.out.println(user2.getId() + " 조회 성공");
	}
	
	
}

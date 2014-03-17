package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao {


	public Connection getConnection() throws ClassNotFoundException , SQLException{
		System.out.println("DB 커넥션생성");
		Class.forName("com.mysql.jdbc.Driver");
		Connection c  = DriverManager.getConnection("jdbc:mysql://gaedong2.iptime.org/springbook","spring","book");
		System.out.println("DB 커넥션생성");
		return c;
	}

}

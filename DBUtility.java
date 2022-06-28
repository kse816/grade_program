package studentProgram;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class DBUtility {
	 public static Connection getConnection() {
	      Connection con=null;
	      
	      FileReader fr=null;
	      
	      try {
	         fr=new FileReader("src\\studentProgram\\db.properties");
	         Properties properties=new Properties();
	         properties.load(fr);
	         
	         String Driver=properties.getProperty("Driver");
	         String URL=properties.getProperty("URL");
	         String userID=properties.getProperty("userID");
	         String userPW=properties.getProperty("userPW");
	         
	         //드라이버 적재
	         Class.forName(Driver);
	         //데이터베이스연결
	         con=(Connection) DriverManager.getConnection(URL, userID, userPW);
	      } catch (ClassNotFoundException e) {
	         System.out.println("mysql database connection fail");
	         e.printStackTrace();
	      } catch (SQLException e) {
	         System.out.println("mysql database connection fail");
	         e.printStackTrace();
	      } catch (FileNotFoundException e) {
	         System.out.println("file db.properties connection fail");
	         e.printStackTrace();
	      } catch (IOException e) {
	         System.out.println("file db.properties connection fail");
	         e.printStackTrace();
	      }finally {
	         try {
	        	 fr.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	      
	      return con;

  }

	
}

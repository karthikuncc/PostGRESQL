import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {


	public static boolean insertUser(UserBean bn) {
		
		boolean status = false;
		Statement ps = null;
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO login (USERNAME,PASSWORD,name,country) VALUES ( '"
					+ bn.getUsername() + "','" + bn.getPassword() + "','" + bn.getName() + "','" + bn.getCountry()  + "');";
			ps = conn.createStatement();
				//	createStatement();
			
			System.out.println(sql);
			ps.execute(sql);
			status=true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
			}
		}
		return status;
	}

	public static boolean getUser(UserBean bn) {
		boolean userFound = false;
		Statement ps = null;
		Connection conn = null;
		try {

			System.out.println("The Jdbc URL is " );
			conn = ConnectionUtil.getConnection();
			ps = conn.createStatement();
			String sql = "SELECT NAME,COUNTRY FROM LOGIN WHERE username = '"+bn.getUsername()
			+"'AND PASSWORD='"+bn.getPassword()+"';";
			

			System.out.println("SQL Query is : " + sql);
			ResultSet resultSet = ps.executeQuery(sql);
			
			if(resultSet.next()){
				userFound= true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
			}
		}
		return userFound;
	}

}

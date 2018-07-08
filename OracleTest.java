import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OracleTest {

	public static void main(String[] args)throws Exception
	{
		// jdbc connection obj and load class
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jas","password");
		
		//create stmt obj
		Statement st = con.createStatement();
		
		//execute query
		ResultSet rs = st.executeQuery("select * from employee");

		//print db
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
		}
		
		//cloase all jdbc obj
		rs.close();
		st.close();
		con.close();
	}

}

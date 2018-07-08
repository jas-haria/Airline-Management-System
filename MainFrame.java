import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame
{
	Container c;
	JButton btnAdd,btnView,btnUpdate,btnDelete;
	
	MainFrame()
	{
		c=getContentPane();
		c.setLayout(new FlowLayout());

		btnAdd=new JButton("Add");
		btnView=new JButton("View");
		btnUpdate=new JButton("Update");
		btnDelete=new JButton("Delete");

		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);


		btnAdd.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					AddFrame a= new AddFrame();
					dispose();
				}
			});

		btnView.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					ViewFrame a= new ViewFrame();
					dispose();
				}
			});
		
		btnUpdate.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					UpdateFrame a= new UpdateFrame();
					dispose();
				}
			});
		btnDelete.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					DeleteFrame a= new DeleteFrame();
					dispose();
				}
			});
		
		setSize(500,150);
		setLocationRelativeTo(null);
		setTitle("Airline Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String args[])
	{
		MainFrame m=new MainFrame();
	}
}

class DatabaseHandler
{
	static Connection con;

	static void getCon()
	{
		try
		{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jas","password");
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}

	public void addEmployee(int id,String name, String designation, int age)
	{
		getCon();		

		try
		{
			String sql="INSERT INTO employee VALUES(?,?,?,?)";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,id);
			pst.setString(2,name);
			pst.setString(3,designation);
			pst.setInt(4,age);
			int r=pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(),r+" records inserted");
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(),"Insert issue");
		}
	}

	public String viewEmployee()
	{
		getCon();
		StringBuffer sb=new StringBuffer();		
		sb.append("ID\tNAME\tDESIGNATION\tAGE");

		try
		{
			Statement s1=con.createStatement();
			String s2="SELECT * FROM employee";
			ResultSet rs=s1.executeQuery(s2);
			while(rs.next())
			{
				int id=rs.getInt(1);
				String name=rs.getString(2);
				String designation=rs.getString(3);
				int age=rs.getInt(4);
				sb.append("\n"+id+"\t"+name+"\t" + designation + "\t" + age);
			}
			//sb.deleteCharAt(0);
		}
		catch(SQLException e){}	
		
		return sb.toString();
	}
	
	public void updateEmployee(int id,String name,String designation, int age)
	{
		getCon();		

		try
		{
			///*
			String sql="UPDATE employee SET ename=?,designation=?,age=?  WHERE eid=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1,name);
			pst.setString(2,designation);
			pst.setInt(3,age);
			pst.setInt(4,id);
			//try{
			int r = pst.executeUpdate();
			/*}catch(Exception e){System.out.println("Except ion");}			
			String sql="UPDATE employee SET ename='"+name+"' WHERE eid="+id;
			Statement pst=con.createStatement();
			int r=pst.executeUpdate(sql);
			*/

			//JOptionPane.showMessageDialog(new JDialog(),r+" records inserted");
			JOptionPane.showMessageDialog(new JDialog(),r+" record(s) updated");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(new JDialog(),"Update issue:\n"+e);
		}
	}

	public void deleteEmployee(int id)
	{
		getCon();

		try
		{
			String sql="DELETE FROM employee WHERE eid=?";
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setInt(1,id);
			int r=pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(),r+" Employee Deleted");
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(),"Delete issue:\n"+e);
		}
	}

}
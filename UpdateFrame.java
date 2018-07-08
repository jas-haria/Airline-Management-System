import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class UpdateFrame extends JFrame
{
	Container c;
	JButton btnSave,btnBack;
	JLabel lblId,lblName,lblDesignation,lblAge;
	JTextField txtId,txtName,txtDesignation,txtAge;
	JPanel p1,p2,p3;

	UpdateFrame()
	{
		c=getContentPane();
		
		p1=new JPanel();
		p2=new JPanel();
		lblId=new JLabel("Id:");
		txtId=new JTextField(5);
		lblName=new JLabel("Name:");
		txtName=new JTextField(10);
		lblDesignation=new JLabel("Designation:");
		txtDesignation=new JTextField(10);
		lblAge=new JLabel("Age:");
		txtAge=new JTextField(5);
		p1.add(lblId);
		p1.add(txtId);
		p1.add(lblName);
		p1.add(txtName);
		p2.add(lblDesignation);
		p2.add(txtDesignation);
		p2.add(lblAge);
		p2.add(txtAge);

		c.add(BorderLayout.NORTH,p1);
		c.add(BorderLayout.CENTER,p2);

		p3=new JPanel();
		btnSave=new JButton("Save");
		btnBack=new JButton("Back");
		p3.add(btnSave);
		p3.add(btnBack);
		c.add(BorderLayout.SOUTH,p3);

		btnSave.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					String i=txtId.getText();
					String n=txtName.getText();
					String de=txtDesignation.getText();
					String a=txtAge.getText();

					if(i.length()==0)
					{
						JOptionPane.showMessageDialog(new JDialog(),"id is empty");
						txtId.requestFocus();
						return;
					}

					if(n.length()==0)
					{
						JOptionPane.showMessageDialog(new JDialog(),"name is empty");
						txtName.requestFocus();
						return;
					}
					
					if(de.length()==0)
					{
						JOptionPane.showMessageDialog(new JDialog(),"designation is empty");
						txtDesignation.requestFocus();
						return;
					}
					
					if(a.length()==0)
					{
						JOptionPane.showMessageDialog(new JDialog(),"age is empty");
						txtAge.requestFocus();
						return;
					}
					
					new DatabaseHandler().updateEmployee(Integer.parseInt(i),n,de,Integer.parseInt(a));
					txtId.setText("");
					txtName.setText("");
					txtDesignation.setText("");
					txtAge.setText("");
				}
			});


		btnBack.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					MainFrame a= new MainFrame();
					dispose();
				}
			});

		setSize(500,150);
		setLocationRelativeTo(null);
		setTitle("Update Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
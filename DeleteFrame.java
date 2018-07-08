import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteFrame extends JFrame
{
	Container c;
	JButton btnSave,btnBack;
	JLabel lblId;
	JTextField txtId;
	JPanel p1,p2;

	DeleteFrame()
	{
		c=getContentPane();
		
		p1=new JPanel();
		lblId=new JLabel("Id:");
		txtId=new JTextField(5);
		p1.add(lblId);
		p1.add(txtId);
		c.add(p1);

		p2=new JPanel();
		btnSave=new JButton("Delete");
		btnBack=new JButton("Back");
		p2.add(btnSave);
		p2.add(btnBack);
		c.add(BorderLayout.SOUTH,p2);

		btnSave.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					String i=txtId.getText();
					

					if(i.length()==0)
					{
						JOptionPane.showMessageDialog(new JDialog(),"id is empty");
						txtId.requestFocus();
						return;
					}

					
					
					new DatabaseHandler().deleteEmployee(Integer.parseInt(i));
					txtId.setText("");
					
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
		setTitle("Delete Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
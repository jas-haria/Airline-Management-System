import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ViewFrame extends JFrame
{
	Container c;
	JTextArea taData;
	JScrollPane spData;
	JButton btnBack;
	JPanel p1,p2;

	ViewFrame()
	{
		c=getContentPane();
		
		p1=new JPanel();
		taData=new JTextArea(5,30);
		taData.setEditable(false);
		spData=new JScrollPane(taData);
		spData.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spData.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		p1.add(spData);
		c.add(p1);

		p2=new JPanel();
		btnBack=new JButton("Back");
		p2.add(btnBack);
		c.add(BorderLayout.SOUTH,p2);

		DatabaseHandler d=new DatabaseHandler();
		String data=d.viewEmployee();
		taData.setText(data);

		btnBack.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					MainFrame a= new MainFrame();
					dispose();
				}
			});

		setSize(500,200);
		setLocationRelativeTo(null);
		setTitle("View Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
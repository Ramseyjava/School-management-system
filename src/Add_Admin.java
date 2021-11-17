
package school;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Add_Admin extends Connection {

	private JFrame frmAddAdmin;
	private JTextField getuser;
	private JTextField getfirst;
	private JTextField getsecond;
	private JTextField getemail;
	private JPasswordField getpass;
	private JTextField showid;

	public Add_Admin() {
		initialize();
		frmAddAdmin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAddAdmin = new JFrame();
		frmAddAdmin.getContentPane().setBackground(Color.ORANGE);
		frmAddAdmin.setBackground(Color.GREEN);
		frmAddAdmin.setForeground(Color.BLACK);
		frmAddAdmin.setTitle("Add Admin");
		frmAddAdmin.setBounds(100, 100, 653, 487);
		frmAddAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddAdmin.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Admin Details:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(12, 59, 384, 379);
		frmAddAdmin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblFirstname = new JLabel("FIRSTNAME");
		lblFirstname.setBounds(26, 102, 95, 15);
		panel.add(lblFirstname);
		
		JLabel lblSecondname = new JLabel("SECONDNAME");
		lblSecondname.setBounds(12, 153, 109, 15);
		panel.add(lblSecondname);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(31, 64, 77, 15);
		panel.add(lblUsername);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(45, 213, 70, 15);
		panel.add(lblEmail);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(26, 268, 95, 28);
		panel.add(lblPassword);
		
		getuser = new JTextField();
		getuser.setFont(new Font("Dialog", Font.BOLD, 12));
		getuser.setBounds(126, 43, 246, 36);
		panel.add(getuser);
		getuser.setColumns(10);
		
		getfirst = new JTextField();
		getfirst.setFont(new Font("Dialog", Font.BOLD, 12));
		getfirst.setColumns(10);
		getfirst.setBounds(126, 92, 246, 36);
		panel.add(getfirst);
		
		getsecond = new JTextField();
		getsecond.setFont(new Font("Dialog", Font.BOLD, 12));
		getsecond.setColumns(10);
		getsecond.setBounds(126, 143, 246, 36);
		panel.add(getsecond);
		
		getemail = new JTextField();
		getemail.setFont(new Font("Dialog", Font.BOLD, 12));
		getemail.setColumns(10);
		getemail.setBounds(126, 203, 246, 36);
		panel.add(getemail);
		
		getpass = new JPasswordField();
		getpass.setFont(new Font("Dialog", Font.BOLD, 12));
		getpass.setBounds(126, 266, 246, 33);
		panel.add(getpass);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					insert();
					Statement stm = conn.createStatement();
					ResultSet rs = stm.executeQuery("select * from admins");
					while(rs.next()) {
						showid.setText(rs.getInt(6)+"");
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAdd.setForeground(Color.GREEN);
		btnAdd.setBackground(Color.BLACK);
		btnAdd.setBounds(223, 323, 90, 44);
		panel.add(btnAdd);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmAddAdmin.setVisible(false);
			}
		});
		btnExit.setBounds(506, 391, 117, 35);
		frmAddAdmin.getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("/home/franc/eclipse-workspace/School/icons/admins.jpeg"));
		lblNewLabel.setBounds(405, 149, 236, 243);
		frmAddAdmin.getContentPane().add(lblNewLabel);
		
		JLabel lblAddNewAdmin = new JLabel("ADD  NEW ADMIN");
		lblAddNewAdmin.setForeground(Color.BLUE);
		lblAddNewAdmin.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 21));
		lblAddNewAdmin.setBounds(414, 60, 227, 77);
		frmAddAdmin.getContentPane().add(lblAddNewAdmin);
		
		showid = new JTextField();
		showid.setForeground(Color.GREEN);
		showid.setBackground(Color.DARK_GRAY);
		showid.setBounds(468, 57, 114, 19);
		frmAddAdmin.getContentPane().add(showid);
		showid.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(491, 33, 70, 15);
		frmAddAdmin.getContentPane().add(lblId);
	}
	private void insert() throws ClassNotFoundException, SQLException {
		connect();
		pst = conn.prepareStatement("insert into admins(username,firstname,secondname,email,password) values(?,?,?,?,?)");
		pst.setString(1, getuser.getText());
		pst.setString(2, getfirst.getText());
		pst.setString(3, getsecond.getText());
		pst.setString(4, getemail.getText());
		pst.setString(5, getpass.getText());
		pst.execute();
		JOptionPane.showMessageDialog(null, "Admin added successfully!!");
		getuser.setText("");
		getfirst.setText("");
		getsecond.setText("");
		getemail.setText("");
		getpass.setText("");
		getuser.requestFocus();
	}
}

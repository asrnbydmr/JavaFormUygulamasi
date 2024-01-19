package formpackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import classpackage.studentClass;

public class studentForm
{
	public JFrame studentForm;
	public static boolean studentFormVisible = true;
	private JTextField txtStudentCode;
	private JTextField txtStudentName;
	private JTextField txtStudentSurname;
	private JTextField txtStudentSection;
	private JTextField txtSearchStudent;
	private JList<String> list;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					studentForm window = new studentForm();
					window.studentForm.setVisible(true);
					studentFormVisible = false;
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public studentForm()
	{
		initialize();
		studentClass.loadStudents(list);
	}
	
	private void initialize()
	{
		studentForm = new JFrame();
		studentForm.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosed(WindowEvent e)
			{
				studentFormVisible = true;
			}
		});
		studentForm.setTitle("Student Form");
		studentForm.setBounds(100, 100, 710, 500);
		studentForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		studentForm.getContentPane().setLayout(null);
		
		JLabel lblStudentCode = new JLabel("Student Code:");
		lblStudentCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentCode.setBounds(10, 11, 120, 20);
		studentForm.getContentPane().add(lblStudentCode);
		
		JLabel lblStudentName = new JLabel("Student Name:");
		lblStudentName.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentName.setBounds(350, 11, 120, 20);
		studentForm.getContentPane().add(lblStudentName);
		
		JLabel lblStudentSurname = new JLabel("Student Surname:");
		lblStudentSurname.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentSurname.setBounds(10, 42, 120, 20);
		studentForm.getContentPane().add(lblStudentSurname);
		
		JLabel lblStudentSection = new JLabel("Student Section:");
		lblStudentSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentSection.setBounds(350, 42, 120, 20);
		studentForm.getContentPane().add(lblStudentSection);
		
		JLabel lblStudentPeriod = new JLabel("Student Period:");
		lblStudentPeriod.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentPeriod.setBounds(10, 73, 120, 20);
		studentForm.getContentPane().add(lblStudentPeriod);
		
		JLabel lblStudentCourse = new JLabel("Student Course:");
		lblStudentCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentCourse.setBounds(350, 73, 120, 20);
		studentForm.getContentPane().add(lblStudentCourse);
		
		JLabel lblSearchStudent = new JLabel("Search Student:");
		lblSearchStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchStudent.setBounds(10, 149, 120, 20);
		studentForm.getContentPane().add(lblSearchStudent);
		
		txtStudentCode = new JTextField();
		txtStudentCode.setHorizontalAlignment(SwingConstants.CENTER);
		txtStudentCode.setColumns(10);
		txtStudentCode.setBounds(140, 11, 200, 20);
		studentForm.getContentPane().add(txtStudentCode);
		
		txtStudentName = new JTextField();
		txtStudentName.setHorizontalAlignment(SwingConstants.CENTER);
		txtStudentName.setColumns(10);
		txtStudentName.setBounds(480, 11, 200, 20);
		studentForm.getContentPane().add(txtStudentName);
		
		txtStudentSurname = new JTextField();
		txtStudentSurname.setHorizontalAlignment(SwingConstants.CENTER);
		txtStudentSurname.setColumns(10);
		txtStudentSurname.setBounds(140, 42, 200, 20);
		studentForm.getContentPane().add(txtStudentSurname);
		
		txtStudentSection = new JTextField();
		txtStudentSection.setHorizontalAlignment(SwingConstants.CENTER);
		txtStudentSection.setColumns(10);
		txtStudentSection.setBounds(480, 42, 200, 20);
		studentForm.getContentPane().add(txtStudentSection);
		
		JSpinner txtStudentPeriod = new JSpinner();
		txtStudentPeriod.setModel(new SpinnerNumberModel(0, 0, 8, 1));
		txtStudentPeriod.setBounds(140, 73, 200, 20);
		studentForm.getContentPane().add(txtStudentPeriod);
		
		JComboBox<String> comboStudentCourse = new JComboBox<>();
		comboStudentCourse.setMaximumRowCount(2);
		comboStudentCourse.setBounds(480, 72, 200, 22);
		studentForm.getContentPane().add(comboStudentCourse);
		
		comboStudentCourse.addItem("");
		studentClass.fillComboBox(comboStudentCourse);
		
		txtSearchStudent = new JTextField();
		txtSearchStudent.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				studentClass.searchStudents(list, txtSearchStudent.getText());
			}
		});
		txtSearchStudent.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearchStudent.setColumns(10);
		txtSearchStudent.setBounds(140, 149, 540, 20);
		studentForm.getContentPane().add(txtSearchStudent);
		
		JButton btnRefresh = new JButton("Refresh Course");
		btnRefresh.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				comboStudentCourse.removeAllItems();
				comboStudentCourse.addItem("");
				studentClass.fillComboBox(comboStudentCourse);
			}
		});
		btnRefresh.setBounds(140, 104, 200, 34);
		studentForm.getContentPane().add(btnRefresh);
		
		JButton btnSave = new JButton("SAVE STUDENT");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				studentClass obj = new studentClass();
				
				obj.studentCode = txtStudentCode.getText().toString();
				obj.studentName = txtStudentName.getText().toString();
				obj.studentSurname = txtStudentSurname.getText().toString();
				obj.studentSection = txtStudentSection.getText().toString();
				obj.studentPeriod = txtStudentPeriod.getValue().toString();
				obj.studentCourse = comboStudentCourse.getSelectedItem().toString();
				
				String result = studentClass.control(obj);
				JOptionPane.showMessageDialog(studentForm, result);
				
				if(result == "This student and course saved!")
				{
					txtStudentCode.setText(null);
					txtStudentName.setText(null);
					txtStudentSurname.setText(null);
					txtStudentSection.setText(null);
					txtStudentPeriod.setValue(0);
					comboStudentCourse.setSelectedIndex(0);
					
					studentClass.loadStudents(list);
				}
			}
		});
		btnSave.setBounds(480, 105, 200, 34);
		studentForm.getContentPane().add(btnSave);

		list = new JList<String>();
		list.setBounds(10, 180, 670, 270);
		studentForm.getContentPane().add(list);
	}
}

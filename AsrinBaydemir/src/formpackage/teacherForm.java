package formpackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import classpackage.teacherClass;

public class teacherForm
{
	public JFrame teacherForm;
	public static boolean teacherFormVisible = true;
	private JTextField txtTeacherCode;
	private JTextField txtTeacherName;
	private JTextField txtTeacherSurname;
	private JTextField txtTeacherSection;
	private JTextField txtSearchTeacher;
	private JList<String> list;
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					teacherForm window = new teacherForm();
					window.teacherForm.setVisible(true);
					teacherFormVisible = false;
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public teacherForm()
	{
		initialize();
		teacherClass.loadTeachers(list);
	}
	
	private void initialize()
	{
		teacherForm = new JFrame();
		teacherForm.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosed(WindowEvent e)
			{
				teacherFormVisible = true;
			}
		});
		teacherForm.setTitle("Teacher Form");
		teacherForm.setBounds(100, 100, 710, 500);
		teacherForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		teacherForm.getContentPane().setLayout(null);
		
		JLabel lblTeacherCode = new JLabel("Teacher Code:");
		lblTeacherCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherCode.setBounds(10, 11, 120, 20);
		teacherForm.getContentPane().add(lblTeacherCode);
		
		JLabel lblTeacherName = new JLabel("Teacher Name:");
		lblTeacherName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherName.setBounds(350, 11, 120, 20);
		teacherForm.getContentPane().add(lblTeacherName);
		
		JLabel lblTeacherSurname = new JLabel("Teacher Surname:");
		lblTeacherSurname.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherSurname.setBounds(10, 42, 120, 20);
		teacherForm.getContentPane().add(lblTeacherSurname);
		
		JLabel lblTeacherSection = new JLabel("Teacher Section:");
		lblTeacherSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherSection.setBounds(350, 42, 120, 20);
		teacherForm.getContentPane().add(lblTeacherSection);
		
		JLabel lblTeacherCourse = new JLabel("Teacher Course:");
		lblTeacherCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacherCourse.setBounds(350, 73, 120, 20);
		teacherForm.getContentPane().add(lblTeacherCourse);
		
		JLabel lblSearchTeacher = new JLabel("Search Teacher:");
		lblSearchTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchTeacher.setBounds(10, 149, 120, 20);
		teacherForm.getContentPane().add(lblSearchTeacher);
		
		txtTeacherCode = new JTextField();
		txtTeacherCode.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeacherCode.setColumns(10);
		txtTeacherCode.setBounds(140, 11, 200, 20);
		teacherForm.getContentPane().add(txtTeacherCode);
		
		txtTeacherName = new JTextField();
		txtTeacherName.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeacherName.setColumns(10);
		txtTeacherName.setBounds(480, 11, 200, 20);
		teacherForm.getContentPane().add(txtTeacherName);
		
		txtTeacherSurname = new JTextField();
		txtTeacherSurname.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeacherSurname.setColumns(10);
		txtTeacherSurname.setBounds(140, 42, 200, 20);
		teacherForm.getContentPane().add(txtTeacherSurname);
		
		txtTeacherSection = new JTextField();
		txtTeacherSection.setHorizontalAlignment(SwingConstants.CENTER);
		txtTeacherSection.setColumns(10);
		txtTeacherSection.setBounds(480, 41, 200, 20);
		teacherForm.getContentPane().add(txtTeacherSection);
		
		JComboBox<String> comboTeacherCourse = new JComboBox<>();
		comboTeacherCourse.setMaximumRowCount(2);
		comboTeacherCourse.setBounds(480, 71, 200, 22);
		teacherForm.getContentPane().add(comboTeacherCourse);
		
		comboTeacherCourse.addItem("");
		teacherClass.fillComboBox(comboTeacherCourse);
		
		txtSearchTeacher = new JTextField();
		txtSearchTeacher.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				teacherClass.searchTeachers(list, txtSearchTeacher.getText());
			}
		});
		txtSearchTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearchTeacher.setColumns(10);
		txtSearchTeacher.setBounds(140, 149, 540, 20);
		teacherForm.getContentPane().add(txtSearchTeacher);
		
		JButton btnRefresh = new JButton("Refresh Course");
		btnRefresh.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				comboTeacherCourse.removeAllItems();
				comboTeacherCourse.addItem("");
				teacherClass.fillComboBox(comboTeacherCourse);
			}
		});
		btnRefresh.setBounds(140, 104, 200, 34);
		teacherForm.getContentPane().add(btnRefresh);
		
		JButton btnSave = new JButton("SAVE TEACHER");
		btnSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				teacherClass obj = new teacherClass();
				
				obj.teacherCode = txtTeacherCode.getText().toString();
				obj.teacherName = txtTeacherName.getText().toString();
				obj.teacherSurname = txtTeacherSurname.getText().toString();
				obj.teacherSection = txtTeacherSection.getText().toString();
				obj.teacherCourse = comboTeacherCourse.getSelectedItem().toString();
				
				String result = teacherClass.control(obj);
				JOptionPane.showMessageDialog(teacherForm, result);
				
				if(result == "This teacher and course saved!")
				{
					txtTeacherCode.setText(null);
					txtTeacherName.setText(null);
					txtTeacherSurname.setText(null);
					txtTeacherSection.setText(null);
					comboTeacherCourse.setSelectedIndex(0);
					
					teacherClass.loadTeachers(list);
				}
			}
		});
		btnSave.setBounds(480, 104, 200, 34);
		teacherForm.getContentPane().add(btnSave);

		list = new JList<String>();
		list.setBounds(10, 180, 670, 270);
		teacherForm.getContentPane().add(list);
	}
}

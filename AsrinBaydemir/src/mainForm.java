import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import apppackage.settingsClass;
import formpackage.courseForm;
import formpackage.studentForm;
import formpackage.teacherForm;

public class mainForm
{
	private JFrame mainForm;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					mainForm window = new mainForm();
					window.mainForm.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public mainForm()
	{
		initialize();
	}
	
	private void initialize()
	{
		mainForm = new JFrame();
		mainForm.setTitle("Menu");
		mainForm.setBounds(100, 100, 400, 80);
		mainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainForm.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCourse = new JButton("Course Form");
		btnCourse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(courseForm.courseFormVisible)
				{
					courseForm.main(null);
				}
			}
		});
		mainForm.getContentPane().add(btnCourse);
		
		JButton btnStudent = new JButton("Student Form");
		btnStudent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(studentForm.studentFormVisible)
				{
					if (settingsClass.openStudentForm())
					{
						studentForm.main(null);
					}
					else
					{
						String error = "Please add a course!";
						JOptionPane.showMessageDialog(mainForm, error);
					}
				}
			}
		});
		mainForm.getContentPane().add(btnStudent);
		
		JButton btnTeacher = new JButton("Teacher Form");
		btnTeacher.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(teacherForm.teacherFormVisible)
				{
					if (settingsClass.openTeacherForm())
					{
						teacherForm.main(null);
					}
					else
					{
						String error = "Please add a course!";
						JOptionPane.showMessageDialog(mainForm, error);
					}
				}
			}
		});
		mainForm.getContentPane().add(btnTeacher);
	}
}

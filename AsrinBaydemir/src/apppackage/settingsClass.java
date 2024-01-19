package apppackage;

import classpackage.jsonClass;

public class settingsClass
{
	private static boolean studentFormEnabled = false;
	private static boolean teacherFormEnabled = false;
	
	public static boolean openStudentForm()
	{
		if (jsonClass.fileExists(apppackage.filenameClass.fileNameCourses))
		{
			studentFormEnabled = true;
		}
		else
		{
			studentFormEnabled = false;
		}
		
		return studentFormEnabled;
	}
	
	
	public static boolean openTeacherForm()
	{
		if (jsonClass.fileExists(apppackage.filenameClass.fileNameCourses))
		{
			teacherFormEnabled = true;
		}
		else
		{
			teacherFormEnabled = false;
		}
		
		return teacherFormEnabled;
	}
}

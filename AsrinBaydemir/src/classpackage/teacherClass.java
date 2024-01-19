package classpackage;

import java.io.*;
import java.util.*;
import javax.swing.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import apppackage.filenameClass;

public class teacherClass
{
	public String teacherCode;
	public String teacherName;
	public String teacherSurname;
	public String teacherSection;
	public String teacherCourse;
	
	private static List<String> teachers;
	
	private static boolean teacherExist(JSONArray teacherArray, String teacherCode, String courseCode)
	{
		for (Object teacherObject : teacherArray)
		{
	        if (teacherObject instanceof JSONObject)
	        {
	            JSONObject jsonObject = (JSONObject) teacherObject;
	            
	            String existingteacherCode = (String) jsonObject.get("teacherCode");
	            String existingcourseCode = (String) jsonObject.get("teacherCourse");
	            
	            if ((existingteacherCode != null && existingteacherCode.equals(teacherCode)) && (existingcourseCode != null && existingcourseCode.equals(courseCode)))
	            {
	                return true;
	            }
	        }
		}
	    
	    return false;
	}
	
	private static String addTeacher(teacherClass obj)
	{
        try
        {
        	JSONArray teacherArray;
        	
            if (jsonClass.fileExists(filenameClass.fileNameTeachers))
            {
            	teacherArray = jsonClass.readFile(filenameClass.fileNameTeachers);
                
                if (teacherExist(teacherArray, obj.teacherCode, obj.teacherCourse))
                {
                    return "This teacher code and course code already exists!";
                }
            }
            
            else
            {
            	teacherArray = new JSONArray();
            }
            
            HashMap<String, String> teacherObject = new HashMap<>();
            
            teacherObject.put("teacherCode", obj.teacherCode);
            teacherObject.put("teacherName", obj.teacherName);
            teacherObject.put("teacherSurname", obj.teacherSurname);
            teacherObject.put("teacherSection", obj.teacherSection);
            teacherObject.put("teacherCourse", obj.teacherCourse);
            
            teacherArray.add(teacherObject);
            
            jsonClass.writeFile(filenameClass.fileNameTeachers, teacherArray);
            
        	return "This teacher and course saved!";
        }
        catch (IOException | ParseException e)
        {
        	e.printStackTrace();
        	
            return "An error occurred!";
        }
    }
	
	public static String control(teacherClass obj)
	{
		if(obj.teacherCode.isEmpty())
		{
			return "Do not leave the 'Teacher Code' field blank!";
		}
		
		else if (obj.teacherName.isEmpty())
		{
			return "Do not leave the 'Teacher Name' field blank!";
		}
		
		else if (obj.teacherSurname.isEmpty())
		{
			return "Do not leave the 'Teacher Surname' field blank!";
		}
		
		else if (obj.teacherSection.isEmpty())
		{
			return "Do not leave the 'Teacher Section' field blank!";
		}
		
		else if (obj.teacherCourse.isEmpty())
		{
			return "Do not leave the 'Teacher Course' field blank!";
		}
		
		else
		{
			return addTeacher(obj);
		}
	}
	
	public static String fillComboBox(JComboBox<String> obj)
	{
		try
		{
			JSONArray courses = jsonClass.readFile(filenameClass.fileNameCourses);
			
			for (Object courseObject : courses)
			{
				if (courseObject instanceof JSONObject)
		        {
					JSONObject item = (JSONObject) courseObject;
			        String courseName = (String) item.get("courseName");
			        obj.addItem(courseName);
		        }
		    }
		}
		catch(Exception e)
		{
			return e.toString();
		}
		
		return "Successful";
	}
	
	public static void loadTeachers(JList<String> list)
	{
		if (jsonClass.fileExists(filenameClass.fileNameTeachers))
        {
			teachers = new ArrayList<>();
			
			try
			{
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(new FileReader(filenameClass.fileNameTeachers));
				
				JSONArray jsonArray = (JSONArray) obj;
				
				for (Object teachersObj : jsonArray)
				{
					JSONObject teacher = (JSONObject) teachersObj;
					String teacherCode = (String) teacher.get("teacherCode");
					String teacherName = (String) teacher.get("teacherName");
					String teacherSurname = (String) teacher.get("teacherSurname");
					String teacherSection = (String) teacher.get("teacherSection");
					String teacherCourse = (String) teacher.get("teacherCourse");
					teachers.add(teacherCode + " - " + teacherName + " - " + teacherSurname + " - " + teacherSection + " - " + teacherCourse);
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			list.setListData(teachers.toArray(new String[0]));			
        }
	}
	
	public static void searchTeachers(JList<String> list, String searchText)
	{
		if (jsonClass.fileExists(filenameClass.fileNameTeachers))
        {
			List<String> filteredTeachers = new ArrayList<>();
			
			for (int counter = 0; counter < teachers.size(); counter++)
			{
				String teacher = teachers.get(counter);
				
				if(teacher.contains(searchText))
				{
					filteredTeachers.add(teacher);
				}
			}
			
			list.setListData(filteredTeachers.toArray(new String[0]));
        }
	}
}

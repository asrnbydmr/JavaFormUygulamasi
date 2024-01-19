package classpackage;

import java.io.*;
import java.util.*;
import javax.swing.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import apppackage.filenameClass;

public class studentClass
{
	public String studentCode;
	public String studentName;
	public String studentSurname;
	public String studentSection;
	public String studentPeriod;
	public String studentCourse;
	
	private static List<String> students;
	
	private static boolean studentExist(JSONArray studentArray, String studentCode, String courseCode)
	{
		for (Object studentObject : studentArray)
		{
	        if (studentObject instanceof JSONObject)
	        {
	            JSONObject jsonObject = (JSONObject) studentObject;
	            
	            String existingstudentCode = (String) jsonObject.get("studentCode");
	            String existingcourseCode = (String) jsonObject.get("studentCourse");
	            
	            if ((existingstudentCode != null && existingstudentCode.equals(studentCode)) && (existingcourseCode != null && existingcourseCode.equals(courseCode)))
	            {
	                return true;
	            }
	        }
		}
	    
	    return false;
	}
	
	private static String addStudent(studentClass obj)
	{
        try
        {
        	JSONArray studentArray;
        	
            if (jsonClass.fileExists(filenameClass.fileNameStudents))
            {
            	studentArray = jsonClass.readFile(filenameClass.fileNameStudents);
                
                if (studentExist(studentArray, obj.studentCode, obj.studentCourse))
                {
                    return "This student code and course code already exists!";
                }
            }
            
            else
            {
            	studentArray = new JSONArray();
            }
            
            HashMap<String, String> studentObject = new HashMap<>();
            
            studentObject.put("studentCode", obj.studentCode);
            studentObject.put("studentName", obj.studentName);
            studentObject.put("studentSurname", obj.studentSurname);
            studentObject.put("studentSection", obj.studentSection);
            studentObject.put("studentPeriod", obj.studentPeriod);
            studentObject.put("studentCourse", obj.studentCourse);
            
            studentArray.add(studentObject);
            
            jsonClass.writeFile(filenameClass.fileNameStudents, studentArray);
            
        	return "This student and course saved!";
        }
        catch (IOException | ParseException e)
        {
        	e.printStackTrace();
        	
            return "An error occurred!";
        }
    }
	
	public static String control(studentClass obj)
	{
		if(obj.studentCode.isEmpty())
		{
			return "Do not leave the 'Student Code' field blank!";
		}
		
		else if (obj.studentName.isEmpty())
		{
			return "Do not leave the 'Student Name' field blank!";
		}
		
		else if (obj.studentSurname.isEmpty())
		{
			return "Do not leave the 'Student Surname' field blank!";
		}
		
		else if (obj.studentSection.isEmpty())
		{
			return "Do not leave the 'Student Section' field blank!";
		}
		
		else if (Integer.parseInt(obj.studentPeriod) == 0)
		{
			return "Do not leave the 'Student Period' field blank!";
		}
		
		else if (obj.studentCourse.isEmpty())
		{
			return "Do not leave the 'Student Course' field blank!";
		}
		
		else
		{
			return addStudent(obj);
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
	
	public static void loadStudents(JList<String> list)
	{
		if (jsonClass.fileExists(filenameClass.fileNameStudents))
        {
			students = new ArrayList<>();
			
			try
			{
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(new FileReader(filenameClass.fileNameStudents));
				
				JSONArray jsonArray = (JSONArray) obj;
				
				for (Object studentObj : jsonArray)
				{
					JSONObject student = (JSONObject) studentObj;
					String studentCode = (String) student.get("studentCode");
					String studentName = (String) student.get("studentName");
					String studentSurname = (String) student.get("studentSurname");
					String studentSection = (String) student.get("studentSection");
					String studentPeriod = (String) student.get("studentPeriod");
					String studentCourse = (String) student.get("studentCourse");
					students.add(studentCode + " - " + studentName + " - " + studentSurname + " - " + studentSection + " - " + studentPeriod + " - " + studentCourse);
				}
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
			list.setListData(students.toArray(new String[0]));			
        }
	}
	
	public static void searchStudents(JList<String> list, String searchText)
	{
		if (jsonClass.fileExists(filenameClass.fileNameStudents))
        {
			List<String> filteredStudents = new ArrayList<>();
			
			for (int counter = 0; counter < students.size(); counter++)
			{
				String student = students.get(counter);
				
				if(student.contains(searchText))
				{
					filteredStudents.add(student);
				}
			}
			
			list.setListData(filteredStudents.toArray(new String[0]));
        }
	}
}

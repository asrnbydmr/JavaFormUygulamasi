package classpackage;

import java.io.*;
import java.util.*;
import javax.swing.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import apppackage.filenameClass;

public class courseClass
{
	public String courseCode;
	public String courseName;
	public String coursePeriod;
	public String courseCredit;
	public String courseQuota;
	
	private static List<String> courses;
	
	private static boolean courseExist(JSONArray courseArray, String courseCode)
	{
		for (Object courseObject : courseArray)
		{
	        if (courseObject instanceof JSONObject)
	        {
	            JSONObject jsonObject = (JSONObject) courseObject;
	            
	            String existingCode = (String) jsonObject.get("courseCode");
	            
	            if (existingCode != null && existingCode.equals(courseCode))
	            {
	                return true;
	            }
	        }
		}
	    
	    return false;
	}
	
	private static String addCourse(courseClass obj)
	{
        try
        {
        	JSONArray courseArray;
        	
            if (jsonClass.fileExists(filenameClass.fileNameCourses))
            {
                courseArray = jsonClass.readFile(filenameClass.fileNameCourses);
                
                if (courseExist(courseArray, obj.courseCode))
                {
                    return "This course code already exists!";
                }
            }
            
            else
            {
                courseArray = new JSONArray();
            }
            
            HashMap<String, String> courseObject = new HashMap<>();
            
            courseObject.put("courseCode", obj.courseCode);
            courseObject.put("courseName", obj.courseName);
            courseObject.put("coursePeriod", obj.coursePeriod);
            courseObject.put("courseCredit", obj.courseCredit);
            courseObject.put("courseQuota", obj.courseQuota);
            
            courseArray.add(courseObject);
            
            jsonClass.writeFile(filenameClass.fileNameCourses, courseArray);
            
        	return "This course saved!";
        }
        catch (IOException | ParseException e)
        {
        	e.printStackTrace();
        	
            return "An error occurred!";
        }
    }
	
	public static String control(courseClass obj)
	{
		if(obj.courseCode.isEmpty())
		{
			return "Do not leave the 'Course Code' field blank!";
		}
		
		else if (obj.courseName.isEmpty())
		{
			return "Do not leave the 'Course Name' field blank!";
		}
		
		else if (Integer.parseInt(obj.coursePeriod) == 0)
		{
			return "Do not leave the 'Course Period' field blank!";
		}
		
		else if (Integer.parseInt(obj.courseCredit) == 0)
		{
			return "Do not leave the 'Course Credit' field blank!";
		}
		
		else if (Integer.parseInt(obj.courseQuota) == 0)
		{
			return "Do not leave the 'Course Quota' field blank!";
		}
		
		else
		{
			return addCourse(obj);
		}
	}

	public static void loadCourses(JList<String> list)
	{
		if (jsonClass.fileExists(filenameClass.fileNameCourses))
        {
			courses = new ArrayList<>();
			
	        try
	        {
	            JSONParser parser = new JSONParser();
	            Object obj = parser.parse(new FileReader(filenameClass.fileNameCourses));

	            JSONArray jsonArray = (JSONArray) obj;

	            for (Object courseObj : jsonArray)
	            {
	                JSONObject course = (JSONObject) courseObj;
	                String courseCode = (String) course.get("courseCode");
	                String courseName = (String) course.get("courseName");
	                String coursePeriod = (String) course.get("coursePeriod");
	                String courseCredit = (String) course.get("courseCredit");
	                String courseQuota = (String) course.get("courseQuota");
	                courses.add(courseCode + " - " + courseName + " - " + coursePeriod + " - " + courseCredit + " - " + courseQuota);
	            }

	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	        list.setListData(courses.toArray(new String[0]));
        }
	}
	
	public static void searchCourses(JList<String> list, String searchText)
	{
		if (jsonClass.fileExists(filenameClass.fileNameCourses))
        {
	        List<String> filteredCourses = new ArrayList<>();
	        
	        for (int counter = 0; counter < courses.size(); counter++)
	        {
	        	String course = courses.get(counter);
	        	
	        	if(course.contains(searchText))
	        	{
	        		filteredCourses.add(course);
	        	}
	        }
	
	        list.setListData(filteredCourses.toArray(new String[0]));
        }
	}
}

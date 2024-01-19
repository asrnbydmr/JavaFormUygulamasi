package classpackage;

import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class jsonClass
{
	public static boolean fileExists(String fileName)
	{
        return new File(fileName).exists();
	}
	
	public static JSONArray readFile(String fileName) throws IOException, ParseException
	{
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader reader = new FileReader(fileName))
		{
			return (JSONArray) jsonParser.parse(reader);
		}
	}
	
	public static void writeFile(String fileName, JSONArray datas) throws IOException
	{
	    try (FileWriter fileWriter = new FileWriter(fileName))
	    {
	        fileWriter.write(datas.toJSONString());
	    }
	}
}

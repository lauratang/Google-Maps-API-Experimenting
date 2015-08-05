import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
 
public class main {
	
	private ArrayList<CityObj> guideAL;
	private ArrayList<CityObj> outputAL;
 
  public static void main(String[] args) {
 
	main obj = new main();
	obj.run();
 
  }
 
  public void run() {
 
	guideAL = new ArrayList<CityObj>();
	outputAL = new ArrayList<CityObj>();
	
	String inputFile = "/Users/ltang201/Desktop/input.csv";
	String guideFile = "/Users/ltang201/Desktop/guide.csv";
	String writeFile = "/Users/ltang201/Desktop/output.csv";
	
	BufferedReader inputReader = null;
	BufferedReader guideReader = null;
	FileWriter writer = null;
	
	String line = "";
	String cvsSplitBy = ",";
	StringBuilder lat = new StringBuilder();
	StringBuilder lng = new StringBuilder();
	
	try {

	    writer = new FileWriter(writeFile);
		inputReader = new BufferedReader(new FileReader(inputFile));
		inputReader.readLine();
		inputReader.readLine();
		guideReader = new BufferedReader(new FileReader(guideFile));
		
		while ((line = guideReader.readLine()) != null) {
			String[] temp = line.split(cvsSplitBy);
			guideAL.add(new CityObj(temp[0].trim(),temp[1].trim(),temp[2].trim()));
		}
		
		writer.append("City,LATITUDE,LONGITUDE,Programmer,count,Division,corp_sysprin\n");
		
		while ((line = inputReader.readLine()) != null) {
			
			String[] temp = line.split(cvsSplitBy);
			String city = temp[6].replace("Infinity","").trim();
			String Division = temp[5] + ",";
			String corp = temp[7];
			String prog = temp[2] + ",";
			String count = temp[3] + ",";
			for (CityObj x: guideAL) {
				if (x.getName().equals(city)) {
					lat.setLength(0);
					lng.setLength(0);
					lat.append(x.getLat() + ",");
					lng.append(x.getLng() + ",");
				}
			}
			writer.append(city);
			writer.append(",");
			writer.append(lat.toString()+lng.toString()+prog+count+Division+corp);
			writer.append("\n");
			lat.setLength(0);
			lng.setLength(0);
			
		}
		
		writer.flush();
	    writer.close();
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (guideReader != null || inputReader != null) {
			try {
				guideReader.close();
				inputReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	System.out.println("Done");
  }

}

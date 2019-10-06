package dulce.PixelFighter.logs;

import java.io.*;
import java.text.*;
import java.util.Date;

import dulce.PixelFighter.logs.Error;
import dulce.PixelFighter.main.*;

public class GameLog {
	
	private static FileWriter file;
	private static Timer time;
	
	public static void createLog() {
		
		try{
			
			time = new Timer(true);
			
			DateFormat format = new SimpleDateFormat("MM-dd HH-mm");
			Date date = new Date();
			String location = ("C:/Users/" + System.getProperty("user.name").toString() + "/AppData/Roaming/PixelFighter/Game Logs/PF Game log:"
					           + format.format(date));
			file = new FileWriter(location + ".txt");
			
			DateFormat format2 = new SimpleDateFormat("20YY/MM/dd");
			DateFormat format3 = new SimpleDateFormat("hh:mm");
		
			file.write("Pixel Fighter Game Log for: " + format2.format(date) + " at " + format3.format(date));
			file.write(System.lineSeparator());
			file.write("User: " + System.getProperty("user.name"));
			file.write(System.lineSeparator());
			file.write("Java version on computer: " + System.getProperty("Java.version"));
			file.write(System.lineSeparator());
			file.write(System.getProperty("os.name") + " version:" + System.getProperty("os.version"));
			file.write(System.lineSeparator());
			file.write("*****************************************************************************");
			file.write(System.lineSeparator());
			file.write("START OF LOG");
			file.write(System.lineSeparator());
		
		}catch(Exception e) {
			e.printStackTrace();
			Main.end();
		}
	}
	
	public static void writeLog(String message) {
		try{
			int[] Time = time.runGet();
			
			file.write("Runtime: " + String.format("%02d", Time[0]) + "h:" + String.format("%20d", Time[1]) + "min:" + 
						String.format("%20d", Time[2]) + "hr |" + message);
			file.write(System.lineSeparator());
		}catch (IOException e) {
		}
	}
	
	protected static void crashLog(int logNumber ,String message) {
		try{			
			writeLog("CRASH");
			file.write(System.lineSeparator());
			file.write("---------------CRASH INFO---------------");
			file.write(System.lineSeparator());
			file.write("Error message: " + message);
			file.write(System.lineSeparator());
			file.write("in Log: " + logNumber);
			file.write(System.lineSeparator());
			file.write("END Of CRASH INFO");
			file.write(System.lineSeparator());
			file.write("----------------------------------------");
			file.write(System.lineSeparator());
		}catch(IOException e) {
			return;
		}
	}
	
	protected static void crashLog(int logNumber ,String message, String message2) {
		try{			
			writeLog("CRASH");
			file.write(System.lineSeparator());
			file.write("---------------CRASH INFO---------------");
			file.write(System.lineSeparator());
			file.write("Error message: " + message);
			file.write(System.lineSeparator());
			file.write(message2);
			file.write(System.lineSeparator());
			file.write("in Log: " + logNumber);
			file.write(System.lineSeparator());
			file.write("END Of CRASH INFO");
			file.write(System.lineSeparator());
			file.write("----------------------------------------");
			file.write(System.lineSeparator());
		}catch(IOException e) {
			return;
		}
	}
	
	protected static void crashLog(StackTraceElement[] stacktraceLog, StackTraceElement[] stacktrace, String message, String message2) {
		try{			
			writeLog("CRASH");
			file.write(System.lineSeparator());
			file.write("---------------CRASH INFO---------------");
			file.write(System.lineSeparator());
			file.write("Error message: " + message);
			file.write(System.lineSeparator());
			if(message2 != null){
				file.write(message2);
				file.write(System.lineSeparator());
			}
			file.write("in Log: " + "Log cannot be created");
			file.write(System.lineSeparator());
			file.write("Log info");
			file.write(System.lineSeparator());
			if(stacktraceLog != null){
				for(StackTraceElement trace : stacktraceLog) {
				file.write(trace.toString());
				file.write(System.lineSeparator());
				}
			}
			
			if(stacktrace != null){
				for(StackTraceElement trace : stacktrace) {
				file.write(trace.toString());
				file.write(System.lineSeparator());
				}
			}
			file.write("END Of CRASH INFO");
			file.write(System.lineSeparator());
			file.write("----------------------------------------");
			file.write(System.lineSeparator());
		}catch(IOException e) {
			return;
		}
	}
	
	public static void endLog() {
		try{
			file.write("END OF LOG");
			file.write(System.lineSeparator());
			file.write("*****************************************************************************");
			file.write(System.lineSeparator());
			
			int[] Time = time.runGet();
			DateFormat format = new SimpleDateFormat("hh:mm:ss");
			Date date = new Date();
			
			file.write("Log closed normally at RunTime: " + String.format("%02d", Time[0]) + "h:" + String.format("%20d", Time[1]) + "min:" + 
						String.format("%20d", Time[2]) + "hr, at System Time of: " + format.format(date));
			file.write(System.lineSeparator());
			file.close();
		}catch(IOException e) {
			return;
		}
	}
}

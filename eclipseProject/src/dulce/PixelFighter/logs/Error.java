package dulce.PixelFighter.logs;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import dulce.PixelFighter.main.*;
import static dulce.PixelFighter.logs.GameLog.*;

public class Error {
	
	private static StackTraceElement[] stack;
	
	public static void crashReport(String message, StackTraceElement[] stacktrace, String GameOpForError) {
		
		JFrame fr = new JFrame("Pixel Fighter error");
		JLabel la = new JLabel("");
		JLabel la2 = new JLabel("");
		JLabel la3 = new JLabel("");
		JLabel la4 = new JLabel("");
		JLabel la5 = new JLabel("");
		JPanel pa = new JPanel();
		String report = "if this error keeps comming up, please report with the";
		String report2 = "error message and error location above";
		String report3 = "see Game Log for more info";
		int logNumber;
		
		GroupLayout layout = new GroupLayout(pa);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		
		
		logNumber = RandomGen.intGen(99999, 1);
		
		la.setText(message);
		
		if(stacktrace != null) {
			if(!createLog(stacktrace, GameOpForError, logNumber)) {
				la2.setText("Log not availble due to an error for an unknown reason");
				logNumber = -5;
			}
			else {
				la2.setText("see Log: " + logNumber + " for more information");
			}
		}
		else {
			la2.setText("No Log availble");
		}
		
		if(!(logNumber < 0))
			crashLog(stack, stacktrace,message , null);
		else
			crashLog(logNumber, message);
		
		Main.end();
		
		la3.setText(report);
		la4.setText(report2);
		la5.setText(report3);
		fr.setLocationRelativeTo(null);
		fr.setSize(500, 150);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addComponent(la)
					.addComponent(la2)
					.addComponent(la3)
					.addComponent(la4)
					.addComponent(la5)
				);
		
		layout.setVerticalGroup(
				layout.createParallelGroup()
					.addComponent(la)
					.addComponent(la2)
					.addComponent(la3)
					.addComponent(la4)
					.addComponent(la5)
				);
		
		pa.add(la, layout);
		pa.add(la2, layout);
		pa.add(la3, layout);
		pa.add(la4, layout);
		pa.add(la5, layout);
		
		fr.add(pa);
		
		fr.setResizable(false);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void crashReport(String message, String message2, StackTraceElement[] stacktrace, String GameOpForError) {
		
		JFrame fr = new JFrame("Pixel Fighter error");
		JLabel la = new JLabel("");
		JLabel la2 = new JLabel("");
		JLabel la3 = new JLabel("");
		JLabel la4 = new JLabel("");
		JLabel la5 = new JLabel("");
		JLabel extra = new JLabel("");
		JPanel pa = new JPanel();
		String report = "if this error keeps comming up, please report with the";
		String report2 = "error message and error location above";
		String report3 = "see Game Log for more info";
		int logNumber;
		
		
		
		GroupLayout layout = new GroupLayout(pa);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		
		logNumber = RandomGen.intGen(99999, 1);

		la.setText(message);
		
		if(stacktrace != null) {
			if(!createLog(stacktrace, GameOpForError, logNumber)) {
				la2.setText("Log not availble due to an error for an unknown reason");
			}
			else {
				la2.setText("see Log: " + logNumber + " for more information");
				logNumber = -5;
			}
		}
		else {
			la2.setText("No Log availble");
		}
		
		if(!(logNumber < 0))
			crashLog(stack, stacktrace,message, message2);
		else
			crashLog(logNumber, message, message2);
			
		Main.end();
		
		la3.setText(report);
		la4.setText(report2);
		la5.setText(report3);
		extra.setText(message2);
		fr.setLocationRelativeTo(null);
		fr.setSize(500, 150);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
					.addComponent(la)
					.addComponent(la2)
					.addComponent(la3)
					.addComponent(la4)
					.addComponent(la5)
					.addComponent(extra)
				);
		
		layout.setVerticalGroup(
				layout.createParallelGroup()
					.addComponent(la)
					.addComponent(la2)
					.addComponent(la3)
					.addComponent(la4)
					.addComponent(la5)
					.addComponent(extra)
				);
		
		pa.add(la, layout);
		pa.add(la2, layout);
		pa.add(la3, layout);
		pa.add(la4, layout);
		pa.add(la5, layout);
		pa.add(extra, layout);
		
		fr.add(pa);
		
		fr.setResizable(false);
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static boolean createLog(StackTraceElement[] stacktrace, String GameOp, int logNumber) {
		String location = "C:/Users/" + System.getProperty("user.name") + "/AppData/Roaming/PixelFighter/Error Logs/PF Error log:";
		try{
			DateFormat date = new SimpleDateFormat("MM-dd HH-mm");
			Date dateObj = new Date();
			
			FileWriter file = new FileWriter(location + logNumber + " " + date.format(dateObj) + ".txt");
			
			file.write("Pixelfighter experienced a fatal Error,");
			file.write(" Stacktrace Below");
			file.write(System.lineSeparator());
			file.write("*******************************************************");
			file.write(System.lineSeparator());
			
			for(StackTraceElement trace : stacktrace) {
				file.write(trace.toString());
				file.write(System.lineSeparator());
			}
			
			file.write("*******************************************************");
			file.write(System.lineSeparator());
			file.write("Game Operation that caused error: [");
			file.write(GameOp + "]");
			
			file.close();
		}catch (IOException e) {
			stack =e.getStackTrace();
			System.out.println("failed to create error log");
			return false;
		}
		return true;
	}
	
}

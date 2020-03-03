
package crunchify.com.tutorials;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
/**
 * @author Crunchify.com
 * Execute Linux commands using Java. We are executing mkdir, ls -ltra and ping in this tutorial
 */
 
public class CrunchifyCommandJava {
	public printOutput getStreamWrapper(InputStream is, String type) {
		return new printOutput(is, type);
	}
 
	public static void main(String[] args) {
 
		Runtime rt = Runtime.getRuntime();
		CrunchifyCommandJava rte = new CrunchifyCommandJava();
		printOutput errorReported, outputMessage;
 
		try {
			Process proc = rt.exec("mkdir /Users/fschorles/Desktop/new-folder");
			errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
			outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
		try {
			Process proc = rt.exec("ls -ltra /Library");
			errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
			outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		try {
			Process proc = rt.exec("ping google.com");
			errorReported = rte.getStreamWrapper(proc.getErrorStream(), "ERROR");
			outputMessage = rte.getStreamWrapper(proc.getInputStream(), "OUTPUT");
			errorReported.start();
			outputMessage.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
 
	private class printOutput extends Thread {
		InputStream is = null;
 
		printOutput(InputStream is, String type) {
			this.is = is;
		}
 
		public void run() {
			String s = null;
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(is));
				while ((s = br.readLine()) != null) {
					System.out.println(s);
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}
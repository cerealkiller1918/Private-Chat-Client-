package com.killermediagroup.main;



import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;


public class BackBones {
	
	private Socket socket;
	private OutputStream outputStream;
	private InputStream inputStream;
	private String host = "localhost";
	private int port = 5000;
	private Window window;
	private String userName;
	
	public BackBones(String userName){
			this.userName = userName;
		try {
			socket = new Socket(host, port);
			outputStream = socket.getOutputStream();
			inputStream = socket.getInputStream();
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Did Not Connect Please Try Restarting");
			System.exit(0);
		}
		
		window = new Window(userName);
		
	}
	
	
	public void run(){
		try{
			while(true){
				int input;
				//StringBuilder message = new StringBuilder();
				while((input=inputStream.read())!=-1){
					//System.out.print((char)input);
					window.updateChatLog(Character.toString((char) input));
				}
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void sendMessage(){
		try{
			byte[] output = ("").getBytes();
			outputStream.flush();
			if(window.getSentMessage().isEmpty()){
				return;
			}
			output = (userName+" : "+window.getSentMessage()+'\n').getBytes();
			outputStream.write(output);
			outputStream.flush();
			window.clearTextBox();
			//window.updateChatLog("this is a test");
		}catch(Exception e){
			
		}
	}
	

}

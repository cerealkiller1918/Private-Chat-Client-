package com.killermediagroup.main;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Window {

	private JFrame frame;
	private JPanel chatPanel;
	private JPanel sendPanel;
	private JButton sendButton;
	private JTextArea chatLog;
	private JTextArea messageField;
	private JScrollPane scrollPaneChatLog;
	private JScrollPane scrollPaneMessage;
	
	private final int WIDTH = 475;
	private final int HEIGHT = 400;
	
	
	public Window(String userName){
		frame = new JFrame();
		initChatPanel();
		initSendPanel();
		frame.setTitle("Private Chat  "+ userName);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLayout(new GridLayout(2, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(chatPanel);
		frame.add(sendPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void initChatPanel(){
		chatPanel = new JPanel();
		chatLog = new JTextArea(10,40);
		scrollPaneChatLog = new JScrollPane(chatLog);
		chatLog.setEditable(false);
		chatLog.setLineWrap(true);
		scrollPaneChatLog.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneChatLog.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		chatPanel.add(scrollPaneChatLog);
	}
	
	private void initSendPanel(){
		sendPanel = new JPanel();
		sendPanel.setLayout(new FlowLayout());
		messageField = new JTextArea(3,30);
		sendButton = new JButton("Send");
		scrollPaneMessage = new JScrollPane(messageField);
		scrollPaneMessage.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneMessage.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		sendButton.setSize(90, 50);
		sendButton.addActionListener(new sendButtonListener());
		sendPanel.add(scrollPaneMessage);
		sendPanel.add(sendButton);
	}
	
	
	public void updateChatLog(String message){
		chatLog.append(message);
		autoScroll();
	}
	
	public String getSentMessage(){
		return messageField.getText();
	}
	
	private void autoScroll(){
		JScrollBar bar = scrollPaneChatLog.getVerticalScrollBar();
		int distance_to_bottom = bar.getMaximum() - ( bar.getValue() + bar.getVisibleAmount());
		if(0==distance_to_bottom){
			bar.setValue(bar.getMinimum());
		}
	}
	
	class sendButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Main.bb.sendMessage();
		}
		
	}
	
}

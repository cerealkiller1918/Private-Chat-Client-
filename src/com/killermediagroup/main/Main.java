package com.killermediagroup.main;

import javax.swing.JOptionPane;

public class Main {
	public static BackBones bb;
	public static void main(String[] args) {
		String userName = JOptionPane.showInputDialog("Enter user name.");
		bb = new BackBones(userName);
		bb.run();
	}

}

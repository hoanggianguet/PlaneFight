package com.hoanggiang.gui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GUI extends JFrame {

	public static final int WIDTH_FRAME = 1000;
	public static final int HEIGHT_FRAME = 800;
	public static boolean IS_RUNNING = false;
	private PlayGame playGame;
	private StartGame startGame;

	public GUI() {
		IS_RUNNING = true;
		setTitle("Battle Plane");
		setSize(WIDTH_FRAME, HEIGHT_FRAME);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		initComs();
		addWindowListener(windowAdapter);
	}

	private void initComs() {
		startGame = new StartGame();
		add(startGame);
		startGame.setGui(this);
	}

	private WindowAdapter windowAdapter = new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			super.windowClosing(e);
			int result = JOptionPane.showConfirmDialog(GUI.this,
					"You want to exit game now ?");
			if (result == JOptionPane.YES_OPTION) {
				GUI.this.dispose();
				IS_RUNNING = false;
			} else if (result == JOptionPane.NO_OPTION) {

			}
		}
	};

}

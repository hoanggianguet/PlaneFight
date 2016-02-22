package com.hoanggiang.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class StartGame extends JPanel implements ActionListener {
	private JButton btnStart, btnExit, btnHighScore;
	private GUI gui;
	private Image image;
	private JLabel game;

	public void setGui(GUI gui) {
		this.gui = gui;
	}

	public StartGame() {
		setBackground(Color.BLUE);
		setLayout(null);
		setFocusable(true);
		initComs();
	}

	private void initComs() {
		game = new JLabel();
		game.setBounds(250, 50, 800, 150);
		game.setFont(new Font("Tahoma", Font.BOLD, 50));
		game.setForeground(Color.WHITE);
		game.setText("SPACE SHOOTER");
		this.add(game);
		btnStart = new JButton();
		btnStart.setBounds(350, 250, 300, 100);
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnStart.setForeground(Color.YELLOW);
		btnStart.setText("Start Game");
		btnStart.setBackground(Color.BLACK);
		this.add(btnStart);
		btnStart.addActionListener(this);
		btnHighScore = new JButton();
		btnHighScore.setBounds(350, 380, 300, 100);
		btnHighScore.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnHighScore.setForeground(Color.YELLOW);
		btnHighScore.setBackground(Color.BLACK);
		btnHighScore.setText("High Score");
		this.add(btnHighScore);
		btnHighScore.addActionListener(this);
		btnExit = new JButton();
		btnExit.setBounds(350, 500, 300, 100);
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnExit.setForeground(Color.YELLOW);
		btnExit.setBackground(Color.BLACK);
		btnExit.setText("Exit Game");
		this.add(btnExit);
		btnExit.addActionListener(this);
		image = new ImageIcon(getClass().getResource("/Image/ba.png"))
				.getImage();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g.drawImage(image, 0, 0, null);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			this.requestFocus(true);
			this.setVisible(false);
			PlayGame playGame = new PlayGame();
			gui.add(playGame);
			playGame.startGame();
		} else if (e.getSource() == btnHighScore) {
			JOptionPane.showMessageDialog(null, this.getHighScore());
		} else if (e.getSource() == btnExit) {
			gui.dispose();
		}
	}

	public String getHighScore() {
		File file = new File("D:\\highScore.txt");
		String str = "";
		if (file.exists() == false) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			int position;
			position = inputStream.read();
			while (position != -1) {
				str = str + (char) position;
				position = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (str == "") {
			return "Nobody:0";
		}
		return str;
	}
}

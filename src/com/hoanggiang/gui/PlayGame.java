package com.hoanggiang.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.Random;
import java.util.logging.Handler;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.hoanggiang.actor.Actor;
import com.hoanggiang.actor.Boss;
import com.hoanggiang.actor.Bullet;
import com.hoanggiang.actor.PlanetManager;
import com.hoanggiang.actor.EnemyPlane;
import com.hoanggiang.actor.EnemyPlaneManager;
import com.hoanggiang.actor.Item;
import com.hoanggiang.actor.MyPlane;
import com.hoanggiang.actor.Plane;
import com.hoanggiang.actor.Star;
import com.hoanggiang.actor.StarManager;

import WAV.GameSound;

public class PlayGame extends JPanel implements Runnable {
	private Random random = new Random();
	private MyPlane myPlane;
	private EnemyPlaneManager enemyPlaneManager;
	private Thread mThread;
	private int count = 0, fire = 0;;
	private BitSet traceKey = new BitSet();
	private Image mHeart, mNhan;
	private JLabel score, heart, status;
	private PlanetManager cloudManager;
	private StarManager starManager;
	private Boss boss;
	private Item rocket, life, guard;
	private String highScore = "";

	public PlayGame() {

		setBackground(Color.BLACK);

		setLayout(null);

		addKeyListener(keyAdapter);

		setFocusable(true);

		initImage();

		initInfor();

		initComs();
		System.out.println("JEJJJJJJJJJJJJJJj");

	}

	public void startGame() {
		mThread = new Thread(this);

		mThread.start();

	}

	public void initComs() {
		myPlane = new MyPlane(0, GUI.WIDTH_FRAME / 2, GUI.HEIGHT_FRAME - 100,
				3, 2, 3, Plane.MY_PLANE);

		enemyPlaneManager = new EnemyPlaneManager();

		enemyPlaneManager.initPlane();

		starManager = new StarManager();

		starManager.initStar();

		boss = new Boss(0, GUI.WIDTH_FRAME / 2, -100, 500, 10, 4, Plane.BOSS);

		rocket = new Item(300, 300, Item.ROCKET);

		life = new Item(500, 400, Item.LIFE);

		guard = new Item(450, 450, 3);

		cloudManager = new PlanetManager();

		cloudManager.initClouds();

	}

	private void initInfor() {
		score = new JLabel();
		score.setFont(new Font("Tahoma", Font.BOLD, 20));
		score.setForeground(Color.WHITE);
		score.setBounds(900, 0, 100, 50);
		add(score);
		heart = new JLabel();
		heart.setFont(new Font("Tahoma", Font.BOLD, 20));
		heart.setForeground(Color.WHITE);
		heart.setBounds(70, 13, 50, 50);
		add(heart);

		status = new JLabel();
		status.setFont(new Font("Tahoma", Font.BOLD, 20));
		status.setForeground(Color.WHITE);
		status.setBounds(10, 50, 100, 50);
		add(status);
	}

	private void initImage() {
		mHeart = new ImageIcon(getClass().getResource(
				"/Image/playerLife2_blue.png")).getImage();
		int wHeart = mHeart.getWidth(this);
		int hHeart = mHeart.getHeight(this);
		setPreferredSize(new Dimension(wHeart, hHeart));

		mNhan = new ImageIcon(getClass().getResource("/Image/numeralX.png"))
				.getImage();
		int wNhan = mNhan.getWidth(this);
		int hNhan = mNhan.getHeight(this);
		setPreferredSize(new Dimension(wNhan, hNhan));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(mHeart, 10, 20, null);

		g2d.drawImage(mNhan, 50, 30, null);

		myPlane.drawActor(g2d);
		enemyPlaneManager.drawAllPlane(g2d);

		starManager.drawAllStar(g2d);

		if (enemyPlaneManager.getEnemyPlaneManager().size() == 0) {
			boss.drawActor(g2d);
			g2d.drawString(String.valueOf(boss.getHeart()), boss.getX() + 20,
					boss.getY() + 10);
		}
		if (myPlane.getHeart() == 2 && myPlane.getType() != Plane.MY_PLANE_2) {
			rocket.drawActor(g2d);
		}
		if (myPlane.getHeart() == 1 && enemyPlaneManager.getLimit() >= 20
				&& enemyPlaneManager.getLimit() <= 30) {
			life.drawActor(g2d);
		}

		cloudManager.drawAllCloud(g2d);

		g2d.drawString("High Score " + highScore, 800, 50);

		if (highScore.equals("")) {
			highScore = this.getHighScore();
		}

	}

	private boolean PRESS_P = false;
	private KeyAdapter keyAdapter = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			traceKey.set(e.getKeyCode());
			if (e.getKeyCode() == KeyEvent.VK_P)
				PRESS_P = !PRESS_P;
		}

		public void keyReleased(KeyEvent e) {
			traceKey.clear(e.getKeyCode());

		}
	};

	public void planeAction(int orient) {
		myPlane.changeOrient(orient);
		myPlane.move(count);
		repaint();
	}

	public String getHighScore() {
		File file = new File("D:\\highScore.txt");
		String str = "";
		if (file.exists() == false) {
			try {
				file.createNewFile();
			} catch (IOException e) {
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
			e.printStackTrace();
		}
		if (str == "") {
			return "Nobody:0";
		}
		return str;
	}

	public void checkScore() {
		if (myPlane.getScore() > Integer.parseInt(highScore.split(":")[1])) {
			String name = JOptionPane
					.showInputDialog("You set a high score.What is your name?");
			highScore = name + ":" + myPlane.getScore();
			File file = new File("D:\\highScore.txt");
			if (file.exists() == false) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				FileOutputStream outputStream = new FileOutputStream(file);
				byte[] arrByte = highScore.toString().getBytes();
				outputStream.write(arrByte);
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		while (GUI.IS_RUNNING) {
			if (!PRESS_P) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (traceKey.get(KeyEvent.VK_LEFT)) {
					planeAction(Actor.LEFT);
				} else if (traceKey.get(KeyEvent.VK_RIGHT)) {
					planeAction(Actor.RIGHT);
				} else if (traceKey.get(KeyEvent.VK_UP)) {
					planeAction(Actor.UP);
				} else if (traceKey.get(KeyEvent.VK_DOWN)) {
					planeAction(Actor.DOWN);
				}

				if (traceKey.get(KeyEvent.VK_SPACE)) {
					if (fire == 0) {
						myPlane.fire();
						GameSound.getIstance().getAudio(GameSound.SHOOTING)
								.play();
						fire = 100;
					}
				}

				if (fire > 0) {
					fire--;
				}

				count++;
				if (count > 1000000000) {
					count = 0;
				}

				myPlane.shoot(enemyPlaneManager);

				myPlane.moveAllBullet(count);

				enemyPlaneManager.moveAllPlane(count);

				if (count % 700 == 0) {
					enemyPlaneManager.fireAllBullet();
				}

				enemyPlaneManager.moveAllBullet(count);

				myPlane.destroyBullet(enemyPlaneManager);

				myPlane.impactBullet(enemyPlaneManager);

				myPlane.imPactEnemyPlane(enemyPlaneManager);

				score.setText(String.valueOf(myPlane.getScore()));

				heart.setText(String.valueOf(myPlane.getHeart()));

				if (count % 5000 == 0) {
					enemyPlaneManager.initPlane();
				}

				if (myPlane.isDie()) {
					JOptionPane.showMessageDialog(null, "You Lost!");
					checkScore();
					System.exit(0);
				}

				if (enemyPlaneManager.getEnemyPlaneManager().size() == 0
						&& boss.isDie()) {
					JOptionPane.showMessageDialog(null, "You win!");
					checkScore();
					System.exit(0);
				}

				if (count == 5000) {
					myPlane.setStatus(Plane.STATUS_PLANE_ALIVE);

				}

				if (myPlane.getStatus() == Plane.STATUS_PLANE_BEGIN) {
					status.setText("BEGIN");
				} else if (myPlane.getStatus() == Plane.STATUS_PLANE_ALIVE) {
					status.setText("ALIVE");
				}

				starManager.moveAllStar(count);

				if (enemyPlaneManager.getEnemyPlaneManager().size() < 1) {
					System.out.println("a");
					boss.move(count);
					boss.setStatus(Plane.STATUS_PLANE_ALIVE);
					boss.moveAllBullet(count);
				}

				if (count % 1000 == 0
						&& enemyPlaneManager.getEnemyPlaneManager().size() == 0) {
					boss.fire();
				}

				if (enemyPlaneManager.getLimit() >= 30) {
					if (myPlane.impactItem(rocket)) {
						myPlane.changeBullet();
					}

					if (myPlane.impactItem(life)) {
						myPlane.setHeart();
					}
				}

				// if (myPlane.impactItem(guard)) {
				// enemyPlaneManager.removeAllEnemy();
				// }
				cloudManager.moveAllCloud(count);

				if (count % 1000 == 0
						&& enemyPlaneManager.getEnemyPlaneManager().size() == 0) {
					boss.changeOrient(new Random().nextInt(4) + 1);
				}
				myPlane.deleteBulletOutside();
				enemyPlaneManager.deleteBulletOutside();
				myPlane.impactBulletBoss(boss);
				myPlane.impactBoss(boss);
				boss.destroyed(myPlane);
				boss.deleteBulletOutside();
				System.out.println(boss.getStatus());

				repaint();

			}
		}
	}
}

package com.hoanggiang.actor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import com.hoanggiang.gui.GUI;

public class StarManager {
	private ArrayList<Star> stars = new ArrayList<Star>();
	private Random rd = new Random();
	public static final int COUNT = 300;

	public StarManager() {

	}

	public void initStar() {
		int width = GUI.WIDTH_FRAME;
		int height = GUI.HEIGHT_FRAME;
		if (stars.size() >= COUNT)
			return;
		for (int i = 0; i < COUNT; i++) {
			int x = rd.nextInt(width);
			int y = rd.nextInt(height);
			int size = rd.nextInt(Star.MAX_SIZE - Star.MIN_SIZE) + Star.MIN_SIZE;
			int R = rd.nextInt(255);
			int G = rd.nextInt(255);
			int B = rd.nextInt(255);
			int speed = rd.nextInt(5) + 5;
			Color color = new Color(R, G, B);

			Star star = new Star(x, y, speed, size, color);
			stars.add(star);
		}
	}

	public void drawAllStar(Graphics2D g) {
		for (int i = 0; i < stars.size(); i++) {
			stars.get(i).drawActor(g);
		}
	}

	public void moveAllStar(int count) {
		for (int i = 0; i < stars.size(); i++) {
			stars.get(i).move(count);
		}
	}
}

package com.hoanggiang.actor;

import java.awt.Color;
import java.awt.Graphics2D;

import com.hoanggiang.gui.GUI;

public class Star extends Actor {
	private Color color;
	private int size;
	public static final int MAX_SIZE = 5;
	public static final int MIN_SIZE = 1;

	public Star(int x, int y, int speed, int size, Color color) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.size = size;
		this.color = color;
	}

	@Override
	public void drawActor(Graphics2D g) {
		g.setColor(this.color);
		g.fillOval(x, y, size, size);
	}

	@Override
	public void move(int count) {
		if (count % speed != 0) {
			return;
		}
		if (y > GUI.HEIGHT_FRAME) {
			y = -size;
		}
		y++;
	}

}

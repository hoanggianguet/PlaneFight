package com.hoanggiang.actor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.hoanggiang.gui.GUI;

public abstract class Actor {
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	protected int x, y, type, orient, widthActor, heightActor, speed;
	protected Image img;

	public void drawActor(Graphics2D g) {
		g.drawImage(img, x, y, null);
	}

	public void move(int count) {
		if (count % speed != 0) {
			return;
		}
		switch (orient) {
		case LEFT:
			if (x <= 0) {
				return;
			}
			x--;
			break;
		case RIGHT:
			if (x >= (GUI.WIDTH_FRAME - widthActor)) {
				return;
			}
			x++;
			break;
		case UP:
			if (y <= 0) {
				return;
			}
			y--;
			break;
		case DOWN:
			if (y >= (GUI.HEIGHT_FRAME - heightActor)) {
				return;
			}
			y++;
			break;
		default:
			break;
		}
	}

	public void changeOrient(int newOrient) {
		this.orient = newOrient;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, widthActor, heightActor);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}

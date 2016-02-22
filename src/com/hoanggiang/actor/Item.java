package com.hoanggiang.actor;

import javax.swing.ImageIcon;

public class Item extends Actor {
	public static final int LIFE = 1;
	public static final int ROCKET = 2;
	public static final int GUARD = 3;

	public Item(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
		switch (type) {
		case LIFE:
			img = new ImageIcon(getClass().getResource("/Image/heart.png")).getImage();
			break;
		case ROCKET:
			img = new ImageIcon(getClass().getResource("/Image/powerupRed_bolt.png")).getImage();
			break;
		case GUARD:
			img = new ImageIcon(getClass().getResource("/Image/shield_gold.png")).getImage();
			break;
		default:
			break;
		}
		widthActor = img.getWidth(null);
		heightActor = img.getHeight(null);
	}
}

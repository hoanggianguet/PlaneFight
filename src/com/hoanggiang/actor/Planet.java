package com.hoanggiang.actor;

import javax.swing.ImageIcon;

import com.hoanggiang.gui.GUI;

public class Planet extends Actor {
	public static final int TYPE_PLANET_1 = 1;
	public static final int TYPE_PLANET_2 = 2;

	public Planet(int x, int y, int speed, int type) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.type = type;
		if (type == TYPE_PLANET_1) {
			img = new ImageIcon(getClass().getResource("/Image/meteorBig.png")).getImage();
		} else {
			img = new ImageIcon(getClass().getResource("/Image/meteorSmall.png")).getImage();
		}
		widthActor = img.getWidth(null);
		heightActor = img.getHeight(null);
	}

	@Override
	public void move(int count) {
		if (count % speed != 0) {
			return;
		}
		if (y > GUI.HEIGHT_FRAME) {
			y = -heightActor;
		}
		y++;
	}

}

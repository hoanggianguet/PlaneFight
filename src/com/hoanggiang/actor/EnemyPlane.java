package com.hoanggiang.actor;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.hoanggiang.gui.GUI;

public class EnemyPlane extends Plane {
	public EnemyPlane(int score, int x, int y, int heart, int speed, int orient, int type) {
		super(score, x, y, heart, speed, orient, type);
		switch (type) {
		case Plane.ENEMY_PLANE_1:
			img = new ImageIcon(getClass().getResource("/Image/enemy1.png")).getImage();
			break;
		case Plane.ENEMY_PLANE_2:
			img = new ImageIcon(getClass().getResource("/Image/enemy2.png")).getImage();
			break;
		case Plane.ENEMY_PLANE_3:
			img = new ImageIcon(getClass().getResource("/Image/enemy3.png")).getImage();
			break;
		}

		widthActor = img.getWidth(null);
		heightActor = img.getHeight(null);
	}

	@Override
	public void move(int count) {
		if (count % speed != 0) {
			return;
		}
		switch (this.type) {
		case Plane.ENEMY_PLANE_1:
			if (y > GUI.HEIGHT_FRAME) {
				y = -heightActor;
				return;
			} else {
				y++;
			}
			break;
		case Plane.ENEMY_PLANE_2:
			if (x > GUI.WIDTH_FRAME) {
				x = -widthActor;
				return;
			} else if (y > GUI.HEIGHT_FRAME) {
				y = -heightActor;
				return;
			} else {
				x++;
				y++;
			}
			break;
		case Plane.ENEMY_PLANE_3:
			if (x < -widthActor) {
				x = GUI.WIDTH_FRAME;
				return;
			} else if (y > GUI.HEIGHT_FRAME) {
				y = -heightActor;
			} else {
				y++;
				x--;
			}
			break;
		default:
			break;
		}
	}

	public void setDamageImage() {
		this.img = new ImageIcon(getClass().getResource("/Image/damage.png")).getImage();
	}
}

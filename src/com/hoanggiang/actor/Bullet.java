package com.hoanggiang.actor;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import com.hoanggiang.gui.GUI;

public class Bullet extends Actor {
	protected boolean visible;
	public static final int TYPE_BULLET_MY = 1;
	public static final int TYPE_BULLET_ENEMY = 2;
	public static final int TYPE_BULLET_ROCKET = 3;
	public static final int TYPE_BULLET_BOSS_1 = 4;
	public static final int TYPE_BULLET_BOSS_2 = 5;
	public static final int TYPE_BULLET_BOSS_3 = 6;

	public Bullet(int x, int y, int speed, int type) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.visible = true;
		this.type = type;
		if (this.type == TYPE_BULLET_MY) {
			img = new ImageIcon(getClass().getResource("/Image/bullet_2_purple.png")).getImage();
		} else if (this.type == TYPE_BULLET_ROCKET) {
			img = new ImageIcon(getClass().getResource("/Image/rocket.png")).getImage();
		} else if (this.type == TYPE_BULLET_ENEMY) {
			img = new ImageIcon(getClass().getResource("/Image/bullet_orange0003.png")).getImage();
		} else if (this.type == TYPE_BULLET_BOSS_1 || this.type == TYPE_BULLET_BOSS_2
				|| this.type == TYPE_BULLET_BOSS_3) {
			img = new ImageIcon(getClass().getResource("/Image/bomb.png")).getImage();
		}
		// switch (type) {
		// case TYPE_BULLET_MY:
		// img = new
		// ImageIcon(getClass().getResource("/Image/bullet_2_purple.png")).getImage();
		// break;
		// case TYPE_BULLET_ROCKET:
		// img = new
		// ImageIcon(getClass().getResource("/Image/rocket.png")).getImage();
		// break;
		// case TYPE_BULLET_ENEMY:
		// img = new
		// ImageIcon(getClass().getResource("/Image/bullet_orange0003.png")).getImage();
		// break;
		// case TYPE_BULLET_BOSS_1:
		// case TYPE_BULLET_BOSS_2:
		// case TYPE_BULLET_BOSS_3:
		// img = new
		// ImageIcon(getClass().getResource("/Image/bomb.png")).getImage();
		// break;
		// default:
		// break;
		// }
		widthActor = img.getWidth(null);
		heightActor = img.getHeight(null);
	}

	@Override
	public void move(int count) {
		if (count % speed != 0) {
			return;
		}
		// if (type == TYPE_BULLET_MY || type == TYPE_BULLET_ROCKET) {
		// if (y < 0) {
		// this.visible = false;
		// return;
		// }
		// y--;
		// } else if (type == TYPE_BULLET_ENEMY) {
		// if (y > GUI.HEIGHT_FRAME) {
		// this.visible = false;
		// return;
		// }
		// y++;
		// }
		switch (type) {
		case TYPE_BULLET_MY:
		case TYPE_BULLET_ROCKET:
			if (y < 0) {
				this.visible = false;
				return;
			} else
				y--;
			break;
		case TYPE_BULLET_ENEMY:
			if (y > GUI.HEIGHT_FRAME) {
				this.visible = false;
				return;
			} else {
				y++;
			}
			break;
		case TYPE_BULLET_BOSS_1:
			if (y > GUI.HEIGHT_FRAME) {
				this.visible = false;
				return;
			} else {
				y += 2;
			}
			break;
		case TYPE_BULLET_BOSS_2:
			if (x < 0 || y > GUI.HEIGHT_FRAME) {
				this.visible = false;
				return;
			} else {
				x--;
				y += 2;
			}
			break;
		case TYPE_BULLET_BOSS_3:
			if (x > GUI.WIDTH_FRAME || y > GUI.HEIGHT_FRAME) {
				this.visible = false;
				return;
			} else {
				x++;
				y += 2;
			}
			break;
		default:
			break;
		}

	}

	public boolean isVisible() {
		return this.visible;
	}

}

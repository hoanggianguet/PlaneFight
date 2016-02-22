package com.hoanggiang.actor;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

import com.hoanggiang.gui.GUI;

public class Boss extends Plane {
	private Random random;

	public Boss(int score, int x, int y, int heart, int speed, int orient, int type) {
		super(score, x, y, heart, speed, orient, type);
		status = STATUS_PLANE_BEGIN;
		img = new ImageIcon(getClass().getResource("/Image/boss1.png")).getImage();
		widthActor = img.getWidth(null);
		heightActor = img.getHeight(null);
	}

	@Override
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
			if (y >= (GUI.HEIGHT_FRAME / 2)) {
				return;
			}
			y++;
			break;
		default:
			break;
		}
	}

	public void destroyed(MyPlane myPlane) {
		for (int i = myPlane.getArrayBullet().size() - 1; i >= 0; i--) {
			Rectangle you = myPlane.getArrayBullet().get(i).getBounds();
			Rectangle my = this.getBounds();
			if (my.intersects(you)) {
				if (this.status == Plane.STATUS_PLANE_ALIVE) {
					if (this.heart > 0) {
						if (myPlane.getType() == Plane.MY_PLANE_2) {
							heart -= 5;
						} else {
							heart--;
						}
					}
				}
				myPlane.addScore();
				myPlane.getArrayBullet().remove(i);
			}
		}
	}

	@Override
	public void fire() {
		int xBullet = this.x + this.widthActor / 2;
		int yBullet = this.y + this.heightActor;
		int typeBullet = Bullet.TYPE_BULLET_BOSS_1;
		int speedBullet = 5;
		int x2Bullet = this.x + this.widthActor / 2 - 15;
		int y2Bullet = this.y + this.heightActor;
		int type2Bullet = Bullet.TYPE_BULLET_BOSS_2;
		int speed2Bullet = 5;
		int x3Bullet = this.x + this.widthActor / 2 + 15;
		int y3Bullet = this.y + this.heightActor;
		int type3Bullet = Bullet.TYPE_BULLET_BOSS_3;
		int speed3Bullet = 5;
		Bullet bullet1 = new Bullet(xBullet, yBullet, speedBullet, typeBullet);
		Bullet bullet2 = new Bullet(x2Bullet, y2Bullet, speed2Bullet, type2Bullet);
		Bullet bullet3 = new Bullet(x3Bullet, y3Bullet, speed3Bullet, type3Bullet);
		arrayBullet.add(bullet1);
		arrayBullet.add(bullet2);
		arrayBullet.add(bullet3);
	}

}

package com.hoanggiang.actor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Plane extends Actor {

	protected ArrayList<Bullet> arrayBullet;
	protected int score;
	protected int heart;
	protected int status;
	public static int STATUS_PLANE_BEGIN = 1;
	public static int STATUS_PLANE_ALIVE = 2;
	public static int STATUS_PLANE_DESTROY = 3;

	public static final int MY_PLANE = 1;
	public static final int MY_PLANE_2 = 0;
	public static final int ENEMY_PLANE_1 = 2;
	public static final int ENEMY_PLANE_2 = 3;
	public static final int ENEMY_PLANE_3 = 4;
	public static final int BOSS = 5;

	public Plane(int score, int x, int y, int heart, int speed, int orient, int type) {
		this.score = score;
		this.x = x;
		this.y = y;
		this.heart = heart;
		this.speed = speed;
		this.orient = orient;
		this.type = type;
		arrayBullet = new ArrayList<Bullet>();
	}

	@Override
	public void drawActor(Graphics2D g) {
		super.drawActor(g);
		if (arrayBullet.size() != 0) {
			for (int i = 0; i < arrayBullet.size(); i++) {
				arrayBullet.get(i).drawActor(g);
			}
		}

	}

	public void fire() {
		int xBullet = 0, yBullet = 0;
		int typeBullet = 0;
		int speedBullet = 0;
		switch (type) {
		case MY_PLANE:
			xBullet = x + widthActor / 2 - 4;
			yBullet = y;
			speedBullet = 2;
			typeBullet = Bullet.TYPE_BULLET_MY;
			break;
		case MY_PLANE_2:
			xBullet = x + widthActor / 2 - 4;
			yBullet = y;
			speedBullet = 2;
			typeBullet = Bullet.TYPE_BULLET_ROCKET;
			break;
		case ENEMY_PLANE_1:
		case ENEMY_PLANE_2:
		case ENEMY_PLANE_3:
			xBullet = this.x + this.widthActor / 2;
			yBullet = this.y + this.heightActor;
			speedBullet = 5;
			typeBullet = Bullet.TYPE_BULLET_ENEMY;
			break;
		// case BOSS:
		// xBullet = this.x + this.widthActor / 2;
		// yBullet = this.y + this.heightActor;
		// speedBullet = 5;
		// typeBullet = Bullet.TYPE_BULLET_BOSS_1;
		// break;
		default:
			break;
		}
		Bullet bullet = new Bullet(xBullet, yBullet, speedBullet, typeBullet);
		arrayBullet.add(bullet);
	}

	@Override
	public void changeOrient(int newOrient) {
		if (newOrient == this.orient)
			return;
		super.changeOrient(newOrient);

	}

	public ArrayList<Bullet> getArrayBullet() {
		return arrayBullet;
	}

	public void remove(int i) {
		arrayBullet.remove(i);
	}

	public void moveAllBullet(int count) {
		for (int i = 0; i < arrayBullet.size(); i++) {
			arrayBullet.get(i).move(count);
		}
	}

	public void deleteBulletOutside() {
		for (int i = this.arrayBullet.size() - 1; i >= 0; i--) {
			if (this.arrayBullet.get(i).isVisible() == false) {
				this.arrayBullet.remove(i);
			}
		}
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return this.type;
	}

	public int getHeart() {
		return this.heart;
	}

	public int getStatus() {
		return this.status;
	}

	public boolean isDie() {
		if (this.heart == 0) {
			return true;
		}
		return false;
	}

}

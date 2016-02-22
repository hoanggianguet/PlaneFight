package com.hoanggiang.actor;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import com.hoanggiang.gui.GUI;

public class EnemyPlaneManager {
	private ArrayList<EnemyPlane> enemyPlanes;
	private Random rd = new Random();
	public static final int COUNT = 10;
	private int limit = 0;

	public EnemyPlaneManager() {
		enemyPlanes = new ArrayList<EnemyPlane>();
	}

	public void initPlane() {
		if (limit >= 30) {
			return;
		}
		int w = GUI.WIDTH_FRAME;
		int h = GUI.HEIGHT_FRAME;
		int x = 50;
		for (int i = 0; i < COUNT; i++) {
			EnemyPlane enemyPlane = new EnemyPlane(0, x, 0, 0, 10, 3, rd.nextInt(3) + 2);
			enemyPlanes.add(enemyPlane);
			x += 100;
		}
		limit += 5;
	}

	public void drawAllPlane(Graphics2D g) {
		for (int i = 0; i < enemyPlanes.size(); i++) {
			enemyPlanes.get(i).drawActor(g);
		}
	}

	public void moveAllPlane(int count) {
		for (int i = 0; i < enemyPlanes.size(); i++) {
			enemyPlanes.get(i).move(count);
		}
	}

	public ArrayList<EnemyPlane> getEnemyPlaneManager() {
		return enemyPlanes;
	}

	public void removeAllEnemy() {
		enemyPlanes.clear();
	}

	public void fireAllBullet() {
		for (int i = 0; i < enemyPlanes.size(); i++) {
			enemyPlanes.get(i).fire();
		}
	}

	public void moveAllBullet(int count) {
		for (int i = 0; i < enemyPlanes.size(); i++) {
			enemyPlanes.get(i).moveAllBullet(count);
		}
	}

	public void drawAllBullet(Graphics2D g) {
		for (int i = 0; i < enemyPlanes.size(); i++) {
			enemyPlanes.get(i).drawActor(g);
		}
	}

	public int getLimit() {
		return limit;
	}

	public void deleteBulletOutside() {
		for (int i = 0; i < enemyPlanes.size(); i++) {
			enemyPlanes.get(i).deleteBulletOutside();
		}
	}

}

package com.hoanggiang.actor;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class MyPlane extends Plane {

	public MyPlane(int score, int x, int y, int heart, int speed, int orient, int type) {
		super(score, x, y, heart, speed, orient, type);
		status = Plane.STATUS_PLANE_BEGIN;
		img = new ImageIcon(getClass().getResource("/Image/DurrrSpaceShip.png")).getImage();
		widthActor = img.getWidth(null);
		heightActor = img.getHeight(null);
	}

	public ArrayList<Bullet> getArrayBullet() {
		return arrayBullet;
	}

	// public void shoot(EnemyPlaneManager enemyPlaneManager) {
	// for (int i = enemyPlaneManager.getNumberEnemyPlaneManager() - 1; i >= 0;
	// i--) {
	// for (int j = this.getArrayBullet().size() - 1; j >= 0; j--) {
	// Rectangle you =
	// enemyPlaneManager.getEnemyPlaneManager().get(i).getBounds();
	// Rectangle my = this.getArrayBullet().get(j).getBounds();
	// if (my.intersects(you)) {
	// enemyPlaneManager.getEnemyPlaneManager().remove(i);
	// this.getArrayBullet().remove(j);
	// this.score += 100;
	// return;
	// }
	// }
	// }
	// }
	public void shoot(EnemyPlaneManager enemyPlaneManager) {
		for (int i = enemyPlaneManager.getEnemyPlaneManager().size() - 1; i >= 0; i--) {
			for (int j = this.getArrayBullet().size() - 1; j >= 0; j--) {
				Rectangle you = enemyPlaneManager.getEnemyPlaneManager().get(i).getBounds();
				Rectangle my = this.getArrayBullet().get(j).getBounds();
				if (my.intersects(you)) {
					enemyPlaneManager.getEnemyPlaneManager().remove(i);
					this.getArrayBullet().remove(j);
					this.score += 100;
					return;
				}
			}
		}
	}

	// public void impactBullet(EnemyPlaneManager enemyPlaneManager) {
	// for (int i = enemyPlaneManager.getNumberEnemyPlaneManager() - 1; i >= 0;
	// i--) {
	// for (int j =
	// enemyPlaneManager.getEnemyPlaneManager().get(i).getSizeBullet() - 1; j >=
	// 0; j--) {
	// Rectangle you =
	// enemyPlaneManager.getEnemyPlaneManager().get(i).getArrayBullet().get(j).getBounds();
	// Rectangle my = this.getBounds();
	// if (my.intersects(you)) {
	// enemyPlaneManager.getEnemyPlaneManager().get(i).getArrayBullet().remove(j);
	// if (this.status == Plane.STATUS_PLANE_ALIVE) {
	// if (this.heart > 0) {
	// this.heart--;
	// }
	// }
	// return;
	// }
	// }
	// }
	// }
	public void impactBullet(EnemyPlaneManager enemyPlaneManager) {
		for (int i = enemyPlaneManager.getEnemyPlaneManager().size() - 1; i >= 0; i--) {
			for (int j = enemyPlaneManager.getEnemyPlaneManager().get(i).getArrayBullet().size() - 1; j >= 0; j--) {
				Rectangle you = enemyPlaneManager.getEnemyPlaneManager().get(i).getArrayBullet().get(j).getBounds();
				Rectangle my = this.getBounds();
				if (my.intersects(you)) {
					enemyPlaneManager.getEnemyPlaneManager().get(i).getArrayBullet().remove(j);
					if (this.status == Plane.STATUS_PLANE_ALIVE) {
						if (this.heart > 0) {
							this.heart--;
						}
					}

				}
			}
		}
	}

	// public void imPactEnemyPlane(EnemyPlaneManager enemyPlaneManager) {
	// for (int i = enemyPlaneManager.getNumberEnemyPlaneManager() - 1; i >= 0;
	// i--) {
	// Rectangle you =
	// enemyPlaneManager.getEnemyPlaneManager().get(i).getBounds();
	// Rectangle my = this.getBounds();
	// if (my.intersects(you)) {
	// if (this.status == Plane.STATUS_PLANE_ALIVE) {
	// if (this.heart > 0) {
	// this.heart--;
	// }
	// }
	// enemyPlaneManager.getEnemyPlaneManager().remove(i);
	// }
	// }
	// }
	public void imPactEnemyPlane(EnemyPlaneManager enemyPlaneManager) {
		for (int i = enemyPlaneManager.getEnemyPlaneManager().size() - 1; i >= 0; i--) {
			Rectangle you = enemyPlaneManager.getEnemyPlaneManager().get(i).getBounds();
			Rectangle my = this.getBounds();
			if (my.intersects(you)) {
				if (this.status == Plane.STATUS_PLANE_ALIVE) {
					if (this.heart > 0) {
						this.heart--;
					}
				}
				enemyPlaneManager.getEnemyPlaneManager().remove(i);
			}
		}
	}

	// public void destroyBullet(EnemyPlaneManager enemyPlaneManager) {
	// for (int i = enemyPlaneManager.getNumberEnemyPlaneManager() - 1; i >= 0;
	// i--) {
	// for (int j =
	// enemyPlaneManager.getEnemyPlaneManager().get(i).getSizeBullet() - 1; j >=
	// 0; j--) {
	// for (int k = this.arrayBullet.size() - 1; k >= 0; k--) {
	// Rectangle my = this.arrayBullet.get(k).getBounds();
	// Rectangle you =
	// enemyPlaneManager.getEnemyPlaneManager().get(i).getArrayBullet().get(j).getBounds();
	// if (my.intersects(you)) {
	// enemyPlaneManager.getEnemyPlaneManager().get(i).getArrayBullet().remove(j);
	// this.arrayBullet.remove(k);
	// }
	// }
	// }
	// }
	// }
	public void destroyBullet(EnemyPlaneManager enemyPlaneManager) {
		for (int i = enemyPlaneManager.getEnemyPlaneManager().size() - 1; i >= 0; i--) {
			for (int j = enemyPlaneManager.getEnemyPlaneManager().get(i).getArrayBullet().size() - 1; j >= 0; j--) {
				for (int k = this.arrayBullet.size() - 1; k >= 0; k--) {
					Rectangle my = this.arrayBullet.get(k).getBounds();
					Rectangle you = enemyPlaneManager.getEnemyPlaneManager().get(i).getArrayBullet().get(j).getBounds();
					if (my.intersects(you)) {
						enemyPlaneManager.getEnemyPlaneManager().get(i).getArrayBullet().remove(j);
						this.arrayBullet.remove(k);
						return;
					}

				}
			}
		}
	}

	public boolean impactItem(Item item) {
		Rectangle my = this.getBounds();
		Rectangle you = item.getBounds();
		return my.intersects(you);
	}

	public void impactBulletBoss(Boss boss) {
		for (int i = boss.getArrayBullet().size() - 1; i >= 0; i--) {
			Rectangle my = this.getBounds();
			Rectangle you = boss.getArrayBullet().get(i).getBounds();
			if (my.intersects(you)) {
				System.out.println("aaaaaaa");
				if (this.heart > 0) {
					heart--;
				}
				boss.getArrayBullet().remove(i);
			}
		}
	}

	public void impactBoss(Boss boss) {
		Rectangle my = this.getBounds();
		Rectangle you = boss.getBounds();
		if (my.intersects(you)) {
			if (this.heart > 0) {
				heart--;
			}
		}
	}

	public int getScore() {
		return this.score;
	}

	public void setHeart() {
		if (this.heart == 1) {
			heart = 2;
		}
	}

	public int getStatus() {
		return status;
	}

	public void changeBullet() {
		this.type = Plane.MY_PLANE_2;
	}

	public void addScore() {
		this.score += 100;
	}

}

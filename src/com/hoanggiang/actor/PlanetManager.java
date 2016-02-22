package com.hoanggiang.actor;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import com.hoanggiang.gui.GUI;

public class PlanetManager {
	private ArrayList<Planet> planets = new ArrayList<Planet>();
	public static final int COUNT = 4;
	private Random random = new Random();

	public PlanetManager() {

	}

	public void initClouds() {
		if (planets.size() >= COUNT) {
			return;
		}
		for (int i = 0; i < COUNT; i++) {
			int xPlanet = random.nextInt(GUI.WIDTH_FRAME - 50);
			int yPlanet = -50;
			int speedPlanet = random.nextInt(20) + 10;
			int typePlanet = random.nextInt(2) + 1;
			Planet newPlanet = new Planet(xPlanet, yPlanet, speedPlanet, typePlanet);
			planets.add(newPlanet);
		}
	}

	public void drawAllCloud(Graphics2D g2d) {
		for (int i = 0; i < planets.size(); i++) {
			planets.get(i).drawActor(g2d);
		}
	}

	public void moveAllCloud(int count) {
		for (int i = 0; i < planets.size(); i++) {
			planets.get(i).move(count);
		}
	}
}

package model;

import java.util.concurrent.Semaphore;

import controller.ChefController;
import controller.DishController;

public class ChefThread extends Thread{
	private Semaphore semaphore;
 
	private ChefController chefController;
	private Semaphore dishSemaphore = new Semaphore(1);
	public ChefThread(Semaphore semaphore, ChefController controller) {
		this.semaphore = semaphore;
		this.chefController = controller;
	}
	@Override
	public void run() {
		DishController controller = new DishController(getId(),chefController,semaphore);
		for(int x = 0;x<5;x++) {
			int number = (int)Math.floor(Math.random()*9);
			DishThread thread = new DishThread(number, dishSemaphore,getId(),controller);
			thread.start();
		}
	}
	
}

package view;

import java.util.concurrent.Semaphore;

import controller.ChefController;
import model.ChefThread;

public class Main {
	public static void main(String[] args) {
		ChefController controller = new ChefController();
		Semaphore semaphore = new Semaphore(1);
		for(int x=0;x<5;x++) {
			ChefThread chefThread = new ChefThread(semaphore, controller);
			chefThread.start();
		}
	}
}

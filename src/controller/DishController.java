package controller;

import java.util.concurrent.Semaphore;

public class DishController {
	private int finishedDishes = 0;
	private long id;
	private Semaphore semaphore;
	private ChefController controller;
	public DishController(long id, ChefController controller,Semaphore semaphore) {
		this.id = id;
		this.controller = controller;
		this.semaphore = semaphore;
	}
	public void finishDish() {
		finishedDishes +=1;
		if(finishedDishes == 5) {
			try {
				if(finishedDishes == 5) {
					semaphore.acquire();
					System.out.println("Chef "+id+" terminou em " + controller.getPosition()+"º lugar");
					controller.finish();
					semaphore.release();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

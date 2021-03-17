package model;

import java.util.concurrent.Semaphore;

import controller.DishController;

public class DishThread extends Thread{
	private int id;
	private Semaphore semaphore;
	private Long chef;
	private String name;
	private DishController controller;
	public DishThread(int id,Semaphore semaphore,Long chef,DishController controller) {
		this.id  = id;
		this.semaphore = semaphore;
		this.chef = chef;
		this.controller = controller;
	}
	@Override
	public void run() {
		long totalTime = 0L;
		
		if(id%2==0) {
			totalTime = (long)(600+Math.random()*1200);
			this.name = "Lasanha a Bolonhesa";
		}else {
			totalTime = (long)(500+Math.random()*800);
			this.name = "Sopa de Cebola";
		}
		long time = totalTime;
		while(time!=0) {
			
			try {
				sleep(100L);
				time = time - 100L;
				long percentage = (totalTime - time)/(totalTime/100L);
				if(percentage >=100L) {
					System.out.println(name+" com id "+getId()+" esta 100% feito (Chef"+ chef+")");
					break;
				}else {
					System.out.println(name+" com id "+getId()+" esta "+percentage+"% feito (Chef"+ chef+")");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			semaphore.acquire();
			sleep(500L);
			System.out.println(name+" com id " + getId() +" entregue em 0.5 seg (Chef "+chef+")");
			controller.finishDish();
			semaphore.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		interrupt();
	}
}

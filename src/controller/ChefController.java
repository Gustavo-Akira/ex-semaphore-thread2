package controller;

public class ChefController {
	private int position = 1;
	public void finish() {
		this.position +=1;
	}
	public int getPosition() {
		return position;
	}
}

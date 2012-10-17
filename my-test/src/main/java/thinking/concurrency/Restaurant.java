package thinking.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal {
	private int orderNum;

	Meal() {
		System.out.println("meal " + orderNum + " prepared");
	}

	public Meal(int orderNum) {
		this.orderNum = orderNum;
		System.out.println("meal " + orderNum + " prepared");
	}

	public String toString() {
		return "Meal " + orderNum;
	}

}

class WaitPerson implements Runnable {
	private Restaurant restaurant;

	WaitPerson(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void run() {

		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal == null) {
						wait();
					}
				}
				System.out.println(restaurant.meal + " sent out");

				synchronized (restaurant.chef) {
					restaurant.meal = null;
					restaurant.chef.notifyAll();
				}
			TimeUnit.SECONDS.sleep(3);
			}
		} catch (InterruptedException e) {
			System.out.println("now waitperson exit by exception");
		}
		System.out.println("now waitperson exit");
	}
}

class Chef implements Runnable {
	private Restaurant restaurant;
	int mealCount = 0;

	Chef(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void run() {

		try {
			while (!Thread.interrupted()) {
				synchronized (this) {
					while (restaurant.meal != null) {
						wait();
					}
				}

				if ((mealCount) == 10) {
					restaurant.eService.shutdownNow();
				}
				synchronized (restaurant.waitPerson) {
					restaurant.meal = new Meal(mealCount++);
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.SECONDS.sleep(3);
			}
		} catch (InterruptedException e) {
			System.out.println("now chef exit by exception");
		}
		System.out.println("now chef exit");
	}
}

public class Restaurant {
	Meal meal;
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);
	ExecutorService eService = Executors.newCachedThreadPool();

	Restaurant() {
		eService.execute(waitPerson);
		eService.execute(chef);
	}

	public static void main(String[] args) {
		Restaurant restaurant = new Restaurant();
	}

}

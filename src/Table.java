import java.util.Arrays;

public class Table implements Runnable {
	public int philosophersCount;
	private Thread[] philosophers;
	private Boolean[] forks;

	public Table(int philosophersCount) {
		this.philosophersCount = philosophersCount;
		philosophers = new Thread[philosophersCount];
		forks = new Boolean[philosophersCount]; // вилок по условию задачи столько же сколько и философов
		fillLists();
		System.out.println(Arrays.toString(philosophers));
	}

	@Override
	public void run() {
		System.out.println("Начали");
		for(Thread t: philosophers) {
			t.start();
		}
	}

	public synchronized boolean takeForksForEating(Boolean leftFork, Boolean rightFork) {
		if (!(leftFork & rightFork)) {
			leftFork = rightFork = true;
			return true;
		}
		return false;
	}

	/*
	 * Заполнение массива по правилу, что каждый новый философ узнаёт о своих
	 * соседних вилках и столе
	 */
	void fillLists() {
		Arrays.fill(forks, false);
		for (int i = 0; i < philosophers.length; i++) {
			if (i == philosophers.length - 1) {
				philosophers[i] = new Thread(new Philosopher("Philosopher " + (i + 1), this, forks[i], forks[0]));
				return;
			}
			philosophers[i] = new Thread(new Philosopher("Philosopher " + (i + 1), this, forks[i], forks[i + 1]));
		}
	}
}

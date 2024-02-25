public class Philosopher implements Runnable {
	private String name;
	private Boolean leftFork;
	private Boolean rightFork;
	private Table table;

	public Philosopher(String name, Table table, Boolean leftFork, Boolean rightFork) {
		this.name = name;
		this.table = table;
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}

	@Override
	public void run() {
		int countEat = 0;
		while (countEat < 3) {
			countEat = eating(countEat);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private int eating(int n) {
		if (table.takeForksForEating(leftFork, rightFork)) {
			System.out.println(name + " похавал " + (n + 1) + "раз");
			return ++n;
		}
		return n;
	}

	@Override
	public String toString() {
		return name;
	}

}

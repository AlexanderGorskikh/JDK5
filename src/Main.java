
public class Main {
	public static void main(String[] args) {
		Thread table = new Thread(new Table(5)); // 5 количество философов
		table.start();
	}
}

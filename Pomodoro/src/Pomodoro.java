import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Pomodoro {
	
	public static void main(String[] args) {
		
		ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
		System.out.println("Let's study!!");
		es.scheduleAtFixedRate(new Task(), 0, 1, TimeUnit.SECONDS);
		
	}
}
	
class Task implements Runnable{
	
	Scanner scanner = new Scanner(System.in);
	ScheduledExecutorService es = Executors.newScheduledThreadPool(1);
	public void run() {
		
		for (int i = 0; i < 3; i++) {
			
			es.schedule(() -> System.out.println("Time to take a break!\nPress enter: "), 
					25, 
					TimeUnit.MINUTES);
			scanner.nextLine();
			es.schedule(() -> System.out.println("Go back to work, peon!\nPress enter: "),
					5,
					TimeUnit.MINUTES);
			scanner.nextLine();
		}
		
		es.schedule(() -> System.out.println("Time to take a break!\nPress enter: "),
					25,
					TimeUnit.MINUTES);
		scanner.hasNextLine();
		es.schedule(() -> System.out.println("Go back to work, peon!\nPress enter: "),
					30,
					TimeUnit.MINUTES);
		scanner.nextLine();
	}
	
	public static void main(String[] args) {
		(new Thread(new Task())).start();	
	}
}



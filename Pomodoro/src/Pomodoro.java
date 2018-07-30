import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.*;

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
			play("alarm.wav");
			scanner.nextLine();
			es.schedule(() -> System.out.println("Go back to work, peon!\nPress enter: "),
					5,
					TimeUnit.MINUTES);
			play("alarm.wav");
			scanner.nextLine();
		}
		
		es.schedule(() -> System.out.println("Time for a BIG BREAK!!!\nPress enter: "),
					25,
					TimeUnit.MINUTES);
		play("alarm.wav");
		scanner.hasNextLine();
		es.schedule(() -> System.out.println("Go back to work, peon!\nPress enter: "),
					30,
					TimeUnit.MINUTES);
		play("alarm.wav");
		scanner.nextLine();
	}
	
	public static void main(String[] args) {
		(new Thread(new Task())).start();	
	}
	
	public static void play(String fileName) {
		
		try {
			File soundFile = new File(fileName);
			AudioInputStream soundIn = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = soundIn.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip.open(soundIn);
			clip.start();
			while(clip.isRunning()) {
				Thread.yield();
			}
		}
		catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
	}
}







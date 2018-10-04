import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.*;
import java.util.concurrent.TimeUnit;

public class Pomodoro {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
        BoundedCounter minutes = new BoundedCounter(60);
        BoundedCounter seconds = new BoundedCounter(60);
        
		System.out.println("Let's study!!");

		
		while (true) {
			for (int i = 0; i < 3; i++) {
				countdown(25, minutes, seconds);	
				if (i != 2){ 			//skip this in the third iteration so that it won't print
								//this line AND line 30
					System.out.println("Time for a break!\nPress Enter: ");
					scanner.nextLine();
					countdown(5, minutes, seconds);
					System.out.println("Go back to work, peon!!\nPress Enter: ");
					scanner.nextLine();	
				}

			}
			System.out.println("Time for a BIG BREAK!!!\nPress Enter: ");
			scanner.nextLine();
			
			countdown(30, minutes, seconds);
			System.out.println("Go back to work, peon!!\nPress Enter: ");
			scanner.nextLine();
		}
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
	
	public static void countdown(int howLong, BoundedCounter minutes, BoundedCounter seconds) {
		
		minutes.setCounter(howLong);		
		seconds.setCounter(0);
		do	{
			try {			
				System.out.println( minutes + ":" + seconds );
		        //TimeUnit.SECONDS.sleep(1);
		        TimeUnit.MILLISECONDS.sleep(1);
		        seconds.next(); //decrements seconds by 1
		        if(seconds.getCounter() == 59)
		            minutes.next(); //decrements minutes by 1		        
			}	
			catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		} while (!(minutes.getCounter() == 59 && seconds.getCounter() == 59)); 
		//comparing to 59 so that it will print 00:00 as well
		play("alarm.wav");
	}
}

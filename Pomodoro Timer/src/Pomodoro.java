import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.*;
import java.util.concurrent.TimeUnit;

public class Pomodoro {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Let's study!!");
		
		while (true) {
			for (int i = 0; i < 3; i++) {
				
				try {
					TimeUnit.MINUTES.sleep(25);
				}	
				catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				play("alarm.wav");
				System.out.println("Time for a break!\nPress Enter: ");
				scanner.nextLine();
				
				try {
					TimeUnit.MINUTES.sleep(5);
				}	
				catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				if (i != 2){ 			//skip this in the third iteration so that it won't print
								//this line AND line 40
					play("alarm.wav");
					System.out.println("Go back to work, peon!!\nPress Enter: ");
					scanner.nextLine();	
				}

			}
			play("alarm.wav");
			System.out.println("Time for a BIG BREAK!!!\nPress Enter: ");
			scanner.nextLine();
			
			try {
				TimeUnit.MINUTES.sleep(30);
			}	
			catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			play("alarm.wav");
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
}

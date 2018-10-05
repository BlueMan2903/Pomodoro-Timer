import java.util.Scanner;

public class Pomodoro {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		Timer TwentyFiveMinutesTimer = new Timer(25,0);
		Timer FiveMinutesTimer = new Timer(5,0);
		Timer ThirtyMinutesTimer = new Timer(30,0);
        
		System.out.println("Let's study!!");

		
		while (true) {
			for (int i = 0; i < 3; i++) {	
				TwentyFiveMinutesTimer.start();
				
				if (i != 2){ 			//skip this in the third and last iteration								
					System.out.println("Time for a break!\nPress Enter: ");
					scanner.nextLine();
					FiveMinutesTimer.start();
					System.out.println("Go back to work, peon!!\nPress Enter: ");
					scanner.nextLine();	
				}
			}
			System.out.println("Time for a BIG BREAK!!!\nPress Enter: ");
			scanner.nextLine();			
			ThirtyMinutesTimer.start();
			System.out.println("Go back to work, peon!!\nPress Enter: ");
			scanner.nextLine();
		}
		
	}
}

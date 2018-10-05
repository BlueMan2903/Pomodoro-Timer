import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/*
 * A class used to represent the timer in the console
 */

public class Timer {
    private int minCounter, secCounter; /*these are used for representing the timer, therefore
    they will change every seconds/minute */
    private final int minutes, seconds; //and these are used to reset the timer
    
    public Timer(int min, int sec){
        this.minCounter = minutes = min;
        this.secCounter = seconds = sec;
        
    }
    
    // decrease seconds by 1 
    public void next(){
    	
    	if (this.secCounter == 0) {
    		this.secCounter = 60;
    		this.minCounter--;
    	}
    	this.secCounter--;
    }
    
    public void start() {
    	//start the count down
    	while (this.minCounter != 0 || this.secCounter != 0) {
    		
    		try {
    			this.print();
    			TimeUnit.SECONDS.sleep(1);
    			this.next();
    		}
    		catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
    	}
		play("alarm.wav");
		//reset
		this.secCounter = this.seconds;
		this.minCounter = this.minutes;
    }
        
    //numbers that are smaller than 10 will have the "0" prefix e.g: 01, 02, 03
    public void print() {
    	if (this.minCounter < 10 && this.secCounter < 10)
    		System.out.println("0" + this.minCounter + ":0" + this.secCounter);
    	else if (this.secCounter < 10)
    		System.out.println(this.minCounter + ":0" + this.secCounter);
    	else if (this.minCounter < 10)
    		System.out.println("0" + this.minCounter + ":" + this.secCounter);
    	else
    		System.out.println(this.minCounter + ":" + this.secCounter);
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
    
    public int getSecCounter() {return this.secCounter;}
    
    public void setSecCounter(int n){
        if (n >= 0 && n <= 60)
            this.secCounter = n;
    }
    
    public int getMinCounter() {return this.minCounter;}
    
    public void setMinCounter(int n){
        if (n >= 0 && n <= 60)
            this.secCounter = n;
    }
}

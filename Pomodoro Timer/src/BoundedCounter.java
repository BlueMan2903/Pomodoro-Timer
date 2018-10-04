/*
 * A class used to represent the timer in the console
 */

public class BoundedCounter {
    private int counter;
    private int upperBound;
    
    public BoundedCounter(int upperBound){
        this.counter = 0;
        this.upperBound = upperBound;
    }
    
    // count down from 59 to 0 then reset back to 59
    public void next(){
    	if (this.counter == 0)
    		this.counter = this.upperBound;
    	this.counter--;
    }
    
    //numbers that are smaller than 10 will have the "0" prefix e.g: 01, 02, 03
    public String toString(){
        return (this.counter < 10) ? ("0" + this.counter) : ("" + this.counter);
    }
    
    public int getCounter() {
     return this.counter;
    }
    
    public void setCounter(int n){
        if (n >= 0 && n <= this.upperBound)
            this.counter = n;
    }
    
}

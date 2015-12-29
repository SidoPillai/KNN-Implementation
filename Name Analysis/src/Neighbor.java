import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Neighbor implements Comparable<Neighbor>{
	
	Double diceValue;
	String name;
	String country;
	/**
	 * 
	 * 
	 * @param name
	 * @param diceValue
	 * @param country
	 * 		  Constructor
	 * 
	 **/
	
	Neighbor(String name, double diceValue, String country){
		this.name=name;
		this.diceValue=diceValue;
		this.country=country;
		
	}
	/**
	 * 
	 * 
	 * @param o
	 * @return nearestNeighbors    
	 * Comparing a neighbor with another neighbor
	 **/
	
	@Override
	public int compareTo(Neighbor o) {
		return o.diceValue.compareTo(this.diceValue);
	}

	

}

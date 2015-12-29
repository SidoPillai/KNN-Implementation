import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

;

public class Input {

	String targetName;
	
	private ArrayList<Neighbor> listOfNeighbors = new ArrayList<Neighbor>();

	private Neighbor[] nearestNeighbors;
	private int k;

	String result;
	Row row;

	Input(String targetName, int k) {
		this.targetName = targetName;
		this.k = k;
		nearestNeighbors = new Neighbor[k];

	}
	/**
	 * @param firstName
	 *            Setter function for result row
	 **/

	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @param r
	 * 
	 *            Set each row
	 **/

	public void setRow(Row r) {
		this.row = r;
	}
	/**
	 * 
	 *            Get each result
	 **/
	
	public String getResult() {
		return this.result;
	}
	/**
	 * 
	 *            Get each result
	 **/

	public String[] getRow() {
		String [] values = new String[3];
		values[0] = this.row.country;
		values[1] = this.row.name;
		values[2] = this.row.gender;
		return values;
	}
	/**
	 * 
	 * 
	 * @param diceValue
	 * @param trainingName
	 * @param country
	 * 
	 * 
	 *            make the list of neighbors 
	 * 
	 * 
	 **/
	
	public void add(double diceValue, String trainingName, String country) {

		Neighbor neighbors = new Neighbor(trainingName, diceValue, country);

		listOfNeighbors.add(neighbors);

	}
	/**
	 * 
	 *            find all nearest neighbors
	 * 
	 * 
	 **/
	
	public void findNearestNeighbors() {

		nearestNeighbors = nearestNeighbors(listOfNeighbors, k);

	}
	/**
	 * 
	 * 
	 * @param listOfNeighbors
	 * @param k
	 * @return nearestNeighbors    
	 * 		   return the set of nearest neighbors
	 * 
	 **/
	
	private Neighbor[] nearestNeighbors(ArrayList<Neighbor> listOfNeighbors,
			int k) {
		Collections.sort(listOfNeighbors);

		Neighbor[] nearestNeighbors = new Neighbor[k];

		for (int i = 0; i < k; i++) {
			nearestNeighbors[i] = listOfNeighbors.get(i);
		}

		return nearestNeighbors;
	}
	/**
	 * 
	 * 
	 * @param country 
	 
	 * @return indexCountry    
	 * 
	 *            find class label
	 * 
	 * 
	 **/

	public int findNeighbor(String[] country) {
		int indexCountry;
		indexCountry = votingCountry(nearestNeighbors, country, k);

		return indexCountry;

	}
	
	/**
	 * 
	 * 
	 * @param nearestNeighbors
	 * @param country
	 * @param k
	 * 
	 * @return indexCountry     
	 * 			majority voting among the neighbors
	 * 
	 * 
	 **/
	
	private int votingCountry(Neighbor[] nearestNeighbors, String[] country,
			int k) {

		int indexCountry;

		int[] countCountry = new int[country.length];

		for (int i = 0; i < countCountry.length; i++) {
			countCountry[i] = 0;
		}

		for (int i = 0; i < k; i++) {
			String temp = "";
			temp = nearestNeighbors[i].country;

			if (temp.equals(country[0])) {
				countCountry[0]++;
			} else if (temp.equals(country[1])) {
				countCountry[1]++;
			} else if (temp.equals(country[2])) {

				countCountry[2]++;
			}
		}

		indexCountry = computeHighestVote(countCountry);
		return indexCountry;
	}
	
	/**
	 * 
	 * 
	 * @param countCountry
	 * @return index    
	 * 			 Compute highest vote
	 * 			  random occurrence if equal
	 * 
	 **/	 
	
	private int computeHighestVote(int[] countCountry) {
		int index = -1;
		Random random = new Random();
		int max = -1;
		for (int i = 0; i < countCountry.length; i++) {
			if (countCountry[i] > max) {
				max = countCountry[i];
				index = i;
			} else if (countCountry[i] == max) {
				int select = (random.nextInt()) % 2;
				if (select == 0) {
					index = i;
				}
			}

		}

		return index;
		
	}

}

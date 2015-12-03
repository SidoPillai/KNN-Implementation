
public class Row {
	
	String name;
	String gender;
	String country;
	
	public Row(String[] in){
		this.name = in[0];
		this.gender = in[1];
		this.country = in[2];
	}

	
	public String getCSV(){
		return name+","+gender+","+country;
	}
}

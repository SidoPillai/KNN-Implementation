package datacleaning;
public class Row {
	
	String name;
	String gender;
	String country;
	
	public Row(String[] in){
		this.name = in[0];
		this.gender = in[1];
		this.country = in[2];
	}
	
	private String getFirstName(){
		String[] sp = name.split(" ");
		return sp[0];
	}
	
	private String checkVan(){
		//if there is van, return it after concatinating
		String temp="";
		String[] sp = name.split(" ");
		
		boolean special=true;
		for(int i=0;i<sp.length;i++){
			if(sp[i].equalsIgnoreCase("van")|| sp[i].equalsIgnoreCase("de")){
				 for(int j=i;j<sp.length;j++){
					 temp= temp + sp[j];
					 
				 }
				 return temp;
			}
		}
		
		
		//if no special case
		return null;
	}
	
	private String getLastName(){
		if(checkVan()!=null){
			return checkVan();
		}
		
		String[] sp = name.split(" ");
		return sp[sp.length-1];
	}
	
	public String getLastCSV(){
		return getLastName()+","+gender+","+country;
	}
	
	public String getFirstCSV(){
		return getFirstName()+","+gender+","+country;
	}
}

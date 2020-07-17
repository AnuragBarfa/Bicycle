package learnDesign2;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
class Part{
	private String name;
	private String description;
	private boolean needs_spare;
	public Part(HashMap<String,Object> params){
		System.out.println("in Part");
		this.name=(String)params.get("name");
		this.description=(String)(params.get("description"));
		this.needs_spare=(boolean)(params.get("needs_spare")!=null?params.get("chain"):default_needs());
	}
	public boolean default_needs(){
		return true;
	}
	public boolean get_needs(){
		return this.needs_spare;
	}
}
class Parts{
	ArrayList<Part> parts;
	public Parts(HashMap<String,Object> params){
		parts=(ArrayList<Part>)params.get("parts");
	}
	public ArrayList<Part> spares(){
		ArrayList<Part> output=parts.stream().filter(x->x.get_needs()).collect(Collectors.toCollection(ArrayList::new));
		return output;
	}
	public int size(){
		return parts.size();
	}

}
/**
* An <code>Bicycle</code> give abstract defination of Bicycle's behavious and store common information related to bicycle chain,size, tire_size
* @author Anurag Barfa
*/
class Bicycle{
	private String size;
	private Parts parts;
	public Bicycle(HashMap<String,Object> params){
		System.out.println("in bicycle");
		this.size=(String)params.get("size");
		this.parts=(Parts)(params.get("parts"));
	}
	/**
	* Calculate size of Bicycle
	* @return (String)size 
	*/
	public String getSize(){
		return this.size;
	}

	/**
	* Calculate spare parts of Bicycle
	* @return (HashMap<String,Object>)output 
	*/
	public ArrayList<Part> spares(){
		return parts.spares();
	}
}
public class OODesign2{
	public static void main(String[] args){
		HashMap<String,Object> chain_params=new HashMap<String,Object>();
		chain_params.put("name","chain");
		chain_params.put("description","10-speed");
		Part chain=new Part(chain_params);

		HashMap<String,Object> road_tire_params=new HashMap<String,Object>();
		road_tire_params.put("name","tire_size");
		road_tire_params.put("description","23");
		Part road_tire=new Part(road_tire_params);

		HashMap<String,Object> tape_params=new HashMap<String,Object>();
		tape_params.put("name","tape_color");
		tape_params.put("description","red");
		Part tape=new Part(tape_params);

		HashMap<String,Object> red_bike_parts_params=new HashMap<String,Object>();
		red_bike_parts_params.put("parts",new ArrayList<>(Arrays.asList(chain,road_tire,tape)));
		Parts red_bike_parts = new Parts(red_bike_parts_params);

		HashMap<String,Object> bicycle_params=new HashMap<>();
		bicycle_params.put("size","M");
		bicycle_params.put("parts",red_bike_parts);
		Bicycle road_bike=new Bicycle(bicycle_params);
		System.out.println(road_bike.spares());

		
	}
}
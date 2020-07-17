package learnDesign3;
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
class PartsFactory{
	public static Parts build(ArrayList<ArrayList<String> >  configs){
		HashMap<String,Object> parts_params=new HashMap<>();
		parts_params.put("parts",configs.stream().map(x->create_part(x)).collect(Collectors.toCollection(ArrayList::new)));
		return new Parts(parts_params);	
	}
	public static Part create_part(ArrayList<String> config){
		HashMap<String,Object> part_params=new HashMap<>();
		part_params.put("name",config.get(0));
		part_params.put("description",config.get(1));
		return new Part(part_params);
	}
}
public class OODesign3{
	public static void main(String[] args){
		ArrayList<ArrayList<String> > road_config=new ArrayList<>();
		road_config.add(new ArrayList<String>(Arrays.asList("chain","10-speed")));
		road_config.add(new ArrayList<String>(Arrays.asList("tire_size","20")));
		road_config.add(new ArrayList<String>(Arrays.asList("tape_color","red")));

		ArrayList<ArrayList<String> > mountain_config=new ArrayList<>();
		mountain_config.add(new ArrayList<String>(Arrays.asList("chain","10-speed")));
		mountain_config.add(new ArrayList<String>(Arrays.asList("tire_size","2.1")));
		mountain_config.add(new ArrayList<String>(Arrays.asList("front_shock","Manitau")));
		mountain_config.add(new ArrayList<String>(Arrays.asList("rear_shock","Fox")));

		Parts parts=PartsFactory.build(mountain_config);
		System.out.println(parts);

		// HashMap<String,Object> chain_params=new HashMap<String,Object>();
		// chain_params.put("name","chain");
		// chain_params.put("description","10-speed");
		// Part chain=new Part(chain_params);

		// HashMap<String,Object> road_tire_params=new HashMap<String,Object>();
		// road_tire_params.put("name","tire_size");
		// road_tire_params.put("description","23");
		// Part road_tire=new Part(road_tire_params);

		// HashMap<String,Object> tape_params=new HashMap<String,Object>();
		// tape_params.put("name","tape_color");
		// tape_params.put("description","red");
		// Part tape=new Part(tape_params);

		// HashMap<String,Object> red_bike_parts_params=new HashMap<String,Object>();
		// red_bike_parts_params.put("parts",new ArrayList<>(Arrays.asList(chain,road_tire,tape)));
		// Parts red_bike_parts = new Parts(red_bike_parts_params);

		// HashMap<String,Object> bicycle_params=new HashMap<>();
		// bicycle_params.put("size","M");
		// bicycle_params.put("parts",red_bike_parts);
		// Bicycle road_bike=new Bicycle(bicycle_params);
		// System.out.println(road_bike.spares());

		
	}
}
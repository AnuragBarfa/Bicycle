package learnDesign;
import java.util.HashMap;

/**
* An <code>CircularObject</code> interface give abstratc behaviour for circular objects
* @author Anurag Barfa
* @version1.1
*/
interface CircularObject{
	/**
	* @return (int)diameter   
	*/
	public int diameter();
}
/**
* An <code>Wheel</code> object store information of rim, tire dimensions
* @author Anurag Barfa
* @version1.1
*/
class Wheel implements CircularObject{
	private int rim;
	private int tire;
	public Wheel(HashMap<String,Object> params){
		this.rim=(int)params.get("rim");
		this.tire=(int)params.get("tire");
	}
	/**
	* Calculate rim of wheel
	* @return (int)rim  
	*/
	public int getRim(){
		return this.rim;
	}
	/**
	* Calculate diameter of wheel
	* @return (int)diameter  
	*/
	public int diameter(){
		return getRim()+2*tire;
	}
	/**
	* Calculate circumference of wheel
	* @return (int)circumference  
	*/
	public double circumference(){
		return diameter()*3.14;
	}
}
/**
* An <code>Gear</code> object store information of chain,cog, wheel
* @author Anurag Barfa
* @version1.1
*/
class Gear{
	private int chainring;
	private int cog;
	private CircularObject wheel;
	public Gear(int chainring,int cog,CircularObject wheel){
		this.chainring=chainring;
		this.cog=cog;
		this.wheel=wheel;
	}
	/**
	* Calculate ratio for gear
	* @return (double)ratio  
	*/
	public double ratio(){
		return (double)this.chainring/(double)this.cog;
	}
	/**
	* Calculate gear_inches for gear
	* @return (double)gear_inches  
	*/
	public double gear_inches(){
		return ratio()*wheel.diameter();
	}
}
/**
* An <code>Wrappers</code> class contain wrapper methods useful in case when input format of function(fixed no of arguments) used is different from what we use(map) and we can not modify the function 
* @author Anurag Barfa
* @version1.1
*/
class Wrapper{
	/**
	* wrappers to messages for which Gear(assume gear is class which can not be modified so we need) can respond 
	*/
	static class GearWrapper{
		public static Gear gear(HashMap<String,Object> params){
			return new Gear((int)params.get("chainring"), (int)params.get("cog"),
				(CircularObject)params.get("wheel"));
		}
	}
}
class Bicycle{
	private String size;
	// private String type_color;
	public Bicycle(HashMap<String,Object> params){
		this.size=(String)params.get("size");
		// this.type_color=(String)params.get("type_color");
	}
	public HashMap<String,Object> spares(){
		HashMap<String,Object> output=new HashMap<>();
		output.put("chain","10-speed");
		output.put("tire_size",23);
		// output.put("type_color",this.type_color);
		return output;
	}
}
class RoadBicycle extends Bicycle{
	private String type_color;
	public RoadBicycle(HashMap<String,Object> params){
		super(params);
		this.type_color=(String)params.get("type_color");
	}
}
class MountainBicycle extends Bicycle{
	private String front_shock;
	private String rear_shock;
	public MountainBicycle(HashMap<String,Object> params){
		super(params);
		this.front_shock=(String)params.get("front_shock");
		this.rear_shock=(String)params.get("rear_shock");
	}
}
public class OODesign{
	public static void main(String[] args){
		HashMap<String,Object> wheel_params=new HashMap<>();
		wheel_params.put("rim",26);
		wheel_params.put("tire",2);
		Wheel wheel=new Wheel(wheel_params);

		HashMap<String,Object> gear_params=new HashMap<>();
		gear_params.put("chainring",17);
		gear_params.put("cog",7);
		gear_params.put("wheel",wheel);
		Gear gear=Wrapper.GearWrapper.gear(gear_params);

		System.out.println(gear.ratio());
		System.out.println(gear.gear_inches());

		HashMap<String,Object> bicycle_params=new HashMap<>();
		bicycle_params.put("size","M");
		bicycle_params.put("type_color","red");
		RoadBicycle bicycle=new RoadBicycle(bicycle_params);
		System.out.println(bicycle.spares().get("chain"));

		HashMap<String,Object> bicycle_params2=new HashMap<>();
		bicycle_params2.put("size","S");
		bicycle_params2.put("front_shock","Monitau");
		bicycle_params2.put("rear_shock","Fox");
		MountainBicycle bicycle2=new MountainBicycle(bicycle_params2);
		System.out.println(bicycle2.spares().get("chain"));
	}
}
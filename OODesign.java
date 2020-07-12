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
	* wrappers to messages for which Gear(assume gear is class which can not be modified) can respond 
	*/
	static class GearWrapper{
		public static Gear gear(HashMap<String,Object> params){
			return new Gear((int)params.get("chainring"), (int)params.get("cog"),
				(CircularObject)params.get("wheel"));
		}
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
	}
}
package learnDesign;

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
	public Wheel(int rim,int tire){
		this.rim=rim;
		this.tire=tire;
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
public class OODesign{
	public static void main(String[] args){
		Wheel wheel=new Wheel(26,2);
		Gear gear=new Gear(12,7,wheel);
		System.out.println(gear.ratio());
		System.out.println(gear.gear_inches());
	}
}
package learnDesign;
class Wheel{
	private int rim;
	private int tire;
	public Wheel(int rim,int tire){
		this.rim=rim;
		this.tire=tire;
	}
	public int diameter(){
		return rim+2*tire;
	}
	public double circumference(){
		return diameter()*3.14;
	}
}
class Gear{
	private int chainring;
	private int cog;
	private Wheel wheel;
	public Gear(int chainring,int cog,Wheel wheel){
		this.chainring=chainring;
		this.cog=cog;
		this.wheel=wheel;
	}
	public double ratio(){
		return (double)this.chainring/(double)this.cog;
	}
	public double gear_inches(){
		return ratio()*wheel.diameter();
	}
}
class OODesign{
	public static void main(String[] args){
		Wheel wheel=new Wheel(26,2);
		Gear gear=new Gear(12,7,wheel);
		System.out.println(gear.ratio());
		System.out.println(gear.gear_inches());
	}
}
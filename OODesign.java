package learnDesign;
class Gear{
	private int chainring;
	private int cog;
	private int rim;
	private int tire;
	public Gear(int chainring,int cog,int rim,int tire){
		this.chainring=chainring;
		this.cog=cog;
		this.rim=rim;
		this.tire=tire;
	}
	public double ratio(){
		return (double)this.chainring/(double)this.cog;
	}
}
class OODesign{
	public static void main(String[] args){
		Gear gear=new Gear(12,7,6,56);
		System.out.println(gear.ratio());

	}
}
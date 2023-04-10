package dndDamage;

public class Damage {
	
	private DamageType damageType;
	private double num;
	
	public Damage(double num, DamageType damageType) {
		this.num = num;
		this.damageType = damageType;
	}
	
	public String toString() {
		if(num == 1) {
			return ((int)Math.floor(num)) + " point of " + damageType + " damage";
		} else {
			return ((int)Math.floor(num)) + " points of " + damageType + " damage";
		}
		
	}
	
}

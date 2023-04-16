package dndDamage;

public class Damage {
	
	private double num;
	private DamageType damageType;
	
	public Damage(double num, DamageType damageType) {
		this.num = num;
		this.damageType = damageType;
	}
	
	public void add(Damage other) throws IllegalArgumentException {
		if(this.damageType != other.damageType) {
			throw new IllegalArgumentException();
		} else {
			this.num += other.num;
		}
	}
	
	public double getNum() {
		return num;
	}
	
	public DamageType getDamageType() {
		return damageType;
	}
	
	public String toString() {
		if(num == 1) {
			return ((int)Math.floor(num)) + " point of " + damageType + " damage";
		} else {
			return ((int)Math.floor(num)) + " points of " + damageType + " damage";
		}
	}
	
}

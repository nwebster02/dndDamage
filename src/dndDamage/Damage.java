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
	
	public void divide(int val) {
		num /= val;
	}
	
	public double getNum() {
		return num;
	}
	
	public DamageType getDamageType() {
		return damageType;
	}
	
	public String toString() {
		if(Math.abs(num - 1.0) <  0.01) {
			return "1.0 point of " + damageType + " damage";
		} else {
			return (Math.round(num * 100) / 100.0) + " points of " + damageType + " damage";
		}
	}
	
}

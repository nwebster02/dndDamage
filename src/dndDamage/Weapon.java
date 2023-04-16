package dndDamage;

public class Weapon extends DamageDealer {
	
	int critNeg;
	int critPos;
	
	Weapon(String name, String damageString) {
		super(name, damageString);
		critNeg = 1;
		critPos = 20;
	}
	
	public void setCrit(int critNeg, int critPos) {
		this.critNeg = critNeg;
		this.critPos = critPos;
	}
	
}

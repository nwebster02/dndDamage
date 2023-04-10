package dndDamage;

import java.util.ArrayList;

public abstract class DamageDealer {

	private int[] diceAmount;
	private int[] diceSides;
	private int[] constants;
	private DamageType[] diceDamageType;

	public DamageDealer(int amount, int sides, int constant, DamageType damageType) {
		diceAmount = new int[1];
		diceSides = new int[1];
		constants = new int[1];
		diceDamageType = new DamageType[1];

		diceAmount[0] = amount;
		diceSides[0] = sides;
		constants[0] = constant;
		diceDamageType[0] = damageType;
	}

	public DamageDealer(int[] diceAmount, int[] diceSides, int[] constants, DamageType[] diceDamageType)
			throws IllegalArgumentException {
		if (diceAmount.length == diceSides.length && diceAmount.length == constants.length
				&& diceAmount.length == diceDamageType.length) {
			this.diceAmount = diceAmount;
			this.diceSides = diceSides;
			this.constants = constants;
			this.diceDamageType = diceDamageType;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public ArrayList<Damage> getAverageDamage(boolean crit) {
		ArrayList<Damage> damageList = new ArrayList<Damage>();
		for(int i = 0; i < diceAmount.length; i++) {
			double damage;
			if(crit) {
				damage = 2 * diceAmount[i] * ((diceSides[i] / 2.0) + 0.5) + constants[i];
			} else {
				damage = diceAmount[i] * ((diceSides[i] / 2.0) + 0.5) + constants[i];
			}
			damageList.add(new Damage(damage, diceDamageType[i]));
		}
		return damageList;
	}

	public double getTotalAverageDamage(boolean crit) {
		double total = 0;
		for(int i = 0; i < diceAmount.length; i++) {
			double damage;
			if(crit) {
				damage = 2 * diceAmount[i] * ((diceSides[i] / 2.0) + 0.5) + constants[i];
			} else {
				damage = diceAmount[i] * ((diceSides[i] / 2.0) + 0.5) + constants[i];
			}
			total += damage;
		}
		return total;
	}

	public ArrayList<Damage> rollDamage(boolean crit) {
		ArrayList<Damage> damageList = new ArrayList<Damage>();
		for(int i = 0; i < diceAmount.length; i++) {
			int damage = rollDice(diceAmount[i], diceSides[i]) + constants[i];;
			if(crit) {
				damage += rollDice(diceAmount[i], diceSides[i]);
			}
			damageList.add(new Damage(damage, diceDamageType[i]));
		}
		return damageList;
	}

	public int rollTotalDamage(boolean crit) {
		int total = 0;
		for(int i = 0; i < diceAmount.length; i++) {
			int damage = rollDice(diceAmount[i], diceSides[i]) + constants[i];;
			if(crit) {
				damage += rollDice(diceAmount[i], diceSides[i]);
			}
			total += damage;
		}
		return total;
	}

	private int rollDice(int amount, int sides) {
		int total = 0;
		for (int i = 0; i < amount; i++) {
			total += (int) (Math.random() * sides + 1);
		}
		return total;
	}

}

package dndDamage;

import java.util.ArrayList;

public abstract class DamageDealer implements Roll{
	
	private String name;
	private int[] diceAmount;
	private int[] diceSides;
	private int[] constants;
	private DamageType[] diceDamageType;
	
	public DamageDealer() throws IllegalArgumentException {
		throw new IllegalArgumentException();
	}
	
	public DamageDealer(String name, String damageString) {
		this.name = name;
		interpretString(damageString);
	}
	
	public DamageDealer(String name, int amount, int sides, int constant, DamageType damageType) {
		this.name = name;
		diceAmount = new int[1];
		diceSides = new int[1];
		constants = new int[1];
		diceDamageType = new DamageType[1];

		diceAmount[0] = amount;
		diceSides[0] = sides;
		constants[0] = constant;
		diceDamageType[0] = damageType;
	}

	public DamageDealer(String name, int[] diceAmount, int[] diceSides, int[] constants, DamageType[] diceDamageType)
			throws IllegalArgumentException {
		if (diceAmount.length == diceSides.length && diceAmount.length == constants.length
				&& diceAmount.length == diceDamageType.length) {
			this.name = name;
			this.diceAmount = diceAmount;
			this.diceSides = diceSides;
			this.constants = constants;
			this.diceDamageType = diceDamageType;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public ArrayList<Damage> rollDamage(boolean crit) {
		ArrayList<Damage> damageList = new ArrayList<Damage>();
		for(int i = 0; i < diceAmount.length; i++) {
			int damage = rollDice(diceAmount[i], diceSides[i]) + constants[i];;
			if(crit) {
				damage += rollDice(diceAmount[i], diceSides[i]);
			}
			damageList.add(new Damage(Math.min(1, damage), diceDamageType[i]));
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
		return Math.min(1, total);
	}
	
	private void interpretString(String input) {
		String[] damages = input.split(" + ");
		int size = damages.length;
		diceAmount = new int[size];
		diceSides = new int[size];
		constants = new int[size];
		diceDamageType = new DamageType[size];
		for(int i = 0; i < size; i++) {
			String[] split = damages[i].split(" ");
			diceDamageType[i] = DamageType.valueOf(split[1]);
			if(split[0].contains("+")) {
				split = split[0].split("+");
				constants[i] = Integer.parseInt(split[1]);
			} else if (split[0].contains("-")) {
				split = split[0].split("-");
				constants[i] = Integer.parseInt(split[1]) * -1;
			}
			if(split[0].contains("d")) {
				split = split[0].split("d");
				diceSides[i] = Integer.parseInt(split[1]);
				diceAmount[i] = Integer.parseInt(split[0]);
			} else {
				constants[i] = Integer.parseInt(split[0]);
			}
			
		}
	}

}

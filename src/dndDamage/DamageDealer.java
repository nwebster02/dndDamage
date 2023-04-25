package dndDamage;

import java.util.ArrayList;

public abstract class DamageDealer {
	
	private String name;
	private ArrayList<ArrayList<Dice>> dice;
	private ArrayList<Integer> constants;
	private ArrayList<DamageType> diceDamageType;
	
	public DamageDealer(String name, String damageString) {
		this.name = name;
		interpretString(damageString);
	}

	public ArrayList<Damage> rollDamage(boolean crit) {
		ArrayList<Damage> damageList = new ArrayList<Damage>();
		for(int i = 0; i < diceDamageType.size(); i++) {
			ArrayList<Dice> diceList = dice.get(i);
			int total = constants.get(i);
			for(Dice d: diceList) {
				total += d.roll();
				if(crit) {
					total += d.roll();
				}
			}
			damageList.add(new Damage(Math.min(1, total), diceDamageType.get(i)));
		}
		return damageList;
	}

	public int rollTotalDamage(boolean crit) {
		ArrayList<Damage> damageList = rollDamage(crit);
		int total = 0;
		for(Damage d: damageList) {
			total += d.getNum();
		}
		return total;
	}
	
	private void interpretString(String input) {
		String[] damages = input.split(" \\+ "); //Different Damages
		int size = damages.length;
		dice = new ArrayList<ArrayList<Dice>>(size);
		constants = new ArrayList<Integer>(size);
		diceDamageType = new ArrayList<DamageType>(size);
		for(int i = 0; i < size; i++) {
			String[] split = damages[i].split(" "); //Damage and Type
			diceDamageType.add(i, DamageType.valueOf(split[1].toUpperCase())); 
			String[] diceVals = split[0].split("[+-]");
			ArrayList<Dice> diceList = new ArrayList<Dice>();
			for(String s: diceVals) {
				if(s.contains("d")) {
					diceList.add(new Dice(s));
				} else {
					if(split[0].contains("-")) {
						constants.add(i, -1 * Integer.parseInt(s));
					} else {
						constants.add(i, Integer.parseInt(s));
					}
				}
			}
			dice.add(i, diceList);
		}
	}

}

package dndDamage;

import java.util.ArrayList;

public abstract class DamageDealer {
	
	private String name;
	private String damageString;
	private ArrayList<ArrayList<Dice>> dice;
	private ArrayList<Integer> constants;
	private ArrayList<DamageType> diceDamageType;
	
	public DamageDealer(String name, String damageString) {
		this.name = name;
		this.damageString = damageString;
		interpretString(damageString);
	}

	public DamageList rollDamage(boolean crit) {
		DamageList damageList = new DamageList();
		for(int i = 0; i < diceDamageType.size(); i++) {
			ArrayList<Dice> diceList = dice.get(i);
			int total = constants.get(i);
			for(Dice d: diceList) {
				total += d.roll();
				if(crit) {
					total += d.roll();
				}
			}
			damageList.add(new Damage(Math.max(1, total), diceDamageType.get(i)));
		}
		return damageList;
	}

	public int rollTotalDamage(boolean crit) {
		DamageList damageList = rollDamage(crit);
		int total = 0;
		for(Damage d: damageList.getList()) {
			total += d.getNum();
		}
		return Math.max(1, total);
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
					} else if(split[0].contains("+")) {
						constants.add(i, Integer.parseInt(s));
					}
				}
			}
			if(constants.size() - 1 < i) {
				constants.add(i, 0);
			}
			dice.add(i, diceList);
		}
	}
	
	public String getName() {
		return name;
	}
	
	public String getDamageString() {
		return damageString;
	}

}

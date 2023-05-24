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
			total = Math.max(1, total);
			damageList.add(new Damage(total, diceDamageType.get(i)));
		}
		return damageList;
	}

	public int rollTotalDamage(boolean crit) {
		return (int)(rollDamage(crit).getTotalDamage());
	}
	
	private void interpretString(String input) throws IllegalArgumentException {
		String[] damages = input.split(" \\+ "); //Different Damage Types
		int size = damages.length;
		dice = new ArrayList<ArrayList<Dice>>(size);
		constants = new ArrayList<Integer>(size);
		diceDamageType = new ArrayList<DamageType>(size);
		for(int i = 0; i < size; i++) {
			String[] split = damages[i].split(" "); //Damage and Type
			diceDamageType.add(i, DamageType.valueOf(split[1].toUpperCase())); 
			String[] diceVals = split[0].split("[+-]"); //Dice and Constants
			ArrayList<Dice> diceList = new ArrayList<Dice>();
			for(String s: diceVals) {
				if(s.contains("d")) { //Dice
					diceList.add(new Dice(s));
				} else if(constants.size() - 1 < i) {
					try {
						if(split[0].contains("-")) { //Negative Constant
							constants.add(i, -1 * Integer.parseInt(s));
						} else if(split[0].contains("+")) { //Positive Constant
							constants.add(i, Integer.parseInt(s));
						} else {
							constants.add(i, Integer.parseInt(s));
						}
					} catch(Error e) {
						throw new IllegalArgumentException("Incorrect format for '" + split[0] + "'.");
					}
				} else {
					throw new IllegalArgumentException("Incorrect format for '" + split[0] + "'.");
				}
			}
			if(constants.size() - 1 < i) { //If no constants included for type i.e. '1d6 slashing', add 0
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

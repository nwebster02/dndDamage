package test;

import java.util.ArrayList;
import java.util.HashMap;

import dndDamage.Dice;
import dndDamage.Weapon;
import dndDamage.Damage;
import dndDamage.DamageType;

public class WeaponTest {

	final static int ITERATION = 5000000;

	final static int PROFICIENCY = 4;
	final static int ABILITY_MOD = 6;

	final static int AC = 0;

	public static void main(String[] args) {

		Dice d20 = new Dice(1, 20);
		ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
		HashMap<String, ArrayList<Damage>> weaponDamages = new HashMap<String, ArrayList<Damage>>();
		
		weaponList.add(new Weapon("Anvyrth's Flaming Wit's End", "1d10+1d6+6 slashing + 2d6 fire"));
		weaponList.add(new Weapon("Quill's Green-Flame Blade", "1d8+1d6+3 slashing + 1d8 fire"));
		weaponList.add(new Weapon("Gunthar's Sharpshooter Crossbow w/ Bursting Bolts", "1d10+13 piercing + 2d6 force"));
		weaponList.add(new Weapon("Ilzak's Smiting Lance of Vengeance", "1d12+7 piercing + 6d8 radiant"));

		for (Weapon weapon : weaponList) {
			ArrayList<Damage> damageList = new ArrayList<Damage>();
			weaponDamages.put(weapon.getName(), damageList);
			for (int i = 0; i < ITERATION; i++) {
				int roll = d20.roll();
				ArrayList<Damage> damages;
				if (roll >= weapon.getCritPos()) {
					damages = weapon.rollDamage(true);
				} else if (roll > weapon.getCritNeg() && roll + ABILITY_MOD >= AC) {
					damages = weapon.rollDamage(false);
				} else {
					damages = new ArrayList<Damage>();
				}
				for (int j = 0; j < damages.size(); j++) {
					if (damageList.size() - 1 < j) {
						damageList.add(damages.get(j));
					} else {
						damageList.get(j).add(damages.get(j));
					}
				}
			}
			for (Damage d : damageList) {
				d.divide(ITERATION);
			}
		}

		System.out.println("Against a target with an AC of " + AC + ":");
		for (Weapon weapon : weaponList) {
			ArrayList<Damage> damageList = weaponDamages.get(weapon.getName());
			double total = 0;
			System.out.println("\n'" + weapon.getName() + "' deals");
			for (int i = 0; i < damageList.size(); i++) {
				total += damageList.get(i).getNum();
				System.out.print(damageList.get(i));
				if (i < damageList.size() - 1) {
					System.out.print(" + ");
				} else {
					System.out.print(" on average");
				}
			}
			total = Math.round(total * 100) / 100.0;
			if(damageList.size() > 1) {
				if(total - 1.0 < 1E-3) {
					System.out.println("\nfor a total of 1.0 point of damage.");
				} else {
					System.out.println("\nfor a total of " + total + " points of damage.");
				}
			} else {
				System.out.println(".");
			}
			
		}

	}

}

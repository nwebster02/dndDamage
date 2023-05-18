package test;

import java.util.ArrayList;
import java.util.HashMap;

import dndDamage.Dice;
import dndDamage.Weapon;
import dndDamage.Damage;
import dndDamage.DamageList;
import dndDamage.DamageType;

public class WeaponTest {

	final static int ITERATION = 5000000;

	final static int PROFICIENCY = 4;
	final static int ABILITY_MOD = 6;

	final static int AC = 22;

	public static void main(String[] args) {

		Dice d20 = new Dice(1, 20);
		ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
		HashMap<String, DamageList> weaponDamages = new HashMap<String, DamageList>();
		/*
		weaponList.add(new Weapon("Anvyrth's Flaming Wit's End", "1d10+1d6+6 slashing + 2d6 fire"));
		weaponList.add(new Weapon("Quill's Green-Flame Blade", "1d8+1d6+3 slashing + 2d8+4 fire"));
		weaponList.add(new Weapon("Gunthar's Sharpshooter Crossbow w/ Bursting Bolts", "1d10+13 piercing + 2d6 force"));
		weaponList.add(new Weapon("Ilzak's Smiting Lance of Vengeance", "1d12+7 piercing + 6d8 radiant"));
		weaponList.add(new Weapon("Qa'ri's Empowered Bite", "1d4+1d6+13 piercing + 2d8 psychic + 1d8 force"));
		*/
		weaponList.add(new Weapon("Eitri's Horrible Mess", "2d8+11 piercing + 4d8 radiant"));
		
		for (Weapon weapon : weaponList) {
			DamageList damageList = new DamageList();
			weaponDamages.put(weapon.getName(), damageList);
			int numHit = 0;
			for (int i = 0; i < ITERATION; i++) {
				int roll = d20.roll();
				//roll = Math.min(roll, d20.roll());
				roll = Math.max(roll, d20.roll());
				DamageList damages;
				if (roll >= weapon.getCritPos()) {
					damages = weapon.rollDamage(true);
					numHit++;
				} else if (roll > weapon.getCritNeg() && roll + ABILITY_MOD + PROFICIENCY >= AC) {
					damages = weapon.rollDamage(false);
					numHit++;
				} else {
					damages = new DamageList();
				}
				damageList.add(damages);
			}
			//damageList.divide(ITERATION);
			damageList.divide(numHit);
		}

		System.out.println("Against a target with an AC of " + AC + ":");
		for (Weapon weapon : weaponList) {
			DamageList damageList = weaponDamages.get(weapon.getName());
			ArrayList<Damage> damages = damageList.getList();
			System.out.println("\n'" + weapon.getName() + "' (" + weapon.getDamageString() + ") deals");
			for (int i = 0; i < damages.size(); i++) {;
				System.out.print(damages.get(i));
				if (i < damages.size() - 1) {
					System.out.print(" + ");
				} else {
					System.out.print(" on average");
				}
			}
			double total = damageList.getTotalDamage();
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

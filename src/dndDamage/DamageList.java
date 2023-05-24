package dndDamage;

import java.util.ArrayList;
import java.util.HashMap;

public class DamageList implements Comparable<DamageList>{

	ArrayList<Damage> list;
	HashMap<DamageType, Damage> map;
	
	public DamageList() {
		list = new ArrayList<Damage>();
		map = new HashMap<DamageType, Damage>();
	}
	
	public void add(Damage damage) {
		DamageType damageType = damage.getDamageType();
		if(map.containsKey(damageType)) {
			map.get(damageType).add(damage);
		} else {
			list.add(damage);
			map.put(damageType, damage);
		}
	}
	
	public void add(DamageList otherList) {
		for(Damage d: otherList.getList()) {
			add(d);
		}
	}
	
	public void divide(int val) {
		for(Damage d: list) {
			d.divide(val);
		}
	}
	
	public ArrayList<Damage> getList() {
		return list;
	}
	
	public int size() {
		return list.size();
	}
	
	public Damage get(int index) {
		return list.get(index);
	}

	public Damage get(DamageType damageType) {
		return map.get(damageType);
	}
	
	public double getTotalDamage() {
		double total = 0;
		for(Damage d: list) {
			total += d.getNum();
		}
		return total;
	}

	@Override
	public int compareTo(DamageList o) {
		return (int)(this.getTotalDamage() - o.getTotalDamage());
	}
	
}

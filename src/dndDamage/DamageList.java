package dndDamage;

import java.util.ArrayList;
import java.util.HashMap;

public class DamageList implements Comparable<DamageList>{

	HashMap<DamageType, Damage> map;
	
	public DamageList() {
		map = new HashMap<DamageType, Damage>();
	}
	
	public void add(Damage damage) {
		DamageType damageType = damage.getDamageType();
		if(map.containsKey(damageType)) {
			map.get(damageType).add(damage);
		} else {
			map.put(damageType, damage);
		}
	}
	
	public void add(DamageList otherList) {
		for(Damage d: otherList.getList()) {
			add(d);
		}
	}
	
	public void divide(int val) {
		for(Damage d: map.values()) {
			d.divide(val);
		}
	}
	
	public ArrayList<Damage> getList() {
		return new ArrayList<Damage>(map.values());
	}
	
	public int size() {
		return getList().size();
	}
	
	public Damage get(int index) {
		return getList().get(index);
	}

	public Damage get(DamageType damageType) {
		return map.get(damageType);
	}
	
	public double getTotalDamage() {
		double total = 0;
		for(Damage d: map.values()) {
			total += d.getNum();
		}
		return total;
	}

	@Override
	public int compareTo(DamageList o) {
		return (int)(this.getTotalDamage() - o.getTotalDamage());
	}
	
}

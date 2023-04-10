package dndDamage;

public enum DamageType {
	ACID("acid"), BLUDGEONING("bludgeoning"), COLD("cold"), FIRE("fire"), FORCE("force"), LIGHTNING("lightning"),
	NECROTIC("necrotic"), PIERCING("piercing"), POISON("poison"), PSYCHIC("psychic"), RADIANT("radiant"),
	SLASHING("slashing"), THUNDER("thunder");

	private String name;

	DamageType(String type) {
		name = type;
	}

	public String toString() {
		return name;
	}

}
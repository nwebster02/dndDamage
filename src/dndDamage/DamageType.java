package dndDamage;

public enum DamageType {
	ACID("acid"), BLUDGEONING("bludgeoning"), COLD("cold"), FIRE("fire"), FORCE("force"), LIGHTNING("lightning"),
	NECROTIC("necrotic"), PIERCING("piercing"), POISON("poison"), PSYCHIC("psychic"), RADIANT("radiant"),
	SLASHING("slashing"), THUNDER("thunder");

	private String type;

	DamageType(String type) {
		this.type = type;
	}

	public String toString() {
		return type;
	}

}

package dndDamage;

public class Dice {

	private int amount;
	private int sides;
	
	public Dice(int amount, int sides) {
		this.amount = amount;
		this.sides = sides;
	}
	
	public Dice(String input) {
		try {
			String[] split = input.split("d");
			amount = Integer.parseInt(split[0]);
			sides = Integer.parseInt(split[1]);
		} catch (Exception e) {
			throw new IllegalArgumentException("Incorrect dice format for '" + input + "'.");
		}
	}
	
	public int roll() {
		int total = 0;
		for(int i = 0; i < amount; i++) {
			total += (int)(Math.random() * sides + 1);
		}
		return total;
	}
	
	public int roll(RollType rollType) {
		int total = roll();
		if(rollType == RollType.ADVANTAGE) {
			total = Math.max(total, roll());
		} else if(rollType == RollType.DISADVANTAGE) {
			total = Math.min(total, roll());
		}
		return total;
	}
	
	public int rollDie() {
		return (int)(Math.random() * sides + 1);
	}
	
}

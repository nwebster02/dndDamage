package dndDamage;

public class Dice {

	private int amount;
	private int sides;
	
	public Dice(int amount, int sides) {
		this.amount = amount;
		this.sides = sides;
	}
	
	public Dice(String input) {
		String[] split = input.split("d");
		amount = Integer.parseInt(split[0]);
		sides = Integer.parseInt(split[1]);
	}
	
	public int roll() {
		int total = 0;
		for(int i = 0; i < amount; i++) {
			total += (int)(Math.random() * sides + 1);
		}
		return total;
	}
	
	public int rollDie() {
		return (int)(Math.random() * sides + 1);
	}
	
}

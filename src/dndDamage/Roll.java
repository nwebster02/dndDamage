package dndDamage;

public interface Roll {

	public default int rollDice(int amount, int sides) {
		int total = 0;
		for (int i = 0; i < amount; i++) {
			total += (int) (Math.random() * sides + 1);
		}
		return total;
	}
	
}

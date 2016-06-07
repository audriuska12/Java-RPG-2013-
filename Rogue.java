public class Rogue extends Player {
	private static final long serialVersionUID = 1L;
	public BasicAttackRogue attack;
	public Poison poison;
	public Evasion evasion;
	public PoisonExploit exploit;

	public Rogue() {
		this.setLevel(Instances.level);
		this.setHealth(100 + 15 * this.getLevel());
		this.setMana(100);
		this.setArmor(1);
		this.setAttack(5);
		this.setDamage(1);
		this.setDefense(5);
		attack = new BasicAttackRogue();
		abilities.add(attack);
		poison = new Poison();
		abilities.add(poison);
		evasion = new Evasion();
		abilities.add(evasion);
		exploit = new PoisonExploit();
		abilities.add(exploit);

	}

	public String toString() {
		return "Rogue";
	}

	public int getMaxHealth() {
		return 100 + 15 * this.getLevel();
	}
}

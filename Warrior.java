
public class Warrior extends Player {
	private static final long serialVersionUID = 1L;
	public BasicAttackWarrior attack;
	public ShieldBash bash;
	public Block block;
	public Taunt taunt;

	public Warrior() {
		this.setLevel(Instances.level);
		this.setHealth(100 + 20 * this.getLevel());
		this.setMana(100);
		this.setArmor(5);
		this.setAttack(5);
		this.setDamage(3);
		this.setDefense(1);
		attack = new BasicAttackWarrior();
		abilities.add(attack);
		block = new Block();
		abilities.add(block);
		bash = new ShieldBash();
		abilities.add(bash);
		taunt = new Taunt();
		abilities.add(taunt);
	}

	public String toString() {
		return "Warrior";
	}

	public int getMaxHealth() {
		return 100 + 20 * this.getLevel();
	}
}

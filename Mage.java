public class Mage extends Player {
	private static final long serialVersionUID = 1L;
	public BasicAttackMage attack;
	public Healing heal;
	public Fireball fireball;
	public Fod fod;

	public Mage() {
		this.setLevel(Instances.level);
		this.setHealth(100 + 10 * this.getLevel());
		this.setMana(150);
		this.setArmor(0);
		this.setAttack(0);
		this.setDamage(0);
		this.setDefense(0);
		attack = new BasicAttackMage();
		abilities.add(attack);
		heal = new Healing();
		abilities.add(heal);
		fireball = new Fireball();
		abilities.add(fireball);
		fod = new Fod();
		abilities.add(fod);
		for (Ability ability : abilities) {
			if (ability.getRank() > 0) {
				activeAbilities.add(ability);
			}
		}
	}

	public String toString() {
		return "Mage";
	}

	public int getMaxHealth() {
		return 100 + 10 * this.getLevel();
	}
}

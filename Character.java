import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

public class Character implements Serializable {
	private static final long serialVersionUID = 1L;
	private int health;
	private int armor;
	private int defense;
	private int damage;
	private int attack;
	private int level;
	private int mana;
	private int manaRegen;
	private int healthRegen = 0;
	private double damageResistance = 0;
	private double dodgeChance = 0;
	private boolean stunned;
	public ArrayList<Buff> activeBuffs = new ArrayList<Buff>();
	public ArrayList<Ability> abilities = new ArrayList<Ability>();
	public ArrayList<Ability> activeAbilities = new ArrayList<Ability>();

	public double getDamageResistance() {
		return damageResistance;
	}

	public double getCappedDamageResistance() {
		if (damageResistance < 1) {
			return damageResistance;
		} else
			return 1;
	}

	public void setDamageResistance(double damageResistance) {
		this.damageResistance = damageResistance;
	}

	public void inflictDamage(int damage) {
		if (damage * (1 - this.getCappedDamageResistance()) > this.getArmor())
			this.setHealth(this.getHealth()
					- (int) (damage * (1 - this.getCappedDamageResistance())));
	}

	public void inflictUnblockableDamage(int damage) {
		this.setHealth(this.getHealth() - damage);
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void animate(Image[] animation) {
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getManaRegen() {
		return manaRegen;
	}

	public void setManaRegen(int manaRegen) {
		this.manaRegen = manaRegen;
	}

	public int getHealthRegen() {
		return healthRegen;
	}

	public void setHealthRegen(int healthRegen) {
		this.healthRegen = healthRegen;
	}

	public boolean isStunned() {
		return stunned;
	}

	public void setStunned(boolean stunned) {
		this.stunned = stunned;
	}

	public int getMaxHealth() {
		return 0;
	}

	public double getDodgeChance() {
		return dodgeChance;
	}

	public void setDodgeChance(double dodgeChance) {
		this.dodgeChance = dodgeChance;
	}
}

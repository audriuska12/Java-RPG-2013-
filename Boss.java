import java.awt.Image;

public class Boss extends Enemy {
	private static final long serialVersionUID = 1L;
	public String toString = "boss";
	public BossAttack attack;
	public BossRage rage;
	public BossHeal heal;

	public Boss() {
		setHealth(100 + 20 * Instances.level
				* Instances.town.sliderDifficulty.getValue());
		setMana(100);
		setManaRegen(25);
		setArmor(Instances.level + 1
				* Instances.town.sliderDifficulty.getValue());
		setDefense(Instances.level + 1
				* Instances.town.sliderDifficulty.getValue());
		setAttack(Instances.level + 1
				* Instances.town.sliderDifficulty.getValue());
		setDamage(Instances.level + 1
				* Instances.town.sliderDifficulty.getValue());
		attack = new BossAttack();
		rage = new BossRage();
		heal = new BossHeal();
	}

	public void takeTurn() {
		if (!isStunned()) {
			{
				if (getHealth() <= getMaxHealth() / 2 && getMana() >= 50) {
					useAbility(heal);
				} else if (getMana() >= 100) {
					useAbility(rage);
				} else {
					useAbility(attack);
				}
			}
		}
	}

	public void animate(Image[] animation) {
		for (int i = 0; i < animation.length; i++) {
			Instances.battle.enemyFrame = animation[i];
			Instances.battle.canvas.repaint();
			try {
				Thread.sleep(125);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		if (Instances.battle.enemyFrame == animation[animation.length - 1]) {
			Instances.battle.enemyFrame = attack.animation[0];
			Instances.battle.canvas.repaint();
		}
		;
	}

	public int getMaxHealth() {
		return 100 + 20 * Instances.level
				* Instances.town.sliderDifficulty.getValue();
	}

	public String toString() {
		return "boss";
	}
}

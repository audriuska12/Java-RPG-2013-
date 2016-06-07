import java.awt.Image;

public class Enemy extends Character {
	private static final long serialVersionUID = 1L;
	public String toString = "enemy";
	public EnemyAttack attack;
	public Rage rage;

	public void useAbility(Ability ability) {
		animate(ability.animation);
		ability.activate();
		setMana(Instances.enemy.getMana() + Instances.enemy.getManaRegen());
		if (Instances.enemy.getMana() > 100){
			Instances.enemy.setMana(100);
		}
	}

	public void takeTurn() {
		if (!isStunned()) {
			if (getMana() >= 100) {
				useAbility(rage);
			} else {
				useAbility(attack);
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Enemy() {
		setHealth(100 + 10 * Instances.level
				* Instances.town.sliderDifficulty.getValue());
		setMana(100);
		setArmor((Instances.level + 1)
				* Instances.town.sliderDifficulty.getValue());
		setDefense((Instances.level + 1)
				* Instances.town.sliderDifficulty.getValue());
		setAttack((Instances.level + 1)
				* Instances.town.sliderDifficulty.getValue());
		setDamage((Instances.level + 1)
				* Instances.town.sliderDifficulty.getValue());
		setManaRegen(10);
		attack = new EnemyAttack();
		rage = new Rage();
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
		return 100 + 10 * Instances.level
				* Instances.town.sliderDifficulty.getValue();
	}

	public String toString() {
		return toString;
	}
}

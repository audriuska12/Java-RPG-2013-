import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BossRage extends Ability {
	private static final long serialVersionUID = 1L;
	public BossRage() {
		id = 2;
		setCost(100);
		animation = new Image[1];
		try {
			animation[0] = ImageIO.read(Battle.class
					.getResource("/boss/rage/rage1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void activate() {
		Instances.enemy.setMana(Instances.enemy.getMana() - getCost());
		Instances.enemy.activeBuffs.add(new BossRageBuff());
	}

	public int getCost() {
		return 100;
	}

}

class BossRageBuff extends Buff {
	public void activate() {
		this.setDuration(2);
		Instances.enemy.setDamage(Instances.enemy
				.getDamage() + 15);
		Instances.enemy.setAttack(Instances.enemy
				.getAttack() + 15);
		try {
			render = ImageIO.read(Battle.class
					.getResource("/boss/rage/rage_buff.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		if (this.getDuration() > 0) {
			this.setDuration(getDuration() - 1);
		}
		if (this.getDuration() == 0) {
			this.end();
		}
	}

	public void end() {
		Instances.enemy.setDamage(Instances.enemy
				.getDamage() - 15);
		Instances.enemy.setAttack(Instances.enemy
				.getAttack() - 15);
		Instances.enemy.activeBuffs.remove(this);
	}

}


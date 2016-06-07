import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;



public class Rage extends Ability {
	private static final long serialVersionUID = 1L;

	public Rage() {
		id = 2;
		setCost(100);
		animation = new Image[1];
		try {
			animation[0] = ImageIO.read(Battle.class
					.getResource("/enemy/rage/rage1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void activate() {
		Instances.enemy.activeBuffs.add(new RageBuff());
		Instances.enemy.setMana(Instances.enemy.getMana() - getCost());
	}

	public int getCost() {
		return 100;
	}

}

class RageBuff extends Buff {
	public void activate() {
		this.setDuration(2);
		Instances.enemy.setDamage(Instances.enemy
				.getDamage() + 10);
		Instances.enemy.setAttack(Instances.enemy
				.getAttack() + 10);
		try {
			render = ImageIO.read(Battle.class
					.getResource("/enemy/rage/rage_buff.png"));
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
				.getDamage() - 10);
		Instances.enemy.setAttack(Instances.enemy
				.getAttack() - 10);
		Instances.enemy.activeBuffs.remove(this);
	}

}

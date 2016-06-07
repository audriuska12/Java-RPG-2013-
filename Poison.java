import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Poison extends Ability {
	private static final long serialVersionUID = 1L;

	public Poison() {
		id = 2;
		animload();
	}

	public void activate() {

		Instances.enemy.inflictDamage(Instances.random.nextInt(9) + 3
				* this.getRank() + Instances.player.getDamage());
		Instances.enemy.activeBuffs.add(new PoisonBuff(getRank()));
		Instances.player.setMana(Instances.player.getMana() - getCost());
	}

	public int getCost() {
		return 50 - 5 * getRank();
	}

	public String toString() {
		return "Poison";
	}

	public void animload() {
		try {
			icon = new ImageIcon(ImageIO.read(Battle.class
					.getResource("/icons/poison.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		animation = new Image[2];
		try {
			animation[0] = ImageIO.read(Battle.class
					.getResource("/player/Rogue/poison/poison1.png"));
			animation[1] = ImageIO.read(Battle.class
					.getResource("/player/Rogue/poison/poison2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCap() {
		return Instances.level / 3 + 1;
	}
}

class PoisonBuff extends Buff {
	public int rank;
	public int turnsPassed = 0;

	public PoisonBuff(int rank) {
		this.rank = rank;
	}

	public void activate() {
		this.setDuration(10 + rank);
		try {
			render = ImageIO.read(Battle.class
					.getResource("/enemy/poison/poison_buff.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		this.turnsPassed++;
		if (this.turnsPassed - 3 + rank > 0) {
			Instances.enemy.inflictDamage((this.turnsPassed - 3 + rank)
					* (this.turnsPassed - 3 + rank));
			this.setDuration(getDuration() - 1);
		}
		if (this.getDuration() == 0) {
			this.end();
		}
	}

	public void end() {
		Instances.enemy.activeBuffs.remove(this);
	}

}

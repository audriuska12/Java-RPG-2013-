import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Evasion extends Ability {
	private static final long serialVersionUID = 1L;

	public Evasion() {
		id = 3;
		setCost(50);
		animload();
	}

	public void activate() {
		Instances.player.setMana(Instances.player.getMana() - getCost());
		Instances.player.activeBuffs.add(new EvasionBuff(getRank()));
	}

	public int getCost() {
		return 50 - 5 * getRank();
	}

	public String toString() {
		return "Evasion";
	}

	public void animload() {
		try {
			icon = new ImageIcon(ImageIO.read(Battle.class
					.getResource("/icons/evasion.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		animation = new Image[1];
		try {
			animation[0] = ImageIO.read(Battle.class
					.getResource("/player/Rogue/evasion/evasion1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCap() {
		return Instances.level / 2 + 1;
	}
}

class EvasionBuff extends Buff {
	public int rank;

	public EvasionBuff(int rank) {
		this.rank = rank;
	}

	public void activate() {
		this.setDuration(2);
		Instances.player.setDodgeChance(Instances.player.getDodgeChance() + 0.5
				+ 0.1 * rank);
		try {
			render = ImageIO.read(Battle.class
					.getResource("/player/Rogue/evasion/evasion_buff.png"));
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
		Instances.player.setDodgeChance(Instances.player.getDodgeChance() - 0.5
				- 0.1 * rank);
		Instances.player.activeBuffs.remove(this);
	}

}
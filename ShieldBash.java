import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ShieldBash extends Ability {
	private static final long serialVersionUID = 1L;

	public ShieldBash() {
		id = 3;
		setCost(50);
		animload();
	}

	public void activate() {
		Instances.enemy.inflictDamage(Instances.random.nextInt(9) + 1
				+ Instances.player.getDamage() + getRank());
		Instances.player.setMana(Instances.player.getMana() - getCost());
		Instances.enemy.activeBuffs.add(new BashBuff());
	}

	public int getCost() {
		return 50 - 5 * getRank();
	}

	public String toString() {
		return "Shield Bash";
	}

	public void animload() {
		try {
			icon = new ImageIcon(ImageIO.read(Battle.class
					.getResource("/icons/bash.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		animation = new Image[2];
		try {
			animation[0] = ImageIO.read(Battle.class
					.getResource("/player/Warrior/bash/bash1.png"));
			animation[1] = ImageIO.read(Battle.class
					.getResource("/player/Warrior/bash/bash2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCap() {
		return Instances.level / 2 + 1;
	}
}

class BashBuff extends Buff {

	public void activate() {
		this.setDuration(2);
		try {
			render = ImageIO.read(Battle.class
					.getResource("/" + Instances.enemy.toString() + "/bash/bash_buff.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Instances.enemy.setStunned(true);
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
		Instances.enemy.setStunned(false);
		Instances.enemy.activeBuffs.remove(this);
	}

}

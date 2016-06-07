import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Fod extends Ability {
	private static final long serialVersionUID = 1L;
	public transient Image[] animateEnemy;
	public transient Image[] animate_fail;

	public Fod() {
		id = 4;
		setCost(100);
		animload();
	}

	public void activate() {
		Instances.enemy.animate(animateEnemy);
		int damage = Instances.random.nextInt(Instances.enemy.getMaxHealth());
		Instances.enemy.inflictUnblockableDamage(damage + 5 * getRank());
		Instances.player.setMana(Instances.player.getMana() - getCost());
		if (Instances.enemy.getHealth() > 0) {
			Instances.player.animate(animate_fail);
			Instances.player.setHealth(Instances.player.getHealth() - damage);
			Instances.player.activeBuffs.add(new FODBuff(getRank()));
		}
	}

	public int getCost() {
		return 100;
	}

	public String toString() {
		return "Finger of Death";
	}

	public void animload() {
		try {
			icon = new ImageIcon(ImageIO.read(Battle.class
					.getResource("/icons/fod.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		animation = new Image[2];
		animate_fail = new Image[1];
		animateEnemy = new Image[1];
		try {
			animation[0] = ImageIO.read(Battle.class
					.getResource("/player/Mage/fod/fod1.png"));
			animation[1] = ImageIO.read(Battle.class
					.getResource("/player/Mage/fod/fod2.png"));
			animateEnemy[0] = ImageIO.read(Battle.class
					.getResource("/" + Instances.enemy.toString() + "/fod/fod_hit.png"));
			animate_fail[0] = ImageIO.read(Battle.class
					.getResource("/player/Mage/fod/fod_fail.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCap() {
		return Instances.level / 3 + 1;
	}
}

class FODBuff extends Buff {
	int rank;

	public FODBuff(int rank) {
		this.rank = rank;
	}

	public void activate() {
		this.setDuration(10 - rank);
		try {
			render = ImageIO.read(Battle.class
					.getResource("/player/Mage/fod/fod_buff.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Instances.player.setManaRegen(Instances.player.getManaRegen()
				- (10 - rank));
	}

	public void tick() {
		if (this.getDuration() > 0) {
			Instances.player.inflictUnblockableDamage(10 - rank);
			this.setDuration(getDuration() - 1);
		}
		if (this.getDuration() == 0) {
			this.end();
		}
	}

	public void end() {
		Instances.player.setManaRegen(Instances.player.getManaRegen() + (10 - rank));
		Instances.player.activeBuffs.remove(this);
	}

}

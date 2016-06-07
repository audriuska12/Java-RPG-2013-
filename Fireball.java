import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Fireball extends Ability {
	private static final long serialVersionUID = 1L;
	public transient Image[] animateEnemy;

	public Fireball() {
		id = 3;
		setCost(100);
		animload();
	}

	public void activate() {
		Instances.enemy.animate(animateEnemy);
		Instances.enemy.inflictDamage(Instances.random.nextInt(19) + 21
				+ Instances.player.getDamage() + getRank() * 10);
		Instances.player.setMana(Instances.player.getMana() - getCost());
		Instances.enemy.activeBuffs.add(new FireballBuff(getRank()));
	}

	public int getCost() {
		return 100 - 5 * getRank();
	}

	public String toString() {
		return "Fireball";
	}

	public void animload() {
		try {
			icon = new ImageIcon(ImageIO.read(Battle.class
					.getResource("/icons/fireball.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		animation = new Image[2];
		animateEnemy = new Image[1];
		try {
			animation[0] = ImageIO.read(Battle.class
					.getResource("/player/Mage/fireball/fireball1.png"));
			animation[1] = ImageIO.read(Battle.class
					.getResource("/player/Mage/fireball/fireball2.png"));
			animateEnemy[0] = ImageIO.read(Battle.class
					.getResource("/" + Instances.enemy.toString() + "/fireball/fireball_hit.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCap() {

		return Instances.level / 2 + 1;
	}
}

class FireballBuff extends Buff {
	public int rank;

	public FireballBuff(int rank) {
		this.rank = rank;
	}

	public void activate() {
		this.setDuration(4);
		try {
			render = ImageIO.read(Battle.class
					.getResource("/" + Instances.enemy.toString() + "/fireball/fireball_buff.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick() {
		if (this.getDuration() > 0) {
			Instances.enemy.inflictDamage(5 + 5 * rank);
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

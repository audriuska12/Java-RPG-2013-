import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Taunt extends Ability {
	private static final long serialVersionUID = 1L;

	public Taunt() {
		id = 4;
		setCost(100);
		animload();
	}

	public void activate() {
		Instances.enemy.activeBuffs.add(new TauntBuff(getRank()));
	}

	public int getCost() {
		return 20 - 5 * getRank();
	}

	public String toString() {
		return "Taunt";
	}

	public void animload() {
		animation = new Image[1];
		try {
			icon = new ImageIcon(ImageIO.read(Battle.class
					.getResource("/icons/taunt.png")));
			animation[0] = ImageIO.read(Battle.class
					.getResource("/player/Warrior/taunt/taunt1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCap() {
		return (Instances.level / 2) + 1;
	}
}

class TauntBuff extends Buff {
	public int rank;

	public TauntBuff(int rank) {
		this.rank = rank;
	}

	public void activate() {
		this.setDuration(4);
		Instances.enemy.setAttack(Instances.enemy.getAttack() - 2 * rank);
		Instances.enemy.setDefense(Instances.enemy.getDefense() - 2 * rank);
		Instances.enemy.setDamage(Instances.enemy.getDamage() - 2 * rank);
		Instances.enemy.setArmor(Instances.enemy.getArmor() - 2 * rank);
		try {
			render = ImageIO.read(Battle.class
					.getResource("/" + Instances.enemy.toString() + "/taunt/taunt_buff.png"));
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
		Instances.enemy.setAttack(Instances.enemy.getAttack() + rank);
		Instances.enemy.setDefense(Instances.enemy.getDefense() + 2 * rank);
		Instances.enemy.setDamage(Instances.enemy.getDamage() + 2 * rank);
		Instances.enemy.setArmor(Instances.enemy.getArmor() + 2 * rank);
		Instances.enemy.activeBuffs.remove(this);
	}

}

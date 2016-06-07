import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BasicAttackWarrior extends Ability {
	private static final long serialVersionUID = 1L;

	public BasicAttackWarrior() {
		id = 1;
		setCost(-10);
		setRank(1);
		animload();
	}

	public void activate() {
		if ((Instances.random.nextInt(19) + 1 + Instances.player.getAttack()
				+ Instances.player.getLevel() > 10 + Instances.enemy
				.getDefense())
				&& Instances.random.nextDouble() > Instances.player
						.getDodgeChance()) {
			int damage = Instances.random.nextInt(9) + 10
					+ Instances.player.getDamage() + getRank() * 5
					- Instances.enemy.getArmor();
			if (damage > 0) {
				Instances.enemy.inflictDamage(damage);
			}
		}
		Instances.player.setMana(Instances.player.getMana() - this.getCost());
	}

	public String toString() {
		return "Attack";
	}

	public void animload() {
		try {
			icon = new ImageIcon(ImageIO.read(Battle.class
					.getResource("/icons/attack.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		animation = new Image[3];
		try {
			animation[0] = ImageIO.read(Battle.class
					.getResource("/player/Warrior/attack/player1.png"));
			animation[1] = ImageIO.read(Battle.class
					.getResource("/player/Warrior/attack/player2.png"));
			animation[2] = ImageIO.read(Battle.class
					.getResource("/player/Warrior/attack/player3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCap() {
		return Instances.level;
	}
}

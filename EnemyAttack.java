import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class EnemyAttack extends Ability {
	private static final long serialVersionUID = 1L;
	public EnemyAttack(){
		id = 1;
		setRank(0);
		setCost(0);
		animation = new Image[3];
		try {
			animation[0] = ImageIO.read(Battle.class
					.getResource("/enemy/attack/enemy1.png"));
			animation[1] = ImageIO.read(Battle.class
					.getResource("/enemy/attack/enemy2.png"));
			animation[2] = ImageIO.read(Battle.class
					.getResource("/enemy/attack/enemy3.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error!");
		}
	}

	public void activate() {
		if (Instances.random.nextInt(19) + 1 + Instances.enemy.getAttack() + Instances.enemy.getLevel() / 2 > 10 + Instances.player
				.getDefense()) {
			Instances.player.inflictDamage(Instances.random.nextInt(9) + 10 + 3 * Instances.town.sliderDifficulty.getValue()
					+ Instances.enemy.getDamage());
		}
	}
}

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Healing extends Ability {
	private static final long serialVersionUID = 1L;

	public Healing() {
		id = 2;
		animation = new Image[1];
		animload();
	}

	public void activate() {
		Instances.player.setHealth(Instances.player.getHealth() + 80 + 5
				* getRank());
		if (Instances.player.getHealth() > Instances.player.getMaxHealth()) {
			Instances.player.setHealth(Instances.player.getMaxHealth());
		}
		Instances.player.setMana(Instances.player.getMana()
				- (80 - 5 * getRank()));
		Instances.battle.manaBarPlayer.setValue(Instances.player.getMana());
	}

	public String toString() {
		return "Healing";
	}

	public int getCost() {
		return 80 - 5 * getRank();
	}

	public void animload() {
		try {
			icon = new ImageIcon(ImageIO.read(Battle.class
					.getResource("/icons/heal.png")));
			animation = new Image[1];
			animation[0] = ImageIO.read(Battle.class
					.getResource("/player/Mage/heal/player_heal.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCap() {
		return Instances.level / 2 + 1;
	}
}

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BossHeal extends Ability {
	private static final long serialVersionUID = 1L;

	public BossHeal() {
		id = 2;
		setCost(50);
		animation = new Image[1];
		try {
			animation[0] = ImageIO.read(Battle.class
					.getResource("/boss/heal/heal1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void activate() {
		Instances.enemy.setMana(Instances.enemy.getMana() - getCost());
		Instances.enemy.setHealth(Instances.enemy.getHealth() + Instances.enemy.getMaxHealth() / 2);
		if(Instances.enemy.getHealth() > Instances.enemy.getMaxHealth()){
			Instances.enemy.setHealth(Instances.enemy.getMaxHealth());
		}
	}

	public int getCost() {
		return 50;
	}

}

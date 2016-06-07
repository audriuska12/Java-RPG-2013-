import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Character {
	private static final long serialVersionUID = 1L;

	public void useAbility(Ability ability) {
		animate(ability.animation);
		ability.activate();
		setMana(getMana() + getManaRegen());
		if (getMana() > 100){
			setMana(100);
		}
	}

	public void takeTurn() {
		if (!isStunned()) {
			while (Instances.action == 0) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					return;
				}
				;
			}
			useAbility(abilities.get(Instances.action - 1));
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Player() {
		this.setLevel(Instances.level);
		this.setHealth(100 + 10 * this.getLevel());
		this.setMana(100);
		this.setArmor(0);
		this.setAttack(0);
		this.setDamage(0);
		this.setDefense(0);
		this.setManaRegen(10);
	}

	public void animate(Image[] animation) {
		for (int i = 0; i < animation.length; i++) {
			Instances.battle.playerFrame = animation[i];
			Instances.battle.canvas.repaint();
			try {
				Thread.sleep(125);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (Instances.battle.playerFrame == animation[animation.length - 1]) {
			try {
				Instances.battle.playerFrame = ImageIO.read(Battle.class
						.getResource("/player/" + toString()
								+ "/attack/player1.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Instances.battle.canvas.repaint();

		}
		;
	}

	public void reset() {
		this.setLevel(Instances.level);
		this.setHealth(getMaxHealth());
		this.setMana(100);
	}
}

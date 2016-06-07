import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Block extends Ability {
	private static final long serialVersionUID = 1L;

	public Block() {
		id = 2;
		setCost(50);
		animload();
	}

	public void activate() {
		Instances.player.setMana(Instances.player.getMana() - getCost());
		Instances.player.activeBuffs.add(new BlockBuff(getRank()));
	}

	public int getCost() {
		return 50 - 5 * getRank();
	}

	public String toString() {
		return "Block";
	}

	public void animload() {
		try {
			icon = new ImageIcon(ImageIO.read(Battle.class
					.getResource("/icons/block.png")));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		animation = new Image[1];
		try {
			animation[0] = ImageIO.read(Battle.class
					.getResource("/player/Warrior/block/block1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCap() {
		if (Instances.level >= 2) {
			return Instances.level / 2 + 1;
		} else {
			return 1;
		}
	}
}

class BlockBuff extends Buff {
	public int rank;

	public BlockBuff(int rank) {
		this.rank = rank;
	}

	public void activate() {
		this.setDuration(2);
		Instances.player.setDamageResistance(Instances.player
				.getDamageResistance() + 0.5 + 0.1 * rank);
		try {
			render = ImageIO.read(Battle.class
					.getResource("/player/Warrior/block/block_buff.png"));
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
		Instances.player.setDamageResistance(Instances.player
				.getDamageResistance() - 0.5 - 0.1 * rank);
		Instances.player.activeBuffs.remove(this);
	}

}

import java.awt.Image;
import java.io.Serializable;
import javax.swing.ImageIcon;

public class Ability implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rank = 0;
	private int cost;
	public int id;
	public int cap;
	public ImageIcon icon;
	public transient Image[] animation;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void activate() {
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void animload() {
	}

	public int getCap() {
		return cap;
	};
}
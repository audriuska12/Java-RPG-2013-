import java.awt.Image;

public class Buff {
	private int duration;
	public Image render;

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void activate() {
	};

	public void tick() {
	};

	public void end() {
	};

	public Buff() {
		activate();
	}
}

public class Engine implements Runnable {
	public void run() {

	}

	public void fight() {
		Instances.player.reset();
		Instances.town.frame.setEnabled(false);
		Instances.town.frame.setVisible(false);
		Instances.battle = new Battle();
		Thread fight = new Thread(Instances.battle);
		fight.start();
	}
}

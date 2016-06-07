import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Instances.level = 0;
		Instances.gold = 0;
		Instances.menu = new MainMenu();
		Instances.backgroundMusic = new BGM();
		Instances.backgroundMusic.playFile("/music/menu.wav");
	}
}

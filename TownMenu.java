import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSlider;

public class TownMenu {
	public JFrame frame;
	public JSlider sliderDifficulty;

	public TownMenu() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Town");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setBounds(0, 0, 400, 250);

		JButton btnFight = new JButton("Fight!");
		btnFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instances.player.reset();
				Instances.enemy = new Enemy();
				frame.setVisible(false);
				frame.setEnabled(false);
				for (int i = 0; i < Instances.player.abilities.size(); i++) {
					Instances.player.abilities.get(i).animload();
				}
				Instances.backgroundMusic.stop();
				Instances.backgroundMusic.playFile("/music/fight.wav");
				Instances.battle = new Battle();
				Thread fight = new Thread(Instances.battle);
				fight.start();
			}
		});
		btnFight.setBounds(0, 0, 112, 23);
		frame.getContentPane().add(btnFight);

		JButton btnFightBoss = new JButton("Fight the Boss!");
		btnFightBoss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instances.enemy = new Boss();
				frame.setVisible(false);
				frame.setEnabled(false);
				for (int i = 0; i < Instances.player.abilities.size(); i++) {
					Instances.player.abilities.get(i).animload();
				}
				Instances.player.reset();
				Instances.backgroundMusic.stop();
				Instances.backgroundMusic.playFile("/music/boss.wav");
				Instances.battle = new Battle();
				Thread fight = new Thread(Instances.battle);
				fight.start();
			}
		});
		btnFightBoss.setBounds(0, 90, 112, 23);
		frame.getContentPane().add(btnFightBoss);

		JButton btnMainMenu = new JButton("Main Menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.setEnabled(false);
				Instances.menu.frame.setEnabled(true);
				Instances.menu.frame.setVisible(true);
			}
		});
		btnMainMenu.setBounds(0, 189, 112, 23);
		frame.getContentPane().add(btnMainMenu);

		JButton btnShop = new JButton("Shop");
		btnShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instances.shop = new ShopMenu();
				frame.setVisible(false);
				frame.setEnabled(false);
			}
		});
		btnShop.setBounds(0, 22, 112, 23);
		frame.getContentPane().add(btnShop);

		JLabel lblDifficulty = new JLabel("Difficulty:");
		lblDifficulty.setBounds(149, 4, 46, 14);
		frame.getContentPane().add(lblDifficulty);

		sliderDifficulty = new JSlider();
		sliderDifficulty.setValue(1);
		sliderDifficulty.setMaximum(3);
		sliderDifficulty.setMinimum(1);
		sliderDifficulty.setBounds(149, 22, 200, 23);
		frame.getContentPane().add(sliderDifficulty);

		JButton btnCharacter = new JButton("Character");
		btnCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instances.charmenu = new CharacterMenu(Instances.charmenu
						.getCharacterPoints());
				frame.setVisible(false);
				frame.setEnabled(false);
			}
		});
		btnCharacter.setBounds(0, 56, 89, 23);
		frame.getContentPane().add(btnCharacter);
	}
}

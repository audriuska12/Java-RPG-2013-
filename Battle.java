import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.Component;
import javax.swing.JProgressBar;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Battle implements Runnable {

	public Image background;
	public JFrame frame;
	public JLabel lblYourHealth;
	public JProgressBar healthBarPlayer;
	public JLabel lblPlayerHealth;
	private JLabel lblPrieoGyvyb;
	public JProgressBar healthBarEnemy;
	public JLabel lblEnemyHealth;
	private JButton btnAbility1;
	public static Image[] player;
	public static Image[] enemy;
	public DrawCanvas canvas;
	public Image playerFrame;
	public Image enemyFrame;
	public JProgressBar manaBarEnemy;
	public JProgressBar manaBarPlayer;
	private JLabel lblInsufficientMana;
	public Timer t;

	public Battle() {
		Instances.menu.frame.setEnabled(false);
		Instances.menu.frame.setVisible(false);
		Instances.town.frame.setEnabled(false);
		Instances.town.frame.setVisible(false);
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Battle");
		frame.setBounds(100, 100, 416, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panelPlayerStats = new JPanel();
		panelPlayerStats.setBounds(0, 250, 200, 100);
		frame.getContentPane().add(panelPlayerStats);
		GridBagLayout gbl_panelPlayerStats = new GridBagLayout();
		gbl_panelPlayerStats.columnWidths = new int[] { 217, 0 };
		gbl_panelPlayerStats.rowHeights = new int[] { 15, 20, 15, 0, 0, 0 };
		gbl_panelPlayerStats.columnWeights = new double[] { 0.0,
				Double.MIN_VALUE };
		gbl_panelPlayerStats.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		panelPlayerStats.setLayout(gbl_panelPlayerStats);

		lblYourHealth = new JLabel("Your Health:");
		lblYourHealth.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_lblYourHealth = new GridBagConstraints();
		gbc_lblYourHealth.anchor = GridBagConstraints.NORTH;
		gbc_lblYourHealth.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblYourHealth.insets = new Insets(0, 0, 5, 0);
		gbc_lblYourHealth.gridx = 0;
		gbc_lblYourHealth.gridy = 0;
		panelPlayerStats.add(lblYourHealth, gbc_lblYourHealth);

		healthBarPlayer = new JProgressBar();
		GridBagConstraints gbc_healthBarPlayer = new GridBagConstraints();
		gbc_healthBarPlayer.fill = GridBagConstraints.BOTH;
		gbc_healthBarPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_healthBarPlayer.gridx = 0;
		gbc_healthBarPlayer.gridy = 1;
		panelPlayerStats.add(healthBarPlayer, gbc_healthBarPlayer);

		lblPlayerHealth = new JLabel(String.valueOf(Instances.player
				.getHealth()));
		lblPlayerHealth.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_lblPlayerHealth = new GridBagConstraints();
		gbc_lblPlayerHealth.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerHealth.anchor = GridBagConstraints.NORTH;
		gbc_lblPlayerHealth.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayerHealth.gridx = 0;
		gbc_lblPlayerHealth.gridy = 2;
		panelPlayerStats.add(lblPlayerHealth, gbc_lblPlayerHealth);

		JPanel panelEnemyStats = new JPanel();
		panelEnemyStats.setBounds(200, 250, 200, 100);
		frame.getContentPane().add(panelEnemyStats);
		GridBagLayout gbl_panelEnemyStats = new GridBagLayout();
		gbl_panelEnemyStats.columnWidths = new int[] { 217, 0 };
		gbl_panelEnemyStats.rowHeights = new int[] { 15, 20, 15, 0, 0, 0 };
		gbl_panelEnemyStats.columnWeights = new double[] { 0.0,
				Double.MIN_VALUE };
		gbl_panelEnemyStats.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		panelEnemyStats.setLayout(gbl_panelEnemyStats);

		lblPrieoGyvyb = new JLabel("Enemy Health");
		lblPrieoGyvyb.setAlignmentX(0.5f);
		GridBagConstraints gbc_lblPrieoGyvyb = new GridBagConstraints();
		gbc_lblPrieoGyvyb.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPrieoGyvyb.anchor = GridBagConstraints.NORTH;
		gbc_lblPrieoGyvyb.insets = new Insets(0, 0, 5, 0);
		gbc_lblPrieoGyvyb.gridx = 0;
		gbc_lblPrieoGyvyb.gridy = 0;
		panelEnemyStats.add(lblPrieoGyvyb, gbc_lblPrieoGyvyb);

		healthBarEnemy = new JProgressBar();
		GridBagConstraints gbc_healthBarEnemy = new GridBagConstraints();
		gbc_healthBarEnemy.fill = GridBagConstraints.BOTH;
		gbc_healthBarEnemy.insets = new Insets(0, 0, 5, 0);
		gbc_healthBarEnemy.gridx = 0;
		gbc_healthBarEnemy.gridy = 1;
		panelEnemyStats.add(healthBarEnemy, gbc_healthBarEnemy);

		lblEnemyHealth = new JLabel(String.valueOf(Instances.enemy.getHealth()));
		lblEnemyHealth.setAlignmentX(0.5f);
		GridBagConstraints gbc_lblEnemyHealth = new GridBagConstraints();
		gbc_lblEnemyHealth.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnemyHealth.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEnemyHealth.anchor = GridBagConstraints.NORTH;
		gbc_lblEnemyHealth.gridx = 0;
		gbc_lblEnemyHealth.gridy = 2;
		panelEnemyStats.add(lblEnemyHealth, gbc_lblEnemyHealth);
		healthBarPlayer.setValue(100);

		JLabel lblMana = new JLabel("Mana:");
		GridBagConstraints gbc_lblMana = new GridBagConstraints();
		gbc_lblMana.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMana.insets = new Insets(0, 0, 5, 0);
		gbc_lblMana.gridx = 0;
		gbc_lblMana.gridy = 3;
		panelPlayerStats.add(lblMana, gbc_lblMana);

		manaBarPlayer = new JProgressBar();
		manaBarPlayer.setValue(100);
		GridBagConstraints gbc_manaBarPlayer = new GridBagConstraints();
		gbc_manaBarPlayer.fill = GridBagConstraints.HORIZONTAL;
		gbc_manaBarPlayer.gridx = 0;
		gbc_manaBarPlayer.gridy = 4;
		panelPlayerStats.add(manaBarPlayer, gbc_manaBarPlayer);
		healthBarEnemy.setValue(100);

		JLabel lblMana_1 = new JLabel("Mana:");
		GridBagConstraints gbc_lblMana_1 = new GridBagConstraints();
		gbc_lblMana_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMana_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblMana_1.gridx = 0;
		gbc_lblMana_1.gridy = 3;
		panelEnemyStats.add(lblMana_1, gbc_lblMana_1);

		manaBarEnemy = new JProgressBar();
		manaBarEnemy.setValue(100);
		GridBagConstraints gbc_manaBarEnemy = new GridBagConstraints();
		gbc_manaBarEnemy.fill = GridBagConstraints.HORIZONTAL;
		gbc_manaBarEnemy.gridx = 0;
		gbc_manaBarEnemy.gridy = 4;
		panelEnemyStats.add(manaBarEnemy, gbc_manaBarEnemy);

		JPanel panelButtons = new JPanel();
		panelButtons.setBounds(0, 350, 400, 40);
		frame.getContentPane().add(panelButtons);
		panelButtons.setLayout(null);

		lblInsufficientMana = new JLabel("Insufficient mana!");
		lblInsufficientMana.setBounds(218, 11, 113, 14);
		panelButtons.add(lblInsufficientMana);
		lblInsufficientMana.setVisible(false);

		btnAbility1 = new JButton("");
		btnAbility1.setBounds(0, 0, 40, 40);
		panelButtons.add(btnAbility1);
		btnAbility1
				.setIcon(Instances.player.abilities
						.get(Instances.charmenu.comboBoxButton1
								.getSelectedIndex()).icon);
		btnAbility1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Instances.player.getMana() >= Instances.player.abilities
						.get(Instances.charmenu.comboBoxButton1
								.getSelectedIndex()).getCost()) {
					Instances.action = ((Ability) Instances.charmenu.comboBoxButton1
							.getSelectedItem()).getId();
				} else {
					lblInsufficientMana.setVisible(true);
					t = new Timer(500, new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							lblInsufficientMana.setVisible(false);
							t.stop();
						}
					});
					t.start();
				}
			}
		});

		JButton btnAbility2 = new JButton("");
		btnAbility2.setIcon(((Ability) (Instances.charmenu.comboBoxButton2
				.getSelectedItem())).icon);
		btnAbility2.setBounds(40, 0, 40, 40);
		panelButtons.add(btnAbility2);
		btnAbility2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Instances.player.getMana() >= Instances.player.abilities
						.get(Instances.charmenu.comboBoxButton2
								.getSelectedIndex()).getCost()) {
					Instances.action = ((Ability) Instances.charmenu.comboBoxButton2
							.getSelectedItem()).getId();
				} else {
					lblInsufficientMana.setVisible(true);
					t = new Timer(750, new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							lblInsufficientMana.setVisible(false);
							t.stop();
						}
					});
					t.start();
				}
			}
		});

		JButton btnAbility3 = new JButton("");
		btnAbility3.setBounds(80, 0, 40, 40);
		panelButtons.add(btnAbility3);
		btnAbility3
				.setIcon(Instances.player.abilities
						.get(Instances.charmenu.comboBoxButton3
								.getSelectedIndex()).icon);
		btnAbility3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Instances.player.getMana() >= Instances.player.abilities
						.get(Instances.charmenu.comboBoxButton3
								.getSelectedIndex()).getCost()) {
					Instances.action = ((Ability) Instances.charmenu.comboBoxButton3
							.getSelectedItem()).getId();
				} else {
					lblInsufficientMana.setVisible(true);
					t = new Timer(750, new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							lblInsufficientMana.setVisible(false);
							t.stop();
						}
					});
					t.start();
				}
			}
		});

		JButton btnAbility4 = new JButton("");
		btnAbility4.setBounds(120, 0, 40, 40);
		panelButtons.add(btnAbility4);
		btnAbility4
				.setIcon(Instances.player.abilities
						.get(Instances.charmenu.comboBoxButton4
								.getSelectedIndex()).icon);
		btnAbility4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Instances.player.getMana() >= Instances.player.abilities
						.get(Instances.charmenu.comboBoxButton4
								.getSelectedIndex()).getCost()) {
					Instances.action = ((Ability) Instances.charmenu.comboBoxButton4
							.getSelectedItem()).getId();
				} else {
					lblInsufficientMana.setVisible(true);
					t = new Timer(750, new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							lblInsufficientMana.setVisible(false);
							t.stop();
						}
					});
					t.start();
				}
			}
		});

		canvas = new DrawCanvas();
		canvas.setBounds(0, 0, 400, 250);
		frame.getContentPane().add(canvas);

		try {
			background = ImageIO.read(Battle.class
					.getResource("/background.png"));
		} catch (IOException e) {
		}
		try {
			playerFrame = ImageIO.read(Battle.class.getResource("/player/"
					+ Instances.player.toString() + "/attack/player1.png"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			enemyFrame = ImageIO.read(Battle.class.getResource("/"
					+ Instances.enemy.toString() + "/attack/enemy1.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void run() {
		while (Instances.enemy.getHealth() > 0
				&& Instances.player.getHealth() > 0) {
			if (Instances.player.getHealth() > 0) {
				Instances.player.takeTurn();
				for (int i = 0; i < Instances.player.activeBuffs.size(); i++) {
					if (Instances.player.activeBuffs.get(i) != null) {
						Instances.player.activeBuffs.get(i).tick();
					}
				}
				Instances.battle.manaBarPlayer.setValue(Instances.player
						.getMana());
				Instances.battle.healthBarEnemy.setValue((Instances.enemy
						.getHealth() * 100 / Instances.enemy.getMaxHealth()));
				Instances.battle.lblEnemyHealth.setText(String
						.valueOf((Instances.enemy.getHealth())));
				Instances.battle.healthBarPlayer.setValue(Instances.player
						.getHealth() * 100 / Instances.player.getMaxHealth());
				Instances.battle.lblPlayerHealth.setText(String
						.valueOf(Instances.player.getHealth()));
				;
			}
			canvas.repaint();
			if (Instances.enemy.getHealth() > 0) {
				Instances.enemy.takeTurn();
				for (int i = 0; i < Instances.enemy.activeBuffs.size(); i++) {
					if (Instances.enemy.activeBuffs.get(i) != null) {
						Instances.enemy.activeBuffs.get(i).tick();
					}
				}
				Instances.battle.manaBarEnemy.setValue(Instances.enemy
						.getMana());
				Instances.battle.healthBarEnemy.setValue((Instances.enemy
						.getHealth() * 100 / Instances.enemy.getMaxHealth()));
				Instances.battle.lblEnemyHealth.setText(String
						.valueOf((Instances.enemy.getHealth())));
				Instances.battle.healthBarPlayer.setValue((Instances.player
						.getHealth() * 100) / Instances.player.getMaxHealth());
				Instances.battle.lblPlayerHealth.setText(String
						.valueOf(Instances.player.getHealth()));
				Instances.action = 0;
			}
		}
		for (int i = 0; i < Instances.player.activeBuffs.size(); i++) {
			if (Instances.player.activeBuffs.get(i) != null) {
				Instances.player.activeBuffs.get(i).end();
			}
		}
		for (int i = 0; i < Instances.enemy.activeBuffs.size(); i++) {
			if (Instances.enemy.activeBuffs.get(i) != null) {
				Instances.enemy.activeBuffs.get(i).end();
			}
		}
		Instances.battle.frame.dispose();
		if (Instances.player.getHealth() <= 0) {
			JOptionPane.showMessageDialog(null, "You Lose!");
		} else if (Instances.enemy.getHealth() <= 0) {
			JOptionPane.showMessageDialog(null, "You Win!");
			Instances.level++;
			Instances.charmenu.setCharacterPoints(Instances.charmenu
					.getCharacterPoints() + 1);
			Instances.gold = Instances.gold + 50
					* Instances.town.sliderDifficulty.getValue();
			Instances.menu.save();
		}
		Instances.action = 0;
		Instances.backgroundMusic.playFile("/music/menu.wav");
		Instances.player.reset();
		Instances.town.frame.setEnabled(true);
		Instances.town.frame.setVisible(true);
	}

	class DrawCanvas extends JPanel {
		private static final long serialVersionUID = -388435498689314961L;

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background, 0, 0, 400, 250, null);
			g.drawImage(playerFrame, 100, 100, null);
			for (int i = 0; i < Instances.player.activeBuffs.size(); i++) {
				g.drawImage(Instances.player.activeBuffs.get(i).render, 100,
						100, null);
			}
			g.drawImage(enemyFrame, 275, 100, null);
			for (int i = 0; i < Instances.enemy.activeBuffs.size(); i++) {
				g.drawImage(Instances.enemy.activeBuffs.get(i).render, 275,
						100, null);
			}
		}
	}
}

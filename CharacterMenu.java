import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CharacterMenu {
	public JFrame frame;
	private int characterPoints = 0;
	private JLabel lblRemainingPoints;
	private JButton btnAbility1;
	private JButton btnAbility2;
	private JButton btnAbility3;
	private JButton btnAbility4;
	private JLabel lblRankAbility1;
	private JLabel lblRankAbility2;
	private JLabel lblRankAbility3;
	private JLabel lblRankAbility4;
	private JLabel lblSelectedAbilities;
	public JComboBox<Ability> comboBoxButton1;
	public JComboBox<Ability> comboBoxButton2;
	public JComboBox<Ability> comboBoxButton3;
	public JComboBox<Ability> comboBoxButton4;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * @wbp.parser.constructor
	 */
	public CharacterMenu() {	
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Character Customization");
		frame.getContentPane().setLayout(null);
		frame.setBounds(0, 0, 450, 300);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instances.town = new TownMenu();
				Instances.town.frame.setVisible(true);
				Instances.town.frame.setEnabled(true);
				frame.setVisible(false);
				frame.setEnabled(false);
			}
		});
		btnBack.setBounds(335, 228, 89, 23);
		frame.getContentPane().add(btnBack);

		buttonSetup();

		lblSelectedAbilities = new JLabel("Selected abilities:");
		lblSelectedAbilities.setBounds(213, 4, 89, 14);
		frame.getContentPane().add(lblSelectedAbilities);
		comboBoxButton1 = new JComboBox<Ability>();
		comboBoxButton1.setModel(new DefaultComboBoxModel(
				Instances.player.abilities.toArray()));
		comboBoxButton1.setSelectedIndex(0);
		comboBoxButton1.setBounds(200, 23, 89, 20);
		frame.getContentPane().add(comboBoxButton1);
		comboBoxButton2 = new JComboBox<Ability>();
		comboBoxButton2.setModel(new DefaultComboBoxModel(
				Instances.player.abilities.toArray()));
		comboBoxButton2.setSelectedIndex(1);
		comboBoxButton2.setBounds(200, 44, 89, 20);
		frame.getContentPane().add(comboBoxButton2);
		comboBoxButton3 = new JComboBox<Ability>();
		comboBoxButton3.setModel(new DefaultComboBoxModel(
				Instances.player.abilities.toArray()));
		comboBoxButton3.setSelectedIndex(2);
		comboBoxButton3.setBounds(200, 65, 89, 20);
		frame.getContentPane().add(comboBoxButton3);
		comboBoxButton4 = new JComboBox<Ability>();
		comboBoxButton4.setModel(new DefaultComboBoxModel(
				Instances.player.abilities.toArray()));
		comboBoxButton4.setSelectedIndex(3);
		comboBoxButton4.setBounds(200, 86, 89, 20);
		frame.getContentPane().add(comboBoxButton4);
		frame.setVisible(true);
		frame.setEnabled(true);

	}
	/**
	 * @wbp.parser.constructor
	 */
	public CharacterMenu(int charpoints) {
		this();
		this.setCharacterPoints(charpoints);
	}

	public int getCharacterPoints() {
		return characterPoints;
	}

	public void setCharacterPoints(int characterPoints) {
		this.characterPoints = characterPoints;
		lblRemainingPoints.setText("Remaining Points: " + getCharacterPoints());
	}

	public void buttonSetup() {
		lblRankAbility1 = null;
		lblRankAbility2 = null;
		lblRankAbility3 = null;
		lblRankAbility4 = null;
		btnAbility1 = null;
		btnAbility2 = null;
		btnAbility3 = null;
		btnAbility4 = null;
		lblRankAbility1 = new JLabel("Rank: "
				+ Instances.player.abilities.get(0).getRank());
		lblRankAbility1.setBounds(144, 4, 49, 14);
		frame.getContentPane().add(lblRankAbility1);

		btnAbility1 = new JButton(Instances.player.abilities.get(0).toString());
		btnAbility1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Instances.charmenu.getCharacterPoints() > 0
						&& Instances.player.abilities.get(0).getRank() < Instances.player.abilities
								.get(0).getCap()) {
					Instances.player.abilities.get(0).setRank(
							Instances.player.abilities.get(0).getRank() + 1);
					lblRankAbility1.setText("Rank: "
							+ Instances.player.abilities.get(0).getRank());
					Instances.charmenu
							.setCharacterPoints(getCharacterPoints() - 1);
				}
			}
		});
		btnAbility1.setBounds(0, 0, 128, 23);
		frame.getContentPane().add(btnAbility1);

		lblRemainingPoints = new JLabel("Remaining Points: "
				+ getCharacterPoints());
		lblRemainingPoints.setBounds(10, 232, 118, 14);
		frame.getContentPane().add(lblRemainingPoints);

		lblRankAbility2 = new JLabel("Rank: "
				+ Instances.player.abilities.get(1).getRank());
		lblRankAbility2.setBounds(144, 27, 49, 14);
		frame.getContentPane().add(lblRankAbility2);

		btnAbility2 = new JButton(Instances.player.abilities.get(1).toString());
		btnAbility2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Instances.charmenu.getCharacterPoints() > 0
						&& Instances.player.abilities.get(1).getRank() < Instances.player.abilities
								.get(1).getCap()) {
					Instances.player.abilities.get(1).setRank(
							Instances.player.abilities.get(1).getRank() + 1);
					lblRankAbility2.setText("Rank: "
							+ Instances.player.abilities.get(1).getRank());
					Instances.charmenu
							.setCharacterPoints(getCharacterPoints() - 1);
				}
			}
		});
		btnAbility2.setBounds(0, 23, 128, 23);
		frame.getContentPane().add(btnAbility2);

		lblRankAbility3 = new JLabel("Rank: "
				+ Instances.player.abilities.get(2).getRank());
		lblRankAbility3.setBounds(144, 52, 49, 14);
		frame.getContentPane().add(lblRankAbility3);

		btnAbility3 = new JButton(Instances.player.abilities.get(2).toString());
		btnAbility3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Instances.charmenu.getCharacterPoints() > 0
						&& Instances.player.abilities.get(2).getRank() < Instances.player.abilities
								.get(2).getCap()) {
					Instances.player.abilities.get(2).setRank(
							Instances.player.abilities.get(2).getRank() + 1);
					lblRankAbility3.setText("Rank: "
							+ Instances.player.abilities.get(2).getRank());
					Instances.charmenu
							.setCharacterPoints(getCharacterPoints() - 1);
				}
			}
		});
		btnAbility3.setBounds(0, 46, 128, 23);
		frame.getContentPane().add(btnAbility3);

		lblRankAbility4 = new JLabel("Rank: "
				+ Instances.player.abilities.get(3).getRank());
		lblRankAbility4.setBounds(144, 73, 49, 14);
		frame.getContentPane().add(lblRankAbility4);

		btnAbility4 = new JButton(Instances.player.abilities.get(3).toString());
		btnAbility4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Instances.charmenu.getCharacterPoints() > 0
						&& Instances.player.abilities.get(3).getRank() < Instances.player.abilities
								.get(3).getCap()) {
					Instances.player.abilities.get(3).setRank(
							Instances.player.abilities.get(3).getRank() + 1);
					lblRankAbility4.setText("Rank: "
							+ Instances.player.abilities.get(3).getRank());
					Instances.charmenu
							.setCharacterPoints(getCharacterPoints() - 1);
				}
			}
		});
		btnAbility4.setBounds(0, 69, 128, 23);
		frame.getContentPane().add(btnAbility4);
	}
}

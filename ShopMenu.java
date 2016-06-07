import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ShopMenu extends JFrame {
	private JPanel contentPane;
	private JLabel lblGold;
	private JButton btnArmor;
	private JButton btnDefense;
	private JButton btnDamage;
	private JButton btnAttack;
	private JLabel lblDefense;
	private JLabel lblAttack;
	private JLabel lblDamage;
	private JLabel lblArmor;

	public ShopMenu() {
		setTitle("Shop");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblGold = new JLabel("Gold: " + Instances.gold);
		lblGold.setBounds(10, 232, 91, 14);
		contentPane.add(lblGold);
		setVisible(true);

		JButton btnBackToTown = new JButton("Back to Town");
		btnBackToTown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				setEnabled(false);
				Instances.town.frame.setEnabled(true);
				Instances.town.frame.setVisible(true);
			}
		});
		btnBackToTown.setBounds(311, 228, 113, 23);
		contentPane.add(btnBackToTown);

		JLabel lblUpgradesCost = new JLabel("Upgrades cost 25 Gold.");
		lblUpgradesCost.setBounds(0, 188, 148, 14);
		contentPane.add(lblUpgradesCost);

		lblArmor = new JLabel("Armor: " + Instances.player.getArmor());
		lblArmor.setBounds(102, 86, 173, 14);
		contentPane.add(lblArmor);

		lblAttack = new JLabel("Attack: " + Instances.player.getAttack());
		lblAttack.setBounds(102, 108, 173, 14);
		contentPane.add(lblAttack);

		lblDamage = new JLabel("Damage: " + Instances.player.getDamage());
		lblDamage.setBounds(99, 132, 176, 14);
		contentPane.add(lblDamage);

		lblDefense = new JLabel("Defense:" + Instances.player.getDefense());
		lblDefense.setBounds(102, 156, 173, 14);
		contentPane.add(lblDefense);

		btnAttack = new JButton("Attack");
		btnAttack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Instances.gold >= 25) {
					Instances.player.setAttack(Instances.player.getAttack() + 1);
					lblAttack.setText("Attack: " + Instances.player.getAttack());
					Instances.gold = Instances.gold - 25;
					lblGold.setText("Gold: " + Instances.gold);
				}
			}
		});
		btnAttack.setBounds(0, 104, 89, 23);
		contentPane.add(btnAttack);

		btnArmor = new JButton("Armor");
		btnArmor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Instances.gold >= 25) {
					Instances.player.setArmor(Instances.player.getArmor() + 1);
					lblArmor.setText("Armor: " + Instances.player.getArmor());
					Instances.gold = Instances.gold - 25;
					lblGold.setText("Gold: " + Instances.gold);
				}
			}
		});
		btnArmor.setBounds(0, 82, 89, 23);
		contentPane.add(btnArmor);

		btnDamage = new JButton("Damage");
		btnDamage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Instances.gold >= 25) {
					Instances.player.setDamage(Instances.player.getDamage() + 1);
					lblDamage.setText("Damage: " + Instances.player.getDamage());
					Instances.gold = Instances.gold - 25;
					lblGold.setText("Gold: " + Instances.gold);
				}
			}
		});
		btnDamage.setBounds(0, 128, 89, 23);
		contentPane.add(btnDamage);

		btnDefense = new JButton("Defense");
		btnDefense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Instances.gold >= 25) {
					Instances.player.setDefense(Instances.player.getDefense() + 1);
					lblDefense.setText("Defense: "
							+ Instances.player.getDefense());
					Instances.gold = Instances.gold - 25;
					lblGold.setText("Gold: " + Instances.gold);
				}
			}
		});
		btnDefense.setBounds(0, 152, 89, 23);
		contentPane.add(btnDefense);
		setResizable(false);
	}
}

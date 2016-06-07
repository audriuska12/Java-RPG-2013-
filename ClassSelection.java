import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ClassSelection extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public ClassSelection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Select Class");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnWarrior = new JButton("Warrior");
		btnWarrior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Instances.player = new Warrior();
				Instances.gold = 0;
				Instances.charmenu = new CharacterMenu(5);
				Instances.level = 0;
				setVisible(false);
				setEnabled(false);
			}
		});
		btnWarrior.setBounds(10, 228, 89, 23);
		contentPane.add(btnWarrior);

		JButton btnMage = new JButton("Mage");
		btnMage.setBounds(335, 228, 89, 23);
		btnMage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Instances.player = new Mage();
				Instances.gold = 0;
				Instances.charmenu = new CharacterMenu(5);
				Instances.level = 0;
				setVisible(false);
				setEnabled(false);
			}
		});
		contentPane.add(btnMage);

		JLabel imgWarrior = new JLabel("");
		imgWarrior.setIcon(new ImageIcon(ClassSelection.class
				.getResource("/Warrior.png")));
		imgWarrior.setBounds(10, 11, 89, 206);
		contentPane.add(imgWarrior);

		JLabel imgMage = new JLabel("");
		imgMage.setIcon(new ImageIcon(ClassSelection.class
				.getResource("/Mage.png")));
		imgMage.setBounds(335, 11, 89, 206);
		contentPane.add(imgMage);

		JButton btnRogue = new JButton("Rogue");
		btnRogue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Instances.player = new Rogue();
				Instances.gold = 0;
				Instances.charmenu = new CharacterMenu(5);
				Instances.level = 0;
				setVisible(false);
				setEnabled(false);
			}
		});
		btnRogue.setBounds(175, 228, 89, 23);
		contentPane.add(btnRogue);

		JLabel imgRogue = new JLabel("");
		imgRogue.setIcon(new ImageIcon(ClassSelection.class
				.getResource("/Rogue.png")));
		imgRogue.setBounds(175, 11, 89, 206);
		contentPane.add(imgRogue);

		setVisible(true);
		setEnabled(true);
	}
}

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

public class MainMenu {
	public JFrame frame;
	public Properties saveGame;
	public FileOutputStream output;
	public FileInputStream input;

	public MainMenu() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Main Menu");
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setBounds(0, 0, 400, 250);
		saveGame = new Properties();

		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Instances.classselect = new ClassSelection();
				frame.setVisible(false);
				frame.setEnabled(false);
				Instances.town = new TownMenu();
				Instances.enemy = new Enemy();
			}
		});
		btnPlay.setBounds(134, 99, 126, 23);
		frame.getContentPane().add(btnPlay);

		JButton btnExit = new JButton("Quit Game");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(134, 165, 126, 23);
		frame.getContentPane().add(btnExit);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		btnSave.setBounds(134, 120, 126, 23);
		frame.getContentPane().add(btnSave);

		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					input = new FileInputStream("saveGame.properties");
				} catch (FileNotFoundException e1) {
				}

				try {
					saveGame.load(input);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Instances.gold = Integer.valueOf(saveGame.getProperty("gold"));
				Instances.level = Integer.valueOf(saveGame.getProperty("level"));
				try {
					FileInputStream fileIn = new FileInputStream("player.ser");
					ObjectInputStream in = new ObjectInputStream(fileIn);
					Instances.player = (Player) in.readObject();
					in.close();
					fileIn.close();
				} catch (IOException i) {
					i.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				Instances.town = new TownMenu();
				Instances.enemy = new Enemy();
				Instances.charmenu = new CharacterMenu();
				Instances.charmenu.setCharacterPoints(Integer.valueOf(saveGame
						.getProperty("charpoints")));
				frame.setVisible(false);
				frame.setEnabled(false);
				for (int i = 0; i < Instances.player.abilities.size(); i++) {
					Instances.player.abilities.get(i).animload();
				}
				JOptionPane.showMessageDialog(null, "Loaded!");
			}
		});
		btnLoad.setBounds(134, 143, 126, 23);
		frame.getContentPane().add(btnLoad);
		frame.setVisible(true);
	}

	public void save() {
		Instances.menu.saveGame.setProperty("level",
				String.valueOf(Instances.level));
		Instances.menu.saveGame.setProperty("gold",
				String.valueOf(Instances.gold));
		if (Instances.charmenu != null)
			Instances.menu.saveGame.setProperty("charpoints",
					String.valueOf(Instances.charmenu.getCharacterPoints()));
		try {
			Instances.menu.output = new FileOutputStream("saveGame.properties");
		} catch (FileNotFoundException e) {
		}

		try {
			Instances.menu.saveGame.store(Instances.menu.output, null);
		} catch (IOException e) {
		}
		try {
			FileOutputStream fileOut = new FileOutputStream("player.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(Instances.player);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Saved!");
	}
}

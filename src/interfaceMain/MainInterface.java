
package interfaceMain;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JSplitPane;

public class MainInterface {

	private JFrame frame;
	private ArrayList<String> rules = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface window = new MainInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton bAvaliar = new JButton("Avaliar Qualidade");
		bAvaliar.setBounds(464, 6, 161, 29);
		frame.getContentPane().add(bAvaliar);

		JButton bGravar = new JButton("Gravar Configuração");
		bGravar.setBounds(464, 47, 161, 29);
		frame.getContentPane().add(bGravar);

		JList list = new JList();
		list.setBounds(616, 370, 161, -219);
		frame.getContentPane().add(list);
		
		for(String s : rules) {
			System.out.println(s);
		}
	}

	public void lerRules() {
		Scanner scan = new Scanner("./rules.cf");
		while (scan.hasNextLine()) {
			rules.add(scan.nextLine());
		}
		scan.close();

	}
}

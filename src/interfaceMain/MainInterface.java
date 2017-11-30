
package interfaceMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class MainInterface {

	private JFrame frame;
	private ArrayList<String> rules = new ArrayList<String>();
	private JTable table;

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
		frame.setBounds(100, 100, 800, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);

		JProgressBar progressBarFNM = new JProgressBar();
		progressBarFNM.setBounds(514, 539, 146, 20);
		frame.getContentPane().add(progressBarFNM);

		JProgressBar progressBarFPM = new JProgressBar();
		progressBarFPM.setBounds(514, 559, 146, 20);
		frame.getContentPane().add(progressBarFPM);

		JLabel lblFnm = new JLabel("FN");
		lblFnm.setBounds(490, 539, 61, 20);
		frame.getContentPane().add(lblFnm);

		JLabel lblFpm = new JLabel("FP");
		lblFpm.setBounds(490, 559, 61, 20);
		frame.getContentPane().add(lblFpm);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.setBounds(413, 591, 117, 29);
		frame.getContentPane().add(btnGravar);

		JButton btnAvaliar = new JButton("Avaliar");
		btnAvaliar.setBounds(524, 591, 117, 29);
		frame.getContentPane().add(btnAvaliar);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(636, 591, 117, 29);
		frame.getContentPane().add(btnEditar);

		JProgressBar progressBarFNA = new JProgressBar();
		progressBarFNA.setBounds(142, 539, 146, 20);
		frame.getContentPane().add(progressBarFNA);

		JProgressBar progressBarFPA = new JProgressBar();
		progressBarFPA.setBounds(142, 559, 146, 20);
		frame.getContentPane().add(progressBarFPA);

		JLabel lblFna = new JLabel("FN");
		lblFna.setBounds(114, 539, 61, 20);
		frame.getContentPane().add(lblFna);

		JLabel lblFpa = new JLabel("FP");
		lblFpa.setBounds(114, 559, 61, 20);
		frame.getContentPane().add(lblFpa);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(413, 82, 345, 445);
		frame.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(32, 82, 345, 445);
		frame.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);

		lerRules("./rules.cf");
		
	}

	/**
	 * Ler ficheiro rules.cf
	 */
	
	public void lerRules(String file) {
		Scanner scan;
		try {
			scan = new Scanner(new File(file));
			while (scan.hasNextLine()) {
				rules.add(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

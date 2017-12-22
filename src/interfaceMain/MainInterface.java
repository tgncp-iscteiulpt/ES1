package interfaceMain;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * 
 * @author tomascostapedro
 * @author andrerouiller
 * 
 */

public class MainInterface {

        private JFrame frame;
        private ArrayList<Rule> rulesList = new ArrayList<Rule>();
        private ArrayList<Email> hamList = new ArrayList<Email>();
        private ArrayList<Email> spamList = new ArrayList<Email>();
        private JTable tableAut;
        private JTable tableMan;
        private DefaultTableModel modelo = new DefaultTableModel();
        private DefaultTableModel modeloAut = new DefaultTableModel();
        private int fn = 0;
        private int fp = 0;
        private int totalMessages = 0;
        private int contador = 0;
        private JButton btnAvaliar;
        private JButton btnStart;

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

                // Manual

                JCheckBox checkBoxHam = new JCheckBox("Ham.log");
                JCheckBox checkBoxSpam = new JCheckBox("Spam.log");
                JProgressBar progressBarFNM = new JProgressBar();
                progressBarFNM.setBounds(514, 539, 146, 20);
                frame.getContentPane().add(progressBarFNM);

                JProgressBar progressBarFPM = new JProgressBar();
                progressBarFPM.setBounds(514, 559, 146, 20);
                frame.getContentPane().add(progressBarFPM);

                JLabel labelFnm = new JLabel("FN");
                labelFnm.setBounds(490, 539, 61, 20);
                frame.getContentPane().add(labelFnm);

                JLabel labelFpm = new JLabel("FP");
                labelFpm.setBounds(490, 559, 61, 20);
                frame.getContentPane().add(labelFpm);
                
                // Autom√°tico

                JProgressBar progressBarFNA = new JProgressBar();
                progressBarFNA.setBounds(142, 539, 146, 20);
                frame.getContentPane().add(progressBarFNA);

                JProgressBar progressBarFPA = new JProgressBar();
                progressBarFPA.setBounds(142, 559, 146, 20);
                frame.getContentPane().add(progressBarFPA);

                JLabel labelFna = new JLabel("FN");
                labelFna.setBounds(114, 539, 61, 20);
                frame.getContentPane().add(labelFna);

                JLabel labelFpa = new JLabel("FP");
                labelFpa.setBounds(114, 559, 61, 20);
                frame.getContentPane().add(labelFpa);
                

                JButton btnGravar = new JButton("Gravar");
                btnGravar.setBounds(413, 591, 117, 29);
                frame.getContentPane().add(btnGravar);
                btnGravar.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub
                                try {
                                        savePeso(modelo, rulesList);
                                } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }

                        }

                });

                btnAvaliar = new JButton("Avaliar");
                btnAvaliar.setBounds(524, 591, 117, 29);
                frame.getContentPane().add(btnAvaliar);
                btnAvaliar.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub
                                lerHam("./ham.log");
                                lerSpam("./spam.log");
                                progressBarFNM.setMinimum(0);
                                progressBarFPM.setMinimum(0);
                                progressBarFNM.setMaximum(695);
                                progressBarFPM.setMaximum(239);
                                
                                progressBarFNA.setMinimum(0);
                                progressBarFPA.setMinimum(0);
                                progressBarFNA.setMaximum(695);
                                progressBarFPA.setMaximum(239);
                                
                                if (checkBoxHam.isSelected()) {
                                        fn= 0;
                                        for (Email email : hamList) {
                                                double peso = avaliarEmail(email);
                                                if (peso < 5) {
                                                        fn++;
                                                        progressBarFNM.setValue(fn);
                                                        progressBarFNM.setStringPainted(true);
                                                        
                                                        progressBarFNA.setValue(fn);
                                                        progressBarFNA.setStringPainted(true);

                                                }
                                        }
                                }
                                System.out.println(fn);
                                if (checkBoxSpam.isSelected()) {
                                        for (Email email : spamList) {
                                                double peso = avaliarEmail(email);
                                                if (peso > 5) {
                                                        fp++;
                                                        progressBarFPM.setValue(fp);
                                                        progressBarFPM.setStringPainted(true);
                                                        
                                                        progressBarFNA.setValue(fp);
                                                        progressBarFNA.setStringPainted(true);

                                                }
                                        }
                                }
                        }
                });

                JButton btnEditar = new JButton("Editar");
                btnEditar.setBounds(636, 591, 117, 29);
                frame.getContentPane().add(btnEditar);
                btnEditar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub
                                tableMan.setEditingColumn(1);
                        }
                });

                JScrollPane scrollPaneMan = new JScrollPane();
                scrollPaneMan.setBounds(413, 82, 345, 445);
                frame.getContentPane().add(scrollPaneMan);
                tableMan = new JTable();

                scrollPaneMan.setViewportView(tableMan);


                JScrollPane scrollPaneAut = new JScrollPane();
                scrollPaneAut.setBounds(32, 82, 345, 445);
                frame.getContentPane().add(scrollPaneAut);

                tableAut = new JTable();
                scrollPaneAut.setViewportView(tableAut);

                // Check Box

                JCheckBox checkBoxRules = new JCheckBox("Rules.cf");
                checkBoxRules.setBounds(32, 30, 82, 23);
                frame.getContentPane().add(checkBoxRules);

                checkBoxHam.setBounds(114, 30, 85, 23);
                frame.getContentPane().add(checkBoxHam);

                checkBoxSpam.setBounds(200, 30, 99, 23);
                frame.getContentPane().add(checkBoxSpam);

                btnStart = new JButton("Start");
                btnStart.setBounds(303, 29, 117, 29);
                frame.getContentPane().add(btnStart);
                btnStart.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                                // TODO Auto-generated method stub
                                if (checkBoxRules.isSelected()) {
                                        lerRules("./gravarPesos.txt");
                                        // gerarValores();

                                        modelo.addColumn("Rules");
                                        modelo.addColumn("Pesos");
                                        if (rulesList.isEmpty()) {
                                                modelo.addRow(new String[] { "Sem informa√ß√µes", "Sem informa√ß√µes" });
                                        } else {
                                                for (int i = 0; i < rulesList.size(); i++) {
                                                        modelo.addRow(new String[] { rulesList.get(i).getName(),
                                                                        String.valueOf(rulesList.get(i).getPeso()) });
                                                }
                                        }
                                        tableMan.setModel(modelo);

                                        gerarValores();
                                        modeloAut.addColumn("Rules");
                                        modeloAut.addColumn("Pesos");
                                        if (rulesList.isEmpty()) {
                                                modeloAut.addRow(new String[] { "Sem informa√ß√µes", "Sem informa√ß√µes" });
                                        } else {
                                                for (int i = 0; i < rulesList.size(); i++) {
                                                        modeloAut.addRow(new String[] { rulesList.get(i).getName(),
                                                                        String.valueOf(rulesList.get(i).getPeso()) });
                                                }
                                        }
                                        tableAut.setModel(modeloAut);

                                }
                        }
                });
        }
/**
 * 
 * @param email
 * @return ClassificaÁ„o do mail
 */
        public double avaliarEmail(Email email) {
                double pesoEmail = 0.0;
                for (Rule rule : email.getRulesList()) {
                        for (Rule ruleList : rulesList) {
                                if (rule.getName().equals(ruleList.getName())) {
                                        pesoEmail += ruleList.getPeso();
                                }
                        }
                }
                return pesoEmail;
        }
/**
 * 
 * @param file
 * 
 * Scanner regras
 */
        public void lerRules(String file) {
                try {
                        Scanner scan = new Scanner(new File(file));
                        while (scan.hasNextLine()) {

                                String line = scan.nextLine();
                                String[] name = line.split("	");
                                Rule rule = new Rule(name[0], Double.parseDouble(name[1]));
                                rulesList.add(rule);
                        }
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
/**
 * 
 * @param file
 * 
 * Scanner ham
 */
        public void lerHam(String file) {
                try {
                        Scanner scan = new Scanner(new File(file));
                        while (scan.hasNextLine()) {
                                ArrayList<Rule> emailRules = new ArrayList<Rule>();
                                String line = scan.nextLine();
                                String[] name = line.split("	");
                                String emailName = name[0];
                                String ruleName = "";
                                Email email = new Email(emailName);
                                for (int i = 1; i < name.length; i++) {
                                        ruleName = name[i];
                                        email.addRule(ruleName);
                                        // System.out.println("Entrou aqui (Regras)- " + ruleName);

                                        // emailRules.add(rule);
                                }

                                // System.out.println("Nome email:" + emailName);

                                // for (Rule rule1 : emailRules) {
                                // System.out.println(rule1.getName());
                                //
                                // }
                                hamList.add(email);
                        }

                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
/**
 * 
 * @param file
 * 
 * Scanner spam
 */
        public void lerSpam(String file) {
                try {
                        Scanner scan = new Scanner(new File(file));
                        while (scan.hasNextLine()) {
                                ArrayList<Rule> emailRules = new ArrayList<Rule>();
                                String line = scan.nextLine();
                                String[] name = line.split("	");
                                String emailName = name[0];
                                String ruleName = "";
                                Email email = new Email(emailName);
                                for (int i = 1; i < name.length; i++) {
                                        ruleName = name[i];
                                        email.addRule(ruleName);
                                        // System.out.println("Entrou aqui (Regras)- " + ruleName);
                                }
                                System.out.println("Nome email:" + emailName);
                                spamList.add(email);
                        }
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
/**
 * Gerar valores aleatÛrios para configuraÁ„o autom·tica
 */
        public void gerarValores() {
                for (int i = 0; i < contador; i++) {
                        Random random = new Random();
                        double n = -5.0 + (6) * random.nextDouble();
                        rulesList.get(i).setPeso(n);
                        // FALTA AQUI FUN√á√ÉO PARA ADICIONAR O N AO FICHEIRO RULES
                        writeFile("./rules.cf");

                }
        }
/**
 * 
 * @param file
 * 
 * Escrever ficheiro para guardar valor das regras
 */
        private void writeFile(String file) {
                // TODO Auto-generated method stub
                PrintWriter write;
                try {
                        write = new PrintWriter(new File(file));
                        for (int i = 0; i < rulesList.size(); i++) {
                                write.print(rulesList.get(i).getName() + "	" + rulesList.get(i).getPeso() + "\n");
                        }
                        write.close();
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
        }
/**
 * 
 * @param table
 * @param list
 * @throws IOException
 * 
 * Guardar pesos configurados manualmente
 */
        public void savePeso(TableModel table, ArrayList<Rule> list) throws IOException {
                list.clear();
                FileWriter fwriter;
                BufferedWriter bwriter = new BufferedWriter(new FileWriter("./gravarPesos.txt"));

                for (int r = 0; r < table.getRowCount(); r++) {
                        String s = (String) table.getValueAt(r, 0);
                        String b = (String) String.valueOf(table.getValueAt(r, 1));

                        Rule a = new Rule(s, Double.parseDouble(b));
                        //System.out.println("Nome " + a.getName());
                        //System.out.println("Valor " + a.getPeso());
                        list.add(a);
                        // gravar(s, b, "./gravarPesos.txt");

                        try {
                                bwriter.append(s + "	" + b + "\n");
                                bwriter.flush();

                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }

                }
                bwriter.close();
        }

        public int getFn() {
                return fn;
        }

        public int getFp() {
                return fp;
        }

        public int getTotalMessages() {
                return totalMessages;
        }

}
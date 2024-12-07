import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyFrame extends JFrame {
    private JTextArea tarStory;
    private ArrayList<String> words;
    private WordChooser wc;
    public void setupMenu() {
        JMenuBar mbar = new JMenuBar();
        setJMenuBar(mbar);
        JMenu mnuFile = new JMenu("File");
        JMenu mnuHelp = new JMenu("Help");
        mbar.add(mnuFile);
        JMenuItem miOpen = new JMenuItem("Open");
        miOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        words = WordFileReader.readWords(chooser.getSelectedFile());
                        for (String word : words) {
                            System.out.println(word);
                        }

                        Scanner fsc = new Scanner(chooser.getSelectedFile());
                        String text = "";
                        String line;
                        while(fsc.hasNextLine()) {
                            line = fsc.nextLine();
                            text = text + "\n" + line;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "An error occurred.");
                    }
                } 
            }
        });
        mnuFile.add(miOpen);
        JMenuItem miSave = new JMenuItem("Save");
        miSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser chooser = new JFileChooser();
                    if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(chooser.getSelectedFile())));
                        pw.println(tarStory.getText());
                        pw.close();
                        JOptionPane.showMessageDialog(null, "File saved successfully!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred.");
                }
            }
        });
        mnuFile.add(miSave);
        JMenuItem miClear = new JMenuItem("Clear");
        miClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tarStory.setText("");
            }
        });
        mnuFile.add(miClear);
        JMenuItem miExit = new JMenuItem("Exit");
        miExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnuFile.add(miExit);
        mbar.add(mnuHelp);
        JMenuItem miABout = new JMenuItem("About");
        miABout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Story Editor by Huy Hoang Nguyen, December 2024.");
            } 
        });
        mnuHelp.add(miABout);

    }
    public void setupGUI() {
        setBounds(300,300,400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Story Composer");
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel panEast = new JPanel();
        GridLayout gl = new GridLayout(3,1);
        panEast.setLayout(gl);
        JButton btn1 = new JButton("noun");
        btn1.addActionListener(new ActionListener() {
            String randomNoun = wc.chooseNoun();
            public void actionPerformed(ActionEvent e) {
                if (words != null) {
                    tarStory.setText(tarStory.getText() + " " + randomNoun);
                } else {
                    JOptionPane.showMessageDialog(null, "Use File -> Open to load the words first.");
                }
            }
        });
        JButton btn2 = new JButton("verb");
        btn2.addActionListener(new ActionListener() {
            String randomVerb = wc.chooseVerb();
            public void actionPerformed(ActionEvent e) {
                if (words != null) {
                    tarStory.setText(tarStory.getText() + " " + randomVerb);
                } else {
                    JOptionPane.showMessageDialog(null, "Use File -> Open to load the words first.");
                }
            }
        });
        JButton btn3 = new JButton("adjective");
        btn3.addActionListener(new ActionListener() {
            String randomAdj = wc.chooseAdjective();
            public void actionPerformed(ActionEvent e) {
                if (words != null) {
                    tarStory.setText(tarStory.getText() + " " + randomAdj);
                } else {
                    JOptionPane.showMessageDialog(null, "Use File -> Open to load the words first.");
                }
            }
        });
        panEast.add(btn1);
        panEast.add(btn2);
        panEast.add(btn3);
        c.add(panEast, BorderLayout.WEST);
        tarStory = new JTextArea();
        tarStory.setLineWrap(true);
        tarStory.setEditable(false);
        c.add(tarStory,BorderLayout.CENTER);
        JPanel panSouth = new JPanel();
        FlowLayout fl = new FlowLayout();
        panSouth.setLayout(fl);
        JLabel lblEnterText = new JLabel("Enter word: ");
        panSouth.add(lblEnterText);
        JTextField txtAddThis = new JTextField(20);
        panSouth.add(txtAddThis);
        JButton btnAdd = new JButton("Add");
        panSouth.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String typedText = txtAddThis.getText();
                String currentText = tarStory.getText();
                tarStory.setText(currentText + " " + typedText);
                txtAddThis.setText("");
            }
        });
        c.add(panSouth,BorderLayout.SOUTH);
        setupMenu();
    }
    
    public MyFrame() {
        words = null;
        wc = new WordChooser();
        setupGUI();
    }
}

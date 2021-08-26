import java.awt.EventQueue;
import java.io.File;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class nesto {

	private JFrame frame;
	private File openedFile;
	private JTextArea txtPolje;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextEditorProjekatJava window = new TextEditorProjekatJava();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public void OpenFile() {
		try {
			JFileChooser IzaberiFajl = new JFileChooser();
			IzaberiFajl.setDialogTitle("Choose file");
			IzaberiFajl.showOpenDialog(null);

			openedFile = IzaberiFajl.getSelectedFile();
			if(!openedFile.exists()) {
				JOptionPane.showMessageDialog(null, "File doesn't exist. Error.");
				openedFile = null;
				return;
			}
			String kopiranTekst = "";
			Scanner citati = new Scanner(openedFile);
			while(citati.hasNextLine()) {
				kopiranTekst += citati.nextLine() + "\n";
			}
			citati.close();
			txtPolje.setText(kopiranTekst);
		}
		catch(RuntimeException e) {
			JOptionPane.showMessageDialog(null, "You must choose file!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void SaveAs() {
		try {
			JFileChooser zapamtiFajl = new JFileChooser();
			zapamtiFajl.showSaveDialog(null);
			
			openedFile = zapamtiFajl.getSelectedFile();
			Save();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Save() {
		try {
			if(openedFile == null) {
				JOptionPane.showMessageDialog(null, "File must be saved first!");
				return ;
			}
			String zapamcenTekst = txtPolje.getText();
			Formatter forma = new Formatter(openedFile);
			forma.format("%s", zapamcenTekst);
			forma.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void New() {
		try {
			Save();
			txtPolje.setText("");
			openedFile = null;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public nesto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(150, 100, 615, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		txtPolje = new JTextArea();
		txtPolje.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		txtPolje.setBounds(12, 13, 573, 311);
		frame.getContentPane().add(txtPolje);
		
		
		JButton btnNew = new JButton("New");
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				New();
			}
		});
		btnNew.setBounds(12, 337, 129, 36);
		frame.getContentPane().add(btnNew);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenFile();
			}
		});
		btnOpen.setBounds(153, 337, 129, 36);
		frame.getContentPane().add(btnOpen);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Save();
			}
		});
		btnSave.setBounds(315, 337, 129, 36);
		frame.getContentPane().add(btnSave);
		
		JButton btnSaveAs = new JButton("Save As");
		btnSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveAs();
			}
		});
		btnSaveAs.setBounds(456, 337, 129, 36);
		frame.getContentPane().add(btnSaveAs);
	}
}

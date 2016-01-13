package geral;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Inicio_old implements ActionListener {

	private JFileChooser fileChooser = new JFileChooser();	

	Inicio_old () {
		final int dimH = 600;
		final int dimV = 600;

		JFrame jan = new JFrame();
		jan.setSize(dimH,dimV);
		jan.setTitle("Pasta para guardar a Base de Dados");
		Container c = jan.getContentPane();				
		//System.out.println(System.getProperty("user.dir"));
		fileChooser.addActionListener(this);
		fileChooser.setCurrentDirectory(new java.io.File( "." ));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setApproveButtonText("Alterar Pasta");
		fileChooser.setAcceptAllFileFilterUsed(false);
		hideComponents(fileChooser.getComponents());

		//Integer opt = j.showSaveDialog(this);
		c.add(fileChooser);
		jan.setVisible(true);
		
	}
	
	public static void main(String[] args) {//implements ActionListener {
		// TODO Auto-generated method stub

		new Inicio_old();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getActionCommand());
		System.out.println(e.getID());
		if (e.getActionCommand().equals("ApproveSelection")){
			System.out.println("aprovado!!");
			inicializa(fileChooser.getCurrentDirectory());	
			
		}
		System.out.println(fileChooser.getCurrentDirectory());
		
	}
	
	public void inicializa(File file){
		Path.setPath(file+"\biblioteca");
		if (Path.read() == null){
		//	fileChooser.
		}	
	}
	
	private void hideComponents(Component[] components) {

		for (int i= 0; i < components.length; i++) {
		  if (components[i] instanceof JPanel)
		    hideComponents(((JPanel)components[i]).getComponents());
		  else if (components[i] instanceof JLabel){
			JLabel label = ((JLabel) components[i]);
			if (label.getText().equals("Look In:")){
				label.setText("Procurar em:");
			}else if (label.getText().equals("Folder name:") || label.getText().equals("Files of Type:")){
				label.setVisible(false);
			}
			
		  }else if (components[i] instanceof JComboBox) {
			  JComboBox combo = ((JComboBox) components[i]);
			  if (combo.getSelectedItem() == null) {
				  combo.setVisible(false);
			  }

		  }else if (components[i] instanceof JTextField){
			  ((JTextField) components[i]).setEditable(false);
			  
		  } else if (components[i] instanceof JButton) {
			  System.out.println(components[i]);
			  JButton button = (JButton) components[i];
			  System.out.println(button.getText());
			  String texto = button.getText();
			 if (texto == "Cancel"){
				 System.out.println("!!!! outra vez");
				 button.setText("Manter Pasta");
			  }
		  }
		  
		}
	}
}

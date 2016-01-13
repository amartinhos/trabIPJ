package interfaceGrafica;

import java.awt.BorderLayout;

import javax.swing.*;

import geral.Biblioteca;

public class EmpExpirados extends JPanel{
	
	PanelBase panelBase;
	Biblioteca biblioteca;
	JButton sair;
	
	EmpExpirados(Biblioteca biblioteca){
		this.biblioteca = biblioteca;
		setLayout(new BorderLayout());
		
		panelBase = new PanelBase();
		
		
		sair = new JButton("Sair");
		panelBase.addButton(sair);
		
		panelBase.addColumn("Titulo");
		panelBase.addColumn("Autor");
		panelBase.addColumn("Utilizador");
		panelBase.addColumn("ID");
		panelBase.addColumn("Data Entrega Esperada");
		
		add(panelBase);
		
	}
	private void setRow(String[] s){
	panelBase.addRow(s);

}
}



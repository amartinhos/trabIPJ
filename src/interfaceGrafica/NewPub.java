package interfaceGrafica;

import java.awt.event.ActionListener;

import javax.swing.*;

public class NewPub extends JPanel{
	
	private JButton revista, tese, livro, jornal, cancelar;
	
	public NewPub(){
		
		livro = new JButton("Livro");
		tese = new JButton("Tese");
		revista = new JButton("Revista");
		jornal = new JButton("Jornal");
		cancelar = new JButton("Cancelar");
		
		add(livro);
		add(tese);
		add(revista);
		add(jornal);
		add(cancelar);
	}
	
	public void addActionCancelar(ActionListener listener) {
		cancelar.addActionListener(listener);
	}
	
	public void addActionLivro(ActionListener listener) {
		livro.addActionListener(listener);
	}

	public void addActionTese(ActionListener listener) {
		tese.addActionListener(listener);
	}
	
	public void addActionRevista(ActionListener listener) {
		revista.addActionListener(listener);
	}
	
	public void addActionJornal(ActionListener listener) {
		jornal.addActionListener(listener);
	}
	
	
}

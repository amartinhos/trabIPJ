package interfaceGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;

import geral.Biblioteca;
import geral.Path;
import geral.Utilizador;

public class Login extends JFrame implements ActionListener {
	
	
	private JLabel user, pass, text;
	private JButton go;
	private JPanel panel;
	private JTextField field1;
	private JPasswordField field2;
	private String username;
	private String password;
	private Biblioteca biblioteca;
	private Utilizador utilizador;
	private Frame g;

	public Login(Biblioteca biblioteca) {
		
		this.biblioteca = biblioteca;
		
		this.setVisible(true);
		this.setSize(500, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		user = new JLabel("username:");
		pass = new JLabel("password:");
		
		field1 = new JTextField(null, 10);
		field2 = new JPasswordField(null, 10);
		
		text = new JLabel();
		text.setForeground(Color.RED);

		go = new JButton("GO");
		go.setSize(20, 20);
		
		go.addActionListener(this);
		field2.addActionListener(this);

		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.add(user);
		panel.add(field1);
		panel.add(pass);
		panel.add(field2);
		panel.add(go);
		panel.add(text, BorderLayout.SOUTH);
		this.setContentPane(panel);
		
        MyShutdownHook shutdownHook = new MyShutdownHook();
        Runtime.getRuntime().addShutdownHook(shutdownHook);        

	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * Implementacao das acoes despoletadas ao pressionar o JButton 'go' ou o botao 'Enter' (quando no field2).
		 * Se o username e a password corresponderem a um dos utilizadores da biblioteca 'biblioteca' sera lancado
		 * um novo objeto da classe Frame, caso contrario, sera despoletada uma mensagem de erro
		 */
		if (e.getSource().equals(go) || e.getSource().equals(field2)) {
			username = field1.getText();			
			password = new String(field2.getPassword());
			utilizador = biblioteca.getUtilizador(username, password);
			
			if (utilizador == null) {
				text.setText("Utilizador ou password invalidos");
			} else {
				try {
					this.dispose();
					g = new Frame(biblioteca, utilizador);
				} catch (ClassNotFoundException | IOException | IllegalArgumentException | SecurityException e1) {					
				e1.printStackTrace();
				}
			}
		}
	}

		
	private void shutdown() {
        System.out.println("Closing do Login");
        Path.write(biblioteca);
	}

	private class MyShutdownHook extends Thread {
    public void run() {
        shutdown();
    }
}

}

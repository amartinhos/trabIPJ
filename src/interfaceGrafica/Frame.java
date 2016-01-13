package interfaceGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import geral.Biblioteca;
import geral.Path;
import geral.Utilizador;
import geral.Utilizador.User;
import interfaceGrafica.EscolhaPubTeste.TipoAcao;

/**
 * Classe em que e' construido um JFrame qu sera preenchido por um JPanel
 * especifico para o tipo de utilizador passado ao construtor
 */

public class Frame extends JFrame {

	private PanelColaborador panel;
	private PanelLeitor2 panel1;
	private PanelChefe panel2;
	private Biblioteca biblioteca;
	
	
	public Frame(Biblioteca biblioteca, Utilizador utilizador)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		
		this.biblioteca = biblioteca;
		    
		this.setVisible(true);
		this.setSize(500, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (utilizador.getTipo().equals(User.colaborador)) {
			panel = new PanelColaborador(biblioteca);
			this.setContentPane(panel);
			logoutListener(panel);

		} else if (utilizador.getTipo().equals(User.leitor)) {
			panel1 = new PanelLeitor2(biblioteca, utilizador);
			this.setContentPane(panel1);
			logoutListener(panel1);
		}

		else if (utilizador.getTipo().equals(User.Bibliotecario_chefe)) {
			panel2 = new PanelChefe(biblioteca);
			this.setContentPane(panel2);
			logoutListener(panel2);
		}
		
		initialize();
	}
	
	//METODOS
	private void logoutListener(PanelColaborador panel) {
		panel.addActionLogout(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Path.write(panel.getBiblioteca());
				setIntGraf();
				dispose();
			}
		});
	}

	private void logoutListener(PanelLeitor2 panel) {
		panel.addActionLogout(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setIntGraf();
				dispose();
			}
		});

	}

	private void logoutListener(PanelChefe panel) {
		panel.addActionLogout(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Path.write(panel.getBiblioteca());
				setIntGraf();
				dispose();
			}
		});

	}

	private void setIntGraf() {
		
		Login login = new Login(biblioteca);
		login.setVisible(true);
		login.setSize(500, 100);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setResizable(false);
	}
	
	
	//SHUTDOWN HOOK
	
    private void initialize() {
        // add shutdown hook
        MyShutdownHook shutdownHook = new MyShutdownHook();
        Runtime.getRuntime().addShutdownHook(shutdownHook);        
    }
	
	private void shutdown() {
            System.out.println("Closing");
            Path.write(biblioteca);
    }
	
    private class MyShutdownHook extends Thread {
        public void run() {
            shutdown();
        }
    }

}

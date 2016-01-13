package interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

import org.joda.time.DateTime;
import org.joda.time.Days;

import geral.Biblioteca;
import geral.Emprestimo;
import geral.Periodico;
import geral.Jornal;
import geral.Path;
import geral.Publicacao;
import geral.Requisitavel;
import geral.Utilizador;
import geral.Periodico.Periodicidade;
import geral.Utilizador.User;

public class PanelChefe extends JPanel implements ActionListener {
	private Biblioteca biblioteca;
	private JButton newUser, newPub, empExpirados, logout;
	private JLabel label;
	private JPanel panel;

	// ...isto nao e' para estar aqui
	private String s = "A sua acao foi concluida com sucesso";

	PanelChefe(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;

		setLayout(new BorderLayout());

		newUser = new JButton("Novo Utilizador");
		newPub = new JButton("Nova Publicacao");
		empExpirados = new JButton("Emprestimos Expirados");
		logout = new JButton("Logout");
		
		
		label = new JLabel();

		panel = new JPanel();
		panel.setVisible(true); // verificar necessidade
		
		newUser.addActionListener(this);
		newPub.addActionListener(this);
		empExpirados.addActionListener(this);
		

		panel.add(newUser);
		panel.add(newPub);
		panel.add(empExpirados);
		panel.add(logout);
		panel.add(label);
		add(panel);
	}

	public void addActionLogout(ActionListener listener) {
		logout.addActionListener(listener);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(newUser)) {
			panel.setVisible(false);
			NewUser novoUser = new NewUser(biblioteca);
			add(novoUser);

			novoUser.addActionCancelar(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					novoUser.setVisible(false);
					panel.setVisible(true);
				}
			});
			novoUser.addActionOK(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					if (novoUser.getTest()) {
						// FALTA USAR O ID
						biblioteca.addUtilizador(new Utilizador(novoUser.getNome(), novoUser.getPassword(),
								novoUser.getUsername(), User.valueOf(novoUser.getTipo())));

						//Path.write(biblioteca);

						novoUser.setVisible(false);
						panel.setVisible(true);
					}
				}
			});
		} else if (e.getSource().equals(newPub)) {
			panel.setVisible(false);
			NewPub novaPub = new NewPub();
			add(novaPub);
			
			novaPub.addActionCancelar(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panel.setVisible(true);		
					novaPub.setVisible(false);
				}
			});

			novaPub.addActionJornal(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AddJornal jornal = new AddJornal();
					add(jornal);
					novaPub.setVisible(false);

					jornal.addActionCancelar(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							novaPub.setVisible(true);
							jornal.setVisible(false);
						}
					});

					jornal.addActionOK(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if (jornal.isOK()) {
								System.out.println("OK");
								String[] areas = jornal.getAreas().split(",");
								ArrayList<String> a = new ArrayList<>();
								for (String string : areas) {
									string.trim();
									a.add(string);
								}
								// FALTA USAR A DATA DE RECECAO
								biblioteca.addPublicacao(new Jornal(jornal.getTitulo(), jornal.getDataPub(), a,
										Periodicidade.valueOf(jornal.getPeriodo())));

								//Path.write(biblioteca);

							}
							novaPub.setVisible(true);
							jornal.setVisible(false);
						}
					});
					


				}
			});
		}
		
		// FALTA TERMINAR ISTO
		else if (e.getSource().equals(empExpirados)){
			panel.setVisible(false);
			EmpExpirados empExpirados = new EmpExpirados(biblioteca);
			add(empExpirados);
			for (Publicacao publicacao : biblioteca.getPublicacao()) {
				if (publicacao instanceof Requisitavel && !(((Requisitavel)publicacao).isDisponivel()) ) {
					ArrayList<Emprestimo> emprestimos = ((Requisitavel)publicacao).getEmprestimos();
					Emprestimo lastEmprestimo = emprestimos.get(emprestimos.size()-1);
				//	int lastEmprestimo.getDataReq()
					if (Math.abs(Days.daysBetween(lastEmprestimo.getDataReq(), new DateTime()).getDays()) > ((Requisitavel)publicacao).getMaxEmp()){
						
					}
				}
			}
		}
	}
	
	public Biblioteca getBiblioteca(){
		return biblioteca;
	}
}

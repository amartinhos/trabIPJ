package interfaceGrafica;

import javax.swing.*;
import org.joda.time.DateTime;

import geral.Biblioteca;
import geral.Emprestimo;
import geral.Path;
import geral.Publicacao;
import geral.Requisitavel;
import geral.Utilizador.User;
import interfaceGrafica.EscolhaPubTeste.TipoAcao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelColaborador extends JPanel implements ActionListener {

	private Publicacao publicacao;
	private Biblioteca biblioteca;
	private JButton devolucao, requisicao, logout;
	private JLabel label;
	private JPanel panel;
	private EscolhaPubTeste escolhaPub;
	private EscolhaUserTeste escolhaUser;
	private String s = "A sua acao foi concluida com sucesso";

	PanelColaborador(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;

		setLayout(new BorderLayout());

		//BUTTONS
		requisicao = new JButton("Requisicao");
		devolucao = new JButton("Devolucao");
		logout = new JButton("Logout");
		
		// LABEL - Quando uma acao e' concluida com sucesso aparece a mensagem
		// da String s nesta Label
		label = new JLabel();
		
		//PANEL
		panel = new JPanel();

		//LISTENERS INTERNOS
		devolucao.addActionListener(this);
		requisicao.addActionListener(this);

		panel.add(requisicao);
		panel.add(devolucao);
		panel.add(logout);
		panel.add(label);
		add(panel);
	}

	// LISTENER EXTERNO
	public void addActionLogout(ActionListener listener) {
		logout.addActionListener(listener);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(devolucao)) {
			panel.setVisible(false);

			escolhaPub = new EscolhaPubTeste(biblioteca, TipoAcao.devolucao);
			add(escolhaPub);

			
			actionConcluir(escolhaPub);
			actionCancel(escolhaPub);
		
		}  else if (e.getSource().equals(requisicao)) {

			escolhaUser = new EscolhaUserTeste(biblioteca);	
			escolhaPub = new EscolhaPubTeste(biblioteca, TipoAcao.requisicao);
			panel.setVisible(false);
			add(escolhaPub);

			// (2)
			actionConcluir(escolhaPub);
			actionConcluir(escolhaUser);
			actionCancel(escolhaPub);
			actionCancel(escolhaUser);
		}
	}

	///////////////////////////////////////// METODOS////////////////////////
	
	public Biblioteca getBiblioteca(){
		return biblioteca;
	}
	
	private void actionCancel(EscolhaUserTeste escolha) {
		escolha.addActionCancel(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				escolha.setVisible(false);
				requisicao.doClick();
			}
		});
	}

	private void actionCancel(EscolhaPubTeste escolha) {
		escolha.addActionCancel(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(true);
				escolhaPub.setVisible(false);
				label.setText(null);
			}
		});
	}

	private void actionConcluir(EscolhaUserTeste escolha) {
		escolha.addActionConcluir(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (escolhaUser.isEscolhido() && escolhaUser.getUtilizador().getTipo().equals(User.leitor)) {
					((Requisitavel) publicacao).requisita(
							new Emprestimo(escolhaUser.getUtilizador(), new DateTime().toString("dd/MM/yyyy")));
					//Path.write(biblioteca);

					escolhaUser.setVisible(false);
					panel.setVisible(true);
					label.setText(s);
				} else
					escolhaUser.setComment("Ocorreu um erro na solicitacao");
				;
			}
		});
	}

	private void actionConcluir(EscolhaPubTeste escolha) {
		escolha.addActionConcluir(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (escolhaPub.isEscolhido() && (escolhaPub.getPublicacao() instanceof Requisitavel)) {

					if (escolha.getTipo().equals(TipoAcao.devolucao)) {
						if (!(((Requisitavel) escolhaPub.getPublicacao()).isDisponivel())) {

							Publicacao p = escolhaPub.getPublicacao();
							((Requisitavel) p).entrega();
							//Path.write(biblioteca);
							escolhaPub.setVisible(false);
							panel.setVisible(true);
							label.setText(s);
						}
					} else if (escolha.getTipo().equals(TipoAcao.requisicao)) {

						if (((Requisitavel) escolhaPub.getPublicacao()).isDisponivel()) {

							publicacao = escolhaPub.getPublicacao();
							escolhaPub.setVisible(false);
							add(escolhaUser);
						}
					}
				}
			}
		});
	}

}

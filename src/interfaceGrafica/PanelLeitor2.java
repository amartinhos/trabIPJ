package interfaceGrafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import geral.Biblioteca;
import geral.Info;
import geral.Publicacao;
import geral.Utilizador;

public class PanelLeitor2 extends JPanel implements ActionListener {

	private PanelBase panelBase;
	private Biblioteca biblioteca;
	private JButton findByArea, findAll, findMine, logout;
	private Utilizador utilizador;

	PanelLeitor2(Biblioteca biblioteca, Utilizador utilizador) {
		this.biblioteca = biblioteca;
		this.utilizador = utilizador;
		setLayout(new BorderLayout());

		panelBase = new PanelBase();

		findByArea = new JButton("Pesquisar por area");
		findAll = new JButton("Todas as publicacoes");
		findMine = new JButton("Minhas Requisicoes");
		logout = new JButton("Logout");

		findByArea.addActionListener(this);
		findAll.addActionListener(this);
		findMine.addActionListener(this);

		panelBase.addColumn("Titulo");
		panelBase.addColumn("Autores");
		panelBase.addColumn("Data Publicacao");
		panelBase.addColumn("Disponivel");

		setRow(GetLists.getAllPubs(biblioteca));

		panelBase.addButton(findByArea);
		panelBase.addButton(findAll);
		panelBase.addButton(findMine);
		panelBase.addButton(logout);
		add(panelBase);
	}

	public void addActionLogout(ActionListener listener) {
		logout.addActionListener(listener);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(findByArea)) {
			panelBase.emptyModel();
			setRow(GetLists.getByArea(biblioteca, panelBase.getText().trim()));
		}
		
		else if (e.getSource().equals(findAll)) {
			panelBase.emptyModel();
			setRow(GetLists.getAllPubs(biblioteca));
		}
		else if (e.getSource().equals(findMine)) {
			panelBase.emptyModel();
			setRow(GetLists.getMine(biblioteca, utilizador));
		}
	}

	private void setRow(ArrayList<Publicacao> pubs) {
		String[] s = null;		
		String[] f = { "getTitulo", "getAutor", "getDataPublicacao", "isDisponivel" };
		for (Publicacao p : pubs) {
			s = Info.getInfo(p, f);
			panelBase.addRow(s);
		}
	}

}

package interfaceGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import geral.Biblioteca;
import geral.Info;
import geral.Publicacao;

public class EscolhaPubTeste extends JPanel implements ActionListener {

	public enum TipoAcao {
		devolucao, requisicao
	}
	
	private TipoAcao tipo;
	private boolean escolhido = false;
	private JButton filtrar, concluir, cancelar, todasPubs;
	private PanelBase panelBase;
	private Biblioteca biblioteca;
	private ArrayList<Publicacao> allPublications = new ArrayList<>();
	private ArrayList<Publicacao> filterPublications = new ArrayList<>();
	private int line;

	EscolhaPubTeste(Biblioteca biblioteca, TipoAcao tipo) {
		this.tipo = tipo;
		this.biblioteca = biblioteca;

		setLayout(new BorderLayout());

		panelBase = new PanelBase();

		// BUTTONS
		filtrar = new JButton("Filtrar");
		todasPubs = new JButton("Todas as Publicacoes");
		cancelar = new JButton("Cancelar");
		if (tipo.equals(tipo.devolucao))
			concluir = new JButton("Concluir");
		else
			concluir = new JButton("Seguinte");

		panelBase.addButton(filtrar);
		panelBase.addButton(todasPubs);
		panelBase.addButton(concluir);
		panelBase.addButton(cancelar);

		// COLUNAS DA TABELA
		panelBase.addColumn("Titulo");
		panelBase.addColumn("Autores");
		panelBase.addColumn("Data Publicacao");
		panelBase.addColumn("Disponivel");

		// PREENCHIMENTO DA TABLE

		if (tipo.equals(TipoAcao.requisicao)) {
			allPublications = GetLists.getAllPubs(biblioteca);
			filterPublications = GetLists.getAllPubs(biblioteca);
			setRows(GetLists.getAllPubs(biblioteca));

		} else {
			allPublications = GetLists.getLoanedPubs(biblioteca);
			filterPublications = GetLists.getLoanedPubs(biblioteca);
			setRows(GetLists.getLoanedPubs(biblioteca));
		}

		if (panelBase.isModelEmpty())
			panelBase.setComment("Nao existem publicacoes emprestadas");

		// LISTENERS
		filtrar.addActionListener(this);
		todasPubs.addActionListener(this);
		
		panelBase.addListenerText(this);
		
		// LISTENERS DA TABLE
		addTableListeners(panelBase);

		this.add(panelBase);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(filtrar) || e.getSource().equals(panelBase)) {
			escolhido = false;
			panelBase.emptyModel();
			String titulo = panelBase.getText();
			filterPublications = GetLists.getPubsByTiTulo(biblioteca, titulo, allPublications);
			setRows(GetLists.getPubsByTiTulo(biblioteca, titulo, allPublications));
		}
		if (e.getSource().equals(todasPubs)) {
			escolhido = false;
			panelBase.emptyModel();
			filterPublications = GetLists.getAllPubs(biblioteca);
			setRows(GetLists.getAllPubs(biblioteca));

		}
	}
	
	//GETTERS/SETTERS

	public void setComment(String s) {
		panelBase.setComment(s);
	}

	public void addActionCancel(ActionListener listener) {
		cancelar.addActionListener(listener);
	}

	public void addActionConcluir(ActionListener listener) {
		concluir.addActionListener(listener);
	}

	public Publicacao getPublicacao() {
		return filterPublications.get(line);
	}

	public boolean isEscolhido() {
		return escolhido;
	}

	public TipoAcao getTipo() {
		return tipo;
	}

	//METODOS
	private void setRows(ArrayList<Publicacao> pubs) {
		String[] s = null;
		String[] f = { "getTitulo", "getAutor", "getDataPublicacao", "isDisponivel" };
		for (Publicacao p : pubs) {
			s = Info.getInfo(p, f);
			panelBase.addRow(s);
		}
	}

	private void addTableListeners(PanelBase panelBase) {
		panelBase.addActionTable(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (panelBase.getSelectedRow() > -1) {
					escolhido = true;
					line = panelBase.getSelectedRow();
				}
			}
		});

		panelBase.addMouseTable(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.getClickCount() == 2 && panelBase.getSelectedRow() > -1) {
					concluir.doClick();
				}
			}
		});
	}
}

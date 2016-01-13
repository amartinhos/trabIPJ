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
import geral.Utilizador;

public class EscolhaUserTeste extends JPanel implements ActionListener{

	private ArrayList<Utilizador> filterUsers = new ArrayList<>();
	private JButton filtrar,cancelar,concluir, allUsers;
	private int line;
	private boolean escolhido;
	private Biblioteca biblioteca;
	private PanelBase panelBase;
	
	EscolhaUserTeste(Biblioteca biblioteca){
		this.biblioteca = biblioteca;
		panelBase = new PanelBase();
		
		setLayout(new BorderLayout());
	
		
		// BUTTONS
		filtrar = new JButton("Filtrar");
		concluir = new JButton("Concluir");
		cancelar = new JButton("Cancelar");
		allUsers = new JButton("Todos os Utilizadores");
		
		panelBase.addButton(filtrar);
		panelBase.addButton(allUsers);
		panelBase.addButton(concluir);
		panelBase.addButton(cancelar);
		
		
		//LISTENERS
		filtrar.addActionListener(this);
		allUsers.addActionListener(this);
		// LISTENERS DA TABELA - passar para um metodo?
		addTableListeners(panelBase);
		
		//ADICIONAR COLUNAS 'A TABELA E 
		panelBase.addColumn("Nome");
		panelBase.addColumn("Tipo");
		
		//PREENCHIMENTO DA TABELA
		filterUsers = biblioteca.getUtilizadores();
		setRow(filterUsers);
		
		//
		add(panelBase);	
	}
	
	// GETTERS/SETTERS
	public boolean isEscolhido(){
		return escolhido;
	}

	public void addActionCancel(ActionListener listener){
		cancelar.addActionListener(listener);
	}
	public void addActionConcluir(ActionListener listener){
		concluir.addActionListener(listener);
	}
	
	public Utilizador getUtilizador(){
		return filterUsers.get(line);
	}
	
	public void setComment(String s) {
		panelBase.setComment(s);;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(filtrar)){
			escolhido = false;	
			panelBase.emptyModel();
			String titulo = panelBase.getText();
			
			filterUsers = GetLists.getUserByTiTulo(biblioteca, titulo);
			setRow(filterUsers);
		}
		if (e.getSource().equals(allUsers)){
			escolhido = false;
			panelBase.emptyModel();
			filterUsers = GetLists.getAllUsers(biblioteca);
			setRow(filterUsers);
		}
	}
	
	
	
	//METODOS
	private void setRow(ArrayList<Utilizador> user) {
	
		for (Utilizador utilizador : user) {
			String[] s = {utilizador.getNome(),utilizador.getTipo().name()};
			panelBase.addRow(s);
		}
	}

	private void addTableListeners(PanelBase panelBase){
		panelBase.addActionTable(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent event) {
		        if (panelBase.getSelectedRow() > -1) {
		          	escolhido = true;
		            line = panelBase.getSelectedRow();
		        }
		    }
		});
		
		panelBase.addMouseTable(new MouseAdapter(){
			public void mousePressed(MouseEvent me) {
				if(me.getClickCount()==2 && panelBase.getSelectedRow()>-1){
					concluir.doClick();
				}
			}
		});
	}
}

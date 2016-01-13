package geral;

import java.util.ArrayList;

public interface Requisitavel {
	void requisita(Emprestimo emprestimo);
	
	void entrega();
	
	boolean isDisponivel();
	
	Utilizador getUtilizador();
	
	int getMaxEmp();
	
	ArrayList<Emprestimo> getEmprestimos();
	
	String estatistica();
}

package geral;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.joda.time.DateTime;

public class Livro extends NaoPeriodico {

	private String[] autores; // nota * mudar para arrayList
	private int edicao;
	private String isbn;
	private String editor;

	public Livro(String titulo, String dataPublicacao, ArrayList<String> areas, int codBarras, int diasRequisicaoMax
			, String[] autores, int edicao, String isbn, String editor) {
		super(titulo, dataPublicacao, areas, codBarras, diasRequisicaoMax);
		this.autores = autores;
		this.edicao = edicao;
		this.isbn = isbn;
		this.disponivel = true;
		this.editor = editor;
	}

	@Override
	public String getAutor() {

		String autoresToString = "";
		if (autores.length == 0)
			return null;
		else {
			autoresToString = autores[0];
			for (int i = 1; i < autores.length; i++) {
				autoresToString += ", " + autores[i];
			}
		}
		return autoresToString;
	}

	// isto tem de ser passado para a interface Requisitavel
	public String estado() {
		if (this.disponivel)
			return "disponivel";
		else
			return "indisponivel";
	}


	@Override
	public Utilizador getUtilizador() {
		if (this.isDisponivel()) {
			return null;
		}
		else{
			return (emprestimos.get((emprestimos.size()-1))).getUtilizador();
		}
	}
	
	@Override
	public boolean isDisponivel(){
		return disponivel;
	}
}

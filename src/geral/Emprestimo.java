package geral;

import java.io.Serializable;

import org.joda.time.DateTime;

import DateTime.SetDateTime;

public class Emprestimo implements Serializable{ // requisicao
	
	private Utilizador user;
	private DateTime dataReq;
	private DateTime dataEnt = null;
	
	public Emprestimo(Utilizador u, String dataRequisicao) {
		super();
		this.user = u;
		this.dataReq = SetDateTime.setDate(dataRequisicao);	
	}
	
	public DateTime getDataEnt() {
		return dataEnt;
	}

	public Emprestimo(Utilizador u, String dataRequisicao, String dataEntrega) {
		super();
		this.user = u;
		this.dataReq = SetDateTime.setDate(dataRequisicao);
		setDataEntrega(dataEntrega);
	}

	public void setDataEntrega(String date){
		dataEnt = SetDateTime.setDate(date);
	}
	
	public Utilizador getUtilizador(){
		return user;
	}
	
	public DateTime getDataReq(){
		return dataReq;
	}
	
	
	
	

}

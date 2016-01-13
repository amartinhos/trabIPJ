package geral;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

import DateTime.SetDateTime;

public abstract class NaoPeriodico extends Publicacao implements Requisitavel{
	
	protected boolean disponivel;
	protected int codBarras;
	protected int tempReq;
	protected ArrayList<Emprestimo> emprestimos = new ArrayList<>();
	
	
	public NaoPeriodico(String titulo, String dataPublicacao, ArrayList<String> areas, int codBarras,
			int tempReq) {
		super(titulo, dataPublicacao, areas);
		this.codBarras = codBarras;
		this.disponivel = true;
		this.tempReq = tempReq;
	}
	
	public int getCodBarras(){
		return codBarras;
	}
	
	@Override
	public boolean isDisponivel(){
		return disponivel;
	}
	
	// isto nao deveria estar aqui certo?
	public String getAutor(){
		return null;
	}

	@Override
	public int getMaxEmp() {
		return tempReq;
	}
	
	@Override
	public ArrayList<Emprestimo> getEmprestimos() {
		return emprestimos;
	}
	
	@Override
	public void requisita(Emprestimo emprestimo) {
		this.disponivel = false;
		emprestimos.add(emprestimo);
	}

	@Override
	public void entrega() {
		disponivel = true;
		DateTime time = new DateTime();		
		emprestimos.get(emprestimos.size()-1).setDataEntrega(new DateTime().toString("dd/MM/yyyy"));
	}

	@Override
	public String estatistica(){
		
		return Auxiliar.estatistica((Publicacao) this, this.emprestimos);
		
/*		String estat_obra = super.titulo+","+super.dataPublicacao.toString("dd/MM/yyyy");
		
		if (emprestimos.size()!=0){
			int meses[]=new int[12];
			int max=0;
			int min=0;
			int total=0;
			int k=0;
	

			for (;k<emprestimos.size() && 
					emprestimos.get(k).getDataReq().isBefore(SetDateTime.setDate("01/"+(new DateTime().minusMonths(12).getMonthOfYear())+"/"+(new DateTime().minusMonths(12).getYear())));k++){
			}
			
			if (emprestimos.size()>k && emprestimos.get(k).getDataReq().isBefore(SetDateTime.setDate("01/"+(new DateTime().getMonthOfYear())+"/"+(new DateTime().getYear())))) {
				meses[emprestimos.get(k).getDataReq().getMonthOfYear()-1]++;
				max = Days.daysBetween(emprestimos.get(k).getDataReq(), emprestimos.get(k).getDataEnt()).getDays();
				min = max;
				total=max;
			}
			for(int i=k+1;i<emprestimos.size() && emprestimos.get(i).getDataReq().isBefore(SetDateTime.setDate("01/"+(new DateTime().getMonthOfYear())+"/"+(new DateTime().getYear())));i++){
				Emprestimo empTemp = emprestimos.get(i);
				meses[empTemp.getDataReq().getMonthOfYear()-1]++;
				int duracao = Days.daysBetween(empTemp.getDataReq(), empTemp.getDataEnt()).getDays();
				if (duracao>max) {
					max=duracao;
				} else if (duracao < min) {
					min = duracao;
				}
				total = total + duracao;
			}
			
			for (int i:meses){
				estat_obra=estat_obra+","+i;
			}
			estat_obra=estat_obra+","+(emprestimos.size()-k)+","+min+","+max+","+total/(emprestimos.size()-k)+","+this.getInfo();
		}else {
			estat_obra = estat_obra+",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"+this.getInfo();
		}
		return estat_obra; */
		
	}
}

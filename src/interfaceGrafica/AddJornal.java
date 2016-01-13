package interfaceGrafica;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Date; 


import javax.swing.*;

import com.michaelbaranov.microba.calendar.DatePicker;

import geral.Periodico.Periodicidade;



public class AddJornal extends JPanel implements FocusListener,ActionListener{
	
	private boolean datasOK = true, tituloOK = false, areasOK = false;
	private JLabel titulo, areas, labelDataRececao, labelDataPub, tituloNotas, areasNotas, dataPubNotas, dataRecNotas, periodo;
	private JTextField textTitulo, textAreas;
	private DatePicker dataPub, dataRececao;
	private JPanel panelTitulo, panelDataRececao, panelDataPub, panelArea, panelButtons, panelPeriodo;
	private JButton cancelar, ok;
	private JComboBox boxPeriodo;

	
	AddJornal() {
		
		this.setLayout(new GridLayout(10,1));		
		Font font = new Font("Serif", Font.PLAIN, 12);
		
		// COMPONENTES PRIMARIOS
		titulo = new JLabel("Titulo");
		areas = new JLabel("Area(s)");
		labelDataRececao = new JLabel ("Data de rececao");
		labelDataPub = new JLabel("Data Publicacao");
		periodo = new JLabel("Periodicidade");
		
		Dimension d = new Dimension(100, 20);
		titulo.setPreferredSize(d);
		areas.setPreferredSize(d);
		labelDataRececao.setPreferredSize(d);
		periodo.setPreferredSize(d);
		labelDataPub.setPreferredSize(d);
		
		// COMPONENTES TERCEIROS
		areasNotas = new JLabel("Indique as areas separadas por virgula");
		tituloNotas = new JLabel();
		dataPubNotas = new JLabel();
		dataRecNotas = new JLabel();
		
		areasNotas.setFont(font);
		tituloNotas.setFont(font);
		
		//COMPONENTES SEGUNDOS
		
		boxPeriodo = new JComboBox(Periodicidade.values());
		textTitulo = new JTextField (null, 10);
		textAreas = new JTextField (null, 10);
		dataPub=new DatePicker(new Date(), new SimpleDateFormat("dd/MMMM/yyyy"));	
		dataRececao = new DatePicker(new Date(), new SimpleDateFormat("dd/MMMM/yyyy"));	
			
		//COMPONENTES PRINCIPAIS
		
		FlowLayout layout = new FlowLayout(new FlowLayout().LEFT);
		panelTitulo = new JPanel(layout);
		panelArea = new JPanel(layout);
		panelDataRececao = new JPanel(layout);
		panelDataPub = new JPanel(layout);
		panelButtons = new JPanel(layout);
		panelPeriodo = new JPanel(layout);
		
		// BUTTONS
		cancelar = new JButton("Cancelar");
		ok = new JButton("OK");
		
		
		//LISTENERS
		textTitulo.addFocusListener(this);
		textAreas.addFocusListener(this);
		dataPub.addFocusListener(this);
		dataRececao.addFocusListener(this);
		dataPub.addActionListener(this);
		dataRececao.addActionListener(this);
	
		//COLAGENS NOS PANELS (DEVIA FAZER UM METODO PARA ISTO ESTAR MAIS FLUIDO)
		panelTitulo.add(titulo);
		panelTitulo.add(textTitulo);
		panelTitulo.add(tituloNotas);
		add(panelTitulo);
		panelArea.add(areas);
		panelArea.add(textAreas);
		panelArea.add(areasNotas);
		add(panelArea);
		panelDataRececao.add(labelDataRececao);
		panelDataRececao.add(dataRececao);
		panelDataRececao.add(dataRecNotas);
		add(panelDataRececao);
		panelDataPub.add(dataPub);
		panelDataPub.add(dataPub);
		panelDataPub.add(dataPubNotas);
		add(panelDataPub);
		panelPeriodo.add(periodo);
		panelPeriodo.add(boxPeriodo);
		add(panelPeriodo);
		panelButtons.add(ok);
		panelButtons.add(cancelar);
		add(panelButtons);

	}
	
	public void addActionOK(ActionListener listener) {
		ok.addActionListener(listener);
	}

	public void addActionCancelar(ActionListener listener) {
		cancelar.addActionListener(listener);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(textAreas)){
			if (!areasOK) {
				areasNotas.setText("Indique as areas separadas por virgula");
			}			
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(textAreas)){
			if (isAlpha(textAreas.getText())) {
				areasOK = true;
				areasNotas.setText("OK!");
				areasNotas.setForeground(Color.BLUE);
			}
			else{
				areasOK=false;
				areasNotas.setText("Erro!");
				areasNotas.setForeground(Color.RED);
			}
		}
		
		if (e.getSource().equals(textTitulo)){
			if(!textTitulo.getText().isEmpty()) {
				tituloOK = true;
				tituloNotas.setText("OK!");
				tituloNotas.setForeground(Color.BLUE);
			}
			else {
				tituloNotas.setText("O campo nao esta preenchido");
				tituloNotas.setForeground(Color.RED);
				tituloOK = false;
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(dataRececao)){
			System.out.println("OK");
			if (dataRececao.getDate().before(dataPub.getDate())) {
				System.out.println("OK");
				dataPubNotas.setText("Verifique as datas escolhidas");
				dataPubNotas.setForeground(Color.RED);
				datasOK = false;
			}
			else {
				dataPubNotas.setText("");
				datasOK = true;
			}	
		}
	}
	
	public String getTitulo(){
		return textTitulo.getText();
	}
	
	public String getAreas(){
		return textAreas.getText();
	}
	
	public String getDataPub(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		return sdf.format(dataPub.getDate());
		//return pickerPub.getDate().toString("dd/MM/yyyy");
	}
	public String getDataRececao(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		return sdf.format(dataRececao.getDate());
	}
	
	public boolean isOK(){
		System.out.println(areasOK+" "+tituloOK+" "+datasOK);
		if (areasOK && tituloOK && datasOK) return true;
		else return false;
	}
	
	public String getPeriodo() {
		return boxPeriodo.getSelectedItem().toString();
	}


	private boolean isAlpha(String s){
		if (s.isEmpty()) return false;
		char[] chars = s.toCharArray();
		
		for (char c : chars) {
			if (!Character.isLetter(c) && !Character.isWhitespace(c) && !(c==',')) {
				return false;
			}
		}
		return true;
	}
	
}

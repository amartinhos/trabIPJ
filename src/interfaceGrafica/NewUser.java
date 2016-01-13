package interfaceGrafica;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.*;

import geral.Biblioteca;
import geral.Utilizador;
import geral.Utilizador.User;

public class NewUser extends JPanel implements FocusListener {

	// nome// tipo// username// password// ID?
	private boolean userOK = false, passOK = false, nomeOK = false, idOK = false;
	private JLabel labelNome, labelTipo, labelUser, labelPass, labelID;
	private JTextField textNome, textUser, textPass, textID;
	private JComboBox<?> boxTipo;
	private JLabel labelErro1, labelErro2, labelErro3, labelErro4, labelErro5;
	private JPanel panelNome, panelTipo, panelUser, panelPass, panelID, panelButton;
	private Biblioteca b;
	private JButton button, cancelar;
	private ArrayList<Utilizador> users;

	NewUser(Biblioteca b) {
		this.b = b;
		setLayout(new GridLayout(6, 1));
		users = b.getUtilizadores();
		
		
		// PAINEIS
		FlowLayout layout = new FlowLayout(new FlowLayout().LEFT);
		panelNome = new JPanel(layout);
		panelTipo = new JPanel(layout);
		panelUser = new JPanel(layout);
		panelPass = new JPanel(layout);
		panelID = new JPanel(layout);
		panelButton = new JPanel();

		//COMPONENTES 1
		labelNome = new JLabel("Nome");
		labelTipo = new JLabel("Tipo");
		labelUser = new JLabel("Username");
		labelPass = new JLabel("Password ");
		labelID = new JLabel("Bilhete Id.");

		Dimension d = new Dimension(75, 20);
		labelNome.setPreferredSize(d);
		labelTipo.setPreferredSize(d);
		labelUser.setPreferredSize(d);
		labelPass.setPreferredSize(d);
		labelID.setPreferredSize(d);
		
		// COMPONENTES 2
		textNome = new JTextField(null, 10);
		textUser = new JTextField(null, 10);
		textPass = new JTextField(null, 10);
		textID = new JTextField(null, 10);
		boxTipo = new JComboBox(User.values());
		boxTipo.setPreferredSize(new Dimension(135, 20));

		textNome.addFocusListener(this);
		textUser.addFocusListener(this);
		textPass.addFocusListener(this);
		textID.addFocusListener(this);

		
		// COMPONENTES 3
		Font font = new Font("Serif", Font.PLAIN, 12);

		labelErro1 = new JLabel();	
		labelErro2 = new JLabel();
		labelErro3 = new JLabel("The username must have 4 or more chars and no spaces");	
		labelErro4 = new JLabel("The password must have 4 or more chars and no spaces");	
		labelErro5 = new JLabel();
		
		labelErro1.setFont(font);
		labelErro3.setFont(font);
		labelErro4.setFont(font);
		labelErro5.setFont(font);
		
		//BUTTONS
		button = new JButton("OK");
		cancelar = new JButton("Cancelar");

		
		//COLAGEM DOS PAINEIS
		panelNome.add(labelNome, FlowLayout.LEFT);
		panelNome.add(textNome);
		panelNome.add(labelErro1);
		add(panelNome);
		panelTipo.add(labelTipo);
		panelTipo.add(boxTipo);
		panelTipo.add(labelErro2);
		add(panelTipo);
		panelUser.add(labelUser);
		panelUser.add(textUser);
		panelUser.add(labelErro3);
		add(panelUser);
		panelPass.add(labelPass);
		panelPass.add(textPass);
		panelPass.add(labelErro4);
		add(panelPass);
		panelID.add(labelID);
		panelID.add(textID);
		panelID.add(labelErro5);
		add(panelID);
		panelButton.add(button);
		panelButton.add(cancelar);
		add(panelButton);

	}

	//GETTERS/SETTERS
	public String getNome() {
		return textNome.getText();
	}

	public String getUsername() {
		return textUser.getText();
	}

	public String getPassword() {
		return textPass.getText();
	}

	public String getID() {
		return textID.getText();
	}

	public String getTipo() {
		return boxTipo.getSelectedItem().toString();
	}

	public boolean getTest() {
		if (userOK && passOK && nomeOK && idOK)
			return true;
		else
			return false;
	}

	//OUTSIDE LISTENERS
	public void addActionOK(ActionListener listener) {
		button.addActionListener(listener);
	}

	public void addActionCancelar(ActionListener listener) {
		cancelar.addActionListener(listener);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource().equals(textUser)) {
			if (!userOK) {
				labelErro3.setForeground(Color.BLACK);
				labelErro3.setText("The username must have 4 or more chars and no spaces");
			}
		}
		if (e.getSource().equals(textPass)) {
			if (!passOK) {
				labelErro4.setForeground(Color.BLACK);
				labelErro4.setText("The password must have 4 or more chars and no spaces");
			}

		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(textUser)) {
			userOK = true;
			int index = textUser.getText().indexOf(' ');

			if (textUser.getText().length() < 4 || index > -1) {
				labelErro3.setForeground(Color.RED);
				labelErro3.setText("Error !!");
				userOK = false;
			}
			
			for (int i = 0; i < users.size() && userOK; i++) {

				if (textUser.getText().equals(users.get(i).getUsername())) {
					labelErro3.setText("The username already exists");
					labelErro3.setForeground(Color.RED);
					userOK = false;
				}
			}
			
			if (userOK) {
				labelErro3.setText("OK!!");
				labelErro3.setForeground(Color.BLUE);
			}
		}
		
		if (e.getSource().equals(textPass)) {
			passOK = true;
			int index = textPass.getText().indexOf(' ');
			if (textPass.getText().length() < 4 || index > -1) {
				labelErro4.setForeground(Color.RED);
				labelErro4.setText("Error !!");
				passOK = false;
			}
			if (passOK) {
				labelErro4.setText("OK!!");
				labelErro4.setForeground(Color.BLUE);
			}
		}

		if (e.getSource().equals(textNome)) {

			if (isAlpha(textNome.getText())) {
				nomeOK = true;
				labelErro1.setText("OK!!");
				labelErro1.setForeground(Color.BLUE);
			} else {
				labelErro1.setText("O nome so pode conter letras e espacos");
				labelErro1.setForeground(Color.RED);
			}
		}

		if (e.getSource().equals(textID)) {

			if (isNumeric(textID.getText())) {
				idOK = true;
				labelErro5.setText("OK!!");
				labelErro5.setForeground(Color.BLUE);
			} else {
				labelErro5.setText("Este campo so pode conter numeros");
				labelErro5.setForeground(Color.RED);
			}

		}

	}

	private static boolean isAlpha(String name) {
		if (name.contains("  ") || name.length() == 0)
			return false;

		char[] chars = name.toCharArray();

		for (char c : chars) {
			if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
				return false;
			}
		}
		return true;
	}

	private static boolean isNumeric(String id) {

		if (id.length() == 0)
			return false;

		char[] chars = id.toCharArray();
		for (char c : chars) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

}

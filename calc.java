package calculadora;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.text.*;

public class calc extends JFrame implements KeyListener {
	private JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bc,bce,bdiv,bmult,bmenos,bmais,bvirg,bt,bp,bb;
	private JTextArea area;
	private JLabel visor,key;
	private JScrollPane scroll;
	private double soma = 0;
	private String valor = "",operador = "", valoranterior = "";
	private Container container;
	private GridBagLayout layout;
	private GridBagConstraints constraints;
	private JRadioButton b2d, b4d, b6d;
	private ButtonGroup bdec;
	private int decimais = 2;
	
	private class calcu implements ActionListener{
		public void actionPerformed (ActionEvent event) {
			if (event.getSource() == bc) teclouC();
		else if (event.getSource() == bce) teclouCE();
		else if (event.getSource() == bp) teclouP();
		else if (event.getSource() == bvirg) teclouVirgula();
		else if (event.getSource() == bb) teclouBackspace();
		else if (event.getSource() == b1) teclouNumeros("1");
		else if (event.getSource() == b2) teclouNumeros("2");
		else if (event.getSource() == b3) teclouNumeros("3");
		else if (event.getSource() == b4) teclouNumeros("4");
		else if (event.getSource() == b5) teclouNumeros("5");
		else if (event.getSource() == b6) teclouNumeros("6");
		else if (event.getSource() == b7) teclouNumeros("7");
		else if (event.getSource() == b8) teclouNumeros("8");
		else if (event.getSource() == b9) teclouNumeros("9");
		else if (event.getSource() == b0) teclouNumeros("0");
		else if (event.getSource() == bmais) teclouMais();
		else if (event.getSource() == bmenos) teclouMenos();
		else if (event.getSource() == bdiv) teclouDiv();
		else if (event.getSource() == bmult) teclouMult();
		else if (event.getSource() == bt) teclouEnter();
					
		}
	}
	private class calcul2 implements FocusListener{
		public void focusGained(FocusEvent event) {
			
		}
		public void focusLost(FocusEvent event) {
			
		}
	}
	private class calcul implements ItemListener{
		public void itemStateChanged(ItemEvent event) {
			if (event.getSource() == b2d) {
				decimais = 2;
			}
			if (event.getSource() == b4d) {
				decimais = 4;
			}
			if (event.getSource() == b6d) {
				decimais = 6;
			}
		}
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_ESCAPE) {
			int selectedOption = JOptionPane.showConfirmDialog(this,"Deseja sair realmente???");
			if(selectedOption == JOptionPane.YES_NO_OPTION) {
				dispose();
				System.exit(0);
			}
		}
		if(e.getKeyCode() == 67) teclouC();
		if(e.getKeyCode() == 69) teclouCE();
		if(e.getKeyCode() == 8) teclouBackspace();
		if(e.getKeyCode() == 110) teclouVirgula();
		if(((e.getKeyCode() >= e.VK_0) && (e.getKeyCode() <= e.VK_9)) || ((e.getKeyCode() >= e.VK_NUMPAD0)
				&& (e.getKeyCode() <= e.VK_NUMPAD9))) teclouNumeros(""+e.getKeyChar());
		if((e.getKeyCode() == 61) || (e.getKeyCode() == 107)) teclouMais();
		if((e.getKeyCode() == 45) || (e.getKeyCode() == 109)) teclouMenos();
		if((e.getKeyCode() == 59) || (e.getKeyCode() == 111)) teclouDiv();
		if(e.getKeyCode() == 106) teclouMult();
		if(e.getKeyCode() == 80) teclouP();
		if(e.getKeyCode() == 10) teclouEnter();
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void keyTyped(KeyEvent e) {
		
	}
	public void teclouC() {
		valor = "0";
		visor.setText(formatStrNumerica(valor,decimais));
	}
	public void teclouCE() {
		valor = "0";
		soma = 0;
		visor.setText(formatStrNumerica(valor,decimais));
		area.append(alinhaStringDir("----------------" + "\n"));
		area.append(alinhaStringDir(""+ "\n"));
	}
	public void teclouP() {
		area.append(alinhaStringDir(formatStrNumerica(valor,decimais) +"%" + "\n"));
		area.append(alinhaStringDir("----------------" + "\n"));
		if(operador == "*") soma = soma* (Double.parseDouble(valor)/100);
		else if(operador == "/") soma = soma / (Double.parseDouble(valor)/100);
		area.append(alinhaStringDir(formatStrNumerica("" + soma,decimais) + "=" + "\n"));
		area.append(alinhaStringDir("" + "\n"));
		visor.setText(formatStrNumerica("" + soma,decimais));
		valoranterior = "" + soma;
		soma = 0;
		valor = "";
	}
	public void teclouVirgula() {
		if (valor.lastIndexOf('.') == -1) valor = valor + ".";
		visor.setText(valor);
	}
	public void teclouBackspace() {
		if(valor.length() == 1) valor = "0";
		else valor = valor.substring(0, valor.length()-1);
		visor.setText(valor);
	}
	public void teclouNumeros (String s) {
		if(valor.length() <= 14) {
			if(valor == "0") valor = "";
			valor = valor + s;
			visor.setText(valor);
		}
	}
	public void teclouMais() {
		if(valor == "") valor = valoranterior;
		area.append(alinhaStringDir("----------------" + "\n"));
		area.append(alinhaStringDir(formatStrNumerica(valor,decimais) + "+" + "\n"));
		soma = soma + Double.parseDouble(valor);
		visor.setText(formatStrNumerica("" + soma,decimais));
		valoranterior = valor;
		valor = "";
		operador = "+";
	}
	public void teclouMenos() {
		if (valor == "") valor = valoranterior;
		area.append(alinhaStringDir("---------------" + "\n"));
		area.append(alinhaStringDir(formatStrNumerica(valor,decimais) + "-" +"\n"));
		soma = soma - Double.parseDouble(valor);
		visor.setText(formatStrNumerica("" + soma,decimais));
		valoranterior = valor;
		valor = "";
		operador = "-";
	}
	public void teclouDiv() {
		if (valor == "") valor = valoranterior;
		area.append(alinhaStringDir("-----------------" + "\n"));
		area.append(alinhaStringDir("" + "\n"));
		area.append(alinhaStringDir(formatStrNumerica(valor,decimais) + "/" + "\n"));
		soma = Double.parseDouble(valor);
		visor.setText(formatStrNumerica("" + soma,decimais));
		valor = "";
		operador = "/";
	}
	public void teclouMult() {
		if (valor == "") valor = valoranterior;
		area.append(alinhaStringDir("----------------" + "\n"));
		area.append(alinhaStringDir("" + "\n"));
		area.append(alinhaStringDir(formatStrNumerica(valor,decimais) + "*" + "\n"));
		soma = Double.parseDouble(valor);
		visor.setText(formatStrNumerica("" + soma,decimais));
		valor = "";
		operador = "*";
	}
	public void teclouEnter() {
		if ((operador == "+") || (operador == "-")) {
			area.append(alinhaStringDir("----------------" + "\n"));
			area.append(alinhaStringDir(formatStrNumerica("" + soma,decimais) + "T" + "\n"));
	}
		if ((operador == "/") && (soma != 0)) {
			area.append(alinhaStringDir(formatStrNumerica(valor,decimais) + "" + "\n"));
			area.append(alinhaStringDir("----------------" + "\n"));
			soma = soma / Double.parseDouble(valor);
			area.append(alinhaStringDir(formatStrNumerica("" + soma,decimais) + "=" + " \n"));
	}
		if (operador == "*") {
			area.append(alinhaStringDir(formatStrNumerica(valor, decimais) + "+" + "\n"));
			area.append(alinhaStringDir("----------------" + "\n"));
			soma = soma * Double.parseDouble(valor);
			area.append(alinhaStringDir(formatStrNumerica("" + soma,decimais) + "=" + "\n"));
	}
		area.append(alinhaStringDir("" + "\n"));
		visor.setText(formatStrNumerica("" + soma,decimais));
		soma = 0;
		valor = "";
	}
	public String alinhaStringDir(String s) {
		String alinhador = "";
		int i;
		for(i = 0; i < (36 - s.length()); i++){
			alinhador = alinhador + "";
		}
		return alinhador + s;
		
	}
	public String formatStrNumerica(String s, int dig) {
		DecimalFormat decimal = new DecimalFormat();
		decimal.setMinimumFractionDigits(dig);
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setGroupingSeparator(',');
		simbolos.setDecimalSeparator('.');
		decimal.setDecimalFormatSymbols(simbolos);
		return decimal.format(new java.math.BigDecimal(s));
	}
	private void addComponent(Component component, int row, int column, int width, int height) {
		constraints.gridx = column;
		constraints.gridy = row;
		constraints.gridwidth = width;
		constraints.gridheight = height;
		layout.setConstraints(component, constraints);
		container.add(component);
	}
public calc () {
		super ("Calc 1.0 (Renan)");
		container = getContentPane();
		layout = new GridBagLayout();
		container.setLayout(layout);
		constraints = new GridBagConstraints();
		
		b1 = new JButton ("1");
		b2 = new JButton ("2");
		b3 = new JButton ("3");
		b4 = new JButton ("4");
		b5 = new JButton ("5");
		b6 = new JButton ("6");
		b7 = new JButton ("7");
		b8 = new JButton ("8");
		b9 = new JButton ("9");
		b0 = new JButton ("0");
		bc = new JButton ("C");
		bc.setToolTipText("Tecle C");
		bce = new JButton ("CE");
		bce.setToolTipText("Tecle E");
		bdiv = new JButton ("/");
		bmult = new JButton ("*");
		bmenos = new JButton ("-");
		bmais = new JButton ("+");
		bvirg = new JButton (",");
		bvirg.setToolTipText("Tecle , (Virgula)");
		bt = new JButton ("=");
		bt.setToolTipText("Tecle Enter");
		bp = new JButton ("%");
		bp.setToolTipText("Tecle P");
		bb = new JButton ("<=");
		bb.setToolTipText("Tecle Backspace");
		key = new JLabel ("0");
		visor = new JLabel ("0.00");
		visor.setHorizontalAlignment(SwingConstants.RIGHT);
		visor.setFont(new Font("Courier New", Font.BOLD, 16));
		visor.setForeground(Color.RED);
		visor.setToolTipText("Valores até 14 digitos");
		area = new JTextArea(12,15);
		area.setEditable(false);
		area.setFont(new Font("Courier New", Font.BOLD,16));
		scroll = new JScrollPane(area, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBorder(new LineBorder(Color.pink,3,true));
		b2d = new JRadioButton ("2", true);
		b4d = new JRadioButton ("4", false);
		b6d = new JRadioButton("6 : Digitos Decimais", false);
		bdec = new ButtonGroup();
		bdec.add(b2d);
		bdec.add(b4d);
		bdec.add(b6d);
		visor.setFocusable(true);
		b1.setFocusable(false);
		b2.setFocusable(false);
		b3.setFocusable(false);
		b4.setFocusable(false);
		b5.setFocusable(false);
		b6.setFocusable(false);
		b7.setFocusable(false);
		b8.setFocusable(false);
		b9.setFocusable(false);
		b0.setFocusable(false);
		bc.setFocusable(false);
		bce.setFocusable(false);
		bmais.setFocusable(false);
		bmenos.setFocusable(false);
		bdiv.setFocusable(false);
		bp.setFocusable(false);
		bmult.setFocusable(false);
		area.setFocusable(false);
		bvirg.setFocusable(false);
		bt.setFocusable(false);
		bb.setFocusable(false);
		b2d.setFocusable(false);
		b4d.setFocusable(false);
		b6d.setFocusable(false);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.weightx = 0;
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.BOTH;
		addComponent (scroll, 0,0,5,1);
		addComponent (visor, 1,0,5,1);
		addComponent (b2d, 2,0,1,1);
		addComponent (b4d, 2,1,1,1);
		addComponent (b6d, 2,2,3,1);
		constraints.weightx = 0;
		constraints.weightx = 5;
		addComponent (bp, 3,0,1,1);
		addComponent (bce, 3,4,1,1);
		addComponent (bc, 4,4,1,1);
		addComponent (bb, 5,4,1,1);
		addComponent (bdiv, 3,1,1,1);
		addComponent (bmult, 3,2,1,1);
		addComponent (bmenos, 3,3,1,1);
		addComponent (b7, 4,0,1,1);
		addComponent (b8, 4,1,1,1);
		addComponent (b9, 4,2,1,1);
		addComponent (bmais, 4,3,1,1);
		addComponent (b4, 5,0,1,1);
		addComponent (b5, 5,1,1,1);
		addComponent (b6, 5,2,1,1);
		addComponent (bvirg, 5,3,1,1);
		addComponent (b1, 6,0,1,1);
		addComponent (b2, 6,1,1,1);
		addComponent (b3, 6,2,1,1);
		addComponent (b0, 6,3,1,1);
		addComponent (bt, 6,4,1,1);
		
		calcu handletAcao = new calcu();
		bc.addActionListener(handletAcao);
		bce.addActionListener(handletAcao);
		bp.addActionListener(handletAcao);
		bdiv.addActionListener(handletAcao);
		bmult.addActionListener(handletAcao);
		bmenos.addActionListener(handletAcao);
		bmais.addActionListener(handletAcao);
		bt.addActionListener(handletAcao);
		bvirg.addActionListener(handletAcao);
		b1.addActionListener(handletAcao);
		b2.addActionListener(handletAcao);
		b3.addActionListener(handletAcao);
		b4.addActionListener(handletAcao);
		b5.addActionListener(handletAcao);
		b6.addActionListener(handletAcao);
		b7.addActionListener(handletAcao);
		b8.addActionListener(handletAcao);
		b9.addActionListener(handletAcao);
		b0.addActionListener(handletAcao);
		bb.addActionListener(handletAcao);
		
		calcul2 handlerFocus = new calcul2();
		calcul handlerRadio = new calcul();
		b2d.addItemListener(handlerRadio);
		b4d.addItemListener(handlerRadio);
		b6d.addItemListener(handlerRadio);
		visor.addKeyListener(this);
		setSize(285,480);
		Dimension resVideo = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension tamForm = getSize();
		setLocation ((resVideo.width - tamForm.width)/2,(resVideo.height - tamForm.height)/2);
		setResizable(false);
		setVisible(true);
		}

	public static void main(String[] args) {
		calc app = new calc ();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

}

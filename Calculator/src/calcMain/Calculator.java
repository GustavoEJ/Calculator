package calcMain;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.*;
import java.awt.*;

public class Calculator implements ActionListener{

	private JFrame frame;
	private JLabel textlabel;
	private JTextArea textfield;
	private JTextPane textfield2;
	JButton[] numberButtons = new JButton[10]; 
	JButton[] functionButtons = new JButton[14];
	JButton addButton, subButton, mulButton, divButton,sqrtButton,pwrButton,modulButton, oneOverX;
	JButton decButton, equButton, delButton, clrButton, clrAllButton, negButton;
	JPanel panel;
	Font myFont = new Font("Arial",Font.PLAIN,30);
	
	private Double num1=0.0, num2=0.0, numLast =0.0, result=0.0;
	private char operator; //stores the operator
	boolean equalPress; //cleans the text field when you press a number button after an operation
	boolean startPress = true; //cleans the text field after a press when starting
	boolean negAfterOp = false; //keeps track of the result values so the negative applies (or not) to the result in the text field
	boolean lastCalc; // keeps track if it's time to move to another operation or stay in the same one, only changing the number values

	
	// ================================================================================================
	// Creates the body of the Calculator
	
	Calculator(){
		
		frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450,650);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		textlabel = new JLabel();
		textlabel.setBounds(12, 45, 410, 90);
		textlabel.setBackground(Color.white);
		textlabel.setOpaque(true);
		
		
		textfield = new JTextArea();
		textfield.setBounds(15, 85, 403, 40);
		textfield.setFont(new Font("Arial",Font.BOLD,33));
		textfield.setEditable(false);
		textfield.setBackground(Color.white);
		textfield.setText("0");

		textfield2 = new JTextPane();
		StyledDocument style = textfield2.getStyledDocument();
		SimpleAttributeSet align= new SimpleAttributeSet();
		StyleConstants.setAlignment(align, StyleConstants.ALIGN_RIGHT);
		style.setParagraphAttributes(0, style.getLength(), align, false);
		
		textfield2.setBounds(80, 50, 338, 25);
		textfield2.setFont(new Font("Arial",Font.PLAIN,17));
		textfield2.setEditable(false);
		textfield2.setBackground(Color.white);
		
		addButton = new JButton("+");
		subButton = new JButton("-");
		mulButton = new JButton("×");
		divButton = new JButton("÷");
		decButton = new JButton(".");
		equButton = new JButton("=");
		delButton = new JButton("<<");
		clrButton = new JButton("CE");
		clrAllButton = new JButton("C");
		negButton = new JButton("+/-");
		sqrtButton = new JButton("√x"); 
		pwrButton = new JButton("^");
		modulButton = new JButton("%");
		oneOverX = new JButton("1/x");
		
		functionButtons[0] = addButton;
		functionButtons[1] = subButton;
		functionButtons[2] = mulButton;
		functionButtons[3] = divButton;
		functionButtons[4] = decButton;
		functionButtons[5] = equButton;
		functionButtons[6] = delButton;
		functionButtons[7] = clrButton;
		functionButtons[8] = negButton;
		functionButtons[9] = clrAllButton;
		functionButtons[10] = sqrtButton;
		functionButtons[11] = pwrButton;
		functionButtons[12] = modulButton;
		functionButtons[13] = oneOverX;
		
		for(int i=0; i<14; i++) {
			functionButtons[i].addActionListener(this);
			functionButtons[i].setFont(myFont);
			functionButtons[i].setFocusable(false);
		}
		
		for(int i=0; i<10; i++) {
			numberButtons[i] = new JButton(String.valueOf(i));
			numberButtons[i].addActionListener(this);
			numberButtons[i].setFont(myFont);
			numberButtons[i].setFocusable(false);
		}
					
		panel = new JPanel();
		panel.setBounds(12, 160, 410, 430);
		panel.setLayout(new GridLayout(6,4,10,10));
		panel.setBackground(Color.LIGHT_GRAY);
		
		panel.add(modulButton);
		panel.add(clrButton);
		panel.add(clrAllButton);
		panel.add(delButton);
		panel.add(oneOverX);
		panel.add(sqrtButton);
		panel.add(pwrButton);
		panel.add(divButton);
		panel.add(numberButtons[7]);
		panel.add(numberButtons[8]);
		panel.add(numberButtons[9]);
		panel.add(mulButton);
		panel.add(numberButtons[4]);
		panel.add(numberButtons[5]);
		panel.add(numberButtons[6]);
		panel.add(subButton);
		panel.add(numberButtons[1]);
		panel.add(numberButtons[2]);
		panel.add(numberButtons[3]);
		panel.add(addButton);
		panel.add(negButton);
		panel.add(numberButtons[0]);
		panel.add(decButton);
		panel.add(equButton);
		
		frame.add(panel);
		frame.add(textfield);
		frame.add(textfield2);
		frame.add(textlabel);
		frame.setVisible(true);
	}
	// ================================================================================================
	
	
	private void returnResult() {
		if(result == result.intValue()) {
			textfield.setText(String.valueOf(Math.round(result)));
			num1 = result;
			
		}
		
		else {
			textfield.setText(String.valueOf(result));	
			num1 = result;
		}
	}
	
	private String returnNumber1() {	
		String Number1 = null;	
		
		if(num1 == num1.intValue()) {	
			Number1 = String.valueOf(num1.intValue());
		}
		
		else if (num1 == num1.doubleValue()) {
			Number1 = String.valueOf(num1);
		}
		
		return Number1;
	}
		
	private String returnNumber2() {
		String Number2 = null;
		
		if(num2 == num2.intValue()) {
			Number2 = String.valueOf(num2.intValue());
		}
		
		else if (num2 == num2.doubleValue()) {
			Number2 = String.valueOf(num2);
		}

		return Number2;
	}
	
	private String returnLastNumber() {
		String lastNumber = null;
		
		if (numLast == numLast.intValue()) {
			lastNumber = String.valueOf(numLast.intValue());
			
		}
		
		else if (numLast == numLast.doubleValue()) {
			lastNumber = String.valueOf(numLast);
		}	
		
		return lastNumber;
	}
	
	 private void showEquation() {
		
		 textfield2.setText(returnNumber1()+" "+operator);
	}
	 
	// ================================================================================================
	 //Starts the calculator
	public static void main(String[] args) {
		
		new Calculator();
	}
	// ================================================================================================
	
	
	
	public void actionPerformed(ActionEvent e) {
		
		if(startPress) {
			textfield.setText("");
			startPress = false;
		}
		
		while(equalPress == true) {
			returnResult();
			equalPress = false;
			textfield.setText("");
			textfield2.setText("");
		}
		
		for(int i=0; i<10; i++) {
		
			if(e.getSource()== numberButtons[i]) {
				textfield.setText(textfield.getText().concat(String.valueOf(i)));
			}
		}
	
		try {
			if(e.getSource()==decButton) {
				if(!(textfield.getText().contains(".")) && textfield.getText().isBlank()) {
					textfield.setText(textfield.getText()+"0.");
					System.out.println("segunda opÃ§Ã£o");
				}
			
				else if(!textfield.getText().contains(".")) {
					textfield.setText(textfield.getText()+".");
				}
			}
		
			if(e.getSource()==addButton) {
				if(textfield.getText().equals("")) {
					operator = '+';
				}
			
				else {
					num1 = Double.parseDouble(textfield.getText());
					operator = '+';
				}
		
				textfield.setText("");
				showEquation();
				lastCalc = false;
			}
			
			if(e.getSource()==subButton) {
				if(textfield.getText().equals("")) {
					operator = '-';
				}
			
				else {
					num1 = Double.parseDouble(textfield.getText());
					operator = '-';	
				}
			
				textfield.setText("");
				showEquation();
				lastCalc = false;
			}
		
			if(e.getSource()==mulButton) {
				if(textfield.getText().equals("")) {
					operator = '×';
				}
			
				else {
					num1 = Double.parseDouble(textfield.getText());
					operator = '×';
				}
			
				textfield.setText("");
				showEquation();
				lastCalc = false;
			}
		
			if(e.getSource()==divButton) {
				if(textfield.getText().equals("")) {
					operator = '÷';
				}
			
				else {
					num1 = Double.parseDouble(textfield.getText());
					operator = '÷';
				}
			
				textfield.setText("");
				showEquation();
				lastCalc = false;
			}
		
			if(e.getSource()==modulButton) {
				if(textfield.getText().equals("")) {
					operator = '%';		
				}
		
				else {
					num1 = Double.parseDouble(textfield.getText());
					operator = '%';
				}
			
				textfield.setText("");
				showEquation();
				lastCalc = false;
			}
		
			if(e.getSource()==sqrtButton) {
				if(textfield.getText().equals("")) {
					operator = '√';	
				}
			
				else {
					num1 = Double.parseDouble(textfield.getText());
					operator = '√';
				}
				
				result = Math.sqrt(num1);
				numLast = num1;	
				textfield2.setText(operator+returnLastNumber());
				returnResult();
				lastCalc = false;
				equalPress = true;
				negAfterOp = true;
				operator = ' '; // temporary operator
			}
			
			if(e.getSource()==pwrButton) {
				if(textfield.getText().equals("")) {
					operator = '^';
				}
				
				else {
					num1 = Double.parseDouble(textfield.getText());
					operator = '^';
				}
				
				textfield.setText("");
				showEquation();
				lastCalc = false;
			}
			
			if(e.getSource()==oneOverX) {
				if(textfield.getText().equals("")) {
					operator = '@';	
				}
				
				else {
					num1 = Double.parseDouble(textfield.getText());
					operator = '@';
				}
				
				result = 1/num1;
				numLast = num1;
				textfield2.setText(1+" "+"/"+" "+returnLastNumber());
				returnResult();
				lastCalc = false;
				equalPress = true;
				negAfterOp = true;
				operator = ' '; // temporary operator		
			}

			if(e.getSource()==equButton) {
				if(lastCalc == true) {
					num1 = numLast;
					num2 = Double.parseDouble(textfield.getText());
				}
				
				else{
					num2 = Double.parseDouble(textfield.getText());
					numLast = num1;
				}
				
				switch(operator) {
				case '+':
					result = num1+num2;
					break;
				case '-':
					result = num1-num2;
					break;
				case '×':
					result = num1*num2;
					break;
				case '÷':
					result = num1/num2;
					break;
				case '%':
					result = num1 % num2;
					break;
				case '^':
					result = Math.pow(num1, num2);
					break;
				}
				returnResult();
				equalPress = true;
				lastCalc = true;
				negAfterOp = true;
				
				textfield2.setText(returnLastNumber()+" "+operator+" "+returnNumber2());
				
				if(num1 == 0 && !(operator == '*') || operator == ' ') {
					textfield2.setText(returnNumber2()+" "+"=");
					textfield.setText(returnNumber2());
					num1 = 0.0;
				}
			}
			
			if(e.getSource()==clrButton) {
				textfield.setText("");
				num2 = 0.0;
			}
			
			if(e.getSource()==clrAllButton) {
				textfield.setText("0");
				textfield2.setText("");
				num1 = 0.0;
				num2 = 0.0;
				numLast = 0.0;
				result = 0.0;
				operator = ' '; //temporary operator, acts as if it's null so the next operation can succeed.
				equalPress = true;
			}
			
			if(e.getSource()==delButton) {
				String string = textfield.getText();
				textfield.setText("");
				for(int i=0;i<string.length()-1;i++) {
					textfield.setText(textfield.getText()+string.charAt(i)); 
				}
			}
			
			if(e.getSource()==negButton) {
		
				if(negAfterOp == true) {
					if(returnNumber1().contains(".")) {
						double temp1 = Double.parseDouble(returnNumber1()) * -1;
						textfield.setText(String.valueOf(temp1));
					}
					
					else {
						int temp1 = Integer.parseInt(returnNumber1())*-1;
						textfield.setText(String.valueOf(temp1));
					}
					
					negAfterOp = false;
				}
				
				else if(textfield.getText().contains(".")) {
					double temp2 = Double.parseDouble(textfield.getText());
					temp2*=-1;
					textfield.setText(String.valueOf(temp2));	
				}
				
				else {
					int temp2 = Integer.parseInt(textfield.getText());
					temp2*=-1;
					textfield.setText(String.valueOf(temp2));	
				}
			}
		} catch(NumberFormatException nexc) {}
	}
}	


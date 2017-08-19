import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import javax.swing.*;
import java.net.URL;
public class Calculator extends JFrame implements ActionListener {
	           
	        private final int WINDOW_WIDTH = 755;
	        private final int WINDOW_HEIGHT = 670;
	        private   JTextField result;
	        private JButton a, b, c, d, e, f, addButton, subButton, multButton, divButton, equalButton, backButton,
	                   one, two, three, four, five, six, seven, eight, nine, zero, cancel, plusMinus, clear, square, remainder, recip, comma, quot , mod;
	        private JTextArea bits;
	        private long leftDigit; 
	        private long rightDigit;
	        private JPanel bases, words, panel;
	        private JRadioButton dec, bin, oct, hex, qWord, dWord, word, buttonByte;
	        private ButtonGroup group1, group2;
	        private String text, textField, operator;
	        private Clipboard clipboard;
	        private int base; 
	        private  boolean solution, digit;
	        private StringSelection stringSelection;
	        
	           
	        public int getWindowWidth(){
	        	   return WINDOW_WIDTH;
	           }
	           
	        public int getWindowHeight(){
	        	   return WINDOW_HEIGHT;
	           }
	           
	           
	        public Calculator(){
	    	  textField = "";
	          operator = "";
	          leftDigit = 0;
	          rightDigit = 0;
	          base = 0;
	      
	       solution = false; digit = false;
	       Font font = new Font("Ariel",Font.PLAIN, 15); 
	       clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

	       panel = new JPanel();
	       Color blue = new Color(153, 204,255);
	       panel.setBackground(blue);
	       panel.setLayout(null);
	      
	       //Create menu bar
	       JMenuBar menuBar= new JMenuBar();
	      
	      
	       // creating menu(names)
	       JMenu menuBarContent1= new JMenu("View");
	       JMenu menuBarContent2= new JMenu("Edit");
	       JMenu menuBarContent3= new JMenu("Help");
	      
	       //  set menu size
	       menuBarContent1.setFont(font);
	       menuBarContent2.setFont(font);
	       menuBarContent3.setFont(font);
	      
	       //Add the names to  menu bar
	       menuBar.add(menuBarContent1);
	       menuBar.add(menuBarContent2);
	       menuBar.add(menuBarContent3);
	      
	       
	       JMenuItem view = new JMenuItem((new AbstractAction("View") {
	            public void actionPerformed(ActionEvent e) {
	               panel.setVisible(true);
	            }
	       }));
	       JMenuItem hide = new JMenuItem((new AbstractAction("Hide") {
	            public void actionPerformed(ActionEvent e) {
	                   panel.setVisible(false);
	                }
	       }));
	       JMenuItem copy = new JMenuItem((new AbstractAction("Copy") {
	            public void actionPerformed(ActionEvent e) {
	               stringSelection = new StringSelection(result.getText());
	               clipboard.setContents(stringSelection, null);
	                }
	       }));
	       JMenuItem help = new JMenuItem((new AbstractAction("Help") {
	            public void actionPerformed(ActionEvent e) {
	               try {
	                        Desktop.getDesktop().browse(new URL("https://support.microsoft.com/en-us/instantanswers/54832c0d-e53c-424d-8f75-bc8a2f87e93d/calculator-in-windows-10").toURI());
	                    } catch (Exception f) {
	                        f.printStackTrace();
	                    }
	                }
	       }));
	       JMenuItem about = new JMenuItem(new AbstractAction("About") {
	            public void actionPerformed(ActionEvent e) {
	                JFrame frame = new JFrame("About");
	                JTextArea label = new JTextArea("Window 7 Calculator \n It has basic, scientific, programmer functions");
	                frame.add(label);
	                frame.setSize(250,150);
	                frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
	                frame.setVisible(true);
	            }
	       });
	     
	       menuBarContent1.add(view);
	       menuBarContent1.add(hide);
	       menuBarContent2.add(copy);
	       menuBarContent3.add(help);
	       menuBarContent3.add(about);
	      
	       this.setJMenuBar(menuBar);

	      
	       result = new JTextField("0");
	       result.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	       result.setEditable(false);
	       result.setBounds(20, 20, 715,70);
	       result.setFont(new Font("Ariel",Font.PLAIN, 35));
	       panel.add(result);
	      
	      
	       bits = new JTextArea();
	       bits.setEditable(false);
	       bits.setBounds(20, 100, 715, 90);
	       bits.setFont(font);
	       bitSelection("0");
	       panel.add(bits);
	       bits.setBackground(blue);
	      
	      
	       bases = new JPanel();
	       bases.setLayout(new GridLayout(4,1));
	       bases.setBounds(20, 200, 150, 195);
	       bases.setBorder(BorderFactory.createLineBorder(null,3));
	       bases.setBackground(blue);
	      
	      
	       words = new JPanel();
	       words.setLayout(new GridLayout(4,1));
	       words.setBounds(20, 410, 150, 195);
	       words.setBorder(BorderFactory.createLineBorder(null,3));
	       words.setBackground(blue);
	      
	      
	       dec = new JRadioButton("Dec");
	       dec.setSelected(true);
	       dec.setFont(font);
	       base = 10;
	       bin = new JRadioButton("Bin");
	       bin.setFont(font);
	       oct = new JRadioButton("Oct");
	       oct.setFont(font);
	       hex = new JRadioButton("Hex");
	       hex.setFont(font);
	       dec.addActionListener(this);
	       bin.addActionListener(this);
	       oct.addActionListener(this);
	       hex.addActionListener(this);
	      
	       group1 = new ButtonGroup();
	       group1.add(dec);
	       group1.add(bin);
	       group1.add(oct);
	       group1.add(hex);
	      
	      
	      
	       bases.add(dec);
	       bases.add(bin);
	       bases.add(oct);
	       bases.add(hex);
	       bases.setBackground(blue);
	       panel.add(bases);
	      
	       qWord = new JRadioButton("Qword");
	       qWord.setSelected(true);
	       qWord.setVisible(true);
	       qWord.setFont(font);
	       dWord = new JRadioButton("Bword");
	       dWord.setEnabled(false);
	       dWord.setVisible(true);
	       dWord.setFont(font);
	       word = new JRadioButton("Word");
	       word.setEnabled(false);
	       word.setVisible(true);
	       word.setFont(font);
	       buttonByte = new JRadioButton("Byte");
	       buttonByte.setEnabled(false);
	       buttonByte.setVisible(true);
	       buttonByte.setFont(font);
	      
	       group2 = new ButtonGroup();
	       group2.add(qWord);
	       group2.add(dWord);
	       group2.add(word);
	       group2.add(buttonByte);
	      
	       words.add(qWord);
	       words.add(dWord);
	       words.add(word);
	       words.add(buttonByte);
	       words.setBackground(blue);
	       panel.add(words);
	      
	     
	       a = new JButton("A");
	       a.setFont(font);
	       a.setBounds(320, 200, 65, 55);
	       panel.add(a);
	      
	       b = new JButton("B");
	       b.setFont(font);
	       b.setBounds(320, 270, 65, 55);
	       panel.add(b);
	      
	       c = new JButton("C");
	       c.setFont(font);
	       c.setBounds(320, 340, 65, 55);
	       panel.add(c);
	      
	       d = new JButton("D");
	       d.setFont(font);
	       d.setBounds(320, 410, 65, 55);
	       panel.add(d);
	      
	       e = new JButton("E");
	       e.setFont(font);
	       e.setBounds(320, 480, 65, 55);
	       panel.add(e);
	      
	       f = new JButton("F");
	       f.setFont(font);
	       f.setBounds(320, 550, 65, 55);
	       panel.add(f);
	      
	       one = new JButton("1");
	       one.setFont(font);
	       one.setBounds(390, 480, 65, 55);
	       panel.add(one);
	      
	       two = new JButton("2");
	       two.setFont(font);
	       two.setBounds(460, 480, 65, 55);
	       panel.add(two);
	      
	       three = new JButton("3");
	       three.setFont(font);
	       three.setBounds(530, 480, 65, 55);
	       panel.add(three);
	      
	       four = new JButton("4");
	       four.setFont(font);
	       four.setBounds(390, 410, 65, 55);
	       panel.add(four);
	      
	       five = new JButton("5");
	       five.setFont(font);
	       five.setBounds(460, 410, 65, 55);
	       panel.add(five);
	      
	       six = new JButton("6");
	       six.setFont(font);
	       six.setBounds(530, 410, 65, 55);
	       panel.add(six);
	      
	       seven = new JButton("7");
	       seven.setFont(font);
	       seven.setBounds(390, 340, 65, 55);
	       panel.add(seven);
	      
	       eight = new JButton("8");
	       eight.setFont(font);
	       eight.setBounds(460, 340, 65, 55);
	       panel.add(eight);
	      
	       nine = new JButton("9");
	       nine.setFont(font);
	       nine.setBounds(530, 340, 65, 55);
	       panel.add(nine);
	      
	       zero = new JButton("0");
	       zero.setFont(font);
	       zero.setBounds(390, 550, 135, 55);
	       panel.add(zero);
	      
	       addButton = new JButton("+");
	       addButton.setFont(font);
	       addButton.setBounds(600, 550, 65, 55);
	       panel.add(addButton);
	      
	       subButton = new JButton("-");
	       subButton.setFont(font);
	       subButton.setBounds(600, 480, 65, 55);
	       panel.add(subButton);
	      
	       multButton = new JButton("*");
	       multButton.setFont(font);
	       multButton.setBounds(600, 410, 65, 55);
	       panel.add(multButton);
	      
	       divButton = new JButton("/");
	       divButton.setFont(font);
	       divButton.setBounds(600, 340, 65, 55);
	       panel.add(divButton);
	      
	       equalButton = new JButton("=");
	       equalButton.setFont(font);
	       equalButton.setBounds(670, 480, 65, 125);
	       panel.add(equalButton);
	      
	       backButton = new JButton("\u2190");
	       backButton.setFont(font);
	       backButton.setBounds(390, 270, 65, 55);
	       panel.add(backButton);
	      
	       cancel = new JButton("CE");
	       cancel.setFont(font);
	       cancel.setBounds(460, 270, 65, 55);
	       panel.add(cancel);
	      
	       clear = new JButton("C");
	       clear.setFont(font);
	       clear.setBounds(530, 270, 65, 55);
	       panel.add(clear);
	      
	       plusMinus = new JButton("\u00B1");
	       plusMinus.setFont(font);
	       plusMinus.setBounds(600, 270, 65, 55);
	       panel.add(plusMinus);
	      
	       square = new JButton("\u221A");
	       square.setFont(font);
	       square.setBounds(670, 270, 65, 55);  
	       square.setEnabled(false);
	       panel.add(square);
	      
	       remainder = new JButton("%");
	       remainder.setFont(font);
	       remainder.setBounds(670, 340, 65, 55);
	       remainder.setEnabled(false);
	       panel.add(remainder);
	      
	       recip = new JButton("1/x");
	       recip.setFont(font);
	       recip.setBounds(670, 410, 65, 55);
	       recip.setEnabled(false);
	       panel.add(recip);
	      
	       comma = new JButton(".");
	       comma.setFont(font);
	       comma.setBounds(530, 550, 65, 55);
	       comma.setEnabled(false);
	       panel.add(comma);
	      
	       quot = new JButton("Quot");
	       quot.setFont(font);
	       quot.setEnabled(false);
	       quot.setBounds(180, 200, 65, 55);
	       panel.add(quot);
	      
	       mod = new JButton("Mod");
	       mod.setFont(font);
	       mod.setBounds(250, 200, 65, 55);
	       panel.add(mod);
	      
	   
	       JButton[] blank = new JButton[15];
	       for(int i = 0; i < 15; i++){
	           blank[i] = new JButton(" ");
	           blank[i].setEnabled(false);
	           if(i < 5){
	               blank[i].setBounds(390 + (70*i), 200, 65, 55);
	           }
	           else if(i < 7){
	               blank[i].setBounds(180 + (70*(i%5)), 270, 65, 55);
	           }
	           else if(i < 9){
	               blank[i].setBounds(180 + (70*(i%7)), 340, 65, 55);
	           }
	           else if(i < 11){
	               blank[i].setBounds(180 + (70*(i%9)), 410, 65, 55);
	           }
	           else if(i < 13){
	               blank[i].setBounds(180 + (70*(i%11)), 480, 65, 55);
	           }
	           else if(i < 15){
	               blank[i].setBounds(180 + (70*(i%13)), 550, 65, 55);
	           }
	           panel.add(blank[i]);
	       }
	  
	      
	       a.setEnabled(false);
	       b.setEnabled(false);
	       c.setEnabled(false);
	       d.setEnabled(false);
	       e.setEnabled(false);
	       f.setEnabled(false);
	      
	       
	       a.addActionListener(this);
	       b.addActionListener(this);
	       c.addActionListener(this);
	       d.addActionListener(this);
	       e.addActionListener(this);
	       f.addActionListener(this);
	       one.addActionListener(this);
	       two.addActionListener(this);
	       three.addActionListener(this);
	       four.addActionListener(this);
	       five.addActionListener(this);
	       six.addActionListener(this);
	       seven.addActionListener(this);
	       eight.addActionListener(this);
	       nine.addActionListener(this);
	       zero.addActionListener(this);
	       equalButton.addActionListener(this);
	       addButton.addActionListener(this);
	       subButton.addActionListener(this);
	       multButton.addActionListener(this);
	       divButton.addActionListener(this);
	       backButton.addActionListener(this);
	       cancel.addActionListener(this);
	       clear.addActionListener(this);
	       mod.addActionListener(this);
	       plusMinus.addActionListener(this);
	      
	       this.add(panel);
	       pack();
	   }
	  
	   public void actionPerformed(ActionEvent ee){
	       text = result.getText();
	      
	       if(ee.getSource() == a){
	           numberEvaluate();
	           boundsChecking("A");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == b){
	           numberEvaluate();
	           boundsChecking("B");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == c){
	           numberEvaluate();
	           boundsChecking("C");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == d){
	           numberEvaluate();
	           boundsChecking("D");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == e){
	           numberEvaluate();
	           boundsChecking("E");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == f){
	           numberEvaluate();
	           boundsChecking("F");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == one){
	           numberEvaluate();
	           boundsChecking("1");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == two){
	           numberEvaluate();
	           boundsChecking("2");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == three){
	           numberEvaluate();
	           boundsChecking("3");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == four){
	           numberEvaluate();
	           boundsChecking("4");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == five){
	           numberEvaluate();
	           boundsChecking("5");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == six){
	           numberEvaluate();
	           boundsChecking("6");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == seven){
	           numberEvaluate();
	           boundsChecking("7");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == eight){
	           numberEvaluate();
	           boundsChecking("8");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == nine){
	           numberEvaluate();
	           boundsChecking("9");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == zero){
	           numberEvaluate();
	           if(result.getText().equals("0")){
	               result.setText("");
	               boundsChecking("0");
	           }
	           else{
	               boundsChecking("0"); 
	           }
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == addButton){
	           digitOperation();
	           operator = "+";
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == subButton){
	           digitOperation();
	           operator = "-";
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == multButton){
	           digitOperation();
	           operator = "*";
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == divButton){
	           digitOperation();
	           operator = "/";
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == equalButton){
	           if(result.getText().equals("Cannot divide by zero")||result.getText().equals("Out of Bounds")){
	               return;
	           }
	           else if(!operator.isEmpty() && !digit){
	               return;
	           }
	          
	           rightDigit = Long.parseLong(result.getText(), base);
	          
	           if(!operator.isEmpty()){
	               if(operator.equals("/") && rightDigit == 0){
	                   result.setText("Cannot divide by zero");
	                   updateBits("0");
	               }
	               else if(operator.equals("%") && rightDigit == 0){
	                   result.setText("Error");
	                   updateBits("0");
	               }
	               else{
	                   if(dec.isSelected()){
	                       result.setText(Long.toString(equate(leftDigit, rightDigit, operator)));
	                   }
	                   else if(bin.isSelected()){
	                       result.setText(Long.toBinaryString(equate(leftDigit, rightDigit, operator)));
	                   }
	                   else if(oct.isSelected()){
	                       result.setText(Long.toOctalString(equate(leftDigit, rightDigit, operator)));
	                   }
	                   else if(hex.isSelected()){
	                       result.setText(Long.toHexString(equate(leftDigit, rightDigit, operator)));
	                   }
	                   updateBits(result.getText());
	                   leftDigit = equate(leftDigit, rightDigit, operator);
	               }
	           }
	           operator = "";
	           textField = "";
	           solution = true;
	       }
	       else if(ee.getSource() == clear){
	           text = "";
	           textField = "";
	           leftDigit = 0;
	           rightDigit = 0;
	           operator = "";
	           solution = false;
	           result.setText("0");
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == cancel){
	           result.setText("0");
	           updateBits("0");
	       }
	       else if(ee.getSource() ==backButton){
	           text = result.getText();
	           if(text.equals("Out of Bounds")||text.equals("Cannot divide by zero")){
	               result.setText("0");
	               return;
	           }
	           if(text.isEmpty()){
	               result.setText("0");
	               return;
	           }
	           else if(text.length() == 1){
	               result.setText("0");
	               text = "0";
	           }
	           else{
	               text = text.substring(0, text.length() - 1);
	               result.setText(text);
	           }
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == plusMinus){
	           if(result.getText().equals("Out of Bounds") ||result.getText().equals("Cannot divide by zero")){
	               return;
	           }
	           String text = result.getText();
	           Long num;
	           if(base == 10){
	               num = -1 * Long.parseLong(text);
	               result.setText(Long.toString(num));
	           }
	           else if(base == 2){
	               num = -1 * Long.parseLong(text, 2);
	               result.setText(Long.toBinaryString(num));
	           }
	           else if(base == 8){
	               num = -1 * Long.parseLong(text, 8);
	               result.setText(Long.toOctalString(num));
	           }
	           else if(base == 16){
	               num = -1 * Long.parseLong(text, 16);
	               result.setText(Long.toHexString(num));
	           }
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == mod){
	           digitOperation();
	           operator = "%";
	           updateBits(result.getText());
	       }
	       else if(ee.getSource() == dec || ee.getSource() == bin || ee.getSource() == oct || ee.getSource() == hex){
	           if(ee.getSource() == dec){
	               if(base == 10){
	                  
	               }
	               else if(base == 2){
	                   result.setText(Long.toString(Long.parseLong(result.getText(), 2)));
	                   bin.setSelected(false);
	               }
	               else if(base == 8){
	                   result.setText(Long.toString(Long.parseLong(result.getText(), 8)));
	                   oct.setSelected(false);
	               }
	               else if(base == 16){
	                   result.setText(Long.toString(Long.parseLong(result.getText(), 16)));
	                   hex.setSelected(false);
	               }
	               dec.setSelected(true);
	               base = 10; 
	               a.setEnabled(false); 
	               b.setEnabled(false);
	               c.setEnabled(false);
	               d.setEnabled(false);
	               e.setEnabled(false);
	               f.setEnabled(false);
	               zero.setEnabled(true);
	               one.setEnabled(true);
	               two.setEnabled(true);
	               three.setEnabled(true);
	               four.setEnabled(true);
	               five.setEnabled(true);
	               six.setEnabled(true);
	               seven.setEnabled(true);
	               eight.setEnabled(true);
	               nine.setEnabled(true);
	               updateBits(result.getText());
	           }
	           else if(ee.getSource() == bin){
	               if(base == 10){
	                   result.setText(Long.toBinaryString(Long.parseLong(result.getText())));
	                   oct.setSelected(false);
	               }
	               else if(base == 2){
	                  
	               }
	               else if(base == 8){
	                   result.setText(Long.toBinaryString(Long.parseLong(result.getText(), 8)));
	                   oct.setSelected(false);
	               }
	               else if(base == 16){
	                   result.setText(Long.toBinaryString(Long.parseLong(result.getText(), 16)));
	                   hex.setSelected(false);
	               }
	               bin.setSelected(true);
	               base = 2;
	               a.setEnabled(false);
	               b.setEnabled(false);
	               c.setEnabled(false);
	               d.setEnabled(false);
	               e.setEnabled(false);
	               f.setEnabled(false);
	               zero.setEnabled(true);
	               one.setEnabled(true);
	               two.setEnabled(false);
	               three.setEnabled(false);
	               four.setEnabled(false);
	               five.setEnabled(false);
	               six.setEnabled(false);
	               seven.setEnabled(false);
	               eight.setEnabled(false);
	               nine.setEnabled(false);
	               updateBits(result.getText());
	           }
	           
	           else if(ee.getSource() == oct){
	        	   if(base == 10){
	        	   result.setText(Long.toOctalString(Long.parseLong(result.getText())));
	        	   dec.setSelected(false);
	        	   }

	        	   else if(base == 2){
	        	   result.setText(Long.toOctalString(Long.parseLong(result.getText(), 2)));
	        	   bin.setSelected(false);
	        	   }

	        	   else if(base == 8){
	        		   
	        	   }

	        	   else if(base == 16){
	        	   result.setText(Long.toOctalString(Long.parseLong(result.getText(), 16)));
	        	   hex.setSelected(false);
	        	   }
	        	   oct.setSelected(true);
	        	   base = 8;
	        	   a.setEnabled(false);
	        	   b.setEnabled(false);
	        	   c.setEnabled(false);
	        	   d.setEnabled(false);
	        	   e.setEnabled(false);
	        	   f.setEnabled(false);
	        	   zero.setEnabled(true);
	        	   one.setEnabled(true);
	        	   two.setEnabled(true);
	        	   three.setEnabled(true);
	        	   four.setEnabled(true);
	        	   five.setEnabled(true);
	        	   six.setEnabled(true);
	        	   seven.setEnabled(true);
	        	   eight.setEnabled(false);
	        	   nine.setEnabled(false);
	        	   updateBits(result.getText());
	        	   }

	        	   else if(ee.getSource() == hex){
	        		   if(base ==10){
	        			   result.setText(Long.toHexString(Long.parseLong(result.getText())));
	        			   dec.setSelected(false);
	        		   }

	        			   else if(base == 2) {
	        			   result.setText(Long.toHexString(Long.parseLong(result.getText(), 2)));
	        			   bin.setSelected(false);
	        			   }

	        			   else if(base == 8){
	        			   result.setText(Long.toHexString(Long.parseLong(result.getText(), 8)));
	        			   oct.setSelected(false);
	        			   }
	        			   else if(base == 16){
	        				   
	        			   }

	        			   hex.setSelected(true);

	        			   base = 16;

	        			   a.setEnabled(true);

	        			   b.setEnabled(true);

	        			   c.setEnabled(true);

	        			   d.setEnabled(true);

	        			   e.setEnabled(true);

	        			   f.setEnabled(true);

	        			   zero.setEnabled(true);

	        			   one.setEnabled(true);

	        			   two.setEnabled(true);

	        			   three.setEnabled(true);

	        			   four.setEnabled(true);

	        			   five.setEnabled(true);

	        			   six.setEnabled(true);

	        			   seven.setEnabled(true);

	        			   eight.setEnabled(true);

	        			   nine.setEnabled(true);

	        			   updateBits(result.getText());
	        			   }
	        	   
	        	   
	       }
	        			   
	        			   
	       }
	        	   
	
	        public long  equate(long num1, long num2, String text){
	            long result = 0;
	            if(text.equals("+")){
	        	result = num1 + num2;
	        	}

	        	else if(text.equals("-")){
	        	result = num1 - num2;
	        	}

	        	else if(text.equals("*")){
	        	result = num1 * num2;
	        	}
	        	else if (text.equals("/")){
	        	result = num1 /num2;
	        	}

	        	return result;
	        	}
	        	   
	        	   
	        		   
	        		 
	 public void numberEvaluate(){ 
		 if(solution){ 
	        		   result.setText("");
	        		   solution = false;
	        		   text = "";
	 }
	else if(!operator.isEmpty() && !textField.isEmpty() && !textField.equals( " ")){ 
		result.setText(""); 
		textField = " ";
	       }
	      if(result.getText().equals("0")){
	        			   result.setText("");
	        }
	       if(result.getText().equals("Out of Bound")||result.getText().equals("Cannot divide by zero")){
	        	result.setText("");
	        	}
	       digit = true;
	        		   }
	        		   
	public void digitOperation(){
	      if(result.getText().equals("Out of Bounds")||result.getText().equals("Cannot divide by zero")){
	        result.setText("0");
	        return;
	         }
	      if(!digit){
	    	  return;
	      }
	      if(result.getText().equals("0")){
	    	  leftDigit = 0;
	      }
	      else if(textField.isEmpty()){
	    	  textField = result.getText();
	    	  if(dec.isSelected()){
	    		  leftDigit = Long.parseLong(textField);
	    		  
	    	  }
	    	  else if(bin.isSelected()){
	    		  leftDigit = Long.parseLong(textField, 2);
	    		  
	    	  }
	    	  else if(oct.isSelected()){
	    		  leftDigit = Long.parseLong(textField,8);
	    		  
	    	  }
	    	  else if(hex.isSelected()){
	    		  leftDigit = Long.parseLong(textField, 16);
	    		  
	    		  
	    	  }
	      }
	        			   
	      else if(!textField.isEmpty() && !operator.isEmpty() && leftDigit != 0){
	    	  if(dec.isSelected()){
	    		  rightDigit = Long.parseLong(result.getText());
	    		  result.setText(Long.toString(equate(leftDigit, rightDigit, operator)));
	    		  leftDigit = equate(leftDigit, rightDigit, operator);
	    		  rightDigit = 0;
	    		  
	    	  }
	    	  else if(bin.isSelected()){
	    		  rightDigit = Long.parseLong(result.getText(),2);
	    		  result.setText(Long.toBinaryString(equate(leftDigit, rightDigit, operator)));
	    		  leftDigit = equate(leftDigit, rightDigit, operator);
	    		  rightDigit = 0;
	    	  }
	    	  else if(oct.isSelected()){
	    		  rightDigit = Long.parseLong(result.getText(),8);
	    		  result.setText(Long.toOctalString(equate(leftDigit, rightDigit, operator)));
	    		  leftDigit = equate(leftDigit, rightDigit, operator);
	    		  rightDigit = 0;
	    	  }
	    	  solution = true;
	      }
	      digit = false;
	}
	public void updateBits(String text){
		try{
			bitSelection(Long.toBinaryString(Long.parseLong(text,base)));
			
		}
		catch(NumberFormatException n){
			result.setText("Out of Bound");
		}
		
			
		}
	public void bitSelection(String s){
		String empty1 = " ";
		String empty22 = "    ";
		for (int i=0; i<5; i++){
			if(i == 3){
				empty1 = empty1 +"               ";
			}
			else { 
				empty1 = empty1 + "              " + "   ";
				
				}
			}
		for(int i =0; i<4; i++){
			empty22 = empty22 + "               ";
		}
		for(int i = 0; i < (4-(s.length() % 4)); i++){
			s = "0" + s;
			
		}
		for(int i =s.length(); i < 65; i++){
			s = "0" + s;
			
		}
		bits.setText("     " + s.substring(0, 4) + "               " + s.substring(4, 8) + "               " + s.substring(8, 12) + "               " + s.substring(12, 16) + 
				"               " + s.substring(16, 20) + "               " +  s.substring(20, 24) + "               " + s.substring(24, 28)+ "               "+ s.substring(28, 32)+ "\n"+
				"     " + "63" + empty1 +"        "+ "47" + empty22 + " 32\n" + " " +
				"    " + s.substring(32, 36) + "               " + s.substring(36, 40) + "               " + s.substring(40,44) + "               " + s.substring(44,48)+ 
				"               " + s.substring(48, 52) + "               " + s.substring(52, 56) + "               " + s.substring(56, 60) + "               "+ s.substring(60, 64)+ "\n"
	+			"     " + "31" + empty1 +"        "+ "15" + empty22 + "   " + "0");
	}
	    		  
	public void boundsChecking(String text){
		text = result.getText() +text;
		if(dec.isSelected()){
			try{ 
				result.setText(Long.toString(Long.parseLong(text)));
			}
			catch(NumberFormatException n){
				return;
			}
				
		}
		else if (bin.isSelected()){
			try{
				result.setText(Long.toBinaryString(Long.parseLong(text, 2)));
				
			}
			catch(NumberFormatException n){
				return;
			}
		}
		else if(oct.isSelected()){
			try{
				result.setText(Long.toOctalString(Long.parseLong(text,8)));
			}
			catch(NumberFormatException n){
				return;
			}
		}
		else if(hex.isSelected()){
			try{
				result.setText(Long.toHexString(Long.parseLong(text,16)));
				
			}
			catch(NumberFormatException n){
				return;
			}
		}
	}	   

	  
}

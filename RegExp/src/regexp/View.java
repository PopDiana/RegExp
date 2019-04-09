package regexp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {

	private JFrame frame;
	private JTextField regularExpression;
	private JTextField testString;
	private JTextArea matchInfo;
	private String regex;
	private String string;
	private JLabel labelRegularExpression;
	private JLabel labelTestString;
	private JLabel labelMatchInfo;
	private JScrollPane scroll;
	private JRadioButton caseSensitive;
    private boolean caseChoice;

	public static void main(String[] args) {

		View window = new View();
		window.frame.setVisible(true);
		window.frame.setResizable(false);

	}

	public View() {
		
		initialize();
	}

	private void initialize() {

		
		frame = new JFrame("RegExp");
		frame.setBounds(600, 250, 750, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		labelRegularExpression = new JLabel("Regular expression");
		labelRegularExpression.setBounds(37, 50, 300, 36);
		frame.add(labelRegularExpression);

		regularExpression = new JTextField();
		regularExpression.setBounds(37, 85, 300, 36);
		frame.getContentPane().add(regularExpression);
		regularExpression.setColumns(10);
		

		labelTestString = new JLabel("Test string");	
		labelTestString.setBounds(37, 120, 300, 36);
		frame.add(labelTestString);

		testString = new JTextField();
		testString.setBounds(37, 155, 300, 36);
		frame.getContentPane().add(testString);
		testString.setColumns(10);

		
		labelMatchInfo = new JLabel("Match information");
		labelMatchInfo.setBounds(350, -7, 216, 148);
		frame.add(labelMatchInfo);

		matchInfo = new JTextArea();
		matchInfo.setEditable(false);
		scroll = new JScrollPane(matchInfo);
		matchInfo.setBounds(350, 85, 353, 200);
		scroll.setBounds(350, 85, 356, 206);
		frame.getContentPane().add(scroll);

				
		
		caseSensitive =new JRadioButton("Case sensitive");	
		caseSensitive.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent arg0) {
				
				if(caseSensitive.isSelected()) { 
					
					caseChoice = false;
					
				}else {
					
					caseChoice = true;
				}
			}
			
		});
		
		caseSensitive.setBounds(130,210,150,30); 
		caseSensitive.setSelected(true);
		frame.add(caseSensitive);
		

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				regex = regularExpression.getText();
				string = testString.getText();
				
				if(!regex.isEmpty() && !string.isEmpty()) {
					
					final Pattern pattern;
					
					if(caseChoice) {
						
						pattern = Pattern.compile(regex, Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
						
					}else {
						
						pattern = Pattern.compile(regex, Pattern.MULTILINE);
					}
				
					final Matcher matcher = pattern.matcher(string);
					
					matchInfo.setText(null);
					
					long startTime = System.currentTimeMillis();

					if(!matcher.find()) {
						
						matchInfo.setForeground(Color.RED);
						matchInfo.append("\n\n     Your regular expression does not match the subject string.\n\n");
						
					}else {
						
						matchInfo.setForeground(Color.GREEN);				
						matchInfo.append("\n     Match 1\n");
						matchInfo.append("\n        Full match: " + matcher.start() + "-" + matcher.end() + " " + matcher.group(0)+"\n");
						
						int i = 2;
						
						while (matcher.find()) {
							
							matchInfo.append("\n     Match "+ i +"\n");
							matchInfo.append("\n        Full match: " + matcher.start() + "-" + matcher.end() + " " + matcher.group(0)+"\n");
							i++;
							
						}
						
						long stopTime = System.currentTimeMillis();
						long elapsedTime = stopTime - startTime;
						
						if(--i==1) {
							
							matchInfo.append("\n   "+ i +" match, ~" + elapsedTime + " ms\n");
							
						}else {
						
							matchInfo.append("\n   "+ i +" matches, ~" + elapsedTime + " ms\n");
						}
						
					}

				}


			}
		});		
		
		btnSubmit.setBounds(65, 260, 106, 29);
		frame.getContentPane().add(btnSubmit);


		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				matchInfo.setText(null);
				regularExpression.setText(null);
				testString.setText(null);
				
				
			}
		});

		btnClear.setBounds(190, 260, 106, 29);
		frame.getContentPane().add(btnClear);


	}
}

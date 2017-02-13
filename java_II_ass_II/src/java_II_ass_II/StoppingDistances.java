package java_II_ass_II;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;

public class StoppingDistances {
	public static void main(String[] args){
		StoppingFrame frame = new StoppingFrame();
		frame.setTitle("Stopping Distances");
		frame.setVisible(true);
	}
	
	static class StoppingFrame extends Frame implements ActionListener{
		public StoppingFrame(){
			final int DEFAULT_FRAME_WIDTH = 250;
			final int DEFAULT_FRAME_HEIGHT = 400;
			setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
			mainPanel = new Panel();
			mainPanel.setLayout(new GridLayout(5,2));

			//Start label + text field
			start = new Label();
			start.setText("Start");
			mainPanel.add(start);
			
			startText = new TextField();
			mainPanel.add(startText);
			
			//End label + text field
			end = new Label();
			end.setText("End");
			mainPanel.add(end);
			
			endText = new TextField();
			mainPanel.add(endText);
			
			//Increment label + text field
			increment = new Label();
			increment.setText("Increment");
			mainPanel.add(increment);
			
			incrementText = new TextField();
			mainPanel.add(incrementText);
			
			//Clear button
			clear = new Button("Clear");
			clear.addActionListener(this);
			mainPanel.add(clear);
			
			//Table button
			table = new Button("Table");
			table.setBackground(buttonColor);
			table.addActionListener(this);
			mainPanel.add(table);
			
			//Exit button
			exit = new Button("Exit");
			exit.addActionListener(this);
			mainPanel.add(exit);
			
			//Output Text Area
			secondPanel = new Panel();
			secondPanel.setLayout(new GridLayout(1,1));
			text = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
			text.setFont(new Font("Courier", 1, 12));
			text.setEditable(false);

			secondPanel.add(text);
			setLayout(new GridLayout(2, 1));
			add(mainPanel); add(secondPanel);
			
			
		}
		
		//Small function to check if a string is also a valid integer/positive
		public static boolean checkInt(String input) {
			try{ 
		    	Integer.parseInt(input); 
		    }catch(NumberFormatException e) { 
		    	return false; 
		    }catch(NullPointerException e) {
		    	return false;
		    }
			
			if(Integer.parseInt(input) > 0){
				return true;
			}else{
				return false;
			}
		}
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == table){
				if(checkInt(startText.getText()) && checkInt(endText.getText()) && checkInt(incrementText.getText()) ){
					text.setText(table(
						Integer.parseInt(startText.getText()), 
						Integer.parseInt(endText.getText()), 
						Integer.parseInt(incrementText.getText())
							));
				}else{
					text.setText("All inputs above must be whole numbers");
				}
			}else if(e.getSource() == clear){
				text.setText(" ");
				startText.setText("");
				endText.setText("");
				incrementText.setText("");
				
			}else if(e.getSource() == exit){
				System.exit(0);
			}
		}
		
		//Small method to return x amount of y strings
		public static String returnString(int n, String s){
			String returnString = "";
			
			while(n>0){
				returnString += s;
				n--;
			}
			
			return returnString;
		}
		
		// This is where the magic happens. This method builds the string that goes in the main text area
		public static String table(int velocity, int end, int increment ){
			int stopDist;
			int asteriskCount = 27;
			String output = String.format("%s\n", returnString(asteriskCount, "*"));
			
			output += "*Speed(mph)*Distance(feet)*\n";
			
			output += String.format("%s\n", returnString(asteriskCount, "*"));
			
			while(velocity <= end){ 
				stopDist = (velocity*velocity) / 20 + velocity;
				output += String.format("*%6d%4s*%7d%7s*\n", velocity, "", stopDist,"");
				velocity += increment;
			}

			output += String.format("%s\n", returnString(asteriskCount, "*"));
			
			return output;
		}
		
		private Color buttonColor = new Color(0, 150, 0);
		private Label start, end, increment;
		private Button clear, table, exit;
		private TextField startText, endText, incrementText;
		private TextArea text;
		private Panel mainPanel, secondPanel;
	}
}

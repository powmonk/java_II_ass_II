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

			start = new Label();
			start.setText("Start");
			mainPanel.add(start);
			
			startText = new TextField();
			mainPanel.add(startText);
			
			end = new Label();
			end.setText("End");
			mainPanel.add(end);
			
			endText = new TextField();
			mainPanel.add(endText);
			
			increment = new Label();
			increment.setText("Increment");
			mainPanel.add(increment);
			
			incrementText = new TextField();
			mainPanel.add(incrementText);
			
			clear = new Button("Clear");
			clear.addActionListener(this);
			mainPanel.add(clear);
			table = new Button("Table");
			table.setBackground(buttonColor);
			table.addActionListener(this);
			mainPanel.add(table);
			exit = new Button("Exit");
			exit.addActionListener(this);
			mainPanel.add(exit);
			//filler = new Button("");
			//filler.addActionListener(this);
			//mainPanel.add(filler);
			
			secondPanel = new Panel();
			secondPanel.setLayout(new GridLayout(1,1));
			text = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
			text.setFont(new Font("Courier", 1, 12));
			text.setEditable(false);

			secondPanel.add(text);
			setLayout(new GridLayout(2, 1));
			add(mainPanel); add(secondPanel);
			
			
		}
		
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == table){
				text.append(table(
				Integer.parseInt(startText.getText()), 
				Integer.parseInt(endText.getText()), 
				Integer.parseInt(incrementText.getText())));
			}else if(e.getSource() == clear){
				text.setText("");
				startText.setText("");
				endText.setText("");
				incrementText.setText("");
				
			}else  if(e.getSource() == exit){
				System.exit(0);
			}
		}
		
		public static String returnString(int n, String s){
			String returnString = "";
			
			while(n>0){
				returnString += s;
				n--;
			}
			
			return returnString;
		}
		
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
		private Button clear, table, exit, filler;
		private TextField startText, endText, incrementText;
		private TextArea text;
		private Panel mainPanel, secondPanel;
	}
}

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
import java.awt.Dimension;

public class StoppingDistances {
	public static void main(String[] args){
		StoppingFrame frame = new StoppingFrame();
		frame.setTitle("Stopping Distances");
		frame.setVisible(true);
	}
	
	static class StoppingFrame extends Frame implements ActionListener{
		public StoppingFrame(){
			this.setMinimumSize(new Dimension(250, 400));
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
			clear.setForeground(Color.WHITE);
			clear.setBackground(new Color(150, 0, 0));
			clear.addActionListener(this);
			mainPanel.add(clear);
			
			//Table button
			table = new Button("Table");
			table.setForeground(Color.WHITE);
			table.setBackground(buttonColor);
			table.addActionListener(this);
			mainPanel.add(table);
			
			//Exit button
			exit = new Button("Exit");
			exit.setForeground(Color.WHITE);
			exit.setBackground(buttonColor);
			exit.addActionListener(this);
			mainPanel.add(exit);

			//Output Text Area
			secondPanel = new Panel();
			secondPanel.setLayout(new GridLayout(1,1));
			text = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
			text.setBackground(Color.BLACK);
			text.setForeground(Color.GREEN);
			text.setFont(new Font("Courier", 1, 12));
			text.setEditable(false);
			secondPanel.add(text);

			setLayout(new GridLayout(2, 1));
			add(mainPanel); add(secondPanel);
		}
		
		//Small method to check if a string is also a valid integer and within an acceptable range
		public static int cleanInt(String input) {
			int i;
			try{ 
				 i = Integer.parseInt(input); 
		    }catch(NumberFormatException e) { 
		    	return -1; 
		    }catch(NullPointerException e) {
		    	return -1;
		    }
			
			if(i >= 0 && i < 9999999){
				return i;
			}else{
				return -1;
			}
		}
		
		//This is the method that check for button presses and performs the appropriate actions
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == table){

				int start     = cleanInt(startText.getText().trim());
				int end 	  = cleanInt(endText.getText().trim());
				int increment = cleanInt(incrementText.getText().trim());
				
				if(start > 0 && end > 0 && increment > 0){
					if(start <= end){
						text.setText(table(start, end, increment));
					}else{
						text.setText("Starting speed cannot be higher than the end speed");
					}
				}else{
					text.setText("All inputs above must be whole numbers between 0 - 9999999");
				}
			}else if(e.getSource() == clear){
				text.setText(" ");text.setText("");
				startText.setText(" ");startText.setText("");
				endText.setText(" ");endText.setText("");
				incrementText.setText(" ");incrementText.setText("");
				startText.requestFocus();
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
			String output = String.format(" %s\n", returnString(asteriskCount, "*"));
			
			output += " *Speed(mph)*Distance(feet)*\n";
			
			output += String.format(" %s\n", returnString(asteriskCount, "*"));
			
			while(velocity <= end){ 
				stopDist = (velocity*velocity) / 20 + velocity;
				if(stopDist > 9999999 || stopDist < 0){
					output += String.format(" %s\n", returnString(asteriskCount, "*"));
					output += String.format(" *%4s%-21s*\n", "", "Result Out of Range");
					break;
				}
				output += String.format(" *%6d%4s*%7d%7s*\n", velocity, "", stopDist,"");
				velocity += increment;
			}

			output += String.format(" %s\n", returnString(asteriskCount, "*"));
			
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

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SizeGUI {
   private static final class ActionListenerImplementation implements ActionListener {
		private final JLabel label;
		private final JFrame frame;

		private ActionListenerImplementation(JLabel label, JFrame frame) {
			this.label = label;
			this.frame = frame;
		}

		@Override 
        public void actionPerformed(final ActionEvent e){
            createContent(label, frame);
         }
		

		 
	}
public static void main(String[] args) {
      createWindow();
   }

    public static void createWindow() {    
      JFrame frame = new JFrame("Size");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      createUI(frame);
      frame.setSize(560, 200);
	  frame.setUndecorated(true);      
      frame.setLocationRelativeTo(null);  
      frame.setVisible(true);
   }

   public static void createUI(final JFrame frame){  
      JPanel panel = new JPanel();
      LayoutManager layout = new FlowLayout();  
      panel.setLayout(layout);       

      JButton button = new JButton("Start Game");
      final JLabel label = new JLabel();
      button.addActionListener(new ActionListenerImplementation(label, frame));

      panel.add(button);
      panel.add(label);
      frame.getContentPane().add(panel, BorderLayout.CENTER);  
   }  

   	public static String createContent(JLabel label, JFrame frame){

		String result = (String)JOptionPane.showInputDialog(
               frame,
               "Enter the size:", 
               "Size",            
               JOptionPane.PLAIN_MESSAGE,
               null,            
               null, 
               "20"
            );
            
            if(result != null && result.length() > 0){
			   return result;
            }else {
               label.setText("None selected");
			   return "20";
            }

		
   }
   	
} 
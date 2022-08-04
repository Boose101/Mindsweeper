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
   public static void main(String[] args) {
      createWindow();
   }

    public static void createWindow() {    
      JFrame frame = new JFrame("Size");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      createUI(frame);
      frame.setSize(560, 200);      
      frame.setLocationRelativeTo(null);  
      frame.setVisible(true);
   }

   private static int createUI(final JFrame frame){  
      JPanel panel = new JPanel();
      LayoutManager layout = new FlowLayout();  
      panel.setLayout(layout);       

      JButton button = new JButton("Start Game");
      final JLabel label = new JLabel();
      button.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
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
               label.setText("You selected:" + result);
               return result;
            }else {
               label.setText("None selected");
               return 20;

            }
         }
      });

      panel.add(button);
      panel.add(label);
      frame.getContentPane().add(panel, BorderLayout.CENTER);    
   }  
} 
import java.util.Scanner;
import javax.swing.*;

public class BoardFrame extends JFrame{

    public BoardFrame(String title)
    {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    void init()
    {
        //Size for minesweeper pane.
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Size:");
        //int size = myObj.nextInt();
        int size = 20;
        SizeGUI sGUI =  new SizeGUI();
        sGUI.createWindow();
        //Create and set up the content pane.
        BoardPanel boardPanel = new BoardPanel(this, size);
        boardPanel.setOpaque(true); //content panes must be opaque
        setContentPane(boardPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

   


}
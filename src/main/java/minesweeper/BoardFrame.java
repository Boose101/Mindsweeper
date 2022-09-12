import javax.swing.JFrame;

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
        
        int size = SizeGUI.sizeReturn();
        //Create and set up the content pane.
        BoardPanel boardPanel = new BoardPanel(this, size);
        boardPanel.setOpaque(true); //content panes must be opaque
        setContentPane(boardPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

   


}
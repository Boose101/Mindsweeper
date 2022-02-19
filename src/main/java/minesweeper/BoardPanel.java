import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class BoardPanel extends JPanel {

    public BoardPanel(BoardFrame frame)
    {
        this.frame = frame;
        width = height = 20;
        int panelSize = 1000;
        panelDimension = new Dimension(panelSize, panelSize);
        buttonDimension = new Dimension(panelSize/width, panelSize/height);
        setSize(panelDimension);

        init();
    }

    void init()
    {
        firstClick = true;

        board = new Board(height, width, this);

        setLayout(null);
        GridLayout layout = new GridLayout(width, height);
        setLayout(layout);

        buttons = new CellButton[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {

                CellButton oldButton = buttons[i][j];

                if (oldButton != null)
                {
                    remove(oldButton);
                }

                CellButton button = new CellButton(this, board.getCell(i, j));
                buttons[i][j] = button;
                button.setPreferredSize(buttonDimension);
                this.add(button);
            }
        }
    }

    void playAgain(String result)
    {
        draw(true);

        Object[] options = {"Yes, please", "No, thanks"};
        int n = JOptionPane.showOptionDialog(
                this,
                "You " + result + "!!!, Play again?",
                "You " + result,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (n == 1) {
            System.out.println("=========== End ============");
            System.exit(0);
        }
        else
        {
            System.out.println("========= restart ==========");
            frame.init();
        }
    }

    void clickCell(CellButton button)
    {
        Cell cell = button.getCell();
        int x = cell.getX();
        int y = cell.getY();

        if(firstClick == true) {
            board.init(y, x);
            firstClick = false;
        }
        else {
            Board.Result result = board.clickCell(y, x);

            if (result == Board.Result.LOSE)
            {
                playAgain("Lose");
            }
            else if (result == Board.Result.WIN)
            {
                playAgain("Win");
            }
        }
    }

    void flagCell(CellButton button)
    {
        Cell cell = button.getCell();
        int x = cell.getX();
        int y = cell.getY();

        if(cell.flagged()) {
            cell.setFlagged(false);
        } else {
            Board.Result result = board.flagCell(y, x);

            if (result == Board.Result.WIN) {
                playAgain("Win");
            }
        }
    }

    void draw(boolean showAll) {
        System.out.println("BoardPanel::draw: " + showAll);
        for (int i = 0; i < height; ++i)
        {
            for (int j = 0; j < width; ++j)
            {
                CellButton button = buttons[i][j];
                button.draw(showAll);
            }
        }
    }

    CellButton getCellButton(int i, int j)  {
        return buttons[i][j];
    }

    public Dimension panelDimension;
    private Dimension buttonDimension;
    private int height;
    private int width;
    private CellButton[][] buttons;
    private Board board;
    private BoardFrame frame;
    private boolean firstClick;
}
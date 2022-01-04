import java.util.Random;


public class Board
{

    public enum Result {
        WIN,
        LOSE,
        CONTINUE
    }

    public Board(int height, int width, BoardPanel boardPanel) {
        this.height = height;
        this.width = width;
        this.boardPanel = boardPanel;

        this.totalSquares = width * height;
        this.numOfBombs = totalSquares / 5;
        //this.numOfBombs = (int)Math.floor(Math.sqrt(totalSquares));

        this.matrix = new Cell[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                matrix[i][j] = new EmptyCell(i, j);
            }
        }
    }

    public void init(int y, int x){
        Random rand = new java.util.Random();
        int a = 0;
        while(a < numOfBombs) {
            int randomX = rand.nextInt(width);
            int randomY = rand.nextInt(height);

            if((randomX < x + 2 && randomX > x - 2 && randomY < y + 2 && randomY > y - 2) ||
                    (matrix[randomY][randomX].getType() == Cell.Type.Bomb)) {
                continue;
            }

            BombCell bombCell = new BombCell(randomY, randomX);
            matrix[randomY][randomX] = bombCell;
            boardPanel.getCellButton(randomY, randomX).setCell(bombCell);

            a++;
        }

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                calcNum(i, j);
            }
        }
        showArea(y, x);

    }

    Cell getCell(int y, int x)
    {
        return matrix[y][x];
    }

    void calcNum(int y, int x) {

        if(matrix[y][x].getType() == Cell.Type.Bomb) {
            return;
        }

        int surroundingBombs = 0;
        for (int i = y - 1; i < y + 2; i++) { // height
            for (int j = x - 1; j < x + 2; j++) { // width
                if(i >= 0 && i < height && j >= 0 && j < width) {
                    if(matrix[i][j].getType() == Cell.Type.Bomb) {
                        surroundingBombs++;
                    }
                }
            }
        }

        if (surroundingBombs > 0) {
            NumCell numCell = new NumCell(y, x, surroundingBombs);
            matrix[y][x] = numCell;
            boardPanel.getCellButton(y, x).setCell(numCell);
        }
    }

    Result clickCell(int y, int x)
    {
        Cell cell = matrix[y][x];
        if(cell.flagged()) {
            return Result.CONTINUE;
        }
        cell.setClicked(true);

        if(cell.getType() == Cell.Type.Bomb) {
            return Result.LOSE;
        }

        if (cell.getType() == Cell.Type.Empty){
            showArea(y, x);
        }

        int numOfClicked = 0;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if(matrix[i][j].clicked()) {
                    numOfClicked++;
                }
            }
        }

        if(numOfClicked == totalSquares - numOfBombs) {
            return Result.WIN;
        }

        return Result.CONTINUE;
    }

    Result flagCell(int y, int x)
    {
        Cell cell = matrix[y][x];
        cell.setFlagged(true);

        int flaggedBombs = 0;
        int flaggedCells = 0;
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                Cell c = matrix[i][j];

                if(c.flagged()) {
                    flaggedCells++;

                    if (c.getType() == Cell.Type.Bomb) {
                        flaggedBombs++;
                    }
                }
            }
        }
        if(flaggedBombs == numOfBombs && flaggedCells == numOfBombs) {
            return Result.WIN;
        }

        return Result.CONTINUE;
    }


    void showArea(int y, int x) {
        for (int i = y - 1; i < y + 2; i++) { // height
            for (int j = x - 1; j < x + 2; j++) { // width
                if(i >= 0 && i < height && j >= 0 && j < width) {
                    if (matrix[i][j].clicked() == false) {
                        if (matrix[i][j].getType() == Cell.Type.Num) {
                            matrix[i][j].setClicked(true);
                        }
                        else if (matrix[i][j].getType() == Cell.Type.Empty && !matrix[i][j].clicked()) {
                            matrix[i][j].setClicked(true);
                            showArea(i, j);
                        }
                    }
                }
            }
        }
    }

    private int height;
    private int width;
    private int totalSquares;
    private int numOfBombs;
    private Cell[][] matrix;
    private BoardPanel boardPanel;
}
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;


public class CellButton extends JButton implements MouseListener {

    public CellButton(BoardPanel board, Cell cell)
    {
        this.cell = cell;
        this.board = board;
        this.addMouseListener(this);
    }

    Cell getCell() {
        return cell;
    }
    void setCell(Cell cell) {
        this.cell = cell;
    }

    public void mousePressed(MouseEvent mouseEvent) {
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            board.clickCell(this);
        }
        if (SwingUtilities.isMiddleMouseButton(mouseEvent)) {
        }
        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
            board.flagCell(this);
        }

        board.draw(false);
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    ImageIcon getBombIcon() {
        if (this.bombIcon == null) {
            this.bombIcon = getIcon("bomb.png");
        }
        return this.bombIcon;
    }

    ImageIcon getFlagIcon() {
        if (this.flagIcon == null) {
            this.flagIcon = getIcon("flag.png");
        }
        return this.flagIcon;
    }

    ImageIcon getPartyIcon() {
        if (this.flagIcon == null) {
            this.flagIcon = getIcon("party.png");
        }
        return this.flagIcon;
    }

    ImageIcon getNoIcon() {
        if (this.flagIcon == null) {
            this.flagIcon = getIcon("party.png");
        }
        return this.flagIcon;
    }

    ImageIcon getIcon(String file) {
        try {
            BufferedImage  img = ImageIO.read(getClass().getResource(file));
            Image newimg = img.getScaledInstance( getWidth(), getHeight(),  java.awt.Image.SCALE_SMOOTH ) ;
            ImageIcon icon = new ImageIcon(newimg);
            return icon;
        } catch (Exception ex) {
            return null;
        }
    }

    private void stayPressed()
    {
        //setEnabled(false);
        setBackground(new Color(239, 245, 252));
    }

    public void draw(Boolean showAll)
    {
//        System.out.println("CellButton::draw: [" + cell.getY() + ", " + cell.getX() + "]: "
//            + "Type: " + cell.getType()
//                +  ", flagged = " + cell.flagged()
//                +  ", clicked = " + cell.clicked()
//        );
        setIcon(null);

        if(showAll) {
            stayPressed();

            if(cell.getType() == Cell.Type.Bomb) {
                    setIcon(getBombIcon());
            } 
            else {
                if (cell.getType() == Cell.Type.Num) {
                    this.setText(Integer.toString(((NumCell)cell).getNum()));
                }
            }
        }
        else {
            if (cell.clicked()) {
                stayPressed();

                if (cell.getType() == Cell.Type.Bomb) {
                    setIcon(getBombIcon());
                } else {
                    if (cell.getType() == Cell.Type.Num) {
                        this.setText(Integer.toString(((NumCell)cell).getNum()));
                    }
                }
            } else if (cell.flagged()) {
                setIcon(getFlagIcon());
            } 
        }
    }

    private ImageIcon bombIcon;
    private ImageIcon flagIcon;

    private Cell cell;
    private BoardPanel board;
}

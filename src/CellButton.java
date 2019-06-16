import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

// TODO:
//  1) explain try,
//  2) explain protected,
//  3) explain implement and extend
// 4) override
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
                CellButton button = (CellButton)mouseEvent.getComponent();

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

private ImageIcon getBombIcon() {
        if (this.bombIcon == null) {
                this.bombIcon = getIcon("resources/bomb.gif");
        }
        return this.bombIcon;
}

private ImageIcon getFlagIcon() {
        if (this.flagIcon == null) {
                this.flagIcon = getIcon("resources/flag.png");
        }
        return this.flagIcon;
}

private ImageIcon getIcon(String file) {
        try {
                BufferedImage img = ImageIO.read(getClass().getResource(file));
                Image newimg = img.getScaledInstance( getWidth(), getHeight(),  java.awt.Image.SCALE_SMOOTH );
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
                } else {
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
                } else {
                }
        }
}

private ImageIcon bombIcon;
private ImageIcon flagIcon;

private Cell cell;
private BoardPanel board;
}

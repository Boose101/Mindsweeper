public class Main {
public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        //Testing2
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                                BoardFrame frame = new BoardFrame("Minesweeper");
                                frame.init();
                        }
                });
}
}

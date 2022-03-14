package Chess;

import Chess.Pieces.Piece;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.stream.Collectors;

public class Window extends Frame {
    class ColumnColorRenderer extends DefaultTableCellRenderer {
        Color color1, color2;

        public ColumnColorRenderer(Color backgroundColor, Color foregroundColor) {
            super();
            this.color1 = backgroundColor;
            this.color2 = foregroundColor;
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if ((row % 2 == 1) && (column % 2 == 1) || (row % 2 == 0) && (column % 2 == 0)) {
                cell.setBackground(color2);
            }
            if ((row % 2 == 1) && (column % 2 == 0) || (row % 2 == 0) && (column % 2 == 1)) {
                cell.setBackground(color1);
            }

            return cell;
        }
    }

    /**
     * Létrehoz egy gombot új játék kezdéshez.
     *
     * @return Visszatér egy új játék gombbal.
     */
    public static Button newGameB() {
        Button newGameB = new Button("Új játék");
        newGameB.setFont(new Font("Arial", Font.PLAIN, 30));
        newGameB.setVisible(true);
        newGameB.setBounds(669, 840, 120, 40);


        return newGameB;
    }

    /**
     * Létrehoz egy gombot amivel elmentett játékot tölthetünk be.
     *
     * @return Visszatér egy játék betöltése gombbal.
     */
    public static Button loadGameB() {
        Button loadGameB = new Button("Betöltés");
        loadGameB.setFont(new Font("Arial", Font.PLAIN, 30));
        loadGameB.setVisible(true);
        loadGameB.setBounds(669, 900, 120, 40);

        return loadGameB;
    }

    /**
     * Létrehoz egy gombot játék mentéséhez.
     *
     * @return Visszatér egy játék mentése gombbal.
     */
    public static Button saveGameB(Board board) {
        Button saveGameB = new Button("Mentés");
        saveGameB.setFont(new Font("Arial", Font.PLAIN, 30));
        saveGameB.setVisible(true);
        saveGameB.setBounds(529, 900, 120, 40);
        saveGameB.addActionListener(e -> {
            try {
                FileOperation.saveGame(board);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        return saveGameB;
    }

    /**
     * A metódus létrehoz egy szöveges mezőt melyben a lépéseket kérdezhetjük be a játékostól
     *
     * @return Visszatér egy JTextField típusú objektummal.
     */
    public JTextField stepTF() {
        JTextField tf_step = new JTextField(" pl.: B1 C3");
        tf_step.setForeground(Color.gray);
        tf_step.setFont(new Font("Arial", Font.ITALIC, 20));
        tf_step.setBounds(30, 850, 300, 40);
        tf_step.addFocusListener(new FocusListener() {
            /**
             * Ha belekattintunk eltűnik a JTextFieldben található példaszöveg és új formátumot kap.
             */
            @Override
            public void focusGained(FocusEvent e) {
                tf_step.setForeground(Color.black);
                tf_step.setFont(new Font("Arial", Font.BOLD, 20));
                tf_step.setText("");
            }

            /**
             * Ha a JTextField elveszti a fókuszt, akkor kap egy példasort, hogy segítse a játékost
             */
            @Override
            public void focusLost(FocusEvent e) {
                tf_step.setForeground(Color.gray);
                tf_step.setFont(new Font("Arial", Font.BOLD, 20));
                tf_step.setText(" pl.: B1 C3");
            }
        });

        return tf_step;
    }

    /**
     * A metódus létrehoz egy JTable-t és személyreszab egy Board objektum felhasználásával
     *
     * @param b Egy Board típusú objektum amiből leképezhető a JTable
     * @return A létrehozott JTable-lel visszatér.
     */
    public JTable chessTable(Board b) {
        String[] columns = {"A", "B", "C", "D", "E", "F", "G", "H"};
        JTable chess = new JTable(b.getBoard(), columns);

        chess.setFont(new Font("Serif", Font.PLAIN, 60));
        chess.setForeground(new Color(20, 20, 20));
        chess.setRowHeight(100);
        chess.setOpaque(false);
        chess.setRowSelectionAllowed(false);
        chess.setCellSelectionEnabled(false);
        chess.setDragEnabled(true);
        chess.setDropMode(DropMode.USE_SELECTION);
        chess.getTableHeader().setReorderingAllowed(false);

        return chess;
    }

    /**
     * A metódusban létrejön a sakktáblát tartalmazó tároló.
     *
     * @param chess A chessTable() metódusban létrehozott JTable-t várja paraméterként.
     * @return Visszatér egy JScrollPane-el.
     */
    public JScrollPane chessPane(JTable chess) {
        //System.out.println("Thread: " + Thread.currentThread().getName());
        for (int i = 0; i < 8; i++) {
            chess.getColumnModel().getColumn(i).setWidth(100);
        }
        chess.setBounds(30, 40, 200, 300);
        JScrollPane chb = new JScrollPane(chess);

        ColumnColorRenderer renderer = new ColumnColorRenderer(
                new Color(73, 73, 73),
                new Color(220, 220, 220));

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                chess.getColumnModel().getColumn(x).setCellRenderer(renderer);
            }
        }
        chb.setOpaque(false);
        chb.setVisible(true);

        return chb;
    }

    /**
     * Létrehoz és visszatér egy "Léptetés" feliratú cimkével.
     */
    public static Label helperlabel1() {
        Label hLabel1 = new Label("Léptetés: ");
        hLabel1.setBounds(30, 825, 120, 30);
        hLabel1.setFont(new Font("Arial", Font.PLAIN, 20));
        return hLabel1;
    }

    /**
     * Létrehoz és visszatér egy körszámláló cimkével.
     */
    public static Label roundlabel() {
        Label rLabel = new Label("Kör: " + "1");
        rLabel.setBounds(350, 850, 300, 40);
        rLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        return rLabel;
    }

    /**
     * Létrehoz és visszatér egy dialógusablakkal, amiben kiírja a nyertest!
     *
     * @param ablak Paramétere, hogy melyik Frame-hez tartozik.
     */
    public static Dialog win(Frame ablak, String nyertes) {
        Dialog dg;
        dg = new Dialog(ablak, (nyertes)+ " nyert!");
        Button btnOK = new Button("OK");
        btnOK.addActionListener(e -> dg.setVisible(false));
        dg.add(btnOK);

        dg.pack();
        dg.setLocationRelativeTo(ablak);
        dg.setLocation(new Point(100, 100));
        dg.setSize(200, 120);
        dg.setVisible(false);
        return dg;
    }

    /**
     * Létrehoz és visszatér egy dialógusablakkal, melyben figyelmeztet arra,
     * ha hibás próbált lépni az egyik játékos, vagy nem található a pozíción bábu.
     * Az OK gomb megnyomása után lehet folytatni a játékot.
     *
     * @param ablak Paramétere, hogy melyik Frame-hez tartozik.
     */
    public static Dialog nvMsg(Frame ablak,String message) {
        Dialog dg;
        dg = new Dialog(ablak, message);
        Button btnOK = new Button("OK");
        btnOK.addActionListener(e -> dg.setVisible(false));
        dg.add(btnOK);

        dg.pack();
        dg.setLocationRelativeTo(ablak);
        dg.setLocation(new Point(100, 100));
        dg.setSize(400, 80);
        dg.setVisible(false);
        return dg;
    }

    /**
     * Létrehoz egy Window objektumot, melyben meghívja a szükséges ablakbeállításokat.
     */
    Window() {
        JFrame ablak = new JFrame("Koloredchess");
        Board b = new Board();

//-----------------------------------------------------------------
        JTable chessTable = chessTable(b);
        JScrollPane chb = chessPane(chessTable);
        JTextField stepTF = stepTF();
        Label roundLabel = roundlabel();
        Button newGameButton = newGameB();
        Button loadGameButton = loadGameB();
        Dialog wW = win(ablak, "Világos");
        Dialog bW = win(ablak, "Sötét");
//-----------------------------------------------------------------
        Dialog nvStep = nvMsg(ablak,"A lépés amivel próbálkoztál nem szabályos!");
        Dialog nvPiece = nvMsg(ablak,"Nem található bábu!");

        ablak.setSize(819, 1000);
        ablak.setResizable(false);

        ablak.add(newGameButton);
        ablak.add(saveGameB(b));
        ablak.add(loadGameButton);

        ablak.add(stepTF);
        ablak.add(roundLabel);

        //----------------- Léptetés ----------------------
        stepTF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            /**
             * Enter lenyomásra a stepTF JTextFieldben lévő Stringet két pozícióvá alakítva felhasználja,
             * hogy kiinduló és célpozíciók alapján léptethesse a kiinduló pozíción álló bábut, feltéve,
             * hogy minden feltételnek megfelel.
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_ENTER) && !stepTF.getText().isEmpty()) {
                    if (!b.checkMate()) {
                        //System.out.println("Thread: " + Thread.currentThread().getName());
                        String[] positions = stepTF.getText().split(" ");

                        Pos thispos = Pos.stringToPos(positions[0]);
                        Pos dest = Pos.stringToPos(positions[1]);
                        if (b.pieceAt(thispos.getPosY(), thispos.getPosX())) {
                            Piece p = b.pieces.stream().filter(x -> (x.where().getPosY() == thispos.getPosY()) && (thispos.getPosX() == x.where().getPosX())).collect(Collectors.toList()).get(0);
                            String pN = p.getName();
                            try {
                                if (b.stepper(pN, dest)) {
                                    stepTF.setText("");
                                    SwingUtilities.invokeLater(() -> {
                                        for (int x = 0; x < 8; x++) {
                                            for (int y = 0; y < 8; y++) {
                                                chessTable.setValueAt(" ", x, y);
                                                chessTable.setValueAt(b.getBoard()[x][y], x, y);
                                            }
                                        }
                                        roundLabel.setText("Kör: " + b.getRoundNum());
                                    });
                                } else {
                                    nvStep.setVisible(true);
                                    System.out.println("Nem szabályos lépés! 1");
                                }
                            } catch (IndexOutOfBoundsException IndexE){
                                nvStep.setVisible(true);
                                System.out.println("Nem szabályos lépés! 2");
                            }
                        } else {
                            nvPiece.setVisible(true);
                            System.out.println("Nincs bábu a megadott pozíción!");
                        }
                    } else {
                        if (b.getKing("wK").getCheckmate()) {
                            bW.setVisible(true);
                            return;
                        }
                        if (b.getKing("bK").getCheckmate()) {
                            wW.setVisible(true);
                        }
                    }
                }
                if (b.checkMate()){
                    if (b.getKing("wK").getCheckmate()) {
                        bW.setVisible(true);
                        return;
                    }
                    if (b.getKing("bK").getCheckmate()) {
                        wW.setVisible(true);
                    }
                }
            }
        });
        //---------------- New Game -----------------
        newGameButton.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {

                Board board = new Board();
                b.pieces = board.pieces;
                b.resetRounds();

                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        chessTable.setValueAt(" ", x, y);
                        chessTable.setValueAt(board.getBoard()[x][y], x, y);
                    }
                }
                roundLabel.setText("Kör: " + b.getRoundNum());

            });
        });

        //---------------- Load Game ----------------
        loadGameButton.addActionListener(e -> {
            try {
                Board loadedBoard = FileOperation.loadGame();
                b.pieces = loadedBoard.pieces;
                b.setRound(loadedBoard.getRoundNum());
                for (int x = 0; x < 8; x++) {
                    for (int y = 0; y < 8; y++) {
                        chessTable.setValueAt(" ", x, y);
                        chessTable.setValueAt(loadedBoard.getBoard()[x][y], x, y);
                    }
                }
                roundLabel.setText("Kör: " + b.getRoundNum());

            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        });

        //-------------------------------------------
        ablak.add(helperlabel1());
        ablak.add(chb);

        ablak.setVisible(true);
    }

    /**
     * Itt jön létre az új Window objektum / játék indítása.
     * @param args
     */
    public static void main(String[] args) {
        new Window();
    }
}

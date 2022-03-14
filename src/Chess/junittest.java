package Chess;

import Chess.Pieces.Piece;
import Chess.Pieces.PieceKing;
import org.junit.jupiter.api.*;


import java.util.stream.Collectors;

//CTRL+SHIFT+F10
public class junittest {

    @Test
    public void testValidStepHorse() {
        Board board = new Board();
        Piece p = board.pieces.stream().filter(x -> (x.getName()
                        .equalsIgnoreCase("wKn1")))
                .collect(Collectors.toList())
                .get(0);

        Assertions.assertTrue(p.validStep(new Pos(2, 2)));
        Assertions.assertFalse(p.validStep(new Pos(3, 2)));
    }

    @Test
    public void testCheckmate() {
        Board board = new Board();

        PieceKing wKing = board.getKing("wK");
        Assertions.assertFalse(wKing.getCheckmate());
    }

    @Test
    public void testPieces() {
        Board board = new Board();

        Assertions.assertEquals(32, board.pieces.size());
    }

    @Test
    public void testRoundNum() {
        Board board = new Board();
        board.setRound(17);
        board.stepper("wKn1", new Pos(0, 2));

        Assertions.assertEquals(18, board.getRoundNum());
    }

    @Test
    public void testStepper() {
        Board board = new Board();

        Assertions.assertFalse(board.stepper("bR2", new Pos(7, 4)));
    }

    @Test
    public void testPieceAt() {
        Board board = new Board();

        Assertions.assertTrue(board.pieceAt(3, 1));
    }

    @Test
    public void testPosToString() {
        Pos testPos = new Pos(2, 5);
        Assertions.assertEquals("[C6]", testPos.toString());
    }

    @Test
    public void testGetColor() {
        Board board = new Board();
        Piece p = board.pieces.stream().filter(x -> (x.getName()
                        .equalsIgnoreCase("bB2")))
                .collect(Collectors.toList())
                .get(0);

        Assertions.assertEquals("B", p.getColor());
    }

    @Test
    public void testPawnLock() {
        Board board = new Board();
        Piece p = board.pieces.stream().filter(x -> (x.getName()
                        .equalsIgnoreCase("wP3")))
                .collect(Collectors.toList())
                .get(0);

        Pos testPos = new Pos(2, 3);
        Assertions.assertTrue(board.pawnLock(p, testPos));
    }

    @Test
    public void testWhere() {
        Board board = new Board();
        Pos testPos = new Pos(2, 5);
        Piece p = board.pieces.stream().filter(x -> (x.getName()
                        .equalsIgnoreCase("wP7")))
                .collect(Collectors.toList())
                .get(0);
        Assertions.assertNotEquals(p.where(), testPos);
    }
    @Test
    public void testFoolsmate(){
        Board board = new Board();
        board.stepper("wP6",new Pos(5,2));
        board.stepper("bP5",new Pos(4,4));
        board.stepper("wP7",new Pos(6,3));
        board.stepper("bQ",new Pos(7,3));
        Assertions.assertTrue(board.isCheckmateWhite());
    }
}

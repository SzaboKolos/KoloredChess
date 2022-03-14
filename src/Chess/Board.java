package Chess;

import Chess.Pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Board implements java.io.Serializable {
    /**
     * A tábla objektum aktuális körének száma.
     */
    private int roundNum;
    /**
     * A bábuk listája, amiből a többi metódus dolgozik.
     */
    protected ArrayList<Piece> pieces;
    /**
     * Kétdimenziós Bábu-tömb amiből egyszerűbb a kiiratás.
     */
    protected Piece[][] board = new Piece[8][8];
    /**
     * Létrehoz egy Board típusú objektumot, 1 körértékkel, 32db sakkbábuval, illetve ezek listájával.
     */
    public Board() {
        roundNum = 1;
        PieceKing bK = new PieceKing(4, 7, "B", "bK");
        PieceQueen bQ = new PieceQueen(3, 7, "B", "bQ");
        PieceRook bR1 = new PieceRook(0, 7, "B", "bR1");
        PieceRook bR2 = new PieceRook(7, 7, "B", "bR2");
        PieceKnight bKn1 = new PieceKnight(1, 7, "B", "bKn1");
        PieceKnight bKn2 = new PieceKnight(6, 7, "B", "bKn2");
        PieceBishop bB1 = new PieceBishop(2, 7, "B", "bB1");
        PieceBishop bB2 = new PieceBishop(5, 7, "B", "bB2");
        PiecePawn bP1 = new PiecePawn(0, 6, "B", "bP1");
        PiecePawn bP2 = new PiecePawn(1, 6, "B", "bP2");
        PiecePawn bP3 = new PiecePawn(2, 6, "B", "bP3");
        PiecePawn bP4 = new PiecePawn(3, 6, "B", "bP4");
        PiecePawn bP5 = new PiecePawn(4, 6, "B", "bP5");
        PiecePawn bP6 = new PiecePawn(5, 6, "B", "bP6");
        PiecePawn bP7 = new PiecePawn(6, 6, "B", "bP7");
        PiecePawn bP8 = new PiecePawn(7, 6, "B", "bP8");

        PieceKing wK = new PieceKing(4, 0, "W", "wK");
        PieceQueen wQ = new PieceQueen(3, 0, "W", "wQ");
        PieceRook wR1 = new PieceRook(0, 0, "W", "wR1");
        PieceRook wR2 = new PieceRook(7, 0, "W", "wR2");
        PieceKnight wKn1 = new PieceKnight(1, 0, "W", "wKn1");
        PieceKnight wKn2 = new PieceKnight(6, 0, "W", "wKn2");
        PieceBishop wB1 = new PieceBishop(2, 0, "W", "wB1");
        PieceBishop wB2 = new PieceBishop(5, 0, "W", "wB2");
        PiecePawn wP1 = new PiecePawn(0, 1, "W", "wP1");
        PiecePawn wP2 = new PiecePawn(1, 1, "W", "wP2");
        PiecePawn wP3 = new PiecePawn(2, 1, "W", "wP3");
        PiecePawn wP4 = new PiecePawn(3, 1, "W", "wP4");
        PiecePawn wP5 = new PiecePawn(4, 1, "W", "wP5");
        PiecePawn wP6 = new PiecePawn(5, 1, "W", "wP6");
        PiecePawn wP7 = new PiecePawn(6, 1, "W", "wP7");
        PiecePawn wP8 = new PiecePawn(7, 1, "W", "wP8");
        pieces = (new ArrayList<>(Arrays.asList(bK, wK, bQ, bR1, bR2, bKn1, bKn2, bB1, bB2, bP1, bP2, bP3, bP4, bP5, bP6, bP7, bP8,
                wQ, wR1, wR2, wKn1, wKn2, wB1, wB2, wP1, wP2, wP3, wP4, wP5, wP6, wP7, wP8)));
        for (Piece piece : pieces) {
            board[piece.pos.getPosY()][piece.pos.getPosX()] = piece;
        }
    }

    /**
     * A metódus visszatér egy kétdimenziós String listával, melyet a Windowban megjelenítésre
     * használ.
     *
     * @return A Board kétdimenziós bábuiból létrehozott String[][]-el tér vissza.
     */
    public String[][] getBoard() {
        String[][] newBoard = new String[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[y][7 - x] == null) {
                    newBoard[x][y] = " ";
                } else {
                    newBoard[x][y] = board[y][7 - x].toString();
                }
            }
        }

        return newBoard;
    }

    /**
     * (Debug) - Kiírja Standard Outputra, hogy honnan, hova lépett a megadott bábu.
     *
     * @param piece A bábu amit léptetünk.
     * @param dest Ahová léptetjük a bábut.
     */
    public void whereto(Piece piece, Pos dest) {
        System.out.println(piece.where() + " -> " + dest);
    }

    /**
     * (Debug) - Kirajzolja a táblát Standard Output-ra, a getBoard() parancssori megfelelője.
     */
    public void boardOut() {                                                                                             //Tábla kiírása (debug)
        System.out.println("Kör: " + getRoundNum());
        for (int x = 7; x > -1; x--) {
            System.out.println();
            for (int y = 0; y < 8; y++) {
                if (board[y][x] == null) {
                    System.out.print(" ▯ ");
                } else {
                    System.out.print(board[y][x] + " ");
                }
            }
        }
        System.out.println();
    }

    /**
     * Hozzáad egyet az aktuális körálláshoz. (új kör)
     */
    public void newRound() {
        roundNum += 1;
    }

    /**
     * Visszaállítja a köröket az alapértelmezett értékre (1-re)
     */
    public void resetRounds() {
        roundNum = 1;
    }

    /**
     * Beállítja az aktuális kört a paraméterben kapott integerrel.
     * @param n Nullánál nagyobb egész számérték, megadott kör.
     */
    public void setRound(int n) {
        if (n > 0) {
            roundNum = n;
        }
    }

    /**
     * Lekérdezi és visszatér az aktuális kör számával.
     * @return Aktuális kör száma.
     */
    public int getRoundNum() {
        return roundNum;
    }

    /**
     * Frissíti a pieces ArrayList elemeivel a kétdimenziós tömb attributumot
     * (Néhány vizsgálathoz és kiiratásokhoz kell)
     */
    public void updateBoard() {
        board = new Piece[8][8];
        for (Piece piece : pieces) {
            board[piece.pos.getPosY()][piece.pos.getPosX()] = piece;
        }
    }

    /**
     * Megvizsgálja, hogy a sötét király sakkban van-e,
     * úgy, hogy végigmegy az összes pieces ArrayList-ben található bábut
     * megpróbálja a sötét király pozícióra léptetni.
     *
     * @return Igaz értékkel tér vissza, ha sakkban van, hamissal, ha nincs.
     */
    public boolean isCheckBlack() {
        Pos bKingPos = getKing("bK").where();
        for (Piece p : pieces) {
            if (p.getColor().equals("W") && p.validStep(bKingPos) && collisionDetection(p, bKingPos)) {
                getKing("bK").setCheck(true);

                return true;
            }
        }
        unCheck("bK");
        return false;
    }

    /**
     * Megvizsgálja, hogy a sötét király sakk-mattot kapott-e,
     * úgy, hogy minden bábuval lelépteti az összes lehetséges lépését, ha
     * van közte olyan, ami megszűnteti a sakkot, akkor nincs sakk-mattban, egyébként igen.
     *
     * @return Igaz értékkel tér vissza, ha sakk-mattban van, hamissal, ha nincs.
     */
    public boolean isCheckmateBlack() {
        for (int p = 0; p <pieces.size(); p++) {
            if (pieces.get(p).getColor().equals("B")) {
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        Pos dest = new Pos(y, x);
                        if (collisionDetection(pieces.get(p), dest) && pieces.get(p).validStep(dest)&& (board[y][x] != null && board[y][x].getColor().equals("W")) ) {
                            if (rollback(pieces.get(p), dest)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        getKing("bK").setCheckmate();
        return true;
    }

    /**
     * Megvizsgálja, hogy a világos király sakkban van-e.
     * úgy, hogy végigmegy az összes pieces ArrayList-ben található bábut
     * megpróbálja a világos király pozícióra léptetni.
     *
     * @return Igaz értékkel tér vissza, ha sakkban van, hamissal, ha nincs.
     */
    public boolean isCheckWhite() {
        Pos wKingPos = getKing("wK").where();
        for (Piece p : pieces) {
            if (p.getColor().equals("B") && p.validStep(wKingPos) && collisionDetection(p, wKingPos)) {
                getKing("wK").setCheck(true);

                return true;
            }
        }
        unCheck("wK");
        return false;
    }

    /**
     * Megvizsgálja, hogy a világos király sakk-mattot kapott-e,
     * úgy, hogy minden bábuval lelépteti az összes lehetséges lépését, ha
     * van közte olyan, ami megszűnteti a sakkot, akkor nincs sakk-mattban, egyébként igen.
     *
     * @return igaz értékkel tér vissza, ha sakk-mattban van, hamissal, ha nincs.
     */
    public boolean isCheckmateWhite() {
        for (int p = 0; p < pieces.size(); p++) {
            if (pieces.get(p).getColor().equals("W")) {
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        Pos dest = new Pos(y, x);
                        if (collisionDetection(pieces.get(p), dest) && pieces.get(p).validStep(dest) && (board[y][x] != null && board[y][x].getColor().equals("B")) ) {
                            if (rollback(pieces.get(p), dest)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        getKing("wK").setCheckmate();
        return true;
    }

    /**
     * A sakk-matt vizsgálatát végzi.
     *
     * @return A függvény igaz vagy hamis értéket adhat vissza.
     */
    public boolean checkMate() {
        if (getKing("wK").getCheckmate()) {
            System.out.println("Sötét nyert!");
            return true;
        } else if (getKing("bK").getCheckmate()) {
            System.out.println("Világos nyert!");
            return true;
        }
        return false;
    }

    /**
     * Megvizsgálja, hogy a tábla [oszlop][sor]. helyén található-e bábu.
     *
     * @param y A vizsgálandó koordináta oszlopa.
     * @param x A vizsgálandó koordináta sora.
     * @return Igaz értékkel tér vissza, ha található a helyen bábu, hamissal ha nem.
     */
    public boolean pieceAt(int y, int x) {
        updateBoard();
        if ((y < 0 || y > 7) || (x < 0 || x > 7)) {
            return false;
        }
        return board[y][x] != null;
    }

    /**
     * Megvizsgálja, hogy az adott bábu, adott helyre lépése során beleakad-e egy másik bábuba.
     *
     * @param p A vizsgálandó bábu.
     * @param dest A célpozíció, ahova léptetnénk a bábut.
     * @return Igaz értékkel tér vissza, ha lehetséges a lépés, hamissal, ha nem.
     */
    public boolean collisionDetection(Piece p, Pos dest) {
        int pX = p.where().getPosX();
        int pY = p.where().getPosY();
        int i;
        int dx = (pX < dest.getPosX()) ? 1 : -1;
        int dy = (pY < dest.getPosY()) ? 1 : -1;


        String pName = p.getName().toLowerCase();
        if (pX == dest.getPosX() && pY == dest.getPosY()) {
            return false;
        }
        if (pName.equals("wkn1") || pName.equals("wkn2") || pName.equals("bkn1") || pName.equals("bkn2")) {
            return true;
        } else {
            if (pX == dest.getPosX()) {                                                                                 //Vízszintes lépés
                for (i = pY + dy; i != dest.getPosY() + dy; i += dy)
                    if (pieceAt(i, pX)) {
                        return false;
                    }
            } else if (pY == dest.getPosY()) {                                                                          //Függőleges lépés
                for (i = pX + dx; i != dest.getPosX() + dx; i += dx)
                    if (pieceAt(pY, i)) {
                        return false;
                    }
            } else {
                int maxStep = Math.abs(dest.getPosX() - pX);
                if (maxStep == 1) {
                    return true;
                    //return !pieceAt(pY + dy, pX + dx);
                } else {
                    for (int s = 1; s < maxStep; s++) {
                        if (pieceAt(pY + s * dy, pX + s * dx)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }

    /**
     * Kikeresi a szükséges királyt a bábuk listájából.
     *
     * @param n Annak a királynak a neve akit keresünk (wK / bK)
     * @return Visszatér a megtalált PieceKing objektummal.
     */
    public PieceKing getKing(String n) {
        return ((PieceKing) pieces.stream().filter(x -> (x.getName().equalsIgnoreCase(n))).collect(Collectors.toList()).get(0));
    }

    /**
     * Megszünteti a sakkot a paraméterben kapott királynak.
     *
     * @param king String - A király neve amelyiknek meg kell szüntetni a sakkot.
     */
    public void unCheck(String king) {
        getKing(king).setCheck(false);
    }

    /**
     * Ha az adott helyen található bábu akkor "kiütik" - kikerül a bábuk listájából.
     *
     * @param yDest A hely oszlopának száma.
     * @param xDest A hely sorának száma.
     */
    public void capture(int yDest, int xDest) {
        updateBoard();
        if (board[yDest][xDest] != null) {
            pieces.removeIf(x -> (x.where().getPosX() == xDest && x.where().getPosY() == yDest));
        }
    }

    /**
     * A metódus a paraméterben kapott bábut átteszi a szintén paraméterben kapott helyre,
     * megvizsgálja, hogy bármelyik király sakkban van-e, majd visszalép, ezzel kipróbálva,
     * hogy szabad-e ezt a lépést tenni azon esetben, ha a király sakkban van
     *
     * @param p  bábu objektum amivel lépni próbálunk.
     * @param dest Pozíció ahova léptetnénk.
     * @return Igaz értékkel tér vissza, ha megtehető a lépés, hamissal, ha nem.
     */
    public boolean rollback(Piece p, Pos dest) {
        Pos oldpos = p.where();
        String color = p.getColor();
        int yDest = dest.getPosY();
        int xDest = dest.getPosX();
        boolean availableS = true;

        updateBoard();
        pawnStep();
        if (p.validStep(dest) && pawnLock(p, dest)) {
            Piece tempPiece = board[yDest][xDest];
            capture(yDest, xDest);
            tempstep(p, dest);
            updateBoard();
            if (isCheckBlack()){
                if (color.equals("B")){
                    availableS = false;
                }
            }
            if (isCheckWhite()){
                if (color.equals("W")){
                    availableS = false;
                }
            }
            p.setPos(oldpos);
            if (tempPiece != null) {
                pieces.add(tempPiece);
            }
            updateBoard();
        }
        return availableS;
    }

    /**
     * Ideiglenes lépés a szabályoknak megfelelően.
     * (sakk/sakk-matt vizsgálatokhoz kell)
     */
    public void tempstep(Piece piece, Pos dest) {
        if (piece.validStep(dest) && pawnLock(piece, dest)) {
            pawnStep();
            piece.step(dest);
        }
    }

    /**
     * Minden lépés után megvizsgálja az összes gyalogot, hogy (menetiránynak megfelelően) bal- illetve
     * jobb-előre található-e bábu, ha igen akkor az adott gyalognak a 'l' illetve 'r' attributumát igazra váltja.
     * Ahhoz szükséges, hogy tudjanak leütni átlósan, de lépni ne.
     */
    public void pawnStep() {
        for (Piece piece : pieces) {
            int pY = piece.where().getPosY();
            int pX = piece.where().getPosX();
            if (piece.getType().equals("Pawn")) {
                if (piece.getColor().equals("W")) {
                    ((PiecePawn) piece).hitR(pieceAt(pY + 1, pX + 1));
                    ((PiecePawn) piece).hitL(pieceAt(pY - 1, pX + 1));
                } else {
                    ((PiecePawn) piece).hitR(pieceAt(pY + 1, pX - 1));
                    ((PiecePawn) piece).hitL(pieceAt(pY - 1, pX - 1));
                }
            }
        }
    }

    /**
     * Megakadályozza, hogy a paraméterben kapott gyalog kiüthesse az előtte álló bábut.
     *
     * @return Igaz értéékel tér vissza, ha léphet előre, hamissal, ha áll előtte valaki, és nem léphet.
     */
    public boolean pawnLock(Piece p, Pos dest) {
        if (p.getType().equals("Pawn")) {
            if (p.getColor().equals("W")) {
                if (board[p.where().getPosY()][p.where().getPosX() + 1] != null) {
                    return false;
                }
                return (p.where().getPosX() != 1) || (dest.getPosX() != 3) || (board[p.where().getPosY()][3] == null);
            } else {
                if (board[p.where().getPosY()][p.where().getPosX() - 1] != null) {
                    return false;
                }
                return (p.where().getPosX() != 6) || (dest.getPosX() != 4) || (board[p.where().getPosY()][4] == null);
            }
        }
        return true;
    }

    /**
     * Lelépteti a paraméterben lévő bábuval a lépését előtte vizsgálva,
     * hogy a bábu típusának megfelelő lépés-szabályokat betartja-e.
     *
     * @param piece Léptetni kívánt bábu.
     * @param dest  Célpozíció.
     */
    public void stepBro(Piece piece, Pos dest) {                                                                         // "what are you doing?"
        int yDest = dest.getPosY();
        int xDest = dest.getPosX();
        pawnStep();
        if (piece.validStep(dest) && pawnLock(piece, dest)) {
            whereto(piece, dest);
            capture(yDest, xDest);
            piece.step(dest);
            newRound();
            updateBoard();
            pawnStep();
        }
    }

    /**
     * Fő léptető függvény - Ez a függvény végzi a legtöbb léptetéssel kapcsolatos vizsgálatot, mint:
     * melyik szín következik, a kiválasztott bábu és a célpozíció között található-e akadály, illetve, hogy nem próbál-e
     * saját színének megfelelő bábut ütni vagy, hogy ha sakkban van a királya akkor megszűnteti-e vagy sem.
     *
     * @param pN A bábu neve amit léptetni szeretnénk, ez alapján keresi meg a fő léptető függvény a bábut, amit léptetünk.
     * @param dest Célpozíció, ide szeretnénk lépni.
     * @return Igaz értékkel tér vissza, ha megtette a lépést, hamissal, ha nem.
     */
    public boolean stepper(String pN, Pos dest) {
        int yDest = dest.getPosY();
        int xDest = dest.getPosX();
        boolean valid = true;
        Piece piece = pieces.stream().filter(x -> (x.getName().equalsIgnoreCase(pN))).collect(Collectors.toList()).get(0);

        if ( ((getRoundNum() % 2 == 0) && piece.getColor().equals("B")) || ((getRoundNum() % 2 == 1) && piece.getColor().equals("W")) ) {
            if ( (pieceAt(yDest,xDest) && collisionDetection(piece,dest) && !piece.getColor().equals(board[yDest][xDest].getColor())) || (!pieceAt(yDest,xDest) &&collisionDetection(piece,dest)) ){

                if (isCheckBlack()) {
                    if (rollback(piece, dest)) {
                        stepBro(piece, dest);
                    } else {
                        valid = false;
                        System.out.println("bs1");
                    }
                } else if (isCheckWhite()) {
                    if (rollback(piece, dest)) {
                        stepBro(piece, dest);
                    } else {
                        valid = false;
                        System.out.println("ws1");
                    }
                } else {
                    if (rollback(piece, dest)) {
                        stepBro(piece, dest);
                    }
                }


                if (isCheckBlack()) {
                    if (isCheckmateBlack()) {
                        //System.out.println("Sakk-Matt sötétnek!");
                    } else {
                        System.out.println("Sakk sötétnek!");
                    }
                }
                if (isCheckWhite()) {
                    if (isCheckmateWhite()) {
                        //System.out.println("Sakk-Matt világosnak!");
                    } else {
                        System.out.println("Sakk világosnak!");
                    }
                }

            } else {
                valid = false;
                System.out.println("1");
            }

        } else {
            valid = false;
            System.out.println("2");
        }

        updateBoard();
        //boardOut();
        getBoard();
        return valid;
    }

    /*public static void main(String[] args){                                                                           //parancssoros teszt
        Board board = new Board();
        board.boardOut();
        System.out.println("-----------------------------");
        Scanner sc = new Scanner(System.in);

        while (!board.checkMate()){
            String input = sc.nextLine();
            String p = input.split(" ")[0];
            Pos pos = new Pos((Integer.parseInt(input.split(" ")[1])-1),(Integer.parseInt(input.split(" ")[2])-1));
            board.stepper(p,pos);

            System.out.println("-----------------------------");
        }
    }*/

}
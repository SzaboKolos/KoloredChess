package Chess;

import java.io.*;

public class FileOperation {
    /**
     * Serialize-olva kimenti az aktuálisan használt játékállast / táblát.
     *
     * @throws IOException IOException hibát dob, ha pl. nem írhat az elérési mappába.
     */
    static public void saveGame(Board b) throws IOException {
        FileOutputStream save = new FileOutputStream("chessSave.txt");
        ObjectOutputStream out = new ObjectOutputStream(save);
        out.writeObject(b);
        out.close();
        save.close();
    }

    /**
     * A metódus visszatölti a kimentett táblát.
     * Ha nem találja a chessSave.txt -t akkor egy új táblát tölt be.
     *
     * @return Visszatér a betöltött tábla objektummal.
     * @throws IOException
     * @throws ClassNotFoundException Ha a betölteni kívánt mentésből, nem tud Board típusra konvertálni.
     */
    public static Board loadGame() throws IOException, ClassNotFoundException {
        Board b = new Board();
        if (new File("chessSave.txt").isFile()) {
            FileInputStream load = new FileInputStream("chessSave.txt");
            ObjectInputStream in = new ObjectInputStream(load);
            b = (Board) in.readObject();
            in.close();
            load.close();
        }
        return (b);
    }
}

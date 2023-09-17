import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Tester {
    public static void main(String args[]) throws NoSuchAlgorithmException, IOException {
        Blob bob = new Blob();
        Index ind = new Index();
        File text = new File("text.txt");
        Blob.makeBlob(text);
        Index.index(text, false);
        Index.deleteEntry("text.txt");

        Tree tree1 = new Tree("Tree1");
        tree1.add("example3.txt", false);
        ind.index(new File("Tree1"), true);
    }
}

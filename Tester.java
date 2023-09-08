import java.io.File;

public class Tester {
    public static void main(String args[]) {
        Blob bob = new Blob();
        Index ind = new Index();
        File text = new File("text.txt");
        Blob.makeBlob(text);
        Index.index(text);
        Index.deleteEntry("text.txt");
    }
}

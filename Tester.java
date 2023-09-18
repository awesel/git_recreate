import java.io.File;
import java.io.IOException;

public class Tester {
    public static void main(String args[]) throws IOException {
        Blob bob = new Blob();
        Index ind = new Index();
        File text = new File("text.txt");
        Blob.makeBlob(text);
        Index.index(text);

        Tree tre = new Tree(Tree.encryptPassword("tre"));

        tre.add("blob : cf5218b1f770dcaf2df718c0232ae5bf8f619ba6");
    }
}

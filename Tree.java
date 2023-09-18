import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Tree {

    private String treeName;
    private File thisTree;

    private HashMap<String, String> blobs = new HashMap();

    public Tree(String name) {
        treeName = name;
        thisTree = new File(treeName);
        thisTree.mkdir();

    }

    public void add(String name, boolean isTree) throws NoSuchAlgorithmException, IOException {
        if (!isTree) {
            Blob blob = new Blob();
            blob.makeBlob(new File(name));

            blobs.put("Blob : " + name, blob.getHashFromFile(new File(name)));
            printBlobs();

        } else {
            Tree tree = new Tree(name);
            blobs.put("Tree : " + name, Blob.getHashFromFile(new File(name)));
        }

    }

    public void remove(String fileName) {
        blobs.remove(fileName);

        for (HashMap.Entry<String, String> entry : blobs.entrySet()) {
            if (Objects.equals(entry.getValue(), fileName)) {
                blobs.remove(entry);
            }
        }

        printBlobs();
    }

    public void printBlobs() {
        try {
            PrintWriter pw = new PrintWriter(
                    "/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/" + treeName + "/stuff.txt");

            String s = "";
            for (HashMap.Entry<String, String> entry : blobs.entrySet()) {
                s += entry.getKey() + " : " + entry.getValue() + "\n";
            }

            pw.print(s);
            pw.close();

        } catch (Exception e) {

        }

    }

    public String getSha1(String input) throws NoSuchAlgorithmException { // credit to
        // http://www.sha1-online.com/sha1-java/
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public String fileContents() throws IOException

    {
        StringBuilder record = new StringBuilder("");
        BufferedReader br = new BufferedReader(new FileReader(treeName));

        while (br.ready()) {
            record.append((char) br.read());
        }

        br.close();
        String s = record.toString();
        return s;
    }

    public void makeFile() throws NoSuchAlgorithmException, IOException {

        String blobFileName = getSha1(fileContents());

        PrintWriter pw = new PrintWriter("./Objects/" + blobFileName); // found out online
                                                                       // from Java Oracle
                                                                       // Print writer makes a file while File file =
                                                                       // newe File gets an already made file.

        String words = fileContents();

        // string

        // not sure if I did it right
        pw.print(words);

        pw.close(); // releases the info

        // System.out.println(words);

    }
}

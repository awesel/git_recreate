import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tree_Tester {

    @Test
    public void testAddDirectory() throws IOException {
        new File("./testa").mkdirs();
        Path path = Paths.get("./testa");

        createFile(path, "bingus.txt", "bingus");
        createFile(path, "flingus.txt", "flingus");
        createFile(path, "blingus.txt", "blingus");

        Tree tree = new Tree();
        tree.addDirectory(path.toString());

        File objectsDir = new File("./objects");
        assertTrue(objectsDir.exists());

        String sha1 = tree.getSha1();
        File treeFile = new File(objectsDir, sha1);
        assertTrue(treeFile.exists());

    }

    private void createFile(Path drectry, String fileName, String content) throws IOException {
        File file = new File(drectry.toFile(), fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }

    // TAKEN FROM STACK
    public static String encryptPassword(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    // TAKEN FROM STACK
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnit_test {
    @Test
    public void testMakeBlob() throws IOException, FileNotFoundException {
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current Directory: " + currentDirectory);
        System.out.println("AHHHHHH");

        Blob blob = new Blob();
        String expectedSha = "cf5218b1f770dcaf2df718c0232ae5bf8f619ba6";
        File text = new File("text.txt");
        Blob.makeBlob(text);

        String toObjectsFolderPath = "./objects/" + expectedSha;
        File thing = new File(toObjectsFolderPath);
        String expectedContent = readFile(thing);
        String actualContent = readFile(new File("test.txt"));

        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testIndex() throws IOException, FileNotFoundException {
        Index indy = new Index();
        assertTrue(Files.exists(Paths.get("./objects/index")));
        File text = new File("text.txt");
        Index.index(text);
        File indx = new File("./objects/index");
        String john = readFile(indx);
        assertTrue(john.contains("text.txt : da39a3ee5e6b4b0d3255bfef95601890afd80709"));
    }

    public static String readFile(File file) {
        String currentString = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while (reader.ready()) {
                char nextChar = (char) reader.read();
                currentString += nextChar;

            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("nah");
        }
        return currentString;

    }

}

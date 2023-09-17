
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import java.io.*;

public class GitTest {

    @BeforeAll // with help from Daniel
    public static void setUpBeforeClass() throws IOException {

        PrintWriter pw = new PrintWriter(
                "/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git /junit_example_file_data.txt");
        pw.print("test file contents");

        Path objectPath = Paths.get("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects");
        Files.delete(objectPath);

        Path indexPath = Paths.get("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/Index");
        Files.delete(indexPath);

    }

    @AfterAll // Credit to Daniel Hernandez for assistance
    public static void tearDownAfterClass() throws IOException

    {
        Path indexPath = Paths.get("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/Index.txt");
        Files.delete(indexPath);

        Path objectPath = Paths.get("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects");
        Files.delete(objectPath);

        Path junitExamplePath = Paths
                .get("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/junit_example_file_data.txt");
        Files.delete(junitExamplePath);

    }

    @Test
    @DisplayName(value = "[8]")
    public void testInitialize() throws Exception { // credit to Daniel Hernandez for showing me that I need full
                                                    // pathnames for Junit and not just ./

        Blob bob = new Blob();
        Index ind = new Index();
        PrintWriter pw1 = new PrintWriter("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/text.txt");

        File text = new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/text.txt");

        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/index");
        Path path = Paths.get("objects");

        // PrintWriter pw = new PrintWriter("./yay.txt");

        Assert.assertEquals(Files.exists(path), true);
        Assert.assertEquals(file.exists(), true);

    }

}

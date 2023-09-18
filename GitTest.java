
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
    public void testInitialize() throws Exception { // credit to Daniel Hernandez for showing me that I need full
                                                    // pathnames for Junit and not just ./

        // File objects = new File("/Users/lilbarbar/Desktop/Honors
        // Topics/Andrews_Amazing_Git/objects");
        // objects.mkdir();
        // PrintWriter indexFile = new PrintWriter(
        // "/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/index");

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

    @Test
    public void testCreateBlob() throws Exception {

        // Manually create the files and folders before the 'testAddFile'
        // MyGitProject myGitClassInstance = new MyGitProject();
        // myGitClassInstance.init();
        // TestHelper.runTestSuiteMethods("testCreateBlob", file1.getName());

        Blob b1 = new Blob();
        Blob b2 = new Blob();
        Index index = new Index();

        File f1 = new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/example.txt");
        File f2 = new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/example2.txt");

        b1.makeBlob(f1);
        b2.makeBlob(f2);

        File blobHash1 = new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/"
                + b1.encryptPassword(b1.readFile(f1)));

        File blobHash2 = new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/"
                + b2.encryptPassword(b1.readFile(f2)));

        assertEquals(blobHash1.exists(), true);
        assertEquals(blobHash2.exists(), true);

        assertEquals(Blob.readFile(blobHash1), Blob.readFile(f1));
        assertEquals(Blob.readFile(blobHash2), Blob.readFile(f2));

        assertEquals(blobHash1.getName(), b1.encryptPassword(b1.readFile(f1)));
        assertEquals(blobHash2.getName(), b2.encryptPassword(b2.readFile(f2)));

        // Check blob exists in the objects folder (DONE)

        // File file_junit1 = new File("objects/" + file1.methodToGetSha1());
        // assertTrue("Blob file to add not found", file_junit1.exists());

        // // Read file contents
        // String indexFileContents = MyUtilityClass.readAFileToAString("objects/" +
        // file1.methodToGetSha1());
        // assertEquals("File contents of Blob don't match file contents pre-blob
        // creation", indexFileContents,file1.getContents());
        // }

    }

    @Test
    public void testIndex() throws Exception {

        Index i = new Index();

        File f1 = new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/example.txt");
        File f2 = new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/example2.txt");
        i.index(f1, false);

        File ind = new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/index");

        assertEquals(Blob.readFile(ind).indexOf("a8dd676bdf983b33ec112e95a43b74a47ff41448") != -1, true);

        i.index(f2, false);
        assertEquals(Blob.readFile(ind).indexOf("f189623835d5eb6d144e65a21d8b7eabad1da78f") != -1, true);

        i.deleteEntry("example.txt");

        assertEquals(Blob.readFile(ind).indexOf("a8dd676bdf983b33ec112e95a43b74a47ff41448") == -1, true);

        // assertEquals(blobHash1.exists(), true);
        // assertEquals(blobHash2.exists(), true);

        // assertEquals(Blob.readFile(blobHash1), Blob.readFile(f1));
        // assertEquals(Blob.readFile(blobHash2), Blob.readFile(f2));

        // Check blob exists in the objects folder (DONE)

        // File file_junit1 = new File("objects/" + file1.methodToGetSha1());
        // assertTrue("Blob file to add not found", file_junit1.exists());

        // // Read file contents
        // String indexFileContents = MyUtilityClass.readAFileToAString("objects/" +
        // file1.methodToGetSha1());
        // assertEquals("File contents of Blob don't match file contents pre-blob
        // creation", indexFileContents,file1.getContents());
        // }

    }

    @Test
    public void testTree() throws Exception {

        Index i = new Index();

        Tree tree1 = new Tree("Tree1");
        tree1.add("example3.txt", false);
        i.index(new File("Tree1"), true);
        assertEquals(new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/Tree1/stuff.txt").exists(),
                true);
    }

}

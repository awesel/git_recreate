import java.io.*;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;

public class Tree {
    private String nameOfTree;
    private ArrayList<String> entries; // this has all the stuff

    public Tree(String hash) throws IOException {
        nameOfTree = hash;
        this.entries = new ArrayList<>();

        if (Files.exists(Paths.get("./objects/" + nameOfTree))) {
            try (BufferedReader reader = new BufferedReader(new FileReader("./objects/" + nameOfTree))) {
                while (reader.ready()) {
                    entries.add(reader.readLine());
                }
            }
        } else {
            FileWriter fileWriter = new FileWriter("./objects/" + nameOfTree);
            fileWriter.close();
        }
    }

    public void add(String entry) throws IOException {
        entries.add(entry);
        generateBlob();
    }

    public void remove(String target) throws IOException {
        ArrayList<String> temps = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader("./objects/" + nameOfTree))) {
            while (reader.ready()) {
                String heh = reader.readLine();
                if (!heh.contains(target))
                    temps.add(heh);
            }
        }
        entries = temps;
        generateBlob();
    }

    private void generateBlob() throws IOException {
        StringBuilder builder = new StringBuilder();
        for (String entry : entries) {
            builder.append(entry).append("\n");
        }
        nameOfTree = encryptPassword(builder.toString());

        // no newline!!
        builder.setLength(builder.length() - 1);

        try (FileWriter writer = new FileWriter("./objects/" + nameOfTree)) {
            writer.write(builder.toString());
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

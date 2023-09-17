import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Index {

    public Index() {
        // File objects = new File("./Objects"); // gotten online
        // if (!objects.exists()) {
        // objects.mkdirs();
        // }

        String name = "/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/index";

        try {
            PrintWriter fileWriter = new PrintWriter(name);
        } catch (IOException ex) {
            System.out.println("some didn't work");
        }
    }

    public static void index(File file) {
        Blob.makeBlob(file);

        Map<String, String> idx = new HashMap();

        idx = readIndex(idx);
        idx.put(file.getName(), Blob.encryptPassword(Blob.readFile(file)));
        writeToIndex(idx);
    }

    public static Map readIndex(Map map) {
        try (BufferedReader buffy = new BufferedReader(
                new FileReader("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/index"))) {
            String line;
            while ((line = buffy.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String x = parts[0].trim();
                    String y = parts[1].trim();
                    map.put(x, y);

                }

            }
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void writeToIndex(Map idx) { // bvlonk
        Set<String> john = idx.keySet();
        String blonkus = "";
        for (String key : john) {
            blonkus += key;
            blonkus += " : ";
            blonkus += idx.get(key);
            blonkus += "\n";
        }

        writeFile(blonkus, "/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/index");

    }

    public static void writeFile(String toWrite, String name) {
        String path = name;
        try {
            PrintWriter fileWriter = new PrintWriter(path);
            fileWriter.write(toWrite);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("some didn't work");
        }
    }

    public static void deleteEntry(String name) {
        File tempFile = new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Gitobjects/index_temp");
        File inputFile = new File("/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/index");

        try

        {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            // BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;

            String out = "";

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.contains(name)) {
                    // writer.write(currentLine);
                    // writer.newLine();

                    out += currentLine;
                }

            }
            // inputFile.delete();
            // tempFile.renameTo(inputFile);

            PrintWriter pw = new PrintWriter(
                    "/Users/lilbarbar/Desktop/Honors Topics/Andrews_Amazing_Git/objects/index");

            pw.write(out);
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
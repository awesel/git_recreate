import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Index {

    public Index() {
        String name = "./objects/index";

        try {
            FileWriter fileWriter = new FileWriter(name);
            fileWriter.close();
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

    private static Map readIndex(Map map) {
        try (BufferedReader buffy = new BufferedReader(new FileReader("./objects/index"))) {
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

    private static void writeToIndex(Map idx) { // bvlonk
        Set<String> john = idx.keySet();
        String blonkus = "";
        for (String key : john) {
            blonkus += key;
            blonkus += " : ";
            blonkus += idx.get(key);
            blonkus += "\n";
        }
        writeFile(blonkus, "index");
    }

    private static void writeFile(String toWrite, String name) {
        String path = "./objects/" + name;
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(toWrite);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("some didn't work");
        }
    }

    public static void deleteEntry(String name) {
        File tempFile = new File("./objects/index_temp");
        File inputFile = new File("./objects/index");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.contains(name)) {
                    writer.write(currentLine);
                    writer.newLine();
                }

            }
            inputFile.delete();
            tempFile.renameTo(inputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
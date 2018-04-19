package edu.gcccd.csis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SelfAware implements Language {

    public static void main(String[] args) {
        final String code = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java" + File.separator +
                SelfAware.class.getName().replace(".", File.separator) + ".java";
        SelfAware sa = new SelfAware();
        try {
            sa.append(code, "\n//Keyword occurrences: " + sa.occurrences(code));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int occurrences(String sourceFile) throws Exception {
        //convert file into string
        final String s = new String(Files.readAllBytes(Paths.get(sourceFile)));
        //convert string file into an array
        String[] wordFile = s.split("\\b");
        int occurrences = 0;
        for (String ReservedWord : ReservedWords) {
            for (String aWordFile : wordFile)
                if (ReservedWord.equals(aWordFile)) {
                    occurrences++;
                }
        }
        occurrences = occurrences / 2;
        return occurrences;
    }

    @Override
    public void append(final String sourceFile, final String message) throws IOException {
        Files.write(Paths.get(sourceFile), message.getBytes(), StandardOpenOption.APPEND);
    }
}

package edu.gcccd.csis;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SelfAware implements Language {
    int k = 0;


    public static void main(String[] args) throws IOException {
        final String code = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java" + File.separator +
                SelfAware.class.getName().replace(".", File.separator) + ".java";
        SelfAware sa = new SelfAware();
        try {
            sa.append(code,"\n//Keyword occurrences: " + sa.occurrences(code));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int occurrences(String sourceFile) throws Exception {
        final String s = new String(Files.readAllBytes(Paths.get(sourceFile)));
        String[] wordFile = s.split("\\b");
        int occurrences = 0;
        for (int i = 0; i < ReservedWords.length; i++) {
            for (int z = 0; z < wordFile.length; z++) {
                if (wordFile[z].equals(ReservedWords[i]) || wordFile[z] == ReservedWords[i]) {
                    System.out.println(ReservedWords[i]);
                    System.out.println(occurrences++);
                    occurrences++;
                }
            }
        }
        return occurrences;
    }

    @Override
    public void append(final String sourceFile, final String message) throws IOException {
        Files.write(Paths.get(sourceFile), message.getBytes(), StandardOpenOption.APPEND);
    }
}
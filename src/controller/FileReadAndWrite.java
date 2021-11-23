package controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReadAndWrite {
    public static List<String> readFileByNIO(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();
        try {
            //read byte from hard dist -> FileInputStream ( obtains input bytes
            // * from a file in a file system.)
            //switch bytes to characters -> InputStreamReader (An InputStreamReader is a bridge from byte streams to character streams)
            //  BufferedReader
            // Reads text from a character-input stream, buffering characters so as to
            // * provide for the efficient reading of characters, arrays, and lines.

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

//            BufferedReader bufferedReader1= new BufferedReader(new FileReader(path));
            String line;
            //read characters from bufferedReader
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void writeFile(String path, List<String> lines) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path));
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (String line : lines
            ) {
                bufferedWriter.write(line);
                bufferedWriter.write(System.lineSeparator());
            }
            bufferedWriter.close();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //store chess1.txt in the root path of your project.
        List<String> strings = readFile("chess1.txt");
        strings.forEach(System.out::println);

        List<String> strings2 = readFileByNIO("chess1.txt");
        strings2.forEach(System.out::println);

        writeFile("chess2.txt", strings);
    }
}

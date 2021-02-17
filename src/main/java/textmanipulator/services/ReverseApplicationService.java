package textmanipulator.services;

import textmanipulator.services.interfaces.IBaseActionApplicationService;

import java.io.*;
import java.util.*;

public class ReverseApplicationService implements IBaseActionApplicationService {
    @Override
    public void runAction(String inputFileName, String outputFileName) throws IOException {
        reverse(inputFileName,outputFileName);
    }

    private void reverse(String inputFileName, String outputFileName) throws IOException {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(inputFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String inputLine;
        LinkedList<String> lineList = new LinkedList<>();
        while ((inputLine = bufferedReader.readLine()) != null) {
            lineList.add(inputLine);
        }
        fileReader.close();

        Iterator<String> iterator = lineList.descendingIterator();
        FileWriter fileWriter = new FileWriter(outputFileName);
        PrintWriter out = new PrintWriter(fileWriter);
        while (iterator.hasNext()) {
            out.println(iterator.next());
        }
        out.flush();
        out.close();
        fileWriter.close();
    }
}

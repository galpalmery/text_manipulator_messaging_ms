package harel.interview.textManipulator.services;

import harel.interview.textManipulator.services.interfaces.IBaseActionApplicationService;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
        List<String> lineList = new ArrayList<String>();
        while ((inputLine = bufferedReader.readLine()) != null) {
            lineList.add(inputLine);
        }
        fileReader.close();

        Collections.reverse(lineList);

        FileWriter fileWriter = new FileWriter(outputFileName);
        PrintWriter out = new PrintWriter(fileWriter);
        for (String outputLine : lineList) {
            out.println(outputLine);
        }
        out.flush();
        out.close();
        fileWriter.close();
    }
}

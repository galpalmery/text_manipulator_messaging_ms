package textmanipulator.services;

import textmanipulator.services.interfaces.IBaseActionApplicationService;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleApplicationService implements IBaseActionApplicationService {
    @Override
    public void runAction(String inputFileName, String outputFileName) throws IOException {
        System.out.println("inside ShuffleApplicationService");
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

//        Collections.shuffle(lineList);
        Random rand = new Random();
        int ListSize = lineList.size();
        for (int i = 0; i < ListSize -1; i++) {
            int randomIndexToSwap = rand.nextInt(ListSize-1);
            String temp = lineList.get(randomIndexToSwap);
            lineList.set(randomIndexToSwap,lineList.get(i));
            lineList.set(i, temp);
        }

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

package harel.interview.textManipulator.services;

import harel.interview.textManipulator.services.interfaces.IBaseActionApplicationService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReverseApplicationService implements IBaseActionApplicationService {
    @Override
    public void runAction(String inputFileName, String outputFileName) {
        reverse(inputFileName,outputFileName);
    }

    private void reverse(String inputFileName, String outputFileName) {
        try {
            File file = new File(inputFileName);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

package textmanipulator.services;

import textmanipulator.services.interfaces.IBaseActionApplicationService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SortApplicationService implements IBaseActionApplicationService {
    private ArrayList<String> inputArray;

    @Override
    public void runAction(String inputFileName, String outputFileName) throws IOException {
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

//        Collections.sort(lineList);
        inputArray = (ArrayList<String>) lineList;
        mergeSortArray();

        FileWriter fileWriter = new FileWriter(outputFileName);
        PrintWriter out = new PrintWriter(fileWriter);
        for (String outputLine : lineList) {
            out.println(outputLine);
        }
        out.flush();
        out.close();
        fileWriter.close();
    }

    public void mergeSortArray() {
        divide(0, this.inputArray.size() - 1);
    }

    public void divide(int startIndex, int endIndex) {

        //Divide till you breakdown your list to single element
        if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
            int mid = (endIndex + startIndex) / 2;
            divide(startIndex, mid);
            divide(mid + 1, endIndex);

            //merging Sorted array produce above into one sorted array
            merge(startIndex, mid, endIndex);
        }
    }

    public void merge(int start, int middle, int end) {

        //Below is the merged array that will be sorted array Array[i-middle] , Array[(middle+1)-end]
        ArrayList<String> mergedSortedArray = new ArrayList<String>();

        int leftIndex = start;
        int rightIndex = middle + 1;

        while (leftIndex <= middle && rightIndex <= end) {
            if (inputArray.get(leftIndex).compareTo(inputArray.get(rightIndex)) <= 0) {
                mergedSortedArray.add(inputArray.get(leftIndex));
                leftIndex++;
            } else {
                mergedSortedArray.add(inputArray.get(rightIndex));
                rightIndex++;
            }
        }

        //Either of below while loop will execute
        while (leftIndex <= middle) {
            mergedSortedArray.add(inputArray.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex <= end) {
            mergedSortedArray.add(inputArray.get(rightIndex));
            rightIndex++;
        }

        int i = 0;
        int j = start;
        //Setting sorted array to original one
        while (i < mergedSortedArray.size()) {
            inputArray.set(j, mergedSortedArray.get(i++));
            j++;
        }
    }
}

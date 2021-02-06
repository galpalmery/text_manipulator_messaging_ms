package harel.interview.textManipulator.services;

import harel.interview.textManipulator.services.interfaces.IBaseActionApplicationService;

public class ShuffleApplicationService implements IBaseActionApplicationService {
    @Override
    public void runAction(String inputFileName, String outputFileName) {
        System.out.println("inside ShuffleApplicationService");
    }
}

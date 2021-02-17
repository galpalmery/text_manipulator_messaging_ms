package textmanipulator.services.interfaces;

import java.io.IOException;

public interface IBaseActionApplicationService {

    void runAction(String inputFileName, String outputFileName) throws IOException;

}

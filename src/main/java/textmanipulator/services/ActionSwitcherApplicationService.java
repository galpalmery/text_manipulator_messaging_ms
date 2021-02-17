package textmanipulator.services;

import textmanipulator.messaging.MessageSender;
import textmanipulator.services.interfaces.IBaseActionApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

public class ActionSwitcherApplicationService {
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MessageSender rabbitMQmessageSender;

    public ResponseEntity<Object> execute(String inputFileName, String outputFileName, String action) throws Exception {
        //get required action and run it
        String applicationServiceName = action.toLowerCase() + "ApplicationService";
        IBaseActionApplicationService actionApplicationService =
                (IBaseActionApplicationService) applicationContext.getBean(applicationServiceName);
        actionApplicationService.runAction(inputFileName, outputFileName);

        //send a message to rabbit mq
        rabbitMQmessageSender.run(inputFileName, outputFileName, action);

        //if we got to here - it means nothing failed, send response 200 - OK
        return ResponseEntity.ok().build();
    }
}

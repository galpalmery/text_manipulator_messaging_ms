package textmanipulator.controllers;

import textmanipulator.services.ActionSwitcherApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextManipulatorController {

    @Autowired
    private ActionSwitcherApplicationService actionSwitcherApplicationService;

    @PostMapping("/textmanipulator/inputfilename/{inputfilename}/outputfilename/{outputfilename}/action/{action}")
    public ResponseEntity<Object> runTextManipulationAction(@PathVariable("inputfilename") String inputFileName,
                                                            @PathVariable("outputfilename") String outputFileName,
                                                            @PathVariable("action") String action) throws Exception {
        return actionSwitcherApplicationService.execute(inputFileName, outputFileName, action);
    }
}

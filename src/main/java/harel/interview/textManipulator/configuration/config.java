package harel.interview.textManipulator.configuration;

import harel.interview.textManipulator.services.ActionSwitcherApplicationService;
import harel.interview.textManipulator.services.ReverseApplicationService;
import harel.interview.textManipulator.services.ShuffleApplicationService;
import harel.interview.textManipulator.services.SortApplicationService;
import harel.interview.textManipulator.services.interfaces.IBaseActionApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {

    @Bean
    IBaseActionApplicationService reverseApplicationService() {
        return new ReverseApplicationService();
    }

    @Bean
    IBaseActionApplicationService shuffleApplicationService() {
        return new ShuffleApplicationService();
    }

    @Bean
    IBaseActionApplicationService sortApplicationService() {
        return new SortApplicationService();
    }

    @Bean
    ActionSwitcherApplicationService actionSwitcherApplicationService() {
        return new ActionSwitcherApplicationService();
    }
}

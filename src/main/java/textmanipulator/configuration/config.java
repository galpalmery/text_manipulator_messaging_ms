package textmanipulator.configuration;

import textmanipulator.services.ActionSwitcherApplicationService;
import textmanipulator.services.ReverseApplicationService;
import textmanipulator.services.ShuffleApplicationService;
import textmanipulator.services.SortApplicationService;
import textmanipulator.services.interfaces.IBaseActionApplicationService;
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

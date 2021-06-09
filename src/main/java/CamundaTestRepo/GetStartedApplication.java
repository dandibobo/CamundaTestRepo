package CamundaTestRepo;

import org.camunda.bpm.client.ExternalTaskClient;

import java.awt.*;
import java.net.URI;
import java.util.logging.Logger;

public class GetStartedApplication {
    private final static Logger LOGGER = Logger.getLogger(GetStartedApplication.class.getName());

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create()
            .baseUrl("http://localhost:8080/engine-rest")
            .asyncResponseTimeout(10000)
            .build();

        client.subscribe("charge-card")
            .lockDuration(1000)
            .handler(((externalTask, externalTaskService) -> {
                //put your business logic here

                //Get a process variable
                String item = (String) externalTask.getVariable("item");
                Long amount = (Long) externalTask.getVariable("amount");

                LOGGER.info("Charging credit card with an amount of '" + amount + "'$ for the item '" + item + "'...");

                try {
                    Desktop.getDesktop().browse(new URI("https://docs.camunda.org/get-started/quick-start/complete"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                //complete the task
                externalTaskService.complete(externalTask);
            }))
            .open();

    }
}

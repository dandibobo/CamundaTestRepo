package CamundaTestRepo;

import org.camunda.bpm.client.ExternalTaskClient;

import java.util.Collections;
import java.util.logging.Logger;

public class ReceiveMessageApplication {

  private final static Logger LOGGER = Logger.getLogger(ReceiveMessageApplication.class.getName());

  public static void main(String[] args) {
    ExternalTaskClient client = ExternalTaskClient.create()
        .baseUrl("http://localhost:8080/engine-rest")
        .asyncResponseTimeout(10000)
        .build();

    client.subscribe("catchExternal1")
        .lockDuration(1000)
        .handler((externalTask, externalTaskService) -> {

          String item = (String) externalTask.getVariable("item");
          Long amount = (Long) externalTask.getVariable("amount");
          LOGGER.info("BUSINESS KEY: " + externalTask.getBusinessKey());
          LOGGER.info("TENANT ID: " + externalTask.getTenantId());
          LOGGER.info("TOPIC 1: Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");

          //add new value to external task so another task can use it
          externalTaskService.complete(externalTask, Collections.singletonMap("color", "blue"));
        })
        .open();

    client.subscribe("catchExternal2")
        .lockDuration(1000)
        .handler((externalTask, externalTaskService) -> {

          String item = (String) externalTask.getVariable("item");
          Long amount = (Long) externalTask.getVariable("amount");
          String color = (String) externalTask.getVariable("color");

          LOGGER.info("TOPIC 2:Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");
          LOGGER.info("GET NEW VARIABLE 'color': " + color);

          externalTaskService.complete(externalTask);
        })
        .open();
  }
}

# CamundaTestRepo
This test application for checking Camunda features

### GetStartedApplication.java
Uses payment.bpmn and payment_retrieval_decision.dmn in get_started_application resource folder

It is Get Started Application from Camunda guide for beginners. 
This app shows a work of user task, gateways, business rule task and external task as an example.

You may use json request in request.txt to start a process in get_started_application resource folder


### ExternalTaskApplication.java
Uses external_task.bpmn in external_task_application directory in resources folder

Shows how one external task can send data to another external task

You may use json request in request.txt to start a process at the same get_started_application directory

### ReceiveMessageApplication.java
Uses catch_event.bpmn in receive_message_application directory in resources folder

Shows how process stops at receive event and continues process after rest request 
As an GUI use cockpit to observe the process

You may use json request in request.txt to start a process at the same receive_message_application directory
or you can start in tasklist creating a new process
# text_manipulator_messaging_ms
this microservice receives information from REST and sends it as a message to RabbitMQ
the information received are 3 strings - an input file name, an output file name and an action.
the microservice will perform the required action on the input file and write the result to the ourput file. 
currently the supported actions are - reverse, sort and shuffle. 

this microservice is one out of 3 microservices in this project.
the  text_manipulator_connector_ms has a listener to the same queue in RabbitMQ as text_manipulator_messaging_ms, 
and once a message arrives it will send a REST to the text_manipulator_persistence_ms via Feign client
the text_manipulator_persistence_ms receives data from the REST sent via Feign and persists the data to mongodb.

please view the flow diagram here - https://github.com/galpalmery/text_manipulator_messaging_ms/blob/master/flow%20diagram.JPG

in order to add a new action to this microservice, a developer will need to provide the implementation to the action.
the implementation should be written in a new ApplicationService and added to here:
https://github.com/galpalmery/text_manipulator_messaging_ms/tree/master/src/main/java/harel/interview/textManipulator/services
also will need to define a Bean for the new ApplicationService in the configuration file. note that the name of the Bean needs to have lowercase letters for the name of the action
and then "ApplicationService" concatenated to it. see the other Beans defined here, and follow the same naming convention:
https://github.com/galpalmery/text_manipulator_messaging_ms/blob/master/src/main/java/harel/interview/textManipulator/configuration/config.java

=======
instructions for running this project locally:
1. install RabbitMQ, MongoDB compass, Postman, your favorite IDE
2. download/clone the code of the 3 microservices in this project, compile and run all 3 microservices. here are the links to the 3 repositories: 
https://github.com/galpalmery/text_manipulator_messaging_ms
https://github.com/galpalmery/text_manipulator_persistence_ms
https://github.com/galpalmery/text_manipulator_connector_ms

3. from postman, send this example POST REST:
http://localhost:8080/textmanipulator/inputfilename/in.txt/outputfilename/out.txt/action/shuffle
(note that the input and output file names can be changed to your prefernce, however you will need to create the input and output files in the root of this microservice - 
where I have put in.txt and out.txt --> https://github.com/galpalmery/text_manipulator_messaging_ms. you can also choose another action- sort or reverse)

4. to follow RabbitMQ go to this URL in your browser: http://127.0.0.1:15672/#/
5. to follow MongoDB, open MongoDB Compass, connect to your localhost, and open the test.tm_transactions database, you will be able to view the documents that were persisted to it.

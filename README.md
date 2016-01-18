# ActiveMQ_Tutorial

ActiveMQ is an implementation of JMS and supports two models. One is Point-to-point model and the other is Publish/subscribe model.

# Point-to-Point model

In ActiveMQ, we use Queue to implement the model.

In point-to-point messaging system, messages are routed to an single consumer mantaining a queue of "incoming" messages. If there are no consumers available at the time when the message is sent,  the message will be kept until a consumer is available that can process the message. If a consumer receives a message and does not acknowledge it before closing then the message will be redelivered to another consumer. A queue can have many consumers with messages load balanced across the available consumers, but only one consumer will receive the message.

# Publish/Subscribe model

In ActiveMQ, we use Topic to implement the model.

In Publish/Subscribe System,neither the publisher nor the subscriber knows about each other. Subscribers may register interest in receiving messages on a particular message topic. When you publish a message, it goes to all the subscribers who are interested. The publisher need to create a message topic for clients to subscribe. The subscriber should remain active to receive messages, unless it has established a durable subscription. In that case, messages published while the subscriber is not connected will be redistributed whenever it reconnects.

# Steps to set up ActiveMQ
1)Download Apache ActiveMQ from ActiveMQ download page via http://activemq.apache.org/download-archives.html.

2)Extract the downloaded archive to suitable location <extracted path>.

3)Set the current path to <extracted path>/bin

4)Run the activemq command.

5) Once the ActiveMQ  completes the startup , the appropriate messages will be displayed in the console.

6) Now open the browser and type the url:http://localhost:8161/admin/, if it ask for username and password, type admin respectively

7) It is possible to view administered objects like queue,topic etc from the page

# Point to Point implementation
8) Creae two Java Projects in Eclipse, one for client and the other for server. Create a user library and add external jars from <extracted path>/lib

9) Copy the files ("Sender.java" and "Client.java" from "PointToPoint" folder) to your two projects ("server" and "client") respectively. 

10) Compile and run the sender.

11) You wll see the enqueued messages on the console. Meanwhile, in http://localhost:8161/admin/, go the queue tab, you will also see the queue has been created, and enqueued messages count has been changed to 2.

12) Compile and run the receiver.

13) Go the queue tab, consumers count has been changed to 1 and dequeued message count has been changed to 2. Two messages are printed out. 

# Publish/Subscriber implementation
14) Creae two Java Projects in Eclipse, one for client and the other for server. Create a user library and add external jars from <extracted path>/lib

15) Copy the files ("Sender.java" and "Client.java" from "PublishSubscribe" folder) to your two projects ("server" and "client") respectively. 

16) Compile and run the sender.

17) You wll see the enqueued messages on the console. Meanwhile, in http://localhost:8161/admin/, go the "topic" tab, you will also see the topic has been created, and enqueued messages count has been changed to 2.

18) Compile and run the receiver.

19) Go the queue tab, consumers count has been changed to 2 and dequeued message count has been changed to 2. Four messages are printed out. 



package com.activeMQ;
import java.util.ArrayList;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
public class Sender {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private String currentTopicName;
    public Sender(String topicName) {
    	this.currentTopicName = topicName;
    }
    
    /**
     *  Sender send a group of messages
     * */
    public void sendMessages(ArrayList<String> textMessages) {
        try 
        {
        	MessageProducer producer = creatProducer();	
	        for (int i = 0; i < textMessages.size(); i++) {
				TextMessage message = session.createTextMessage(textMessages.get(i));
				producer.send(message);
				System.out.println("Sent: " + message.getText());
	        }
	        if (connection != null) {
	            connection.close();
	       }    
        } 
        catch(JMSException e){
        	e.printStackTrace();
        }
    }
    /**
     * Create a producer
     * 
     * */

	private MessageProducer creatProducer() throws JMSException {
		MessageProducer producer = null;
		try {
    		// create the connection factory using URL
			factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			// create connection
			connection = factory.createConnection();
			//start connection
			connection.start();
			//create session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//create topic with given TopicName
			destination = session.createTopic(this.currentTopicName);
			//use the topic to create 
			producer = session.createProducer(destination);
		} catch (JMSException e) {
			e.printStackTrace();
			throw e;
		}
	    return producer;
	}

    public static void main(String[] args) {
        Sender sender = new Sender("TESTTopic");
        ArrayList<String> textMessages = new ArrayList<String>();
        textMessages.add("Hello ...This is the first sample message..sending from Sender");
        textMessages.add("Hello ...This is the second sample message..sending from Sender");
        sender.sendMessages(textMessages);
    }
}
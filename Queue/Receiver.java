package com.activeMQ;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.io.IOException;

public class Receiver implements MessageListener {

    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private String currentQueueName;

    public static void main(String[] args) throws JMSException, IOException {
    	Receiver receiver = new Receiver("TESTQueue");
        receiver.run();
    }
    public Receiver(String queueName)
    {
    	this.currentQueueName = queueName;
    }
    /**
     * Receiver set the message listener to receive the message
     * */
    public void run() throws JMSException, IOException {
    	try {
    		MessageConsumer consumer1 = createConsumer();
			System.out.println("consumer1 created.");			
			consumer1.setMessageListener(this);
			System.in.read();
			consumer1.close();			
			session.close();			
			connection.close();			
			
		} catch (JMSException e) {
			e.printStackTrace();
			throw e;
		}
    	catch (IOException e2){
    		e2.printStackTrace();
    		throw e2;
    	}catch (Exception e) {
			System.out.println("Exception while sending message to the queue" + e);
			throw e;
		}
    }
    
    /**
     * This method is to create a consumer
     * 
     * */

	private MessageConsumer createConsumer() throws JMSException
    {
    	MessageConsumer consumer = null;
    	try {
    		// create the connection factory using URL
			factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
			// create connection
			connection = factory.createConnection();
			connection.start();
			System.out.println("connection.start() successfully");
			// create session
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// create queue if queue doesn't exist
			destination = session.createQueue(this.currentQueueName);
			// create consumer
			consumer = session.createConsumer(destination);
		} catch (JMSException e) {
			e.printStackTrace();
			throw e;
		}
    	return consumer;
    }
    /**
     * Implement onMessage to receive any messages from queue
     * 
     * */
	@Override
    public void onMessage(Message message) {
        try {
        	System.out.println("Messages received for the consumer");
        	System.out.println("onMessage triggered: --end ");
            if (message instanceof TextMessage) {
                TextMessage txtMessage = (TextMessage) message;
                System.out.println("Message received: " + txtMessage.getText());
            } else {
                System.out.println("Invalid message received.");
            }
            System.out.println("onMessage triggered: --end ");
        } catch (JMSException e1) {
				e1.printStackTrace();
			}
        }
    }

package CTFActiveMQ;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ActiveMQ {

	private String errorMessage;
    private Connection conn;
    private Boolean error;
    private String Queue;
    private  Session session;
    private Destination destination;
    
    public ActiveMQ()
    {
    	errorMessage = "";
    	conn = null;
    }
    
    public  String getErrorMessage()
    {
    	return errorMessage;
    }
    
    public Boolean getError()
    {
    	return error;
    }
    
    public boolean isConnected()
    {
    	return (conn != null);
    }
    
    public void close()
    {
    	try
    	{
    		session.close();
    		conn.close();
    	}
    	catch (Exception ex)
    	{
        	error = true;
        	errorMessage = ex.getMessage();    		
    	}
    }
 
    public void send(String msg)
    {
    	try
    	{
            // Create a messages
            TextMessage message = session.createTextMessage(msg);
            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Tell the producer to send the message
             producer.send(message);
             producer.close();
    	}
    	catch (Exception ex)
    	{
        	error = true;
        	errorMessage = ex.getMessage();    		
    	}
    }
 
    
    public String recv()
    {
    	String msg = "";
    	try
    	{
            // Create a messages
            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            Message message = consumer.receive(1000);
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                msg = textMessage.getText();
            } else {
            }
            consumer.close();
    	}
    	catch (Exception ex)
    	{
        	error = true;
        	errorMessage = ex.getMessage();    		
    	}
    	
    	return msg;
    }
    
    
    public boolean connect (String IPAddress, String queue)
    {
    	error = false;
    	errorMessage = "";
    	Queue = queue;
		try {
            // Create a ConnectionFactory
            //ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.1.110:61616");
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://" + IPAddress);


            // Create a Connection
            conn = connectionFactory.createConnection();
            conn.start();

            // Create a Session
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            destination = session.createQueue(queue);
 
         }
        catch (Exception e) {
        	error = true;
        	errorMessage = e.getMessage();
        }
    	
    	return !error;
     }
}

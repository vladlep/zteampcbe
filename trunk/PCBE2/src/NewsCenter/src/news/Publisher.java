package news;

import java.util.ArrayList;
import java.util.Date;

import javax.jms.*;

public class Publisher extends Subscriber {

	private ReadNewsListener topicListener = null;

    private PubFrame pubFrame;
	
	private class ReadNewsListener implements MessageListener{

		@Override
		public void onMessage(Message msg) {

            String txt = "";
			try {
				
                txt = txt.concat("\n----------------------------------------------------------\n" +
								name + " - Event Received - News " + msg.getStringProperty("NewsType") + "\n" +
								msg.getStringProperty("NewsTitle") + " by " + msg.getStringProperty("NewsAuthor") + "\n" +
								msg.getStringProperty("NewsDomain") + "\n" +
								"published on: " + msg.getStringProperty("NewsDate") + " / last modified: " + msg.getStringProperty("NewsLastDate"));
				if(msg instanceof TextMessage) {
				
                    txt = txt.concat("\n" + ((TextMessage)msg).getText());
				}
                pubFrame.appendText(txt);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public Publisher(String name, ArrayList<NewsAttributes> newsAttr) {
		super(name, newsAttr);
	}
	
    @Override
	public void startSubscriber() {
		
		String selector = "";
				
		for(NewsAttributes attr : this.newsAttr) {
			
			selector = selector.concat("(");
			selector = selector.concat(attr.getSQLSyntaxAttributes());
			selector = selector.concat(") OR ");
		}
		
		if(selector.equals("")) {
			selector = "TRUE = FALSE";
		} else {
			selector = selector.substring(0, selector.lastIndexOf("OR"));
		}
		
		try {
            this.stopSubscriber();
			this.topicConnection.stop();
			
			this.topicSubscriber = this.topicSession.createDurableSubscriber(this.newsTopic, this.name, selector, false);
			this.topicListener = new ReadNewsListener();
			this.topicSubscriber.setMessageListener(this.topicListener);
			
			this.topicConnection.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Message createMessage(String author, String domain, Date firstPublished,
			Date lastModified, String source, String title, String type, String textMessage) throws JMSException {
		
		Message msg;
		
		// TODO check null parameters
		
		if(type.equals(NewsSelector.NEWS_TYPES[3])) {
		
			msg = this.topicSession.createMessage();
		} else {
			
			msg = this.topicSession.createTextMessage();
			((TextMessage)msg).setText(textMessage);
		}
		
		msg.setStringProperty("NewsAuthor", author);
		msg.setStringProperty("NewsDomain", domain);
		msg.setStringProperty("NewsDate", firstPublished.toString());
		msg.setStringProperty("NewsLastDate", lastModified.toString());
		msg.setStringProperty("NewsSource", source);
		msg.setStringProperty("NewsTitle", title);
		msg.setStringProperty("NewsType", type);
		
		return msg;
	}
	
	public void publishNews(NewsContent newsContent) {
		
		Message msg1;
		try {
			msg1 = this.createMessage(newsContent.getNewsAttributes().getAuthor(),
					newsContent.getNewsAttributes().getDomain(),
					newsContent.getNewsAttributes().getFirstPublished(),
					newsContent.getNewsAttributes().getLastModified(),
					newsContent.getNewsAttributes().getSource(),
					newsContent.getNewsAttributes().getTitle(),
					newsContent.getNewsAttributes().getType(),
					newsContent.getNewsText());
			this.topicPublisher.publish(msg1);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    @Override
	public void run() {

        this.pubFrame = new PubFrame(this);
        this.pubFrame.setVisible(true);
	}

}

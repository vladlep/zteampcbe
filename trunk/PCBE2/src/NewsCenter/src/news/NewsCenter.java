package news;

public class NewsCenter {
	
	public static void main(String args[]) {
		

        Publisher pub1 = new Publisher("Publisher001", null);
        Publisher pub2 = new Publisher("Publisher002", null);
        Subscriber sub1 = new Subscriber("Subscriber001", null);
        Subscriber sub2 = new Subscriber("Subscriber002", null);
		
		sub1.start();
		sub2.start();
		pub1.start();
		pub2.start();
		
	}

}

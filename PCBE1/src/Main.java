
public class Main 
{

	public static void main(String[] args) {
		MovementSpace mvsp= new MovementSpace(10,20);
		
		Atoms nr1 = new Atoms(new Position(0, 0), "C");
		Atoms nr2 = new Atoms(new Position(7,12), "H");
		Atoms nr3 = new Atoms(new Position(0,1),"O");
		mvsp.printSpace();
		nr1.start();
		nr2.start();
	}
	
}

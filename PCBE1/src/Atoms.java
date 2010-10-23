
public class Atoms extends ChemicalEntity {

	private String name;
	private boolean canMoveFlag = true; // it's sayt that this thread can move
	public  Atoms(Position currentPosition, String name)
	{
		super(currentPosition);
		this.name = name;
	}


	
	
	public synchronized void stopThread()
	{
		canMoveFlag = false;
	}
	public synchronized void startThread()
	{
		canMoveFlag = true;
	}
	
	public boolean canMove() {
		
		return canMoveFlag;
	}
	
	public String toString() {
	
		return name;

	}
}

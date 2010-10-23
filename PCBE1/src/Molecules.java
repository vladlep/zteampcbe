import java.util.Vector;


public class Molecules extends ChemicalEntity {

	private boolean canMoveFlag = true;
	private Vector<ChemicalEntity> containedAtoms;
	
	public Molecules(Position currentPosition)
	{
		super(currentPosition);
		
	}
	
	
	public String toString() {
	String str= "";
		for ( int i=0; i<containedAtoms.size(); i++)
		{
			str = str + containedAtoms.toString() + " ";
		}
		
	return "";
	}
	
	



	public boolean canMove() 
	{

		return canMoveFlag;
	}


	@Override
	public void startThread() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void stopThread() {
		// TODO Auto-generated method stub
		
	}
	
}

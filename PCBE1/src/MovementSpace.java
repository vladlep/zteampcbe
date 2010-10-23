import java.util.ArrayList;


public class MovementSpace {
	private ArrayList<ChemicalEntity> space = null;
	private int lengthOx;
	private int lengthOy;
	
	public MovementSpace(int lengthOx,int lengthOy)
	{
		space = new ArrayList<ChemicalEntity>(lengthOx);		
	}

	

	/**
	 * @return : true if movement is posible, false otherwise
	 * 
	 */
	public synchronized boolean  moveInSpace(ChemicalEntity particle, Direction dirOfMovement)
	{
		if(validSpace(particle,dirOfMovement))
		{
		// TODO Auto-generated method stub
		}
		printSpace();
		return false;
	}
	
	public void printSpace()
	{
		for (int i=0; i <lengthOx; i++)
			for ( int j=0; j<lengthOy; j++)
			{
				;
			}
	
	}
	
	private synchronized boolean validSpace(ChemicalEntity particle, Direction dirOfMovement)
	{
		return false;
	}
}

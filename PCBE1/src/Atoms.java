
public class Atoms extends ChemicalEntity {

	private MovementSpace mvSpace; 
	private Position currentPosition;

	public  Atoms(Position currentPosition)
	{
		super(currentPosition);

	}


	public void run() 
	{
		space = MovementSpace.getMovementSpace();
		while (true )
		{

			
			mvSpace.moveInSpace(currentPosition, generatePosition());

		}
	}

	private synchronized Position generatePosition() // shouldn't be sync?
	{
		int differenceOnX = (int)(Math.random() * 3 -1); // can be -1, 0 or 1
		int differenceOnY = (int)(Math.random() * 3 -1); // can be -1, 0 or 1
			
		return new Position(currentPosition.getX()+differenceOnX, currentPosition.getY()+differenceOnY);

	}
}

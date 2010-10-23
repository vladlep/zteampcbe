import java.util.ArrayList;
import java.util.Vector;

public class MovementSpace {
	private Vector<Vector<ChemicalEntity>> space = null;
	private static MovementSpace movementSpace = null;
	private int lengthOx;
	private int lengthOy;

	public MovementSpace(int lengthOx, int lengthOy) {
		space = new Vector<Vector<ChemicalEntity>>(lengthOx);
		for (int i = 0; i < lengthOx; i++)
			space.add(new Vector<ChemicalEntity>(lengthOy));

	}

	/**
	 * @return : true if movement is posible, false otherwise
	 * 
	 */
	public synchronized boolean moveInSpace(Position currentPosition,
			Position newPosition) {
		if (emptySpace(newPosition)) {
			// TODO Auto-generated method stub
		}
		printSpace();
		return false;
	}

	public void printSpace() {
		for (int i = 0; i < lengthOx; i++)
		{
			for (int j = 0; j < lengthOy; j++) 
				System.out.println(space.get(i).get(j)+" ");
			System.out.println();
		}


	}

	private synchronized boolean emptySpace(Position pos) {
		// if(space.indexOf(pos.getX()))
		return false;
	}

	public static MovementSpace getMovementSpace() {
		if (movementSpace == null)
			movementSpace = new MovementSpace(100, 100);
		return movementSpace;
	}
}

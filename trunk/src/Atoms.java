import java.util.Collections;
import java.util.Vector;


public class Atoms extends ChemicalEntity {

	private String name;
	private Vector<Integer> MaxLayer;
	private Vector<Integer> OccLayer;
	private Vector<Integer> OccLayerNow;
	private boolean canMoveFlag = true; // it's sayt that this thread can move
	public  Atoms(Position currentPosition, String name, Vector<Integer> MaxLayer, Vector<Integer> OccLayer)
	{
		super(currentPosition);
		this.name = name;
		this.MaxLayer = (Vector) MaxLayer.clone();
		this.OccLayer = (Vector) OccLayer.clone();
		this.OccLayerNow = (Vector) OccLayer.clone();
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
	
		return name+super.toString();

	}

	public Vector<Integer> getMax()
	{
		return MaxLayer;
	}
	
	public Vector<Integer> getOccuppied()
	{
		return OccLayer;
	}
	
	/*
	 * Add in pos n.
	 */
	public boolean setOccupied(int pos, int n)
	{
		if (OccLayerNow.get(pos) + n >= MaxLayer.get(pos))
			return false;
		OccLayerNow.set(pos, OccLayerNow.get(pos) + n);
			return true;
	}
	
	public boolean unsetOccupied(int pos, int n)
	{
		if (OccLayerNow.get(pos) - n < OccLayer.get(pos))
			return false;
		OccLayerNow.set(pos, OccLayerNow.get(pos) - n);
			return true;
	}
	/*
	 * Return the force of the atom.
	 */
	public int GetForce()
	{
		int force = 0;
		int factor = OccLayerNow.size();
		for (int i = 0; i < OccLayerNow.size(); ++i)
			force += factor--*(MaxLayer.get(i) - OccLayerNow.get(i));
		return force;
	}
	
	@Override
	public int getValence() {
		// TODO Auto-generated method stub
		return 0;
	}
}

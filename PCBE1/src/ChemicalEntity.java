import javax.crypto.spec.PSource;


public abstract class ChemicalEntity extends Thread{

	protected Position currentPosition;
	protected MovementSpace space;

	public ChemicalEntity(Position currentPosition)
	{
		this.currentPosition = currentPosition;
	}

	public abstract void run();
	
}

import javax.crypto.spec.PSource;

public abstract class ChemicalEntity extends Thread {

	private MovementSpace mvSpace;
	private Position currentPosition;

	public ChemicalEntity(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public void run() {
		mvSpace = MovementSpace.getMovementSpace();
		while (true) {

			synchronized (this) {
				while (canMove()) {
					if (!mvSpace.moveInSpace(currentPosition,
							generatePosition()))
						System.out
								.println("couldn't move in space. It is ocupied.");
					try {
						sleep(600);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private synchronized Position generatePosition() // shouldn't be sync?
	{
		int differenceOnX = (int) (Math.random() * 3 - 1); // can be -1, 0 or 1
		int differenceOnY = (int) (Math.random() * 3 - 1); // can be -1, 0 or 1

		return new Position(currentPosition.getX() + differenceOnX,
				currentPosition.getY() + differenceOnY);
	}

	public abstract boolean canMove();

	public abstract int getValence();

	public abstract void stopThread();

	public abstract void startThread();

}

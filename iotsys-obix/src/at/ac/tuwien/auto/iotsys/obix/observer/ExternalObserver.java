package at.ac.tuwien.auto.iotsys.obix.observer;

/**
 * This class is used to notify external observers about changes on oBIX
 * objects.
 */
public interface ExternalObserver {
	public void objectChanged(String fullContextPath);
}

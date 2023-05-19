import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class AllOrders implements Iterator<Order> {

  private final Vector<Order> orders;
  private int currentIndex = 0;
  private Order nextOrder;

  public AllOrders(Vector orders) {
    this.orders = orders;
  }

  @Override
  public boolean hasNext() {
    boolean matchFound = false;
    nextOrder = null;
    if (currentIndex >= this.orders.size()) {
      return false;
    }
    nextOrder = this.orders.get(currentIndex);
    return true;
  }

  @Override
  public Order next() {
    if (nextOrder == null) {
      throw new NoSuchElementException();
    } else {
      currentIndex++;
      return nextOrder;
    }
  }

  @Override
  public void remove() {
    if (currentIndex > 0) {
      this.orders.remove(currentIndex - 1);
      currentIndex--;
    }
  }
}

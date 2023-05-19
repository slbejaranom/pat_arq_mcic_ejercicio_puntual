import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class AllOrders implements Iterator<Order> {

  private Vector<Order> orders;
  Enumeration<Order> ec;
  Order nextOrder;

  public AllOrders() {
    this.orders = new Vector<>();
  }

  @Override
  public boolean hasNext() {
    boolean matchFound = false;
    nextOrder = null;
    while (ec.hasMoreElements()) {
      Order tempObj = ec.nextElement();
      nextOrder = tempObj;
      break;
    }
    return (nextOrder != null);
  }

  @Override
  public Order next() {
    if (nextOrder == null) {
      throw new NoSuchElementException();
    } else {
      return nextOrder;
    }
  }

  public void addElement(Order order){
    this.orders.addElement(order);
  }

  double getBigTotal(){
    double total = 0;
    for(Order order : orders){
      total = total + order.getTotal();
    }
    return total;
  }
}

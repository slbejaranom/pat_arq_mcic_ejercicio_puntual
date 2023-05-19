import java.util.Iterator;

public class AllOrdersTotalDecorator implements Iterator<Order> {

  private final AllOrders allOrders;

  public AllOrdersTotalDecorator(AllOrders allOrders){
    this.allOrders = allOrders;
  }

  double getBigTotal() {
    double total = 0;
    while(allOrders.hasNext()){
      Order order = allOrders.next();
      total += order.getTotal();
    }
    return total;
  }

  @Override
  public boolean hasNext() {
    return allOrders.hasNext();
  }

  @Override
  public Order next() {
    return allOrders.next();
  }

  @Override
  public void remove() {
    allOrders.remove();
  }
}

public interface Order {
  double getTotal();
  public void accept(OrderVisitor v);
}

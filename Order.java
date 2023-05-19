public interface Order {
  double getTotal();
  void setTotal(double total);
  public void accept(OrderVisitor v);
}

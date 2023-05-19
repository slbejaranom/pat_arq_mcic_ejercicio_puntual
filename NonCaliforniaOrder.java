public class NonCaliforniaOrder implements Order {
  private double orderAmount;
  private double total;
  public void setTotal(double total) {
    this.total = total;
  }

  public NonCaliforniaOrder() {
  }
  public NonCaliforniaOrder(double inp_orderAmount) {
    orderAmount = inp_orderAmount;
  }
  public double getOrderAmount() {
    return orderAmount;
  }

  @Override
  public double getTotal() {
    return this.total;
  }

  public void accept(OrderVisitor v) {
    v.visit(this);
  }

  @Override
  public String toString() {
    return "NonCalifornia Order - Order amount: "+this.getOrderAmount()+" - Total: "+this.getTotal();
  }
}

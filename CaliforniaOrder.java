public class CaliforniaOrder implements Order {
  private double orderAmount;
  private double additionalTax;
  private double total;
  public void setTotal(double total) {
    this.total = total;
  }

  public CaliforniaOrder() {
  }
  public CaliforniaOrder(double inp_orderAmount,
      double inp_additionalTax) {
    orderAmount = inp_orderAmount;
    additionalTax = inp_additionalTax;
  }
  public double getOrderAmount() {
    return orderAmount;
  }
  public double getAdditionalTax() {
    return additionalTax;
  }

  @Override
  public double getTotal() {
    return this.total;
  }

  public void accept(OrderVisitor v) {
    v.visit(this);
  }
}


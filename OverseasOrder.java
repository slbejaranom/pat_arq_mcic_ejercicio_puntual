public class OverseasOrder implements Order {
  private double orderAmount;
  private double additionalSH;
  private double total;
  public void setTotal(double total) {
    this.total = total;
  }

  public OverseasOrder() {
  }
  public OverseasOrder(double inp_orderAmount,
      double inp_additionalSH) {
    orderAmount = inp_orderAmount;
    additionalSH = inp_additionalSH;
  }
  public double getOrderAmount() {
    return orderAmount;
  }
  public double getAdditionalSH() {
    return additionalSH;
  }

  @Override
  public double getTotal() {
    return this.total;
  }

  public void accept(OrderVisitor v) {
    v.visit(this);
  }
}

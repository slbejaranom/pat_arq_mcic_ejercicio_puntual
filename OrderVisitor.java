class OrderVisitor implements VisitorInterface {
  public void visit(NonCaliforniaOrder inp_order) {
    inp_order.setTotal(inp_order.getOrderAmount());
  }
  public void visit(CaliforniaOrder inp_order) {
    inp_order.setTotal(inp_order.getOrderAmount() +
                 inp_order.getAdditionalTax());
  }
  public void visit(OverseasOrder inp_order) {
    inp_order.setTotal(inp_order.getOrderAmount() +
                 inp_order.getAdditionalSH());
  }
}

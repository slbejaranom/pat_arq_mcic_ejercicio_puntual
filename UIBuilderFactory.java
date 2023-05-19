public class UIBuilderFactory {

  UIBuilder getUIBuilder(String orderType) {
    switch (orderType) {
      case "California Order":
        return new CaliforniaOrderUIBuilder();
      case "Non-California Order":
        return new NonCaliforniaOrderUIBuilder();
      case "Overseas Order":
        return new OverseasOrderUIBuilder();
      default:
        return null;
    }
  }
}

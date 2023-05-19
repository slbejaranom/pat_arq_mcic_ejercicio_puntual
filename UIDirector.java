import javax.swing.*;

public class UIDirector {

  private UIBuilder uiBuilder;
  private final JPanel jPanel;

  public UIDirector(UIBuilder uiBuilder, JPanel jPanel) {
    this.jPanel = jPanel;
    this.uiBuilder = uiBuilder;
  }

  public void build() {
    uiBuilder.addComponents(jPanel);
  }
}

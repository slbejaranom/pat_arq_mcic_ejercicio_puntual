import javax.swing.*;
import java.awt.*;

public abstract class UIBuilder {

  abstract void addComponents(JPanel panel);

  abstract Order getOrder();

  void deleteCurrentUI(JPanel jPanel) {
    Component[] components = jPanel.getComponents();
    for (int i = 0; i < components.length; i++) {
      String componentName = components[i].getName();
      if (componentName == null) {
        jPanel.remove(components[i]);
      }
    }
  }
}

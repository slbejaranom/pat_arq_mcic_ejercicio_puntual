import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NonCaliforniaOrderUIBuilder extends UIBuilder{

  private JTextField txtOrderAmount;
  private JLabel lblOrderAmount;
  @Override
  void addComponents(JPanel panel) {
    deleteCurrentUI(panel);
    txtOrderAmount = new JTextField(10);
    lblOrderAmount = new JLabel("Order Amount:");

    GridBagLayout gridbag = new GridBagLayout();
    panel.setLayout(gridbag);
    GridBagConstraints gbc = new GridBagConstraints();
    panel.add(lblOrderAmount);
    panel.add(txtOrderAmount);
    GridBagLayout gridbag2 = new GridBagLayout();
    gbc.insets.top = 5;
    gbc.insets.bottom = 5;
    gbc.insets.left = 5;
    gbc.insets.right = 5;

    gbc.anchor = GridBagConstraints.EAST;
    gbc.gridx = 0;
    gbc.gridy = 1;
    gridbag.setConstraints(lblOrderAmount, gbc);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 1;
    gridbag.setConstraints(txtOrderAmount, gbc);
  }

  @Override
  Order getOrder() {
    double orderAmount = Double.parseDouble(txtOrderAmount.getText());
    return new NonCaliforniaOrder(orderAmount);
  }
}

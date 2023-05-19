import javax.swing.*;
import java.awt.*;

public class CaliforniaOrderUIBuilder extends UIBuilder {

  private JTextField txtOrderAmount;
  private JLabel lblOrderAmount;
  private JTextField txtAdditionalTax;
  private JLabel lblAdditionalTax;

  @Override
  public void addComponents(JPanel panel) {
    deleteCurrentUI(panel);
    txtOrderAmount = new JTextField(10);
    lblOrderAmount = new JLabel("Order Amount:");
    txtAdditionalTax = new JTextField(10);
    lblAdditionalTax = new JLabel("Additional Tax");
    GridBagLayout gridbag = new GridBagLayout();
    panel.setLayout(gridbag);
    GridBagConstraints gbc = new GridBagConstraints();
    panel.add(lblOrderAmount);
    panel.add(txtOrderAmount);
    panel.add(lblAdditionalTax);
    panel.add(txtAdditionalTax);
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
    gbc.anchor = GridBagConstraints.EAST;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gridbag.setConstraints(lblAdditionalTax, gbc);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 2;
    gridbag.setConstraints(txtAdditionalTax, gbc);
  }

  @Override
  public Order getOrder() {
    double orderAmount = Double.parseDouble(txtOrderAmount.getText());
    double additionalTax = Double.parseDouble(txtAdditionalTax.getText());
    return new CaliforniaOrder(orderAmount, additionalTax);
  }
}

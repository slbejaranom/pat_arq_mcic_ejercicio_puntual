import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OverseasOrderUIBuilder extends UIBuilder{

  private JTextField txtOrderAmount;
  private JLabel lblOrderAmount;
  private JTextField txtAdditionalSH;
  private JLabel lblAdditionalSH;
  @Override
  void addComponents(JPanel panel) {
    deleteCurrentUI(panel);
    txtOrderAmount = new JTextField(10);
    lblOrderAmount = new JLabel("Order Amount:");
    txtAdditionalSH = new JTextField(10);
    lblAdditionalSH = new JLabel("Additional S&H");
    GridBagLayout gridbag = new GridBagLayout();
    panel.setLayout(gridbag);
    GridBagConstraints gbc = new GridBagConstraints();
    panel.add(lblOrderAmount);
    panel.add(txtOrderAmount);
    panel.add(lblAdditionalSH);
    panel.add(txtAdditionalSH);
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
    gbc.anchor = GridBagConstraints.EAST;
    gbc.gridx = 0;
    gbc.gridy = 2;
    gridbag.setConstraints(lblAdditionalSH, gbc);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 2;
    gridbag.setConstraints(txtAdditionalSH, gbc);
  }

  @Override
  Order getOrder() {
    double orderAmount = Double.parseDouble(txtOrderAmount.getText());
    double additionalSH = Double.parseDouble(txtAdditionalSH.getText());
    return new OverseasOrder(orderAmount, additionalSH);
  }
}

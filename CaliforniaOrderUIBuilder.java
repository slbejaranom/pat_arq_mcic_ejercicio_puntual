import javax.swing.*;
import java.awt.*;

public class CaliforniaOrderUIBuilder extends UIBuilder {

    private JTextField txtOrderAmount;
    private JLabel lblOrderAmount;

    @Override
    public void addComponents(JPanel panel) {
        deleteCurrentUI(panel);
        txtOrderAmount = new JTextField(10);
        lblOrderAmount = new JLabel("Order Amount:");
        GridBagConstraints gbc = new GridBagConstraints();
        panel.add(lblOrderAmount);
        panel.add(txtOrderAmount);

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
    public Order getOrder() {
        return null;
    }
}

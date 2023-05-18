import javax.swing.*;

public class CaliforniaOrderUIBuilder implements UIBuilder {

    private JTextField txtOrderAmount;
    private JLabel lblOrderAmount;

    @Override
    public void addComponents(JPanel panel) {
        txtOrderAmount = new JTextField(10);
        lblOrderAmount = new JLabel("Order Amount:");
    }

    @Override
    public Order getOrder() {
        return null;
    }
}

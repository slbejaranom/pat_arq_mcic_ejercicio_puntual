import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sun.java.swing.plaf.windows.*;

public class OrderManager extends JFrame {

  public static final String newline = "\n";
  public static final String GET_TOTAL = "Get Total";
  public static final String CREATE_ORDER = "Create Order";
  public static final String EXIT = "Exit";
  public static final String CA_ORDER = "California Order";
  public static final String NON_CA_ORDER =
      "Non-California Order";

  public static final String OVERSEAS_ORDER = "Overseas Order";


  private JComboBox cmbOrderType;
  private JTextField txtOrderAmount, txtAdditionalTax,
      txtAdditionalSH;
  private JLabel lblOrderType, lblOrderAmount;
  private JLabel lblAdditionalTax, lblAdditionalSH;
  private JLabel lblTotal, lblTotalValue;

  public void setUiBuilder(UIBuilder uiBuilder) {
    this.uiBuilder = uiBuilder;
  }

  private UIBuilder uiBuilder;
  private OrderVisitor objVisitor;

  public OrderManager() {
    super("Ejercicio puntual Patrones y Arquitecturas de Software 2023-1");

    //Create the visitor instance
    objVisitor = new OrderVisitor();

    lblOrderType = new JLabel("Order Type:");
    lblOrderType.setName("lblOrderType");
    lblTotal = new JLabel("Result:");
    lblTotal.setName("lblTotal");
    lblTotalValue =
        new JLabel("Click Create or GetTotal Button");
    lblTotalValue.setName("lblTotalValue");
    cmbOrderType = new JComboBox();
    cmbOrderType.addItem(OrderManager.CA_ORDER);
    cmbOrderType.addItem(OrderManager.NON_CA_ORDER);
    cmbOrderType.addItem(OrderManager.OVERSEAS_ORDER);
    cmbOrderType.setName("cmbOrderType");

    //Create the open button
    JButton getTotalButton =
        new JButton(OrderManager.GET_TOTAL);
    getTotalButton.setMnemonic(KeyEvent.VK_G);
    JButton createOrderButton =
        new JButton(OrderManager.CREATE_ORDER);
    getTotalButton.setMnemonic(KeyEvent.VK_C);
    JButton exitButton = new JButton(OrderManager.EXIT);
    exitButton.setMnemonic(KeyEvent.VK_X);
    AllOrders allOrders = new AllOrders();
    ButtonHandler objButtonHandler = new ButtonHandler(this, allOrders);

    getTotalButton.addActionListener(objButtonHandler);
    createOrderButton.addActionListener(objButtonHandler);
    exitButton.addActionListener(new ButtonHandler());

    //For layout purposes, put the buttons in a separate panel
    JPanel fieldsPanel = new JPanel();

    JPanel buttonPanel = new JPanel();
    GridBagLayout gridbag2 = new GridBagLayout();
    buttonPanel.setLayout(gridbag2);
    GridBagConstraints gbc2 = new GridBagConstraints();
    buttonPanel.add(getTotalButton);
    buttonPanel.add(createOrderButton);
    buttonPanel.add(exitButton);
    gbc2.anchor = GridBagConstraints.EAST;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gridbag2.setConstraints(createOrderButton, gbc2);
    gbc2.gridx = 1;
    gbc2.gridy = 0;
    gridbag2.setConstraints(getTotalButton, gbc2);
    gbc2.gridx = 2;
    gbc2.gridy = 0;
    gridbag2.setConstraints(exitButton, gbc2);

    //****************************************************
    GridBagLayout gridbag = new GridBagLayout();
    fieldsPanel.setLayout(gridbag);
    GridBagConstraints gbc = new GridBagConstraints();

    fieldsPanel.add(lblOrderType);
    fieldsPanel.add(cmbOrderType);
    fieldsPanel.add(lblTotal);
    fieldsPanel.add(lblTotalValue);

    gbc.insets.top = 5;
    gbc.insets.bottom = 5;
    gbc.insets.left = 5;
    gbc.insets.right = 5;

    gbc.anchor = GridBagConstraints.EAST;
    gbc.gridx = 0;
    gbc.gridy = 0;
    gridbag.setConstraints(lblOrderType, gbc);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 0;
    gridbag.setConstraints(cmbOrderType, gbc);
    gbc.anchor = GridBagConstraints.EAST;
    gbc.gridx = 0;
    gbc.gridy = 4;
    gridbag.setConstraints(lblTotal, gbc);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 4;
    gridbag.setConstraints(lblTotalValue, gbc);

    gbc.insets.left = 2;
    gbc.insets.right = 2;
    gbc.insets.top = 40;

    //****************************************************

    //Add the buttons and the log to the frame
    Container contentPane = getContentPane();
    cmbOrderType.addItemListener(new ItemChangeListener(fieldsPanel, contentPane, OrderManager.this));
    contentPane.add(fieldsPanel, BorderLayout.NORTH);
    this.uiBuilder = new CaliforniaOrderUIBuilder();
    this.uiBuilder.addComponents(fieldsPanel);
    contentPane.add(buttonPanel, BorderLayout.CENTER);
    try {
      UIManager.setLookAndFeel(new WindowsLookAndFeel());
      SwingUtilities.updateComponentTreeUI(
          OrderManager.this);
    } catch (Exception ex) {
      System.out.println(ex);
    }

  }

  public Order getOrder() {
    return this.uiBuilder.getOrder();
  }

  public static void main(String[] args) {
    JFrame frame = new OrderManager();

    frame.addWindowListener(new WindowAdapter() {
                              public void windowClosing(WindowEvent e) {
                                System.exit(0);
                              }
                            }
    );

    //frame.pack();
    frame.setSize(500, 400);
    frame.setVisible(true);
  }

  public void setTotalValue(String msg) {
    lblTotalValue.setText(msg);
  }

  public OrderVisitor getOrderVisitor() {
    return objVisitor;
  }

  public String getOrderType() {
    return (String) cmbOrderType.getSelectedItem();
  }

  public String getOrderAmount() {
    return txtOrderAmount.getText();
  }

  public String getTax() {
    return txtAdditionalTax.getText();
  }

  public String getSH() {
    return txtAdditionalSH.getText();
  }

} // End of class OrderManager

class ButtonHandler implements ActionListener {

  OrderManager objOrderManager;
  private AllOrders allOrders;

  public void actionPerformed(ActionEvent e) {
    String totalResult = null;

    if (e.getActionCommand().equals(OrderManager.EXIT)) {
      System.exit(1);
    }
    if (e.getActionCommand().equals(OrderManager.CREATE_ORDER)
    ) {
      //get input values
      Order order = objOrderManager.getOrder();
      //Get the Visitor
      OrderVisitor visitor =
          objOrderManager.getOrderVisitor();

      // accept the visitor instance
      order.accept(visitor);
      this.allOrders.addElement(order);
      objOrderManager.setTotalValue(
          " Order Created Successfully");
    }

    if (e.getActionCommand().equals(OrderManager.GET_TOTAL)) {
      totalResult = new Double(
          allOrders.getBigTotal()).toString();
      totalResult = " Orders Total = " + totalResult;
      objOrderManager.setTotalValue(totalResult);
    }
  }

  public Order createOrder(String orderType,
      double orderAmount, double tax, double SH) {
    if (orderType.equalsIgnoreCase(OrderManager.CA_ORDER)) {
      return new CaliforniaOrder(orderAmount, tax);
    }
    if (orderType.equalsIgnoreCase(
        OrderManager.NON_CA_ORDER)) {
      return new NonCaliforniaOrder(orderAmount);
    }
    if (orderType.equalsIgnoreCase(
        OrderManager.OVERSEAS_ORDER)) {
      return new OverseasOrder(orderAmount, SH);
    }
    return null;
  }

  public ButtonHandler() {
  }

  public ButtonHandler(OrderManager inObjOrderManager, AllOrders allOrders) {
    this.allOrders = allOrders;
    objOrderManager = inObjOrderManager;
  }

}

// End of class ButtonHandler

class ItemChangeListener implements ItemListener {

  private JPanel jPanel;
  private Container contentPane;
  private OrderManager orderManager;

  @Override
  public void itemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
      String selectedOrderType = (String) e.getItem();
      UIBuilderFactory uiBuilderFactory = new UIBuilderFactory();
      UIBuilder uiBuilder = uiBuilderFactory.getUIBuilder(selectedOrderType);
      orderManager.setUiBuilder(uiBuilder);
      UIDirector uiDirector = new UIDirector(uiBuilder, this.jPanel);
      uiDirector.build();

      contentPane.add(this.jPanel, BorderLayout.NORTH);

      try {
        UIManager.setLookAndFeel(new WindowsLookAndFeel());
        SwingUtilities.updateComponentTreeUI(
            this.orderManager);
      } catch (Exception ex) {
        System.out.println(ex);
      }
    }
  }

  public ItemChangeListener(JPanel jPanel, Container contentPane, OrderManager orderManager) {
    this.jPanel = jPanel;
    this.contentPane = contentPane;
    this.orderManager = orderManager;
  }
}
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;
import com.sun.java.swing.plaf.windows.*;

public class OrderManager extends JFrame {

  public static final String newline = "\n";
  public static final String GET_TOTAL = "Get Total";
  public static final String CREATE_ORDER = "Create Order";
  public static final String EXIT = "Exit";
  public static final String REMOVE_LAST_ORDER = "Remove last order";
  public static final String CA_ORDER = "California Order";
  public static final String NON_CA_ORDER =
      "Non-California Order";

  public static final String OVERSEAS_ORDER = "Overseas Order";


  private JComboBox cmbOrderType;
  private JLabel lblOrderType;
  private JLabel lblTotal, lblTotalValue;
  private JTextArea ordersTextArea;
  private Vector<Order> orders;
  private UIBuilder uiBuilder;
  private OrderVisitor objVisitor;

  public OrderManager() {
    super("Ejercicio puntual Patrones y Arquitecturas de Software 2023-1");
    orders = new Vector<>();
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
    JButton removeLastOrderButton = new JButton(OrderManager.REMOVE_LAST_ORDER);
    removeLastOrderButton.setMnemonic(KeyEvent.VK_R);
    this.ordersTextArea = new JTextArea();
    this.ordersTextArea.setEditable(false);
    JScrollPane scroll = new JScrollPane(this.ordersTextArea);
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    ButtonHandler objButtonHandler = new ButtonHandler(this, orders);

    getTotalButton.addActionListener(objButtonHandler);
    createOrderButton.addActionListener(objButtonHandler);
    exitButton.addActionListener(new ButtonHandler());
    removeLastOrderButton.addActionListener(new ButtonHandler(this, orders));

    //For layout purposes, put the buttons in a separate panel
    JPanel fieldsPanel = new JPanel();

    JPanel buttonPanel = new JPanel();
    GridBagLayout gridbag2 = new GridBagLayout();
    buttonPanel.setLayout(gridbag2);
    GridBagConstraints gbc2 = new GridBagConstraints();
    buttonPanel.add(getTotalButton);
    buttonPanel.add(createOrderButton);
    buttonPanel.add(removeLastOrderButton);
    buttonPanel.add(exitButton);
    buttonPanel.add(scroll);
    gbc2.anchor = GridBagConstraints.EAST;
    gbc2.gridx = 0;
    gbc2.gridy = 0;
    gridbag2.setConstraints(createOrderButton, gbc2);
    gbc2.gridx = 1;
    gbc2.gridy = 0;
    gridbag2.setConstraints(getTotalButton, gbc2);
    gbc2.gridx = 2;
    gbc2.gridy = 0;
    gridbag2.setConstraints(removeLastOrderButton, gbc2);
    gbc2.gridx = 3;
    gbc2.gridy = 0;
    gridbag2.setConstraints(exitButton, gbc2);
    gbc2.anchor = GridBagConstraints.WEST;
    gbc2.gridx = 0;
    gbc2.gridy = 1;
    gbc2.fill = GridBagConstraints.BOTH;
    gbc2.gridwidth = 4;
    gbc2.weightx = 4;
    gbc2.weighty = 5;
    gridbag2.setConstraints(scroll, gbc2);

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
    cmbOrderType.addItemListener(
        new ItemChangeListener(fieldsPanel, contentPane, OrderManager.this));
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

  public Order getOrder() {
    return this.uiBuilder.getOrder();
  }

  public Vector<Order> getOrders() {
    return orders;
  }

  public void setUiBuilder(UIBuilder uiBuilder) {
    this.uiBuilder = uiBuilder;
  }

  public void fillTextAreaWithOrders() {
    this.ordersTextArea.setText("");
    AllOrders allOrders = new AllOrders(orders);
    while (allOrders.hasNext()) {
      Order order = allOrders.next();
      this.ordersTextArea.append(order.toString() + "\n");
    }
  }
} // End of class OrderManager

class ButtonHandler implements ActionListener {

  OrderManager objOrderManager;
  private Vector<Order> orders;

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
      objOrderManager.getOrders().addElement(order);
      objOrderManager.setTotalValue(
          " Order Created Successfully");
      this.objOrderManager.fillTextAreaWithOrders();
    }
    if (e.getActionCommand().equals(OrderManager.GET_TOTAL)) {
      AllOrders allOrders = new AllOrders(orders);
      AllOrdersTotalDecorator allOrdersTotalDecorator = new AllOrdersTotalDecorator(allOrders);
      totalResult = new Double(
          allOrdersTotalDecorator.getBigTotal()).toString();
      totalResult = " Orders Total = " + totalResult;
      objOrderManager.setTotalValue(totalResult);
    }
    if (e.getActionCommand().equals(OrderManager.REMOVE_LAST_ORDER)) {
      if (orders.size() > 0) {
        this.orders.remove(orders.size() - 1);
        this.objOrderManager.fillTextAreaWithOrders();
      }
    }
  }

  public ButtonHandler() {
  }

  public ButtonHandler(OrderManager inObjOrderManager, Vector<Order> orders) {
    this.orders = orders;
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
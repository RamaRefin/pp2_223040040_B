import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class SteakOrder extends JFrame {
    private JTextField customerNameField;
    private JTextArea notesArea;
    private JRadioButton smallSize, mediumSize, largeSize;
    private JCheckBox potatowedges, frenchfries;
    private JComboBox<String> foodComboBox;
    private JSpinner quantitySpinner;
    private JSlider donenessSlider;
    private JTable orderTable;
    private DefaultTableModel tableModel;

    public SteakOrder() {
        setTitle("Steak Ordering System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Left Panel (Form Panel)
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margin between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Customer Name Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Customer Name:"), gbc);

        customerNameField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(customerNameField, gbc);

        // Food Selection
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Select Food:"), gbc);

        foodComboBox = new JComboBox<>(new String[]{"Sirloin","Tenderloin","Wagyu","Rib-eye"});
        gbc.gridx = 1;
        formPanel.add(foodComboBox, gbc);

        // Quantity Spinner
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Quantity:"), gbc);

        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1)); // Min: 1, Max: 20, Step: 1
        gbc.gridx = 1;
        formPanel.add(quantitySpinner, gbc);

        // Size Selection
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Size:"), gbc);

        smallSize = new JRadioButton("Small");
        mediumSize = new JRadioButton("Medium");
        largeSize = new JRadioButton("Large");
        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(smallSize);
        sizeGroup.add(mediumSize);
        sizeGroup.add(largeSize);
        smallSize.setSelected(true);

        JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        sizePanel.add(smallSize);
        sizePanel.add(mediumSize);
        sizePanel.add(largeSize);
        gbc.gridx = 1;
        formPanel.add(sizePanel, gbc);

        // Doneness Slider
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Doneness Level:"), gbc);

        donenessSlider = new JSlider(0, 10, 0); // Min: 0, Max: 10, Initial: 0
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel("Rare"));
        labelTable.put(5, new JLabel("Medium Rare"));
        labelTable.put(10, new JLabel("Well Done"));
        donenessSlider.setLabelTable(labelTable);
        donenessSlider.setPaintTicks(true);
        donenessSlider.setPaintLabels(true);
        donenessSlider.setMajorTickSpacing(5);
        gbc.gridx = 1;
        formPanel.add(donenessSlider, gbc);

        // Additional Options
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Add-ons:"), gbc);

        potatowedges = new JCheckBox("Potato Wedges");
        frenchfries = new JCheckBox("French Fries");
        JPanel addonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        addonsPanel.add(potatowedges);
        addonsPanel.add(frenchfries);
        gbc.gridx = 1;
        formPanel.add(addonsPanel, gbc);

        // Notes Area
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Notes:"), gbc);

        notesArea = new JTextArea(3, 15);
        gbc.gridx = 1;
        formPanel.add(new JScrollPane(notesArea), gbc);

        // Submit Button
        JButton submitButton = new JButton("Add Order");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrder();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(submitButton, gbc);

        // Table for Orders (on the right)
        String[] columns = {"Customer Name", "Food", "Quantity", "Size", "Doneness", "Add-ons", "Notes"};
        tableModel = new DefaultTableModel(columns, 0);
        orderTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(orderTable);

        // Set layout for main frame
        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.WEST);      // Form on the left
        add(tableScrollPane, BorderLayout.CENTER); // Table in the center/right
    }

    private void addOrder() {
        String customerName = customerNameField.getText();
        String food = (String) foodComboBox.getSelectedItem();
        int quantity = (int) quantitySpinner.getValue();
        String size = smallSize.isSelected() ? "Small" : mediumSize.isSelected() ? "Medium" : "Large";
        String doneness = donenessSlider.getValue() == 0 ? "Rare" : donenessSlider.getValue() == 5 ? "Medium Rare" : "Well Done";
        String addons = "";
        if (potatowedges.isSelected()) addons += "Potato Wedges ";
        if (frenchfries.isSelected()) addons += "French Fries";
        String notes = notesArea.getText();

        // Add data to the table
        Object[] rowData = {customerName, food, quantity, size, doneness, addons, notes};
        tableModel.addRow(rowData);

        // Clear input fields after adding to the table
        customerNameField.setText("");
        quantitySpinner.setValue(1);
        smallSize.setSelected(true);
        donenessSlider.setValue(0);
        potatowedges.setSelected(false);
        frenchfries.setSelected(false);
        notesArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SteakOrder app = new SteakOrder();
            app.setVisible(true);
        });
    }
}

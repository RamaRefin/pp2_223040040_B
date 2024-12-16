/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
/**
 *
 * @author thega
 */
public class ObatsView extends JFrame {
    private JTable table;
    private JTextField tfNama, tfJenis, tfHarga, tfStok;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh;

    public ObatsView() {
        setTitle("Data Obat Masuk");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Nama:"));
        tfNama = new JTextField();
        inputPanel.add(tfNama);
        inputPanel.add(new JLabel("Jenis Obat:"));
        tfJenis = new JTextField();
        inputPanel.add(tfJenis);
        inputPanel.add(new JLabel("Harga:"));
        tfHarga = new JTextField();
        inputPanel.add(tfHarga);
        inputPanel.add(new JLabel("Stok:"));
        tfStok = new JTextField();
        inputPanel.add(tfStok);
        add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTable getTable() { return table; }
    public JTextField getTfNama() { return tfNama; }
    public JTextField getTfJenis() { return tfJenis; }
    public JTextField getTfHarga() { return tfHarga; }
    public JTextField getTfStok() { return tfStok; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }

}
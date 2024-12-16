/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.GridLayout;
import model.Obat;
import model.ObatMapper;
import view.ObatsView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 *
 * @author thega
 */
public class ObatsController {
    private ObatsView view;
    private ObatMapper mapper;
    
    

    public ObatsController(ObatsView view, ObatMapper mapper) {
        this.view = view;
        this.mapper = mapper;

        view.getBtnAdd().addActionListener(e -> addObat());
        view.getBtnDelete().addActionListener(e -> deleteObat());
        view.getBtnRefresh().addActionListener(e -> loadObats());
        
        view.getBtnUpdate().addActionListener(e -> {
    int selectedRow = view.getTable().getSelectedRow();
    if (selectedRow == -1) {

        return;
    }

    int id = (int) view.getTable().getValueAt(selectedRow, 0);
    String currentName = view.getTable().getValueAt(selectedRow, 1).toString();
    String currentBrand = view.getTable().getValueAt(selectedRow, 2).toString();
    double currentPrice = Double.parseDouble(view.getTable().getValueAt(selectedRow, 3).toString());
    int currentStock = Integer.parseInt(view.getTable().getValueAt(selectedRow, 4).toString());

    JDialog dialog = new JDialog(view, "Update Obat", true);
    dialog.setLayout(new GridLayout(5, 2, 10, 10));
    dialog.setSize(400, 300);

    JTextField tfNama = new JTextField(currentName);
    JTextField tfJenis = new JTextField(currentBrand);
    JTextField tfHarga = new JTextField(String.valueOf(currentPrice));
    JTextField tfStok = new JTextField(String.valueOf(currentStock));
    JButton btnUpdateDialog = new JButton("Update");

    dialog.add(new JLabel("Nama:"));
    dialog.add(tfNama);
    dialog.add(new JLabel("Jenis:"));
    dialog.add(tfJenis);
    dialog.add(new JLabel("Harga:"));
    dialog.add(tfHarga);
    dialog.add(new JLabel("Stock:"));
    dialog.add(tfStok);
    dialog.add(new JLabel()); // untuk jarak
    dialog.add(btnUpdateDialog);

    btnUpdateDialog.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Obat e = new Obat();
            e.setId(id);
            e.setNama(tfNama.getText());
            e.setJenis(tfJenis.getText());
            e.setHarga(Double.parseDouble(tfHarga.getText()));
            e.setStok(Integer.parseInt(tfStok.getText()));
            
            mapper.updateObat(e);
            JOptionPane.showMessageDialog(dialog, "Data berhasil di update");
            dialog.dispose();
            loadObats();
        }
    });
        dialog.setLocationRelativeTo(view);
        dialog.setVisible(true);
    });  
        loadObats();
    }

    private void loadObats() {
        List<Obat> Obats = mapper.getAllObats();
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nama", "Jenis", "Harga", "Stok"}, 0);
        for (Obat e : Obats) {
            model.addRow(new Object[]{e.getId(), e.getNama(), e.getJenis(), e.getHarga(), e.getStok()});
        }
        view.getTable().setModel(model);
    }

    private void addObat() {
        Obat e = new Obat();
        e.setNama(view.getTfNama().getText());
        e.setJenis(view.getTfJenis().getText());
        e.setHarga(Double.parseDouble(view.getTfHarga().getText()));
        e.setStok(Integer.parseInt(view.getTfStok().getText()));
        mapper.insertObat(e);
        loadObats();
        JOptionPane.showMessageDialog(view, "Obat berhasil ditambahkan!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateObat() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Pilih baris yang ingin di update");
            return;
        }
        Obat e = new Obat();
    }

    private void deleteObat() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Pilih baris yang ingin di hapus");
            return;
        }
        
    int confirm = JOptionPane.showConfirmDialog(view, 
            "Apakah yakin ingin menghapus obat ini?", 
            "Konfirmasi Hapus", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);

    if (confirm == JOptionPane.YES_OPTION) {
        int id = (int) view.getTable().getValueAt(selectedRow, 0);
        mapper.deleteObat(id);
        loadObats();
        JOptionPane.showMessageDialog(view, "Obat berhasil dihapus");
        }
    }
}
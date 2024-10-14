package Materi2.Tugas;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class FormBiodata extends JFrame {

    public FormBiodata() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400); // increased size for better layout
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label "Form Biodata" dengan font besar
        JLabel formLabel = new JLabel("Form Biodata");
        formLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(formLabel, gbc);

        // Mengatur kembali gridwidth dan anchor
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Label "Nama"
        JLabel nameLabel = new JLabel("Nama:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(nameLabel, gbc);

        // Text Field Nama
        JTextField nameField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        this.add(nameField, gbc);

        // Label "Nomor HP"
        JLabel phoneLabel = new JLabel("Nomor HP:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.add(phoneLabel, gbc);

        // Text Field Nomor HP
        JTextField phoneField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 4;
        this.add(phoneField, gbc);

        // Label "Jenis Kelamin"
        JLabel genderLabel = new JLabel("Jenis Kelamin:");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(genderLabel, gbc);

        // Radio Button "Laki-Laki"
        JRadioButton maleRadio = new JRadioButton("Laki-Laki");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        this.add(maleRadio, gbc);

        // Radio Button "Perempuan" (di bawah "Laki-Laki")
        JRadioButton femaleRadio = new JRadioButton("Perempuan");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        this.add(femaleRadio, gbc);

        // Grouping Radio Buttons
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Checkbox "Warga Negara Asing" (di bawah RadioButton Perempuan)
        JCheckBox foreignCheckBox = new JCheckBox("Warga Negara Asing");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(foreignCheckBox, gbc);

        // Tombol Simpan (diletakkan di bawah)
        JButton saveButton = new JButton("Simpan");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(saveButton, gbc);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FormBiodata form = new FormBiodata();
                form.setVisible(true);
            }
        });
    }
}

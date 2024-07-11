package com.devyat.inventorysystem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.devyat.inventorysystem.res.ColorPallete;
import com.devyat.inventorysystem.uxElements.RoundedPanel;
import com.devyat.inventorysystem.uxElements.uxcustomJTextField;
import com.devyat.inventorysystem.uxElements.uxJPasswordField;

public class Test {
    private JFrame window;
    private JTextField usernameField; // Variável de instância para o campo de usuário
    private JPasswordField passwordField; // Variável de instância para o campo de senha

    public Test() {
        window = new JFrame("System Inventory");
        window.setSize(1366, 768);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        JPanel uiBackground = new JPanel(new GridBagLayout());
        uiBackground.setBackground(Color.DARK_GRAY);
        window.add(uiBackground, BorderLayout.CENTER);

        JPanel uiLoginPainel = new JPanel(new GridBagLayout());
        uiLoginPainel.setPreferredSize(new Dimension(340, 200));
        uiLoginPainel.setBackground(Color.WHITE);
        uiLoginPainel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER; // Centraliza o painel de login
        uiBackground.add(uiLoginPainel, gbc);

        addComponents(uiLoginPainel);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        uiLoginPainel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (authenticate(usernameField.getText(), new String(passwordField.getPassword()))) {
                    window.setVisible(false);
                    // Coloque aqui a lógica para mostrar o próximo painel ou janela
                } else {
                    JOptionPane.showMessageDialog(window, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void addComponents(JPanel uiLoginPainel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding, distâncias entre itens no layout

        JPanel usernamePanel = createUsernameC();
        addComponent(uiLoginPainel, usernamePanel, gbc, 0, 0);

        JPanel passwordPanel = createPasswordC();
        addComponent(uiLoginPainel, passwordPanel, gbc, 0, 1);
    }

    private void addComponent(JPanel panel, Component component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }

    private RoundedPanel createUsernameC() {
        RoundedPanel usernamePanel = new RoundedPanel(ColorPallete.D_GRAY);
        usernamePanel.setCornerRadius(10, 10, 10, 10);
        JLabel usernameLabel = new JLabel("Username:");
        uxcustomJTextField usernameField = new uxcustomJTextField();
        usernameField.uxJTextFieldsetlineColor(ColorPallete.T_RED);
        usernameField.uxJTextFieldsetlineThickness(0, 0, 2, 0);
        usernameField.uxJTextFieldSetColumns(10);
        usernameField.setBorder(new EmptyBorder(0, 0, 0, 0));
        usernameField.setPreferredSize(new Dimension(100, 25));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        return usernamePanel;
    }

    private RoundedPanel createPasswordC() {
        RoundedPanel passwordPanel = new RoundedPanel(ColorPallete.ALICE_BLUE);
        passwordPanel.setCornerRadius(10, 10, 10, 10);
        JLabel passwordLabel = new JLabel("Password:");
        uxJPasswordField passwordField = new uxJPasswordField();
        passwordField.uxJPasswordFieldsetlineColor(ColorPallete.T_ORANGE);
        passwordField.uxJPasswordFieldsetlineThickness(0, 0, 2, 0);
        passwordField.uxJPasswordldSetColumns(10);
        passwordField.setBorder(new EmptyBorder(0, 0, 0, 0));
        passwordField.setPreferredSize(new Dimension(100, 25));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        return passwordPanel;
    }

    private boolean authenticate(String username, String password) {
        // Lógica de autenticação fictícia
        return "admin".equals(username) && "admin".equals(password);
    }

    public void show() {
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Test launcher = new Test();
            launcher.show();
        });
    }
}

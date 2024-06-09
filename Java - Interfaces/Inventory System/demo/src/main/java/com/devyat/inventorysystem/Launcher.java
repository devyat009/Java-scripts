package com.devyat.inventorysystem;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.devyat.inventorysystem.uxElements.*;
import com.devyat.inventorysystem.res.*;

public class Launcher {
    private JFrame window;
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Launcher() {
        /*
         * The Window itself
         */
        window = new JFrame("Login");
        window.setSize(1000, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        /*
         * Background Panel witch goes on top of the window space
         */
        JPanel uiBackground = new JPanel(new GridBagLayout());
        uiBackground.setBackground(ColorPallete.darkjunglegreen); // Cor de fundo do painel
        window.add(uiBackground, BorderLayout.CENTER);

        /*
         * UI Login Painel, allocates the login options
         */
        RoundedPanel uiLoginPainel = new RoundedPanel(45, 45, 45, 45, ColorPallete.white);
        uiLoginPainel.setPreferredSize(new Dimension(340, 410));
    
        
        
        /*
         * Configuração das restrições para centralizar o painel de login
         */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER; // Centraliza o componente
        uiBackground.add(uiLoginPainel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 30, 80, 25);
        uiLoginPainel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(140, 30, 100, 25);
        uiLoginPainel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 70, 80, 25);
        uiLoginPainel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(140, 70, 100, 25);
        uiLoginPainel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 120, 80, 25);
        uiLoginPainel.add(loginButton);

        // Event listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (authenticate(usernameField.getText(), new String(passwordField.getPassword()))) {
                    window.setVisible(false); // Hide the login window
                    //Main main = new Main(); // Create an instance of the Main class
                    //main.showhomePainel(); // Show the main window
                } else {
                    JOptionPane.showMessageDialog(window, "Login failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean authenticate(String username, String password) {
        // Replace this with your real authentication logic
        return "123".equals(username) && "123".equals(password);
    }

    public void show() {
        window.setVisible(true);
    }

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.show();
    }
}

package com.devyat.inventorysystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import com.devyat.inventorysystem.res.ColorPallete;
import com.devyat.inventorysystem.uxElements.RoundedPanel;

public class Launcher {
    private JFrame window;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Launcher() {
        /*
         * The Window itself
         */
        window = new JFrame("Login");
        window.setSize(1000, 600);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        uiLoginPainel.setLayout(new GridBagLayout());
        
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

        GridBagConstraints uiGbc = new GridBagConstraints();
        uiGbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        uiGbc.gridx = 0;
        uiGbc.gridy = 0;
        uiGbc.anchor = GridBagConstraints.WEST;
        uiLoginPainel.add(usernameLabel, uiGbc);

        // Username Field
        usernameField = new JTextField(10);
        usernameField.setPreferredSize(new Dimension(50, 25)); // Define a preferred size
        uiGbc.gridx = 1;
        uiGbc.gridy = 0;
        uiGbc.fill = GridBagConstraints.NONE; // Prevent horizontal expansion
        uiLoginPainel.add(usernameField, uiGbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        uiGbc.gridx = 0;
        uiGbc.gridy = 1;
        uiGbc.fill = GridBagConstraints.NONE;
        uiLoginPainel.add(passwordLabel, uiGbc);

        // Password Field
        passwordField = new JPasswordField(10);
        passwordField.setPreferredSize(new Dimension(50, 25)); // Define a preferred size
        uiGbc.gridx = 1;
        uiGbc.gridy = 1;
        uiGbc.fill = GridBagConstraints.NONE; // Prevent horizontal expansion
        uiLoginPainel.add(passwordField, uiGbc);

        // Login Button
        uxElements.Hover loginButton = new uxElements.Hover("Login");
        
        loginButton.setPreferredSize(new Dimension(120, 25)); // Define a preferred size
        loginButton.setBackgroundAndForeground(ColorPallete.aliceBlue, ColorPallete.blue);
        loginButton.setHoverBackgroundColor(ColorPallete.tRed); // Background color
        loginButton.setHoverForegroundColor(ColorPallete.blue); // Text Color
        loginButton.setPressedBackgroundColor(Color.PINK); // When button is pressed
        //loginButton.setBorder(BorderFactory.createEmptyBorder());

        loginButton.setCustomBorder(ColorPallete.tPink,30, 30, 50, 10);

        uiGbc.gridx = 0;
        uiGbc.gridy = 2;
        uiGbc.gridwidth = 2;
        uiGbc.fill = GridBagConstraints.NONE;
        uiGbc.anchor = GridBagConstraints.CENTER;
        uiLoginPainel.add(loginButton, uiGbc);

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

package com.devyat.inventorysystem;

import java.awt.BorderLayout;
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
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.EmptyBorder;

import com.devyat.inventorysystem.res.ColorPallete;
import com.devyat.inventorysystem.uxElements.RoundedPanel;
import com.devyat.inventorysystem.uxElements.uxcustomJTextField;

public class Launcher {
    private JFrame window;
    //private JTextField usernameField;
    private uxcustomJTextField usernameField;
    private JPasswordField passwordField;

    public Launcher() {
        /*
         * The Window itself
         */
        window = new JFrame("System Inventory");
        window.setSize(1366, 768);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        /*
         * Background Panel witch goes on top of the window space
         */
        JPanel uiBackground = new JPanel(new GridBagLayout());
        uiBackground.setBackground(ColorPallete.DARK_JUNGLE_GREEN); // Cor de fundo do painel
        window.add(uiBackground, BorderLayout.CENTER);

        /*
         * UI Login Painel, allocates the login options
         */
        RoundedPanel uiLoginPainel = new RoundedPanel(ColorPallete.WHITE);
        uiLoginPainel.setPreferredSize(new Dimension(340, 400));
        uiLoginPainel.setCornerRadius(45, 45, 45, 45);
        uiLoginPainel.setLayout(new GridBagLayout());
        /*
         * Configure the restrictions to centrelize the panel
         */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.CENTER; // Centralize the login panel 
        uiBackground.add(uiLoginPainel, gbc);

        /*
         * The title background of uiLoginPainel
         */
        RoundedPanel uiLoginPainelTitleBackground = new RoundedPanel(ColorPallete.BLUE);
        uiLoginPainelTitleBackground.setPreferredSize(new Dimension(340, 51));
        uiLoginPainelTitleBackground.setCornerRadius(40,40,0,0);
        GridBagConstraints titleGbc = new GridBagConstraints();
        titleGbc.gridx = 0;
        titleGbc.gridy = 0;
        // Add weight to make the panel go to the top of the page
        titleGbc.weighty = 1.0;
        titleGbc.gridwidth = 2; // Make the panel ocupy the entire uiLoginPanel
        titleGbc.fill = GridBagConstraints.HORIZONTAL; // Expand in the horizontal axis
        titleGbc.anchor = GridBagConstraints.NORTH;
        titleGbc.insets = new Insets(0, 0, 0, 0); // Remove any spaces
        uiLoginPainel.add(uiLoginPainelTitleBackground, titleGbc);

        JLabel uiLoginPainelTitle = new JLabel("<html><font size=14><div style='text-align: center; margin-top: 10px;'><b>Login</b></div></font></html>");
        uiLoginPainelTitleBackground.add(uiLoginPainelTitle);

        GridBagConstraints uiGbc = new GridBagConstraints();
        uiGbc.insets = new Insets(10, 10, 10, 10); // Padding
        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        uiGbc.gridx = 0;
        uiGbc.gridy = 1;
        uiGbc.anchor = GridBagConstraints.WEST;
        uiLoginPainel.add(usernameLabel, uiGbc);
        
        uxElements.uxcustomJTextField usernameField = new uxElements.uxcustomJTextField();
        usernameField.uxJTextFieldsetlineColor(ColorPallete.T_RED);
        usernameField.uxJTextFieldsetlineThickness(0, 0, 2, 0);
        usernameField.uxJTextFieldSetColumns(10);
        usernameField.setBorder(new EmptyBorder(0, 0, 0, 0));
        usernameField.setPreferredSize(new Dimension(100, 25));
        uiGbc.gridx = 1;
        uiGbc.gridy = 1;
        uiGbc.fill = GridBagConstraints.NONE; // Prevent horizontal expansion
        uiLoginPainel.add(usernameField, uiGbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        uiGbc.gridx = 0;
        uiGbc.gridy = 2;
        uiGbc.fill = GridBagConstraints.NONE;
        uiLoginPainel.add(passwordLabel, uiGbc);

        uxElements.uxJPasswordField passwordField = new uxElements.uxJPasswordField();
        passwordField.uxJPasswordFieldsetlineColor(ColorPallete.T_ORANGE);
        passwordField.uxJPasswordFieldsetlineThickness(0, 0,2,0);
        passwordField.uxJPasswordldSetColumns(10);
        passwordField.setBorder(new EmptyBorder(0, 0, 0, 0));
        passwordField.setPreferredSize(new Dimension(100, 25));
        uiGbc.gridx = 1;
        uiGbc.gridy = 2;
        uiGbc.fill = GridBagConstraints.NONE; // Prevent horizontal expansion
        uiLoginPainel.add(passwordField, uiGbc);

        // Login Button
        uxElements.Hover loginButton = new uxElements.Hover("<html><font size=4><div style='text-align: center; margin-left: 0px;'><b>Login</b></div></font></html>");
        loginButton.setPreferredSize(new Dimension(120, 25)); // Define a preferred size
        loginButton.setBackgroundAndForeground(ColorPallete.BLUE, ColorPallete.WHITE);
        loginButton.setHoverBackgroundColor(ColorPallete.GBORO); // Background color
        loginButton.setHoverForegroundColor(ColorPallete.T_PINK); // Text Color
        loginButton.setPressedBackgroundColor(ColorPallete.DARK_JUNGLE_GREEN); // When button is pressed
        loginButton.setCornerRadius(5,5,5,5);
        loginButton.setBorder(BorderFactory.createEmptyBorder()); // Remove border lines
        loginButton.setFocusable(false); // Remove the square lines when pressed

        uiGbc.gridx = 0;
        uiGbc.gridy = 3;
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

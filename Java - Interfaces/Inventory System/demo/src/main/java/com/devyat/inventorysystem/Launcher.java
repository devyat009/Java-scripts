package com.devyat.inventorysystem;

import java.awt.BorderLayout;
import java.awt.Component;
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
import com.devyat.inventorysystem.uxElements.uxJPasswordField;
import com.devyat.inventorysystem.uxElements.uxcustomJTextField;

/**
 * The launcher of the program, allocates the login panel.
 * @devyat009
 * @version 1.3
 * @since 1.0
 */
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
        uiBackground.setBackground(ColorPallete.DARK_JUNGLE_GREEN); // Background panel color.
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
    
        // Username and Login fields
        addComponents(uiLoginPainel);

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

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 10;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        uiLoginPainel.add(loginButton, gbc);

        // Event listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (authenticate(usernameField.getText(), new String(passwordField.getPassword()))) {
                    window.setVisible(false);
                    // Logic to the next windown. (WIP)
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
    /**
     * Layout organizer.
     * @param panel
     * @param component
     * @param gbc
     * @param gridx
     * @param gridy
     */
    private void addComponent(JPanel panel, Component component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        panel.add(component, gbc);
    }

    /**
     * Component layout manager.
     * @param panelOfChoice the panel choosed to add the components.
     * @version 1.0
     * @since 
     */
    private void addComponents(JPanel panelOfChoice) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding, distances betwen itens.
        gbc.weightx = 1.0;
        gbc.weighty = 0.0; 

        RoundedPanel usernamePanel = createUsernameC();
        addComponent(panelOfChoice, usernamePanel, gbc, 1, 0);

        RoundedPanel passwordPanel = createPasswordC();
        addComponent(panelOfChoice, passwordPanel, gbc, 1, 1);
    }

    /**
     * Username label and customizable JTextField field.
     * @return usernamePanel - The panel with the itens.
     * @version 1.0 - First design
     */
    private RoundedPanel createUsernameC() {
        RoundedPanel usernamePanel = new RoundedPanel(ColorPallete.D_GRAY);
        usernamePanel.setCornerRadius(10, 10, 10, 10); // Add corners.
        JLabel usernameLabel = new JLabel("Username:");
        uxcustomJTextField usernameField = new uxcustomJTextField();
        usernameField.uxJTextFieldsetlineColor(ColorPallete.T_RED); // Color of the line.
        usernameField.uxJTextFieldsetlineThickness(0, 0, 2, 0); // Witch lines show up.
        usernameField.uxJTextFieldSetColumns(10); // Set space of the text field.
        usernameField.setBorder(new EmptyBorder(0, 0, 0, 0)); // No border.
        usernameField.setPreferredSize(new Dimension(100, 25)); // Set size.
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);
        return usernamePanel;
    }

    /**
     * Password label and customizable PasswordField field.
     * @return passwordPanel - The panel with itens.
     * @version 1.0 - First design.
     */
    private RoundedPanel createPasswordC() {
        RoundedPanel passwordPanel = new RoundedPanel(ColorPallete.ALICE_BLUE);
        passwordPanel.setCornerRadius(10, 10, 10, 10);
        JLabel passwordLabel = new JLabel("Password:");
        uxJPasswordField passwordField = new uxJPasswordField();
        passwordField.uxJPasswordFieldsetlineColor(ColorPallete.T_ORANGE); // Color of the line
        passwordField.uxJPasswordFieldsetlineThickness(0, 0, 2, 0); // Witch lines show up
        passwordField.uxJPasswordldSetColumns(10); // Set space of the text field
        passwordField.setBorder(new EmptyBorder(0, 0, 0, 0)); // No border
        passwordField.setPreferredSize(new Dimension(100, 25)); // Set size
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        return passwordPanel;
    }

    public void show() {
        window.setVisible(true);
    }

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.show();
    }
}

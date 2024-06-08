package src.com.devyat.inventorysystemgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Console;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.com.devyat.inventorysystemgui.res.ConsoleColors;



public class MainWindow extends JFrame {
     
    private JFrame  window;
    private JLabel projectTitle;
    private JPanel toolbarPanel;
    private Point mouseOffset;
    private boolean isDragging = false;
    private boolean isResizing = false;
    private Point resizeStart;
    private Dimension initialSize;
    private static final int RESIZE_BORDER_SIZE = 10;
    Font koho_bold;
    // Font Lemon;
    // Font Magical;

    public MainWindow() {
        initialize();
    }
    public void initialize(){
        try{
            Font kohoBold = CustomFontLoader.loadCustomFont1(24);

        window = new JFrame(); // The window itself
        window.setTitle("Hello World!"); // Windown title bar
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close window on ?
        window.setSize(1000, 700); // Windown Size when oppen
        window.setLocationRelativeTo(null); // Set location to the screen center
        window.setUndecorated(true); // Make the frame undecorated (without title bar)

        toolbarPanel = createToolbarPanel();
        window.getContentPane().add(toolbarPanel, BorderLayout.NORTH);

        toolbarPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isPointInDraggableRegion(e.getPoint())) {
                    mouseOffset = e.getPoint();
                    isDragging = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
            }
        });

        toolbarPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isDragging) {
                    Point newLocation = window.getLocation();
                    newLocation.translate(e.getX() - mouseOffset.x, e.getY() - mouseOffset.y);
                    constrainWindowPosition(newLocation);
                    window.setLocation(newLocation);
                }
            }
        });

        window.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                checkResize(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isResizing = false;
                window.setCursor(Cursor.getDefaultCursor());
            }
        });

        window.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isResizing) {
                    resizeWindow(e.getPoint());
                }
            }
        });



        


        JPanel uiBackground = new JPanel(); // Create a JPanel - Interface background
        uiBackground.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5)); // Flow of the itens added, if is set to LEFT, it will pack all itens to the left when resible
        uiBackground.setBackground(ConsoleColors.aliceBlue); // Set background color
        window.add(uiBackground, BorderLayout.CENTER); // Set background location


        // <!-- Menu da Esquerda -->
        JPanel uiLeftMenu = new JPanel(new GridLayout(12,1)); // Create a JPanel - Left Menu // Using GridLayout you get the buttons one below the other (5 rows  and 1 column)
        uiLeftMenu.setPreferredSize(new Dimension(140, 100)); // Set panel width and height
        uiLeftMenu.setBackground(ConsoleColors.darkjunglegreen); // Set background color
        window.add(uiLeftMenu, BorderLayout.WEST); // Set panel location to Left side

        projectTitle = new JLabel("  PROJECT"); // JLabel nome_função* = new JLabel("TEXTO")
        projectTitle.setForeground(ConsoleColors.white); // Setting the text color
        projectTitle.setFont(kohoBold); // 
        uiLeftMenu.add(projectTitle, BorderLayout.EAST);

        // <!-- Invisible Button -->
        JButton buttonBlank = new JButton(""); // Blank Text, Button used to give space betwen title and button 1
        uiLeftMenu.add(buttonBlank, BorderLayout.CENTER);
        buttonBlank.setBackground(ConsoleColors.darkjunglegreen); buttonBlank.setForeground(ConsoleColors.darkjunglegreen); buttonBlank.setBorder(BorderFactory.createEmptyBorder());
        buttonBlank.setEnabled(false); // Disable the button
        // <!-- First Row Button -->
        HoverButton button1 = new HoverButton("btn test 0"); // JButton Title  
        button1.setFont(kohoBold);
        uiLeftMenu.add(button1, BorderLayout.CENTER); // Add JButton and position
        button1.setBorder(BorderFactory.createEmptyBorder()); //Set an empty border for the JButon.
        button1.setBackgroundAndForeground(ConsoleColors.darkjunglegreen, ConsoleColors.white);
        button1.setHoverBackgroundColor(ConsoleColors.white);
        button1.setHoverForegroundColor(ConsoleColors.blue); 
        button1.setPressedBackgroundColor(ConsoleColors.white);

        // <!-- Second Row Button -->
        HoverButton button2 = new HoverButton("btn test 1");
        button2.setFont(kohoBold);
        uiLeftMenu.add(button2);
        button2.setBackgroundAndForeground(ConsoleColors.darkjunglegreen, ConsoleColors.white);
        button2.setBorder(BorderFactory.createEmptyBorder());
        button2.setHoverBackgroundColor(ConsoleColors.white);
        button2.setHoverForegroundColor(ConsoleColors.blue);
        button2.setPressedBackgroundColor(ConsoleColors.white);

        // <!-- Third Row Button -->
        HoverButton button3 = new HoverButton("btn test 2");
        button3.setFont(kohoBold);
        uiLeftMenu.add(button3);
        button3.setBackgroundAndForeground(ConsoleColors.darkjunglegreen, ConsoleColors.white);
        button3.setBorder(BorderFactory.createEmptyBorder());
        button3.setHoverBackgroundColor(ConsoleColors.white);
        button3.setHoverForegroundColor(ConsoleColors.blue);
        button3.setPressedBackgroundColor(ConsoleColors.white);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void show() { // Can be called on the laucher.java
        window.setVisible(true); // Make a visible windown
    }
   
    private JPanel createToolbarPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ConsoleColors.aliceBlue);


        HoverButton minimizeButton = new HoverButton("━");
        HoverButton maximizeButton = new HoverButton("🗖");
        HoverButton closeButton = new HoverButton("✖");
        JButton title = new JButton("Hello World");

        title.setBackground(ConsoleColors.darkjunglegreen);
        title.setForeground(ConsoleColors.white);
        title.setBorder(BorderFactory.createEmptyBorder());
        title.setFocusPainted(false);
        title.setContentAreaFilled(false);
        title.setPreferredSize(new Dimension(140,30));

        // Configure closeButton Proprieties 
        closeButton.setBackgroundAndForeground(ConsoleColors.lightpink, ConsoleColors.white);
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setHoverBackgroundColor((ConsoleColors.redGL));
        closeButton.setHoverForegroundColor(ConsoleColors.white);
        closeButton.setPressedBackgroundColor(ConsoleColors.redGL);
        closeButton.setPreferredSize(new Dimension(42, 30));
        // Configure minimizeButton Proprieties 
        minimizeButton.setBackgroundAndForeground(ConsoleColors.aliceBlue, ConsoleColors.silver);
        minimizeButton.setBorder(BorderFactory.createEmptyBorder());
        minimizeButton.setHoverBackgroundColor((ConsoleColors.gboro));
        minimizeButton.setHoverForegroundColor(ConsoleColors.smokyblack);
        //minimizeButton.setPressedBackgroundColor(ConsoleColors.gboro);
        minimizeButton.setPreferredSize(new Dimension(42, 30));
        // Configure maximizeButton Proprieties 
        maximizeButton.setBackgroundAndForeground(ConsoleColors.aliceBlue, ConsoleColors.silver);
        maximizeButton.setBorder(BorderFactory.createEmptyBorder());
        maximizeButton.setHoverBackgroundColor((ConsoleColors.gboro));
        maximizeButton.setHoverForegroundColor(ConsoleColors.smokyblack);
        //maximizeButton.setPressedBackgroundColor(ConsoleColors.gboro);
        maximizeButton.setPreferredSize(new Dimension(42, 30));



        JButton[] buttons = {minimizeButton, maximizeButton, closeButton};
        //for (JButton button : buttons) {
            //button.setFocusPainted(false);
            //button.setForeground(Color.WHITE);
            //button.setPreferredSize(new Dimension(40, 30));
            //button.setBorder(BorderFactory.createEmptyBorder());
            //button.setContentAreaFilled(false);
            //closeButton.setPressedBackgroundColor(ConsoleColors.testred);
        //}
        

        // Add buttons to a panel with FlowLayout.RIGHT
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        for (JButton button : buttons) {
            buttonPanel.add(button);
        }

        minimizeButton.addActionListener(e -> window.setState(Frame.ICONIFIED));
        maximizeButton.addActionListener(e -> toggleMaximize());
        closeButton.addActionListener(e -> window.dispose());

        // Add title label to a panel with FlowLayout.LEFT
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        titlePanel.add(title);

        // Add title panel and button panel to the main panel
        panel.add(titlePanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);
        

        return panel;
    }

    private void toggleMaximize() {
        int state = window.getExtendedState();
        if ((state & Frame.MAXIMIZED_BOTH) == 0) {
            window.setExtendedState(state | Frame.MAXIMIZED_BOTH);
        } else {
            window.setExtendedState(state & ~Frame.MAXIMIZED_BOTH);
        }
    }

    private boolean isPointInDraggableRegion(Point point) {
        return point.y < toolbarPanel.getHeight(); // Only allow dragging within the toolbar panel
    }

    private void checkResize(Point point) {
        Dimension size = window.getSize();
        Insets insets = window.getInsets();
        int x = size.width - insets.right;
        int y = size.height - insets.bottom;

        Rectangle rightResizeArea = new Rectangle(x - RESIZE_BORDER_SIZE, 0, RESIZE_BORDER_SIZE, size.height);
        Rectangle bottomResizeArea = new Rectangle(0, y - RESIZE_BORDER_SIZE, size.width, RESIZE_BORDER_SIZE);
        Rectangle cornerResizeArea = new Rectangle(x - RESIZE_BORDER_SIZE, y - RESIZE_BORDER_SIZE, RESIZE_BORDER_SIZE, RESIZE_BORDER_SIZE);

        if (rightResizeArea.contains(point) || bottomResizeArea.contains(point) || cornerResizeArea.contains(point)) {
            isResizing = true;
            resizeStart = point;
            initialSize = window.getSize();
            if (rightResizeArea.contains(point)) {
                window.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
            } else if (bottomResizeArea.contains(point)) {
                window.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
            } else if (cornerResizeArea.contains(point)) {
                window.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
            }
        } else {
            isResizing = false;
            window.setCursor(Cursor.getDefaultCursor());
        }
    }

    private void resizeWindow(Point point) {
        int deltaX = point.x - resizeStart.x;
        int deltaY = point.y - resizeStart.y;

        int newWidth = Math.max(initialSize.width + deltaX, window.getMinimumSize().width);
        int newHeight = Math.max(initialSize.height + deltaY, window.getMinimumSize().height);

        window.setSize(newWidth, newHeight);
    }

    private void constrainWindowPosition(Point newLocation) {
        Rectangle screenBounds = getScreenBounds();

        // Adjust the newLocation to keep the window within the screen bounds
        int newX = Math.max(screenBounds.x, Math.min(newLocation.x, screenBounds.x + screenBounds.width - window.getWidth()));
        int newY = Math.max(screenBounds.y, Math.min(newLocation.y, screenBounds.y + screenBounds.height - window.getHeight()));

        newLocation.setLocation(newX, newY);
    }

    private Rectangle getScreenBounds() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();
        Rectangle bounds = new Rectangle();

        for (GraphicsDevice screen : screens) {
            bounds = bounds.union(screen.getDefaultConfiguration().getBounds());
        }

        return bounds;
    }
}

class HoverButton extends JButton {
    private static HoverButton currentPressedButton;
    
    private Color hoverBackgroundColor;
    private Color hoverForegroundColor;
    private Color originalBackground;
    private Color originalForeground;
    private Color pressedBackgroundColor;
    private boolean pressed;

    public HoverButton(String text) {
        super(text);

        // Initialize original colors to defaults
        originalBackground = getBackground();
        originalForeground = getForeground();
        pressed = false;

        // Remove focus border
        setFocusPainted(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If another button is already pressed, return
                if (currentPressedButton != null && currentPressedButton != HoverButton.this) {
                    return;
                }

                // Toggle pressed state
                pressed = !pressed;
                if (pressed) {
                    setBackground(pressedBackgroundColor); // Change color when pressed
                    currentPressedButton = HoverButton.this; // Set current pressed button
                } else {
                    setBackground(originalBackground); // Revert to original color
                    currentPressedButton = null; // Unset current pressed button
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackgroundColor);
                setForeground(hoverForegroundColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!pressed) {
                    setBackground(originalBackground);
                    setForeground(originalForeground);
                }
            }
        });
    }

    public void setHoverBackgroundColor(Color color) {
        hoverBackgroundColor = color;
    }

    public void setHoverForegroundColor(Color color) {
        hoverForegroundColor = color;
    }

    public void setBackgroundAndForeground(Color background, Color foreground) {
        originalBackground = background;
        originalForeground = foreground;
        setBackground(background);
        setForeground(foreground);
    }

    public void setPressedBackgroundColor(Color color) {
        pressedBackgroundColor = color;
    }
}



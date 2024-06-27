package com.devyat.inventorysystem;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

/**
 * uxElements is a class for alocate many custom ux codes for JAVA
 *  * <p>Changelog:
 * <ul>
 *   <li>Version 1.0 (2024-06-05): Initial release</li>
 *   <li>Version 1.1 (2024-06-10):
 *       <ul>
 *           <li>Added a way to make rounded JButtons</li>
 *           <li>Fixed the code logic for rounded corners</li>
 *       </ul>
 *   </li>
 * </ul>
 * @devyat009
 * @version 1.1
 * @since 1.0
 * PT-BR | Classe responsável por abrigar códigos relacionados à customização de interface
 * EN    | This class is responsible to have some code related to interface customization
 */
// I don't care about the CamelCase :) and doest need add a private constructor because the code serves the purpose to drawn interfaces.
@SuppressWarnings({"java:S101", "java:S1118"}) 
public class uxElements {

    /**
    * Rounded corner borders
    * 
    * Example usage:
    * <pre>{@code
    * RoundedPanel panel = new RoundedPanel(Color.PINK);
    * panel.setCornerRadius(20,20,0,0) // Only top corners rounded
    * }</pre>
    */
    static class RoundedPanel extends JPanel {
        private final Color backgroundColor;
        private double cornerRadiusTopLeft;
        private double cornerRadiusTopRight;
        private double cornerRadiusBottomLeft;
        private double cornerRadiusBottomRight;
        
        /**
        * Constructor to create a panel with rounded corners.
        * 
        * @param topLeft the radius of the top left corner
        * @param topRight the radius of the top right corner
        * @param bottomLeft the radius of the bottom left corner
        * @param bottomRight the radius of the bottom right corner
        * @param bgColor the background color of the panel
        */
        public RoundedPanel(Color bgColor) {
            backgroundColor = bgColor;
            setOpaque(false);
        }

        public void setCornerRadius(double topLeft, double topRight, double bottomLeft, double bottomRight) {
            cornerRadiusTopLeft = topLeft;
            cornerRadiusTopRight = topRight;
            cornerRadiusBottomLeft = bottomLeft;
            cornerRadiusBottomRight = bottomRight;
            repaint(); // Re drawn the panel to reflect the radius change on borders
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Drawn the panel with rounded cornes with the background color
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
                GeneralPath path = new GeneralPath();
                // Move to topLeft and drawn a quadTo
                path.moveTo(0, cornerRadiusTopLeft);
                path.quadTo(0, 0, cornerRadiusTopLeft, 0);
                // Drawn line to topLeft
                path.lineTo(width - cornerRadiusTopRight, 0);
                path.quadTo(width, 0, width, cornerRadiusTopRight);
                // Drawn line to bottom right
                path.lineTo(width, height - cornerRadiusBottomRight);
                path.quadTo(width, height, width - cornerRadiusBottomRight, height);
                // Drawn Line to bottom Left
                path.lineTo(cornerRadiusBottomLeft, height);
                path.quadTo(0, height, 0, height - cornerRadiusBottomLeft);
                path.closePath();
                graphics.fill(path);
            }
        }
    }

    /**
    * Custom rounded corner borders
    */
    static class RoundedBorder extends AbstractBorder {
        private final Color borderColor;
        private final int thickness;
        private final int cornerRadiusTopLeft;
        private final int cornerRadiusTopRight;
        private final int cornerRadiusBottomLeft;
        private final int cornerRadiusBottomRight;

        /**
        * Constructor to create a custom border with rounded corners.
        * 
        * @param borderColor the color of the border
        * @param thickness the thickness of the border
        * @param cornerRadiusTopLeft the radius of the top left corner
        * @param cornerRadiusTopRight the radius of the top right corner
        * @param cornerRadiusBottomLeft the radius of the bottom left corner
        * @param cornerRadiusBottomRight the radius of the bottom right corner
        */
        public RoundedBorder(Color borderColor, int thickness, int cornerRadiusTopLeft, int cornerRadiusTopRight, int cornerRadiusBottomLeft, int cornerRadiusBottomRight) {
            this.borderColor = borderColor;
            this.thickness = thickness;
            this.cornerRadiusTopLeft = cornerRadiusTopLeft;
            this.cornerRadiusTopRight = cornerRadiusTopRight;
            this.cornerRadiusBottomLeft = cornerRadiusBottomLeft;
            this.cornerRadiusBottomRight = cornerRadiusBottomRight;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(thickness));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw top-left corner
            g2.drawArc(x, y, 2 * cornerRadiusTopLeft, 2 * cornerRadiusTopLeft, 90, 90);

            // Draw top-right corner
            g2.drawArc(x + width - 2 * cornerRadiusTopRight, y, 2 * cornerRadiusTopRight, 2 * cornerRadiusTopRight, 0, 90);

            // Draw bottom-left corner
            g2.drawArc(x, y + height - 2 * cornerRadiusBottomLeft, 2 * cornerRadiusBottomLeft, 2 * cornerRadiusBottomLeft, 180, 90);

            // Draw bottom-right corner
            g2.drawArc(x + width - 2 * cornerRadiusBottomRight, y + height - 2 * cornerRadiusBottomRight, 2 * cornerRadiusBottomRight, 2 * cornerRadiusBottomRight, -90, 90);

            // Draw top and bottom lines
            g2.drawLine(x + cornerRadiusTopLeft, y, x + width - cornerRadiusTopRight, y);
            g2.drawLine(x + cornerRadiusBottomLeft, y + height, x + width - cornerRadiusBottomRight, y + height);

            // Draw left and right lines
            g2.drawLine(x, y + cornerRadiusTopLeft, x, y + height - cornerRadiusBottomLeft);
            g2.drawLine(x + width, y + cornerRadiusTopRight, x + width, y + height - cornerRadiusBottomRight);

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(thickness + cornerRadiusTopLeft, thickness + cornerRadiusTopLeft, thickness + cornerRadiusBottomLeft, thickness + cornerRadiusBottomRight);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }

    
    /**
    * Hover button style with rounded corners.
    * 
    * Example usage:
    * <pre>{@code
    * uxElements.Hover buttonName = new uxElements.Hover("buttonNameExample");
    * buttonName.setBackgroundAndForeground(ConsoleColors.darkjunglegreen, ConsoleColors.white);
    * buttonName.setHoverBackgroundColor(ConsoleColors.white);
    * buttonName.setHoverForegroundColor(Color.BLUE);
    * buttonName.setPressedBackgroundColor(Color.WHITE);
    * buttonName.setCornerRadius(20,20,20,20); // topLeft, topRight, bottomLeft, bottomRight
    * }</pre>
    * 
    * @version 1.0
    * @since 1.0
    */
    static class Hover extends JButton {
        private Color hoverBackgroundColor;
        private Color pressedBackgroundColor;
        private Color hoverForegroundColor;
        private Color backgroundColor;
        private Color originalForegroundColor;
        private double cornerRadiusTopLeft;
        private double cornerRadiusTopRight;
        private double cornerRadiusBottomLeft;
        private double cornerRadiusBottomRight;
        
    
        /**
        * Constructor that accepts a String argument for the button text.
        * 
        * @param text the text displayed on the button
        */
        public Hover(String text) {
            super(text);
            super.setContentAreaFilled(false); // Prevent the default content area from being filled
        }
        /**
        * Sets the corner radius for each corner of the button.
        *
        * @param topLeft the radius for the top-left corner
        * @param topRight the radius for the top-right corner
        * @param bottomLeft the radius for the bottom-left corner
        * @param bottomRight the radius for the bottom-right corner
        */
        public void setCornerRadius(double topLeft, double topRight, double bottomLeft, double bottomRight) {
            this.cornerRadiusTopLeft = topLeft;
            this.cornerRadiusTopRight = topRight;
            this.cornerRadiusBottomLeft = bottomLeft;
            this.cornerRadiusBottomRight = bottomRight;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Set the background color based on the button's state
            if (getModel().isPressed()) {
                g2.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g2.setColor(hoverBackgroundColor);
                setForeground(hoverForegroundColor);
            } else {
                g2.setColor(backgroundColor != null ? backgroundColor : getBackground());
                setForeground(originalForegroundColor);
            }

            // Draw a custom rounded rectangle with different corner radii
            int width = getWidth();
            int height = getHeight();
            GeneralPath path = new GeneralPath();
            path.moveTo(0, cornerRadiusTopLeft);
            path.quadTo(0, 0, cornerRadiusTopLeft, 0);
            path.lineTo(width - cornerRadiusTopRight, 0);
            path.quadTo(width, 0, width, cornerRadiusTopRight);
            path.lineTo(width, height - cornerRadiusBottomRight);
            path.quadTo(width, height, width - cornerRadiusBottomRight, height);
            path.lineTo(cornerRadiusBottomLeft, height);
            path.quadTo(0, height, 0, height - cornerRadiusBottomLeft);
            path.closePath();
            
            g2.fill(path);

            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        public void setContentAreaFilled(boolean b) {
            // This method is intentionally left empty to prevent the default content area from being filled.
            // This allows the paintComponent method to handle all painting operations for custom backgrounds.
        }
        /**
        * Sets the background and foreground colors of the button.
        * 
        * @param background the background color
        * @param foreground the foreground (text) color
        */
        public void setBackgroundAndForeground(Color background, Color foreground) {
            setBackground(background);
            setForeground(foreground);
            originalForegroundColor = foreground; // Save for return the original color
        }
        /**
        * Gets the hover background color of the button.
        * 
        * @return the hover background color
        */
        public Color getHoverBackgroundColor() {
            return hoverBackgroundColor;
        }
        /**
        * Sets the hover background color of the button.
        * 
        * @param hoverBackgroundColor the hover background color
        */
        public void setHoverBackgroundColor(Color hoverBackgroundColor) {
            this.hoverBackgroundColor = hoverBackgroundColor;
        }
        /**
        * Gets the pressed background color of the button.
        * 
        * @return the pressed background color
        */
        public Color getPressedBackgroundColor() {
            return pressedBackgroundColor;
        }
        /**
        * Sets the pressed background color of the button.
        * 
        * @param pressedBackgroundColor the pressed background color
        */
        public void setPressedBackgroundColor(Color pressedBackgroundColor) {
            this.pressedBackgroundColor = pressedBackgroundColor;
        }
        /**
        * Gets the hover foreground color of the button.
        * 
        * @return the hover foreground color
        */
        public Color getHoverForegroundColor() {
            return hoverForegroundColor;
        }
        /**
        * Sets the hover foreground color of the button.
        * 
        * @param hoverForegroundColor the hover foreground color
        */
        public void setHoverForegroundColor(Color hoverForegroundColor) {
            this.hoverForegroundColor = hoverForegroundColor;
        }
        /**
         * Sets a custom border with rounded corners for the button.
         * 
         * @param borderColor the color of the border
         * @param topLeft the radius for the top-left corner of the border
         * @param topRight the radius for the top-right corner of the border
         * @param bottomLeft the radius for the bottom-left corner of the border
         * @param bottomRight the radius for the bottom-right corner of the border
         * @version 1.0
         * @since 1.0
         */
        public void setCustomBorder(Color borderColor, int topLeft, int topRight, int bottomLeft, int bottomRight) {
            Border customBorder = new CustomBorder(borderColor, 2, topLeft, topRight, bottomLeft, bottomRight);
            setBorder(customBorder);
        }
        
        /**
        * A custom border class with rounded corners.
        * @devyat009
        * @version 1.0
        * @since 1.0
        */ 
        static class CustomBorder extends AbstractBorder {
            private final Color borderColor;
            private final int thickness;
            private final int cornerRadiusTopLeft;
            private final int cornerRadiusTopRight;
            private final int cornerRadiusBottomLeft;
            private final int cornerRadiusBottomRight;
            /**
            * Constructor for creating a custom border with rounded corners.
            * 
            * @param borderColor the color of the border
            * @param thickness the thickness of the border
            * @param cornerRadiusTopLeft the radius for the top-left corner of the border
            * @param cornerRadiusTopRight the radius for the top-right corner of the border
            * @param cornerRadiusBottomLeft the radius for the bottom-left corner of the border
            * @param cornerRadiusBottomRight the radius for the bottom-right corner of the border
            */
            public CustomBorder(Color borderColor, int thickness, int cornerRadiusTopLeft, int cornerRadiusTopRight, int cornerRadiusBottomLeft, int cornerRadiusBottomRight) {
                this.borderColor = borderColor;
                this.thickness = thickness;
                this.cornerRadiusTopLeft = cornerRadiusTopLeft;
                this.cornerRadiusTopRight = cornerRadiusTopRight;
                this.cornerRadiusBottomLeft = cornerRadiusBottomLeft;
                this.cornerRadiusBottomRight = cornerRadiusBottomRight;
            }
    
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
        
                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(thickness));
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
                // Draw top-left corner
                g2.drawArc(x, y, 2 * cornerRadiusTopLeft, 2 * cornerRadiusTopLeft, 90, 90);
        
                // Draw top-right corner
                g2.drawArc(x + width - 2 * cornerRadiusTopRight, y, 2 * cornerRadiusTopRight, 2 * cornerRadiusTopRight, 0, 90);
        
                // Draw bottom-left corner
                g2.drawArc(x, y + height - 2 * cornerRadiusBottomLeft, 2 * cornerRadiusBottomLeft, 2 * cornerRadiusBottomLeft, 180, 90);
        
                // Draw bottom-right corner
                g2.drawArc(x + width - 2 * cornerRadiusBottomRight, y + height - 2 * cornerRadiusBottomRight, 2 * cornerRadiusBottomRight, 2 * cornerRadiusBottomRight, -90, 90);
        
                // Draw top and bottom lines
                g2.drawLine(x + cornerRadiusTopLeft, y, x + width - cornerRadiusTopRight, y);
                g2.drawLine(x + cornerRadiusBottomLeft, y + height, x + width - cornerRadiusBottomRight, y + height);
        
                // Draw left and right lines
                g2.drawLine(x, y + cornerRadiusTopLeft, x, y + height - cornerRadiusBottomLeft);
                g2.drawLine(x + width, y + cornerRadiusTopRight, x + width, y + height - cornerRadiusBottomRight);
        
                g2.dispose();
            }
    
            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(thickness + cornerRadiusTopLeft, thickness + cornerRadiusTopLeft, thickness + cornerRadiusBottomLeft, thickness + cornerRadiusBottomRight);
            }
    
            @Override
            public boolean isBorderOpaque() {
                return false;
            }
        }
    }

    /**
     * Resposible to custumize the JTextField with borders thickness and colors.
     */
    static class uxcustomJTextField extends JTextField {
        private Color lineColor = Color.BLACK;
        private int topThickness = 0;
        private int leftThickness = 0;
        private int bottomThickness = 0;
        private int rightThickness = 0;
        
        public void uxJTextFieldsetlineThickness(int top, int left, int bottom, int right) {
            this.topThickness = top;
            this.leftThickness = left;
            this.bottomThickness = bottom;
            this.rightThickness = right;
            repaint(); // Used to repaint this component, if don't use it will not reflect any change.
        }

        public void uxJTextFieldsetlineColor(Color lineColor) {
            this.lineColor = lineColor;
            repaint();
        }

        public void uxJTextFieldSetColumns(int columns) {
            FontMetrics fm = getFontMetrics(getFont());
            int charWidth = fm.charWidth('W'); // Largura aproximada de um caractere 'W'
            int fieldWidth = charWidth * columns + 6; // Adicionar uma margem para espaço extra
            setPreferredSize(new Dimension(fieldWidth, getPreferredSize().height));
        }

        /**
         * getInsets to change the Caret (| writing cursor), move 2 pixels away from the lines.
        */
        @Override
        public Insets getInsets() {
            return new Insets(topThickness+2, leftThickness+2, bottomThickness+2, rightThickness+2);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(lineColor);

            // Top border
            if (topThickness > 0) {
                g2.fillRect(0, 0, getWidth(), topThickness);
            }

            // Left border
            if (leftThickness > 0) {
                g2.fillRect(0, 0, leftThickness, getHeight());
            }

            // Bottom border
            if (bottomThickness > 0) {
                g2.fillRect(0, getHeight() - bottomThickness, getWidth(), bottomThickness);
            }

            // Right border
            if (rightThickness > 0) {
                g2.fillRect(getWidth() - rightThickness, 0, rightThickness, getHeight());
            }

            g2.dispose();
        }
    }

    /*
     * Resposible to custumize the JPasswordField with borders thickness and colors.
     */
    static class uxJPasswordField extends JPasswordField {
        private Color lineColor = Color.BLACK;
        private int topThickness = 0;
        private int leftThickness = 0;
        private int bottomThickness = 0;
        private int rightThickness = 0;

        public void uxJPasswordFieldsetlineThickness(int top, int left, int bottom, int right) {
            this.topThickness = top;
            this.leftThickness = left;
            this.bottomThickness = bottom;
            this.rightThickness = right;
            repaint(); // Used to repaint this component, if don't use it will not reflect any change.
        }

        public void uxJPasswordFieldsetlineColor(Color lineColor) {
            this.lineColor = lineColor;
            repaint();
        }

        public void uxJPasswordldSetColumns(int columns) {
            FontMetrics fm = getFontMetrics(getFont());
            int charWidth = fm.charWidth('W'); // Largura aproximada de um caractere 'W'
            int fieldWidth = charWidth * columns + 6; // Adicionar uma margem para espaço extra
            setPreferredSize(new Dimension(fieldWidth, getPreferredSize().height));
        }

        /**
         * getInsets to change the Caret (| writing cursor), move 2 pixels away from the lines.
        */
        @Override
        public Insets getInsets() {
            return new Insets(topThickness+2, leftThickness+2, bottomThickness+2, rightThickness+2);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(lineColor);

            // Top border
            if (topThickness > 0) {
                g2.fillRect(0, 0, getWidth(), topThickness);
            }

            // Left border
            if (leftThickness > 0) {
                g2.fillRect(0, 0, leftThickness, getHeight());
            }

            // Bottom border
            if (bottomThickness > 0) {
                g2.fillRect(0, getHeight() - bottomThickness, getWidth(), bottomThickness);
            }

            // Right border
            if (rightThickness > 0) {
                g2.fillRect(getWidth() - rightThickness, 0, rightThickness, getHeight());
            }

            g2.dispose();
        }
    }
}

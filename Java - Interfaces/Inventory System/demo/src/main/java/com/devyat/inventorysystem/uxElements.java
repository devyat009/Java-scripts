package com.devyat.inventorysystem;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JPanel;
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
// I don't care about the CamelCase :) and doest need add a private constructor because the code servers the purpose to drawn interfaces.
@SuppressWarnings({"java:S101", "java:S1118"}) 
public class uxElements {

    /**
    * Rounded corner borders
    * 
    * Example usage:
    * <pre>{@code
    * RoundedPanel panel = new RoundedPanel(20, 20, 20, 20, Color.PINK);
    * }</pre>
    */
    static class RoundedPanel extends JPanel {
        private Color backgroundColor;
        private int cornerRadiusTopLeft;
        
        /**
        * Constructor to create a panel with rounded corners.
        * 
        * @param topLeft the radius of the top left corner
        * @param topRight the radius of the top right corner
        * @param bottomLeft the radius of the bottom left corner
        * @param bottomRight the radius of the bottom right corner
        * @param bgColor the background color of the panel
        */
        public RoundedPanel(int topLeft, int topRight, int bottomLeft, int bottomRight, Color bgColor) {
            cornerRadiusTopLeft = topLeft;
            backgroundColor = bgColor;
            setOpaque(false);
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
            // Draws the rounded panel with background color.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
                graphics.fillRoundRect(0, 0, width, height, cornerRadiusTopLeft, cornerRadiusTopLeft);
            }
        }
    }

    /**
    * Custom rounded corner borders
    */
    static class RoundedBorder extends AbstractBorder {
        private Color borderColor;
        private int thickness;
        private int cornerRadiusTopLeft;
        private int cornerRadiusTopRight;
        private int cornerRadiusBottomLeft;
        private int cornerRadiusBottomRight;

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
    * uxElements.Hover buttonName = new uxElements.Hover("buttonNameExample", 20, 20, 20, 20, Color.PINK);
    * buttonName.setBackgroundAndForeground(ConsoleColors.darkjunglegreen, ConsoleColors.white);
    * buttonName.setHoverBackgroundColor(ConsoleColors.white);
    * buttonName.setHoverForegroundColor(Color.BLUE);
    * buttonName.setPressedBackgroundColor(Color.WHITE);
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
        private int cornerRadiusTopLeft;
        private int cornerRadiusTopRight;
        private int cornerRadiusBottomLeft;
        private int cornerRadiusBottomRight;
        
    
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
        * Constructor for creating a button with rounded corners.
        * 
        * @param text the text displayed on the button
        * @param topLeft the radius for the top-left corner of the button
        * @param topRight the radius for the top-right corner of the button
        * @param bottomLeft the radius for the bottom-left corner of the button
        * @param bottomRight the radius for the bottom-right corner of the button
        * @param bgColor the background color of the button
        */
        public Hover(String text, int topLeft, int topRight, int bottomLeft, int bottomRight, Color bgColor) {
            this(text);
            this.cornerRadiusTopLeft = topLeft;
            this.cornerRadiusTopRight = topRight;
            this.cornerRadiusBottomLeft = bottomLeft;
            this.cornerRadiusBottomRight = bottomRight;
            this.backgroundColor = bgColor;
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
                setForeground(getForeground());
            }

            // Draw a custom rounded rectangle with different corner radii
            int width = getWidth();
            int height = getHeight();
            g2.fillRoundRect(0, 0, width, height, cornerRadiusTopLeft, cornerRadiusTopLeft); // Top left corner
            g2.fillRoundRect(0, 0, width, height, cornerRadiusTopRight, cornerRadiusTopRight); // Top right corner
            g2.fillRoundRect(0, 0, width, height, cornerRadiusBottomLeft, cornerRadiusBottomLeft); // Bottom left corner
            g2.fillRoundRect(0, 0, width, height, cornerRadiusBottomRight, cornerRadiusBottomRight); // Bottom right corner

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
}

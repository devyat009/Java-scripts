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
 * @author devyat009
 * @version 1.0
 * @since 1.0
 * PT-BR | Classe responsável por abrigar códigos relacionados à customização de interface
 * EN    | This class is responsible to have some code related to interface customization
 */
public class uxElements {

    /**
     * PT-BR | Bordas arredondadas
     * EN    | Rounded corner borders | exemple.se
     */
    static class RoundedPanel extends JPanel {
        private Color backgroundColor;
        private int cornerRadiusTopLeft;
        private int cornerRadiusTopRight;
        private int cornerRadiusBottomLeft;
        private int cornerRadiusBottomRight;
    
        public RoundedPanel(int topLeft, int topRight, int bottomLeft, int bottomRight, Color bgColor) {
            cornerRadiusTopLeft = topLeft;
            cornerRadiusTopRight = topRight;
            cornerRadiusBottomLeft = bottomLeft;
            cornerRadiusBottomRight = bottomRight;
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
     * PT-BR | Bordas arredondadas personalizadas
     * EN    | Custom rounded corner borders
     */
    static class RoundedBorder extends AbstractBorder {
        private Color borderColor;
        private int thickness;
        private int cornerRadiusTopLeft;
        private int cornerRadiusTopRight;
        private int cornerRadiusBottomLeft;
        private int cornerRadiusBottomRight;

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

    
    /*
     * Hover button style
     * The use:
        Hoverbutton buttonName = new Hoverbutton("buttonNameExemple")
        buttonName.setBackgroundAndForeground(ConsoleColors.darkjunglegreen, ConsoleColors.white); // Principal button Text and Background colors
        buttonName.setHoverBackgroundColor(ConsoleColors.white); // Background color
        buttonName.setHoverForegroundColor(Color.BLUE); // Text Color
        buttonName.setPressedBackgroundColor(Color.WHITE); // When button is pressed
     */
    static class Hover extends JButton {

        private Color hoverBackgroundColor;
        private Color pressedBackgroundColor;
        private Color hoverForegroundColor;
        private Border customBorder;

        // Constructor that accepts a String argument for the button text
        public Hover(String text) {
            super(text);
            super.setContentAreaFilled(false); // Prevent the default content area from being filled
        }

        /**
         * Overrides the paintComponent method to customize the button's appearance.
         * Depending on the button's state (pressed, hover, or default), it sets the background color.
         * @param g the Graphics object used for painting
         */
        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isPressed()) {
                g.setColor(pressedBackgroundColor);
            } else if (getModel().isRollover()) {
                g.setColor(hoverBackgroundColor);
                setForeground(hoverForegroundColor);
            } else {
                g.setColor(getBackground());
                setForeground(getForeground());
            }
            g.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
        }

        /**
         * Overrides the setContentAreaFilled method to do nothing.
         * This prevents the button's default content area from being filled, allowing paintComponent to handle all painting.
         * @param b ignored boolean parameter
         */
        @Override
        public void setContentAreaFilled(boolean b) {
            // This method is intentionally left empty to prevent the default content area from being filled.
            // This allows the paintComponent method to handle all painting operations for custom backgrounds.
        }

        /**
         * Sets the background and foreground colors.
         * @param background the background color
         * @param foreground the foreground color
         */
        public void setBackgroundAndForeground(Color background, Color foreground) {
            setBackground(background);
            setForeground(foreground);
        }

        /**
         * Gets the color used when the button is hovered.
         * @return the hover background color
         */
        public Color getHoverBackgroundColor() {
            return hoverBackgroundColor;
        }

        /**
         * Sets the color to be used when the button is hovered.
         * @param hoverBackgroundColor the new hover background color
         */
        public void setHoverBackgroundColor(Color hoverBackgroundColor) {
            this.hoverBackgroundColor = hoverBackgroundColor;
        }

        /**
         * Gets the color used when the button is pressed.
         * @return the pressed background color
         */
        public Color getPressedBackgroundColor() {
            return pressedBackgroundColor;
        }

        /**
         * Sets the color to be used when the button is pressed.
         * @param pressedBackgroundColor the new pressed background color
         */
        public void setPressedBackgroundColor(Color pressedBackgroundColor) {
            this.pressedBackgroundColor = pressedBackgroundColor;
        }

        /**
         * Gets the color used for the text when the button is hovered.
         * @return the hover foreground color
         */
        public Color getHoverForegroundColor() {
            return hoverForegroundColor;
        }

        /**
         * Sets the color to be used for the text when the button is hovered.
         * @param hoverForegroundColor the new hover foreground color
         */
        public void setHoverForegroundColor(Color hoverForegroundColor) {
            this.hoverForegroundColor = hoverForegroundColor;
        }

        public void setCustomBorder(Color borderColor, int topLeft, int topRight, int bottomLeft, int bottomRight) {
            customBorder = new CustomBorder(borderColor, 2, topLeft, topRight, bottomLeft, bottomRight);
            setBorder(customBorder);
        }

        /**
        * Classe interna estática para a customização da borda do botão.
        */
        static class CustomBorder extends AbstractBorder {
            private Color borderColor;
            private int thickness;
            private int cornerRadiusTopLeft;
            private int cornerRadiusTopRight;
            private int cornerRadiusBottomLeft;
            private int cornerRadiusBottomRight;
        
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

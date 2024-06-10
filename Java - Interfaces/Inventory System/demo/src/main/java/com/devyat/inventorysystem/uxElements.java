package com.devyat.inventorysystem;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
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
}

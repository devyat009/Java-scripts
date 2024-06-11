package com.devyat.inventorysystem.res;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.svg.SVGDocument;

/*
 * SVGUtils is class for customizing and adding svg files
 * @devyat009
 * - Indenpendent Resize in X and Y axis
 * - Indenpendent Fill and Stroke color
 * - Custom Stroke Thickness 
 * - Auto Recenter
 * - Idependent position in X and Y axis. (Horizontal and Vertical) | WIP
 *  
 * @version 1.1
 * @since 1.0
 */

/**
 * Utility class for loading, colorizing, scaling, and drawing SVG files.
 *
 * Usage example:
 * <pre>{@code
 * // Load and colorize the SVG
 * GraphicsNode svgNodeLogin = SVGUtils.loadAndColorizeSVG(SVGUtils.SVGPaths.LOGIN, Color.CYAN, Color.CYAN, 2.0f);
 *
 * // Create a JPanel to draw the SVG
 * JPanel svgPanel = SVGUtils.createSVGPanel(svgNodeLogin, 2.0, 2.0, 0, 0);
 *
 * // Set up a JFrame to display the panel
 * JFrame frame = new JFrame("SVG Example");
 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * frame.setSize(800, 400);
 * frame.add(svgPanel);
 * frame.setVisible(true);
 * }</pre>
 */
public class SVGUtils {

    /**
     * SVGPaths provides static paths to the SVG files used in the application.
     */
    public static class SVGPaths {
        public static final String WARNING = "com/devyat/inventorysystem/res/icons/warning.svg";
        public static final String LOGOUT = "com/devyat/inventorysystem/res/icons/logout.svg";
        public static final String LOGIN = "com/devyat/inventorysystem/res/icons/login.svg";
    }

    // Método para carregar e colorir um arquivo SVG
    /**
     * Loads and colorizes an SVG file.
     *
     * @param svgPath    the path to the SVG file
     * @param fillColor  the fill color to apply
     * @param strokeColor the stroke color to apply
     * @param strokeWidth the stroke width to apply
     * @return a GraphicsNode representing the SVG
     * @throws IOException if the SVG file cannot be loaded
     */
    public static GraphicsNode loadAndColorizeSVG(String svgPath, Color fillColor, Color strokeColor, float strokeWidth) throws IOException {
        // Abre um fluxo de entrada para o arquivo SVG
        InputStream svgStream = SVGUtils.class.getClassLoader().getResourceAsStream(svgPath);
        if (svgStream == null) {
            throw new IOException("SVG file not found: " + svgPath);
        }

        // Cria um documento SVG usando o Batik
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        SVGDocument document;
        try {
            document = factory.createSVGDocument(svgPath, svgStream);
        } finally {
            svgStream.close();
        }

        // Modifica a cor do SVG
        changeSVGColor(document, fillColor, strokeColor, strokeWidth);

        // Configura o contexto do Batik
        UserAgentAdapter userAgentAdapter = new UserAgentAdapter();
        BridgeContext bridgeContext = new BridgeContext(userAgentAdapter);
        GVTBuilder builder = new GVTBuilder();
        GraphicsNode graphicsNode = builder.build(bridgeContext, document);

        return graphicsNode;
    }

    // Método para modificar a cor e a largura da linha do SVG
    /**
     * Modifies the color and stroke width of an SVG document.
     *
     * @param document    the SVG document to modify
     * @param fillColor   the fill color to apply
     * @param strokeColor the stroke color to apply
     * @param strokeWidth the stroke width to apply
     */
    private static void changeSVGColor(SVGDocument document, Color fillColor, Color strokeColor, float strokeWidth) {
        // Converte as cores para formato hexadecimal
        String fillHex = String.format("#%02x%02x%02x", fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue());
        String strokeHex = String.format("#%02x%02x%02x", strokeColor.getRed(), strokeColor.getGreen(), strokeColor.getBlue());

        // Obtém o elemento raiz do documento SVG
        Element rootElement = document.getDocumentElement();

        // Obtém todos os elementos dentro do documento SVG
        NodeList elements = rootElement.getElementsByTagName("*");
        // Itera sobre todos os elementos
        for (int i = 0; i < elements.getLength(); i++) {
            Element element = (Element) elements.item(i);
            // Modifica a cor de preenchimento se o elemento tiver o atributo 'fill'
            if (element.hasAttribute("fill") && !element.getAttribute("fill").equals("none")) {
                element.setAttribute("fill", fillHex);
            }
            // Modifica a cor da linha se o elemento tiver o atributo 'stroke'
            if (element.hasAttribute("stroke") && !element.getAttribute("stroke").equals("none")) {
                element.setAttribute("stroke", strokeHex);
            }
            // Modifica a largura da linha se o elemento tiver o atributo 'stroke-width'
            if (element.hasAttribute("stroke-width")) {
                element.setAttribute("stroke-width", String.valueOf(strokeWidth));
            } else if ("circle".equals(element.getTagName()) || "path".equals(element.getTagName())) {
                // Adiciona o atributo 'stroke-width' se não existir e se o elemento for um círculo ou caminho
                element.setAttribute("stroke-width", String.valueOf(strokeWidth));
            }
        }
    }
    /**
     * Draws the SVG always in the center of a JPanel.
     *
     * @param g2d         the Graphics2D object to draw on
     * @param svgNode     the SVG GraphicsNode to draw, the SVG File itself
     * @param scaleX      the scale factor in the X direction of the SVG
     * @param scaleY      the scale factor in the Y direction of the SVG
     * @param panelWidth  the width of the panel of the JPanel
     * @param panelHeight the height of the panel of the JPanel
     * @param moveHorizontal change the SVG horizonal position after centered
     * @param moveVertical change the SVG vertical position after centered
     */
    public static void paintCenteredSVG(Graphics2D g2d, GraphicsNode svgNode, double scaleX, double scaleY, int panelWidth, int panelHeight, double moveHorizontal, double moveVertical) {
        double svgWidth = svgNode.getPrimitiveBounds().getWidth();
        double svgHeight = svgNode.getPrimitiveBounds().getHeight();
        double svgCenterX = svgWidth / 2.0;
        double svgCenterY = svgHeight / 2.0;
        double panelCenterX = panelWidth / 2.0;
        double panelCenterY = panelHeight / 2.0;
        double translateX = panelCenterX - svgCenterX * scaleX + moveHorizontal;
        double translateY = panelCenterY - svgCenterY * scaleY + moveVertical;

        g2d.translate(translateX, translateY);
        g2d.scale(scaleX, scaleY);
        svgNode.paint(g2d);
    }

    
    /** 
     * @param svgNode
     * @param scaleX
     * @param scaleY
     * @param moveHorizontal
     * @param moveVertical
     * @return JPanel
     */
    // New method to create a JPanel that draws the SVG
    public static JPanel createSVGPanel(GraphicsNode svgNode, double scaleX, double scaleY, double moveHorizontal, double moveVertical) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Dimension size = getSize();
                SVGUtils.paintCenteredSVG(g2d, svgNode, scaleX, scaleY, size.width, size.height, moveHorizontal, moveVertical);
                g2d.dispose();
            }
        };
    }

    /**
     * The main method to launch the application.
     * Will be removed in a near future
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cria uma janela Swing
            JFrame frame = new JFrame("QUACK 𓅭 QUACK");
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setSize(400, 400); // Aumentar o tamanho da janela para acomodar ambos os painéis

            // Cria um JPanel para o fundo com layout de grid
            JPanel bground = new JPanel(new GridBagLayout());
            bground.setBackground(Color.BLUE);
            frame.add(bground, BorderLayout.CENTER);

            try {
                // Carrega o SVG "login" e aplica cores e largura da linha
                GraphicsNode svgNodeIconTest = SVGUtils.loadAndColorizeSVG(SVGPaths.LOGOUT, Color.RED, Color.RED, 2.0f);
                JPanel iconTester = SVGUtils.createSVGPanel(svgNodeIconTest, 1.0, 1.0, 0, 0);
                //iconTester.setBackground(new Color(255,255,255,255));
                iconTester.setBackground(new Color(0,0,0,0));
                iconTester.setPreferredSize(new Dimension(30, 30));

                // Adiciona ambos os painéis ao fundo com restrições de layout
                GridBagConstraints gbc = new GridBagConstraints();
                // Adicona o Painel 1 como o primeiro da fila
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(5, 5, 5, 5);
                bground.add(iconTester, gbc);
            } catch (IOException e) {
                e.printStackTrace();
            }
            frame.setVisible(true);
        });
    }
}
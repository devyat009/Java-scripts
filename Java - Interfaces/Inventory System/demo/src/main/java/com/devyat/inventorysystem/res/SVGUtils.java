package com.devyat.inventorysystem.res;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.gvt.GraphicsNode;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

public class SVGUtils {

    /**
     * PT-BR | Caminhos para os arquivos SVG
     * EN    | Paths for SVG files
     */
    public static class SVGPaths {
        public static final String EXAMPLE = "com/devyat/inventorysystem/res/images/example.svg";
        // Adicione mais constantes de caminho de SVG conforme necessário
    }

    /**
     * PT-BR | Carrega um arquivo SVG e redimensiona para a largura e altura especificadas
     * EN    | Loads an SVG file and resizes it to the specified width and height
     */
    public static BufferedImage loadSVG(String svgPath, int width, int height) throws IOException {
        InputStream svgStream = SVGUtils.class.getClassLoader().getResourceAsStream(svgPath);
        if (svgStream == null) {
            throw new IOException("SVG file not found: " + svgPath);
        }

        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        SVGDocument document;
        try {
            document = factory.createSVGDocument(svgPath, svgStream);
        } finally {
            svgStream.close();
        }

        // Configure the Batik bridge
        UserAgentAdapter userAgentAdapter = new UserAgentAdapter();
        BridgeContext bridgeContext = new BridgeContext(userAgentAdapter);
        GVTBuilder builder = new GVTBuilder();
        GraphicsNode graphicsNode = builder.build(bridgeContext, document);

        // Render the SVG to a BufferedImage
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphicsNode.paint(g2d);
        g2d.dispose();

        return image;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SVG Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);

            try {
                // Use a constante definida em SVGPaths para carregar a imagem SVG
                BufferedImage svgImage = SVGUtils.loadSVG(SVGUtils.SVGPaths.EXAMPLE, 200, 200);

                JPanel panel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        g.drawImage(svgImage, 0, 0, this);
                    }
                };

                frame.add(panel);
            } catch (IOException e) {
                e.printStackTrace();
            }

            frame.setVisible(true);
        });
    }
}

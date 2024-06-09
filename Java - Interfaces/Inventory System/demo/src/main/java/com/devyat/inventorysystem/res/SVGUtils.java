package com.devyat.inventorysystem.res;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import org.apache.batik.anim.dom.*;
import org.apache.batik.bridge.*;
import org.apache.batik.gvt.*;
import org.apache.batik.util.*;
import org.w3c.dom.*;
import org.w3c.dom.svg.*;

public class SVGUtils {

    // Caminhos para os arquivos SVG
    public static class SVGPaths {
        public static final String WARNING = "com/devyat/inventorysystem/res/images/warning.svg";
        public static final String LOGOUT = "com/devyat/inventorysystem/res/images/logout.svg";
        public static final String LOGIN = "com/devyat/inventorysystem/res/images/login.svg";
    }

    // Método para carregar e colorir um arquivo SVG
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cria uma janela Swing
            JFrame frame = new JFrame("QUACK 𓅭 QUACK");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 400); // Aumentar o tamanho da janela para acomodar ambos os painéis

            // Cria um JPanel para o fundo com layout de grid
            JPanel bground = new JPanel(new GridBagLayout());
            bground.setBackground(Color.BLUE);
            frame.add(bground, BorderLayout.CENTER);

            try {
                // Carrega o SVG "warning" e aplica cores e largura da linha
                GraphicsNode svgNodeWarning = SVGUtils.loadAndColorizeSVG(SVGPaths.WARNING, Color.BLACK, Color.BLACK, 14.0f);

                // Cria um JPanel para exibir o SVG "warning"
                JPanel panel1 = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g.create();
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        // Calcula a escala para ajustar o SVG ao tamanho do painel
                        Dimension size = getSize();
                        float scaleX = 0.4f; // Ajusta a escala horizontal
                        float scaleY = 0.4f; // Ajusta a escala vertical
                        g2d.scale(scaleX, scaleY);
                        // Desenha o SVG no JPanel com ajuste de posição
                        int moveHorizontal = 0; // Mover no eixo horizontal
                        int moveVertical = 0; // Mover no eixo vertical
                        g2d.translate(moveHorizontal, moveVertical);
                        svgNodeWarning.paint(g2d);
                        g2d.dispose();
                    }
                };
                panel1.setBackground(Color.WHITE);
                panel1.setOpaque(true);
                panel1.setPreferredSize(new Dimension(200, 200));

                // Carrega o SVG "logout" e aplica cores e largura da linha
                GraphicsNode svgNodeLogout = SVGUtils.loadAndColorizeSVG(SVGPaths.LOGOUT, Color.GREEN, Color.GREEN, 2.0f);

                // Cria um JPanel para exibir o SVG "logout"
                JPanel panel2 = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g.create();
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        // Calcula a escala para ajustar o SVG ao tamanho do painel
                        Dimension size = getSize();
                        float scaleX = 2.0f; // Ajusta a escala horizontal
                        float scaleY = 2.0f; // Ajusta a escala vertical
                        g2d.scale(scaleX, scaleY);
                        // Desenha o SVG no JPanel com ajuste de posição
                        int moveHorizontal = 0; // Mover no eixo horizontal
                        int moveVertical = 0; // Mover no eixo vertical
                        g2d.translate(moveHorizontal, moveVertical);
                        svgNodeLogout.paint(g2d);
                        g2d.dispose();
                    }
                };
                panel2.setBackground(Color.WHITE);
                panel2.setOpaque(true);
                panel2.setPreferredSize(new Dimension(200, 200));


                // Carrega o SVG "login" e aplica cores e largura da linha
                GraphicsNode svgNodeLogin = SVGUtils.loadAndColorizeSVG(SVGPaths.LOGIN, Color.CYAN, Color.CYAN, 2.0f);
                JPanel panel3 = new JPanel(){
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);
                        Graphics2D g2d = (Graphics2D) g.create();
                        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        
                        // Calcula a escala para ajustar o SVG ao tamanho do painel
                        Dimension size = getSize();
                        // Obtém as dimensões do painel
                        int panelWidth = size.width;
                        int panelHeight = size.height;

                        // Ajuste de escala do SVG
                        double scaleX = 2.0f; // Ajusta a escala horizontal do SVG
                        double scaleY = 2.0f; // Ajusta a escala vertical do SVG
                        


                         // Obtém as dimensões do SVG sem escala
                        double svgWidth = svgNodeLogin.getPrimitiveBounds().getWidth();
                        double svgHeight = svgNodeLogin.getPrimitiveBounds().getHeight();

                        // Calcula o centro do SVG (no sistema de coordenadas do SVG)
                        double svgCenterX = svgWidth / 2.0;
                        double svgCenterY = svgHeight / 2.0;

                        // Calcula o centro do painel
                        double panelCenterX = panelWidth / 2.0;
                        double panelCenterY = panelHeight / 2.0;

                        // Calcula as coordenadas para centralizar o centro do SVG no centro do painel
                        double translateX = panelCenterX - svgCenterX * scaleX;
                        double translateY = panelCenterY - svgCenterY * scaleY;

                        // Aplica a translação e escala
                        g2d.translate(translateX, translateY); // Ajusta a posição considerando a escala
                        g2d.scale(scaleX, scaleY); // Aplica a escala antes de desenhar
                        svgNodeLogin.paint(g2d); // Desenha o SVG

                        g2d.dispose();



                        // Obtém as dimensões do SVG após a escala
                        ///double svgWidth = svgNodeLogin.getPrimitiveBounds().getWidth() * scaleX;
                        ///double svgHeight = svgNodeLogin.getPrimitiveBounds().getHeight() * scaleY;
                        // Calcula as cordenadas para centralizar o SVG
                        ///double x = (panelWidth - svgWidth) / 2; // Calcula a posição central em relação ao tamanho do SVG escalado
                        ///double y = (panelHeight - svgHeight) / 2;
                        
                        
                        // Desenha o SVG no JPanel com ajuste de posição
                        //g2d.translate(x, y); // Simples é direta
                        //g2d.scale(scaleX, scaleY); // Aplica a escala antes de desenhar
                        // Aplica a translação e escala
                        ///g2d.translate(x / scaleX, y / scaleY); // Robusta e flexivel a diversos SVG
                        ///g2d.scale(scaleX, scaleY); // Aplica a escala antes de desenhar
                        ///svgNodeLogin.paint(g2d); // Desenha o SVG
                        
                        ///g2d.dispose();
                    }
                    
                };
                panel3.setBackground(Color.WHITE);
                panel3.setPreferredSize(new Dimension(200, 200));

                // Adiciona ambos os painéis ao fundo com restrições de layout
                GridBagConstraints gbc = new GridBagConstraints();
                // Adicona o Painel 1 como o primeiro da fila
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.insets = new Insets(5, 5, 5, 5);
                bground.add(panel1, gbc);
                // Adiciona o Painel 2 a Direita do Painel 1
                gbc.gridx = 1;
                bground.add(panel2, gbc);
                // Adiciona o Painel 3 a Direita do Painel 2
                gbc.gridx = 2;
                bground.add(panel3, gbc);
            } catch (IOException e) {
                e.printStackTrace();
            }

            frame.setVisible(true);
        });
    }
}

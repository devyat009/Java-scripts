package src.com.devyat.inventorysystemgui;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class CustomFontLoader {

    private static Font loadFont(String fontFilePath, float size) throws IOException, FontFormatException {
        InputStream fontStream = CustomFontLoader.class.getClassLoader().getResourceAsStream(fontFilePath);
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(size);
        fontStream.close();
        return font;
    }

    public static Font loadCustomFont1(float size) throws IOException, FontFormatException {
        return loadFont("com/devyat/inventorysystemgui/res/fonts/KoHo_Bold.ttf", size);
    }

    public static Font loadCustomFont2(float size) throws IOException, FontFormatException {
        return loadFont("com/devyat/inventorysystemgui/res/fonts/Lemon.ttf", size);
    }
}
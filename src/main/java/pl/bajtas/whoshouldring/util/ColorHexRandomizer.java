package pl.bajtas.whoshouldring.util;

import java.awt.*;
import java.util.Random;

/**
 * Created by Bajtas on 19.03.2017.
 */
public class ColorHexRandomizer {
    public static String random() {
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        Color randomColor = new Color(r, g, b);
        return "#" + Integer.toHexString(randomColor.getRGB()).substring(2).toUpperCase();
    }
}

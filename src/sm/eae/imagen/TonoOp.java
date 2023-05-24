/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.eae.imagen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author enriquearaqueespinosa
 */
public class TonoOp extends BufferedImageOpAdapter {
    private int hue;
    
    public TonoOp(int hue) {
        this.hue = hue;
    }
    
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        int[] pixelComp = new int[srcRaster.getNumBands()];
        int[] pixelCompDest = new int[srcRaster.getNumBands()];
        float[] pixelCompAux = new float[srcRaster.getNumBands()];
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                srcRaster.getPixel(x, y, pixelComp);
                Color.RGBtoHSB(pixelComp[0], pixelComp[1], pixelComp[2], pixelCompAux);
                pixelCompAux[0] = ((pixelCompAux[0] * 360 + hue) % 360)/360;
                int rgb = Color.HSBtoRGB(pixelCompAux[0], pixelCompAux[1], pixelCompAux[2]);
                
                pixelCompDest[0] = (rgb >> 16) & 0xFF;
                pixelCompDest[1] = (rgb >> 8) & 0xFF;
                pixelCompDest[2] = rgb & 0xFF;
                
                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        return dest;
    }
}

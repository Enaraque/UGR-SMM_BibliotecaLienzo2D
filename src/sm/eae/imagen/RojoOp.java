/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.eae.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author enriquearaqueespinosa
 */
public class RojoOp extends BufferedImageOpAdapter {
    private int umbral;

    public RojoOp(int umbral) {
        this.umbral = umbral;
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
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                srcRaster.getPixel(x, y, pixelComp);             
                if (pixelComp[0] - pixelComp[1] - pixelComp[2] >= umbral) {
                    pixelCompDest[0] = pixelComp[0];
                    pixelCompDest[1] = pixelComp[1];
                    pixelCompDest[2] = pixelComp[2];
                }
                else {
                    int media = (pixelComp[0] + pixelComp[1] + pixelComp[2])/3;
                    pixelCompDest[0] = media;
                    pixelCompDest[1] = media;
                    pixelCompDest[2] = media;
                }
                
                destRaster.setPixel(x, y, pixelCompDest);
            }
        }
        return dest;
    }
}

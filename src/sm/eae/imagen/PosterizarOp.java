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
public class PosterizarOp extends BufferedImageOpAdapter{
    private int niveles;
    
    public PosterizarOp(int niveles) {
            this.niveles = niveles;
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
        int sample;
        
        float k = 256.f/niveles;
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                for (int band = 0; band < srcRaster.getNumBands(); band++) {
                    sample = srcRaster.getSample(x, y, band);
                    
                    //Ecuacion de Posterizacion
                    sample = (int)(k*(int)(sample/k));
                    
                    destRaster.setSample(x, y, band, sample);
                }
            }
        }
        return dest;
    } 
}

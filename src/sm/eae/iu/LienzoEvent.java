/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.eae.iu;

import java.awt.Color;
import java.awt.Shape;
import java.util.EventObject;

/**
 *
 * @author enriquearaqueespinosa
 */
public class LienzoEvent extends EventObject {
    private Shape forma;
    private Color color;
    private FormaActiva formaActiva;

    public LienzoEvent(Object source, Shape forma, Color color, FormaActiva formaActiva) {
        super(source);
        this.forma = forma;
        this.color = color;
        this.formaActiva = formaActiva;
        
    }

    public Shape getForma() {
        return forma;
    }

    public Color getColor() {
        return color;
    }
    
    public FormaActiva getFormaActiva() {
        return formaActiva;
    }
}

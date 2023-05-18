/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.eae.iu;

import java.awt.Color;
import java.awt.Point;
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
    private Point coordenadas;
    private float coordX;
    private float coordY;
    private Color rgb;

    public LienzoEvent(Object source, Shape forma, Color color, FormaActiva formaActiva) {
        super(source);
        this.forma = forma;
        this.color = color;
        this.formaActiva = formaActiva;
    }
    
    public LienzoEvent(Object source, Point coordenadas) {
        super(source);
        this.coordenadas = coordenadas;
        this.coordX = coordenadas.x;
        this.coordY = coordenadas.y;
    }
    
    public LienzoEvent(Object source, Point coordenadas, Color rgb) {
        super(source);
        this.coordenadas = coordenadas;
        this.coordX = coordenadas.x;
        this.coordY = coordenadas.y;
        this.rgb = rgb;
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
    
    public float getX() {
        return coordX;
    }
    public float getY() {
        return coordY;
    }
    public Point getCoord() {
        return coordenadas;
    }
    
    public Color getRGB() {
        return rgb;
    }
}

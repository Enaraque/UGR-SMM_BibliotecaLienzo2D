/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.eae.graficos;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;

/**
 *
 * @author enriquearaqueespinosa
 */
public class Smile extends Area {
    /**
     * Start point
     */
    private Point2D inicio;
    
    /**
     * Offset point
     */
    private Point2D despPoint;
    
    /**
     * Width of shape
     */
    private float ancho;
    
    /**
     * Height of shape
     */
    private float alto;
    
    /**
     * Creates an empty instance of Smile.
     */
    public Smile() {
        super();
    }
    
    /**
     * Creates a smile given a shape
     * @param s Current shape
     */
    public Smile(Shape s) {
        super(s);
    }
    
    /**
     * Creates an empty instance of Smile in pos position.
     * @param pos 
     */
    public Smile(Point2D pos) {
        this.inicio = pos;
        this.despPoint = pos;
    }
    
    /**
     * Initializes a Smile from the specified point
     * and its width and height.
     * @param pos Start point
     * @param ancho Weight of shape
     * @param alto  Height of shape
     */
    public Smile(Point2D pos, float ancho, float alto) {
        this.inicio = pos;
        this.ancho = ancho;
        this.alto = alto;
        this.setSmile(pos, ancho, alto);
    }
    
    /**
     * Creates a Smile from the specified point
     * and its width and height.
     * @param pos Start point
     * @param ancho Weight of shape
     * @param alto  Height of shape
     */
    public void setSmile(Point2D pos, float ancho, float alto) {
        this.reset();
        
        Elipse cara = new Elipse((float)pos.getX(), (float)pos.getY(), ancho, alto);
        Elipse ojoL = new Elipse((float)(pos.getX() + (ancho *0.2)), (float)(pos.getY()+(alto *0.2)), (float)(ancho *0.2), (float)(alto *0.2));
        Elipse ojoR = new Elipse((float)(pos.getX()+(ancho *0.6)), (float)(pos.getY()+(alto *0.2)), (float)(ancho *0.2), (float)(alto *0.2));
        
        Curva boca = new Curva((float)(pos.getX() + (ancho *0.3)), (float)(pos.getY()+(alto *0.6)),
                                (float)(pos.getX() + (ancho *0.7)), (float)(pos.getY()+(alto *0.6)));
        
        double ctrlPointX = (boca.getP1().getX() + boca.getP2().getX())/2;
        double ctrlPointY = pos.getY() + alto;
        boca.setCurve((float)ctrlPointX, (float)ctrlPointY);
        
        Area smile = new Area(cara);
        smile.subtract(new Area(ojoL));
        smile.subtract(new Area(ojoR));
        smile.subtract(new Area(boca));
        
        this.add(smile);
    }
    
    /**
     * 
     * @return Start point
     */
    public Point2D getInicio(){
        return this.inicio;
    }
    
    /**
     * 
     * @return The X coordinate of the start point
     */
    public double getX() {
        return this.inicio.getX();
    }
    
    /**
     * 
     * @return The Y coordinate of the start point 
     */
    public double getY() {
        return this.inicio.getY();
    }
    
    /**
     * 
     * @return The weight of the Smile
     */
    public double getAncho() {
        return this.ancho;
    }
    
    /**
     * 
     * @return The height of the Smile
     */
    public double getAlto() {
        return this.alto;
    }
    
    /**
     * Set the location of current shape given the pressed point
     * @param pos Pressed point of the new location
     */
    public void setDesplazamiento(Point2D pos) {
        this.despPoint = pos;
    }

    /**
     * 
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return ("Smile :)");
    }
    
    public void setLocation(Point2D pos) {
        if (despPoint == inicio)
            despPoint = pos;
        
        double dx=pos.getX()-despPoint.getX();
        double dy=pos.getY()-despPoint.getY();
                       
        this.despPoint = pos;
        Point2D newInicio = new Point2D.Float((float)(this.getX()+dx), (float)(this.getY()+dy));
        this.inicio = newInicio;
        
        AffineTransform at;        
        at = AffineTransform.getTranslateInstance(dx,dy); 
        this.transform(at);
    }
    
}

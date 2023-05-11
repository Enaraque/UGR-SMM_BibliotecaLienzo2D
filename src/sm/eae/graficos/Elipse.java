/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.eae.graficos;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 *
 * @author enriquearaqueespinosa
 */
public class Elipse extends Ellipse2D.Float {
    /**
     * Start point
     */
    private Point2D inicio;
    
    /**
     * Offset point
     */
    private Point2D despPoint;
    
    /**
     * Creates an empty instance of Elipse.
     */
    public Elipse() {
        super();
    }
    
    /**
     * Constructs and initializes an Elipse from the specified float coordinates
     * and its width and height.
     * @param x the X coordinate of the start point
     * @param y the Y coordinate of the start point
     * @param w width of shape 
     * @param h height of shape
     */
    public Elipse(float x, float y, float w, float h) {
        super(x, y, w, h);
        Point2D aux = new Point2D.Float(x, y);
        inicio = aux;
        despPoint = inicio;        
    }
    
    /**
     * 
     * @return Start point of current shape
     */
    public Point2D getInicio(){
        return this.inicio;
    }
    
    /**
     * set the offset point 
     * @param pos Offset point
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
        return ("Elipse");
    }
    
    /**
     * Set the location of current shape given the pressed point
     * @param pos Pressed point of the new location
     */
    public void setLocation(Point2D pos) {
        if (despPoint == inicio)
            despPoint = pos;
        
        double dx=pos.getX()-despPoint.getX();
        double dy=pos.getY()-despPoint.getY();
           
        this.despPoint = pos;
        Point2D newInicio = new Point2D.Float((float)(this.getX()+dx), (float)(this.getY()+dy));
        this.inicio = newInicio;
        
        double x2 = this.getX() + this.getWidth();
        double y2 = this.getY() + this.getHeight();
        
        double dist2X = x2 - this.despPoint.getX();
        double dist2Y = y2 - this.despPoint.getY();
        
        Point2D newp2 = new Point2D.Double(this.despPoint.getX()+dx+dist2X,this.despPoint.getY()+dy+dist2Y); 
        this.setFrameFromDiagonal(inicio, newp2);
    }
}

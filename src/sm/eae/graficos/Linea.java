/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.eae.graficos;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author enriquearaqueespinosa
 */
public class Linea extends Line2D.Float {
    /**
     * Start point
     */
    private Point2D inicio;
    
    /**
     * Offset point
     */
    private Point2D despPoint;
    
    /**
     * Creates an empty instance of Linea.
     */
    public Linea() {
        super();
    }
    
    /**
     * Constructs and initializes a Linea from the specified end points
     * @param p1 Start point
     * @param p2 End point
     */
    public Linea(Point2D p1, Point2D p2) {
        super(p1,p2);
        inicio = p1;
        despPoint = inicio;
    }
    
    public boolean isNear(Point2D p){
      // Caso p1=p2 (punto)
        if(this.getP1().equals(this.getP2())) return this.getP1().distance(p)<=2.0;
      // Caso p1!=p2
        return this.ptLineDist(p)<=2.0; 
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
    
    @Override
    public boolean contains(Point2D p) {
        return isNear(p);
    }
    
    /**
     * 
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return ("Linea");
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
        Point2D newInicio = new Point2D.Float((float)(this.getX1()+dx), (float)(this.getY1()+dy));
        this.inicio = newInicio;
        
        
        Point2D newp2 = new Point2D.Double(this.getX2()+dx, this.getY2()+dy); 
        this.setLine(inicio,newp2);
    }
}

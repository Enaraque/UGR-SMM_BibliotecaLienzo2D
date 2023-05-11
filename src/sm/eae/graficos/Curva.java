/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.eae.graficos;

import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

/**
 *
 * @author enriquearaqueespinosa
 */
public class Curva extends QuadCurve2D.Float {
    /**
     * Start point
     */
    private Point2D inicio;
    
    /**
     * Offset point
     */
    private Point2D despPoint;
    
    /**
     * Constructs and initializes a Curva with coordinates (0, 0, 0, 0, 0, 0).
     */
    public Curva(){
        super();
    }
    
    /**
     * Constructs and initializes a Curva from the specified float coordinates.
     * @param x1 the X coordinate of the start point
     * @param y1 the Y coordinate of the start point
     * @param ctrlx the X coordinate of the control point
     * @param ctrly the Y coordinate of the control point
     * @param x2 the X coordinate of the end point
     * @param y2 the Y coordinate of the end point 
     */
    public Curva(float x1, float y1, float ctrlx, float ctrly, float x2, float y2) {
        super(x1, y1, ctrlx, ctrly, x2, y2);
        
        Point2D aux = new Point2D.Float(x1, y2);
        inicio = aux;
        despPoint = inicio; 
    }
    
    /**
     * Constructs and initializes a Curva from the specified float coordinates.
     * @param x1 the X coordinate of the start point
     * @param y1 the Y coordinate of the start point
     * @param x2 the X coordinate of the end point
     * @param y2 the Y coordinate of the end point
     */
    public Curva(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        
        Point2D aux = new Point2D.Float(x1, y2);
        inicio = aux;
        despPoint = inicio; 
    }
    
    /**
     * Constructs and initializes a Curva from the specified end points
     * @param p1 Start point
     * @param p2 End point
     */
    public Curva(Point2D p1, Point2D p2) {
        this.x1 = (float) p1.getX();
        this.y1 = (float) p1.getY(); 
        this.x2 = (float) p2.getX();
        this.y2 = (float) p2.getY(); 
        
        this.ctrlx = x2;
        this.ctrly = y2;   
        
        inicio = p1;
        despPoint = inicio;
    }
    
    /**
     * Constructs and initializes a Curva from the specified end points
     * @param p1 Start point
     * @param ctrlPoint  Control point
     * @param p2 End point
     */
    public Curva(Point2D p1, Point2D ctrlPoint, Point2D p2) {
        this.x1 = (float) p1.getX();
        this.y1 = (float) p1.getY(); 
        this.x2 = (float) p2.getX();
        this.y2 = (float) p2.getY(); 
        
        this.ctrlx = (float) ctrlPoint.getX();
        this.ctrly = (float) ctrlPoint.getY();
        
        inicio = p1;
        despPoint = inicio;
    }
    
    /**
     * Sets the location of the end points of this curve to the specified points.
     * @param p1 Start point 
     * @param p2 End point
     */
    public void setCurve(Point2D p1, Point2D p2) {
        this.x1 = (float) p1.getX();
        this.y1 = (float) p1.getY(); 
        this.x2 = (float) p2.getX();
        this.y2 = (float) p2.getY(); 
        
        inicio = p1;
        despPoint = inicio;
    }
    
    /**
     * Sets the location of the end points of this curve to the specified float coordinates.
     * @param x1 the X coordinate of the start point
     * @param y1 the Y coordinate of the start point
     * @param x2 the X coordinate of the end point
     * @param y2 the Y coordinate of the end point
     */
    public void setCurve(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        
        Point2D aux = new Point2D.Float(x1, y2);
        inicio = aux;
        despPoint = inicio; 
    }
    
    /**
     * Set the location of the control point.
     * @param ctrlPoint  Control point
     */
    public void setCurve(Point2D ctrlPoint) {
        this.ctrlx = (float) ctrlPoint.getX();
        this.ctrly = (float) ctrlPoint.getY();
    }
    
    /**
     * Set the location of the control point to the specified float coordinates.
     * @param ctrlx the X coordinate of the control point
     * @param ctrly the Y coordinate of the control point
     */
    public void setCurve(float ctrlx, float ctrly) {
        this.ctrlx = ctrlx;
        this.ctrly = ctrly;
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
        return ("Linea Curva");
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
        Point2D newPuntoControl = new Point2D.Double(this.getCtrlX()+dx, this.getCtrlY()+dy); 
        this.setCurve(inicio, newPuntoControl, newp2);
    }   
}

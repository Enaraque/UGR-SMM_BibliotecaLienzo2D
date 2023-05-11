/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.eae.graficos;

import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;



/**
 *
 * @author enriquearaqueespinosa
 */
public class Trazo extends Path2D.Float {
    /**
     * Start point
     */
    private Point2D inicio;
    
    /**
     * Offset point
     */
    private Point2D despPoint;
    
    /**
     * Creates an empty instance of Trazo.
     */
    public Trazo() {
        super();
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
        return ("Trazo libre");
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
        Point2D newInicio = new Point2D.Float((float)(this.getCurrentPoint().getX()+dx), (float)(this.getCurrentPoint().getY()+dy));
        this.inicio = newInicio;
        
        AffineTransform at;        
        at = AffineTransform.getTranslateInstance(dx,dy); 
        this.transform(at);
    }
}

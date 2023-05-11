/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sm.eae.iu;

import java.util.EventListener;

/**
 *
 * @author enriquearaqueespinosa
 */
public interface LienzoListener extends EventListener {
    public void shapeAdded(LienzoEvent evt);
    
    public void propertyChange(LienzoEvent evt);
    
    public void shapeChange(LienzoEvent evt);

}

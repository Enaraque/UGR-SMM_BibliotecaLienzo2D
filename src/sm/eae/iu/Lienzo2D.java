/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sm.eae.iu;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import sm.eae.graficos.Curva;
import sm.eae.graficos.Elipse;
import sm.eae.graficos.Linea;
import sm.eae.graficos.Rectangulo;
import sm.eae.graficos.Smile;
import sm.eae.graficos.Trazo;



/**
 *
 * @author enriquearaqueespinosa
 */
public class Lienzo2D extends javax.swing.JPanel {
    Shape forma;
    private Point auxPoint;
    private Color color;
    private boolean relleno, mover, transparente, renderizado;
    private FormaActiva formaActiva;
    private BufferedImage img;
    private boolean defaultImage;
    private Shape img_form;
    private List<Shape> vShape = new ArrayList();
    private int etapaCurva;
    int grosor;
    ArrayList<LienzoListener> lienzoEventListeners = new ArrayList();
    ArrayList<Integer> indice = new ArrayList();
    
    /**
     * Creates new form Lienzo
     */
    public Lienzo2D() {
        initComponents();
        formaActiva = FormaActiva.LINEA;
        etapaCurva = 0;
        grosor = 1;
        indice = null;
        color = new Color(0,0,0);
    }
    
//---- MÉTODO PAINT ----//
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        // Insertar imagen en Lienzo2D
        if(img!=null) {
            g2d.drawImage(img,0,0,this);
            if (defaultImage) {
                g2d.setPaint(Color.WHITE);
                g2d.fill(img_form);
            }
        }
        
        //Dibujar límites de la imagen
        this.drawLimites(g2d);
        
        // Aplicar el color actual a las figuras del lienzo
        g2d.setPaint(color);
                       
        // Aplicar transparencia
        if (transparente)
            this.setComposicion(g2d);
        
        // Aplicar renderizado
        if (renderizado) {
            this.renderizar(g2d);
        }
        
        // Aplicar grosor
        this.setStroke(g2d, grosor);   
        
        if (relleno)
            for(Shape forma:vShape)
                g2d.fill(forma);      
        
        if (indice == null)
            for(Shape s:vShape)
                g2d.draw(s);
        else 
            for (int i = 0; i < indice.size(); i++) 
                g2d.draw(this.vShape.get(indice.get(i)));          
    }

//---- MÉTODOS PRIVADOS ----//
    
    private void drawLimites(Graphics2D g2d) {
        Shape clipArea;
        clipArea = new Rectangulo.Float(0, 0, img.getWidth()+0.5f,img.getHeight()+0.5f);
        g2d.clip(clipArea);
        
        Stroke trazo;
        float patronDiscontinuidad[] = {4.0f, 2.0f};
        trazo = new BasicStroke(1.0f,
                                BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_MITER, 1.0f,
                                patronDiscontinuidad, 0.0f);
        g2d.setStroke(trazo);
        g2d.setPaint(Color.black);
        g2d.draw(new Rectangulo(-1, -1, img.getWidth()+1.0f,img.getHeight()+1.0f));

    }
    
    private void renderizar(Graphics2D g2d) {
        RenderingHints render;
        render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(render);
    }
    
    private void setComposicion(Graphics2D g2d) {
        Composite comp;
        comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        g2d.setComposite(comp);
    }
    
    private void setStroke(Graphics2D g2d, float grosor) {
        Stroke stroke = new BasicStroke(grosor);
        g2d.setStroke(stroke);
    }
    
    private Shape getFiguraSeleccionada(Point2D p) {
        for(Shape s:vShape)
          if(s.contains(p)) 
              return s;
        return null;
    }

//---- MÉTODOS PÚBLICOS ----//
    
    public void setColor(Color color) {
        this.color = color;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }
       
    public void setMover(boolean mover) {
        this.mover = mover;
    }
    
    public void setFormaActiva(FormaActiva formaActiva) {
        this.formaActiva = formaActiva;
        notifyShapeChangeEvent( new LienzoEvent(this,forma,color, formaActiva) );
        
    }
    
    public void setDefaultImage(BufferedImage img) {
        this.img = img;
        if(img!=null) {
            setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
            defaultImage = true;
            img_form = new Rectangulo.Float(0, 0, img.getWidth(),img.getHeight());
        }
    }
    
    public void setImage(BufferedImage img){
        this.img = img;
        if(img!=null) {
            setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
        }
        defaultImage = false;
    }
    
    public void setGrosor(int grosor) {
        this.grosor = grosor;
        
    }
    
    public int getEtapaCurva() {
        return etapaCurva;
    }
    public void setRenderizado(boolean renderizado) {
        this.renderizado = renderizado;
    }
    
    public void setTransparencia(boolean transparencia) {
        this.transparente = transparencia;
    }
    
    public boolean isMover() {
        return mover;
    }
    
    public boolean isRelleno() {
        return relleno;
    }
    
    public boolean isTransparencia() {
        return this.transparente;
    }
    
    public boolean isRenderizado() {
        return renderizado;
    }
              
    public FormaActiva getFormaActiva() {
        return formaActiva;
    }
    
    public Color getColor() {
        return color;
    }
    
    public int getGrosor() {
        return grosor;
    }
    
    public BufferedImage getImagen(boolean pintaVector) {
        if (pintaVector) {
            BufferedImage imgout = new BufferedImage(img.getWidth(),
                                                    img.getHeight(),
                                                    img.getType());
          
            boolean opacoActual = this.isOpaque();
            if (img.getColorModel().hasAlpha()) {
                this.setOpaque(false);
            }
            this.paint(imgout.createGraphics()); 
            this.setOpaque(opacoActual);
            return imgout;         
        }
        else {
            return img;
        }
    }
    
    public ArrayList<Shape> getShapeList() {
        ArrayList<Shape> arrayShape = new ArrayList<>();
        
        for(Shape s : vShape) {
            arrayShape.add(s);
        }
        
        return arrayShape;
    }
    
    public void volcado(ArrayList<Integer> indice) {
        BufferedImage imgout = new BufferedImage(img.getWidth(),
                                                    img.getHeight(),
                                                    img.getType());
          
        this.indice = indice;
        boolean opacoActual = this.isOpaque();
        if (img.getColorModel().hasAlpha()) {
            this.setOpaque(false);
        }
        this.paint(imgout.createGraphics()); // pinto la imagen con las figuras que queremos volcar
        
        for (int i = 0; i < indice.size(); i++) {
            this.vShape.remove(indice.get(i) - i);
        }   
        this.setOpaque(opacoActual);
        this.setImage(imgout);
        this.indice = null; // para que pinte todas las figuras
        this.repaint();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
//---- MÉTODOS DE GESTIÓN DE EVENTOS ----//
    
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (mover) {
            forma = getFiguraSeleccionada(evt.getPoint());
        }
        else {
            switch (formaActiva) {
                case RECTANGULO: 
                    auxPoint = evt.getPoint();
                    forma = new Rectangulo(evt.getPoint().x, evt.getPoint().y, 0, 0);
                    break;
                case ELIPSE:
                    auxPoint = evt.getPoint();
                    forma = new Elipse(evt.getPoint().x, evt.getPoint().y, 0, 0);
                    break;
                case LINEA:
                    forma = new Linea(evt.getPoint(), evt.getPoint());
                    break;
                case TRAZO:
                    forma = new Trazo();
                    ((Trazo)forma).moveTo(evt.getX(), evt.getY());
                    break;
                case CURVA:
                    if (etapaCurva == 0) {
                        forma = new Curva(evt.getPoint(), evt.getPoint());
                    }
                    else {
                        ((Curva)forma).setCurve(evt.getX(), evt.getY()); 
                        this.repaint();
                    }  
                    break;
                case SMILE:
                    forma = new Smile(evt.getPoint());
                    break;
                    
            }
            if (etapaCurva == 0)
                vShape.add(forma);
            
            if (formaActiva != FormaActiva.CURVA || etapaCurva == 1)
                notifyShapeAddedEvent( new LienzoEvent(this,forma,color, formaActiva) );
        }   
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (mover) {
            if (forma!=null && forma instanceof Rectangulo)
                ((Rectangulo)forma).setLocation(evt.getPoint());
            
            else if (forma!=null && forma instanceof Elipse)
                ((Elipse)forma).setLocation(evt.getPoint());   
            
            else if (forma!=null && forma instanceof Linea)
                ((Linea)forma).setLocation(evt.getPoint());
            
            else if (forma != null && forma instanceof Trazo) {
                ((Trazo)forma).setLocation(evt.getPoint());
            }
            else if (forma != null && forma instanceof Curva) {
                ((Curva)forma).setLocation(evt.getPoint());
            }
            else if (forma != null && forma instanceof Smile) {
                ((Smile)forma).setLocation(evt.getPoint());
            }
                
        }
        else {
            if (forma!=null && forma instanceof Rectangulo)
                ((Rectangulo)forma).setFrameFromDiagonal(auxPoint.x, auxPoint.y, 
                evt.getX(), evt.getY());  
            
            else if (forma!=null && forma instanceof Elipse)
                ((Elipse)forma).setFrameFromDiagonal(auxPoint.x, auxPoint.y, 
                evt.getX(), evt.getY());      
            
            else if (forma!=null && forma instanceof Linea)
                ((Linea)forma).setLine(((Linea)forma).getP1(), evt.getPoint());
            
            else if (forma != null && forma instanceof Trazo)
                ((Trazo)forma).lineTo(evt.getX(), evt.getY());
            
            else if (forma != null && forma instanceof Curva) {
                if (etapaCurva == 0) {
                    ((Curva)forma).setCurve(((Curva)forma).getP1(), evt.getPoint());
                }
                else {
                    ((Curva)forma).setCurve(evt.getPoint());
                }
            }
            else if (forma != null && forma instanceof Smile) {
                double ancho = evt.getX() - ((Smile)forma).getX();
                double alto = evt.getY() - ((Smile)forma).getY();
                ((Smile)forma).setSmile(((Smile)forma).getInicio(), (float)ancho, (float)alto);
            }
                
        }
        
        this.repaint();      
    }//GEN-LAST:event_formMouseDragged

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        
        if (forma != null && forma instanceof Curva && etapaCurva == 0) 
            etapaCurva = 1;
        else 
            etapaCurva = 0;
        
        // Para mover las formas desde donde pulsamos
        if (forma != null && forma instanceof Smile) 
            ((Smile)forma).setDesplazamiento(((Smile)forma).getInicio());
        else if (forma != null && forma instanceof Linea) 
            ((Linea)forma).setDesplazamiento(((Linea)forma).getInicio());
        else if (forma != null && forma instanceof Elipse) 
            ((Elipse)forma).setDesplazamiento(((Elipse)forma).getInicio());
        else if (forma != null && forma instanceof Rectangulo) 
            ((Rectangulo)forma).setDesplazamiento(((Rectangulo)forma).getInicio());
        else if (forma != null && forma instanceof Trazo) 
            ((Trazo)forma).setDesplazamiento(((Trazo)forma).getInicio());
        else if (forma != null && forma instanceof Curva) 
            ((Curva)forma).setDesplazamiento(((Curva)forma).getInicio());
                    
    }//GEN-LAST:event_formMouseReleased
    
    public void addLienzoListener(LienzoListener listener) {
        if (listener != null) {
            lienzoEventListeners.add(listener);
         }
    }

    private void notifyShapeAddedEvent(LienzoEvent evt) {
        if (!lienzoEventListeners.isEmpty()) {
            for (LienzoListener listener : lienzoEventListeners) {
                listener.shapeAdded(evt);
            }
        }
    }

    private void notifyPropertyChangeEvent(LienzoEvent evt) {
        if (!lienzoEventListeners.isEmpty()) {
            for (LienzoListener listener : lienzoEventListeners) {
                listener.propertyChange(evt);
            }
        }
    }
    
    private void notifyShapeChangeEvent(LienzoEvent evt) {
        if (!lienzoEventListeners.isEmpty()) {
            for (LienzoListener listener : lienzoEventListeners) {
                listener.shapeChange(evt);
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

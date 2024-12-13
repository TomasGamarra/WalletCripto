package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class ComponenteCircular extends JComponent {
    private String iniciales;
    private final int diameter = 75; 

    public ComponenteCircular(String iniciales) {
        this.iniciales = iniciales;
        setPreferredSize(new Dimension(diameter, diameter));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      
        g2d.setPaint(new GradientPaint(0, 0, new Color(47,224,189), getWidth(), getHeight(),new Color (255,127,172)));
        g2d.fillOval(0, 0, diameter, diameter);

        // Dibujar las iniciales en el centro
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(iniciales);
        int textHeight = fm.getAscent();
        int x = (diameter - textWidth) / 2;
        int y = (diameter + textHeight) / 2 - 2;
        g2d.drawString(iniciales, x, y);
    }
    
    public void setIniciales(String nuevasIniciales) {
        this.iniciales = nuevasIniciales;
        repaint(); 
    }
}

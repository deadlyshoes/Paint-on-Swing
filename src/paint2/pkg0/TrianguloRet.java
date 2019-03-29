package paint2.pkg0;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author João Neto
 * @author José Ilmar
 */
public class TrianguloRet extends Forma{

    /**
     * 
     * @param x
     * @param y
     * @param largura
     * @param altura
     * @param cor 
     */
    public TrianguloRet(int x, int y, int largura, int altura, Color cor) {
        super(x, y, largura, altura, cor);
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void Desenhar(Graphics g) {
        g.setColor(cor);
        int pontosX[] = {x, x, x+largura};
        int pontosY[] = {y + altura, y, y + altura};
        g.fillPolygon(pontosX, pontosY, 3);
    }

    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    @Override
    public boolean noLimite(int x, int y) {
        double yMax = (altura/(float) largura)*x + this.y - ((altura / (float) largura) * this.x);
        return (x > this.x && x < this.x + largura &&
                y > yMax && y < this.y + altura);
    }
    
    @Override
    public String toString() {
        return "Triângulo retângulo" + " " + getCorString("m");  
    }
}

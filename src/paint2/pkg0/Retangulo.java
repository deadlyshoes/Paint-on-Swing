package paint2.pkg0;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author João Neto
 * @author José Ilmar
 */
public class Retangulo extends Forma {
    public Retangulo(int x, int y, int largura, int altura, Color cor) {
        super(x, y, largura, altura, cor);
    }
    
    @Override
    public void Desenhar(Graphics g) {
        g.setColor(cor);
        g.fillRect(x, y, largura, altura);
    }
    
    @Override
    public boolean noLimite(int x, int y) {
        return x > this.x && x < this.x + largura &&
               y > this.y && y < this.y + altura;
    }
    
    @Override
    public String toString() {
        if (largura == altura)
            return "Quadrado" + " " + getCorString("m");
        return "Retângulo" + " " + getCorString("m");  
    }
}
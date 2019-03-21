/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint2.pkg0;

import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author João Neto
 */
public class Triangulo extends Forma{

    /**
     * 
     * @param x
     * @param y
     * @param largura
     * @param altura
     * @param cor 
     */
    public Triangulo(int x, int y, int largura, int altura, Color cor) {
        super(x, y, largura, altura, cor);
    }
    
    /**
     *
     * @param g
     */
    @Override
    public void Desenhar(Graphics g) {
        g.setColor(cor);
        int pontosX[] = {x,x+(largura/2), x+largura};
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

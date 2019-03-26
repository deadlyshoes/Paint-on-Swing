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
        double y1Max = (-1*altura*2/ (float) largura)*x + this.y + altura + this.x*2*altura/ (float) largura;
        double y2Max = (altura*2/ (float) largura)*x + altura + -1*(this.y + altura + this.x*2*altura/ (float) largura);
        System.out.println(y1Max);
        System.out.println(y2Max);
        System.out.println(y);
        return (x > this.x && x < this.x + largura / 2 &&
                y > y1Max && y < this.y + altura) ||
               (x > this.x + largura / 2 && x < this.x + largura
                && y > y2Max && y < this.y + altura);
    }
    
}

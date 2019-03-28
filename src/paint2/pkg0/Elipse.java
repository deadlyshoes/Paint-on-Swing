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
 * @author José Ilmar
 */
public class Elipse extends Forma {
    /**
     * 
     * @param x
     * @param y
     * @param largura
     * @param altura
     * @param cor 
     */
    public Elipse(int x, int y, int largura, int altura, Color cor) {
        super(x, y, largura, altura, cor);
    }

    /**
     *
     * @param g
     */
    @Override
    public void Desenhar(Graphics g) {
        g.setColor(cor);
        g.fillOval(x, y, largura, altura);
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean noLimite(int x, int y) {
        int centroX = this.x + largura / 2;
        int centroY = this.y + altura / 2;
        double pontoY = (altura / 2) * Math.sqrt(1 - ((4 * (x - centroX) * (x - centroX)) / (float) (largura * largura)));
        return (x > this.x && x < this.x + largura &&
                y > centroY - pontoY && y < centroY + pontoY);
    }
}

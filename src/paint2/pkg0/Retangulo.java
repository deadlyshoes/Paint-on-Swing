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
 * @author ilmar
 */
public class Retangulo extends Forma {
    private int largura;
    private int altura;
    
    public Retangulo(int x, int y, Color cor, int largura, int altura) {
        super(x, y, cor);
        this.largura = largura;
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }
    
    public int getAltura() {
        return altura;
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
}
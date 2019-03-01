/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint2.pkg0;

/**
 *
 * @author ilmar
 */

import java.awt.Color;
import java.awt.Graphics;

public abstract class Forma {
    protected int x;
    protected int y;
    protected int largura;
    protected int altura;
    protected Color cor;
    
    public Forma(int x, int y, int largura, int altura, Color cor) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.cor = cor;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }  
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    public Color getCor() {
        return cor;
    }
    
    public abstract void Desenhar(Graphics g);
    public abstract boolean noLimite(int x, int y);
}

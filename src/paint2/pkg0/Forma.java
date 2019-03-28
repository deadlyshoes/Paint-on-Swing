/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint2.pkg0;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author João Neto
 * @author José Ilmar
 */
public abstract class Forma implements Serializable {
    protected int x;
    protected int y;
    protected int largura;
    protected int altura;
    protected Color cor;
    
    /**
     * 
     * @param x
     * @param y
     * @param largura
     * @param altura
     * @param cor 
     */
    public Forma(int x, int y, int largura, int altura, Color cor) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.cor = cor;
    }

    /**
     * 
     * @param x 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 
     * @param y 
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 
     * @param largura 
     */
    public void setLargura(int largura) {
        this.largura = largura;
    }

    /**
     * 
     * @param altura 
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * 
     * @param cor 
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }  
    
    /**
     * 
     * @return 
     */
    public int getX() {
        return x;
    }

    /**
     * 
     * @return 
     */
    public int getY() {
        return y;
    }

    /**
     * 
     * @return 
     */
    public int getLargura() {
        return largura;
    }

    /**
     * 
     * @return 
     */
    public int getAltura() {
        return altura;
    }

    /**
     * 
     * @return 
     */
    public Color getCor() {
        return cor;
    }
    
    /**
     *
     * @param g
     */
    public abstract void Desenhar(Graphics g);

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public abstract boolean noLimite(int x, int y);
}

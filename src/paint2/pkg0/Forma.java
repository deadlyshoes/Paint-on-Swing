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
    protected Color cor;
    
    public Forma(int x, int y, Color cor) {
        this.x = x;
        this.y = y;
        this.cor = cor;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public abstract void Desenhar(Graphics g);
    public abstract boolean noLimite(int x, int y);
}

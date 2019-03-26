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
public class Quadrado extends Retangulo {
   private int X;
   private int Y;
    
    public Quadrado(int x, int y, int x1, int y1, int largura, int altura, Color cor) {
        super(x, y, largura, altura, cor);
        this.X = x1;
        this.Y = y1;
    }
    
    @Override
    public void Desenhar(Graphics g) {
        largura = altura;
        g.setColor(cor);
        if (X - largura == x - largura){
            g.fillRect(X, y, largura, altura);
        } else {
            g.fillRect(x, y, largura, altura);
        }
    }
}
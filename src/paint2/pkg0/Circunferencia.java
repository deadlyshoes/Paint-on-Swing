/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint2.pkg0;

import java.awt.Color;
import static java.lang.Math.min;

/**
 *
 * @author ilmar
 */
public class Circunferencia extends Elipse {
    /**
     * 
     * @param x
     * @param y
     * @param largura
     * @param altura
     * @param cor 
     */
    public Circunferencia(int x, int y, int largura, int altura, Color cor) {
        //raio = min(largura, altura)
        super(x, y, min(largura, altura), min(largura, altura), cor);
    }
}

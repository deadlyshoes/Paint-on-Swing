package paint2.pkg0;

/**
 *
 * @author ilmar
 */

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author João Neto
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

    public Color getCor() {
        return cor;
    }
    
    /**
     * 
     * @return 
     */
    public String getCorString(String genero) {
        String sufix;
        if (genero == "m")
            sufix = "o";
        else
            sufix = "a";
        if (cor == Color.BLACK) return "pret" + sufix;
        if (cor == Color.BLUE) return "azul";
        if (cor == Color.CYAN) return "ciano";
        if (cor == Color.GRAY) return "cinza";
        if (cor == Color.GREEN) return "verde";
        if (cor == Color.LIGHT_GRAY) return "cinza claro";
        if (cor == Color.MAGENTA) return "magenta";
        if (cor == Color.ORANGE) return "laranja";
        if (cor == Color.PINK) return "rosa";
        if (cor == Color.RED) return "vermelh" + sufix;
        if (cor == Color.WHITE) return "branc" + sufix;
        return "amarel" + sufix;
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

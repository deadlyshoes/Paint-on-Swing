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
     * @param x coordenada x da origem da forma
     * @param y coordenada y da origem da forma
     * @param largura largura da forma
     * @param altura altura da forma
     * @param cor cor da forma 
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
     * @param x novo x da origem
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 
     * @param y novo y da origem
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 
     * @param largura nova largura
     */
    public void setLargura(int largura) {
        this.largura = largura;
    }

    /**
     * 
     * @param altura nova altura
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * 
     * @param cor nova cor
     */
    public void setCor(Color cor) {
        this.cor = cor;
    }  
    
    /**
     * 
     * @return x da origem
     */
    public int getX() {
        return x;
    }

    /**
     * 
     * @return y da origem
     */
    public int getY() {
        return y;
    }

    /**
     * 
     * @return largura
     */
    public int getLargura() {
        return largura;
    }

    /**
     * 
     * @return altura
     */
    public int getAltura() {
        return altura;
    }

    public Color getCor() {
        return cor;
    }
    
    /**
     * 
     * @return cor da classe Colors em uma String com o nome da cor
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
     * @param x coordenada x a ser analisada
     * @param y coordenada y a ser analisada
     * @return True se o valor de x e y estão dentro da forma e False caso contário
     */
    public abstract boolean noLimite(int x, int y);
}

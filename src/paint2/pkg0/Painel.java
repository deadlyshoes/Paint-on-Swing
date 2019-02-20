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

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
  
        
public class Painel extends JPanel {
    private int x;
    private int y;
    private int altura;
    private int largura;
    private String tipoForma;
    private ArrayList<Forma> formas = new ArrayList();
    private Color[] cores = {Color.BLACK, Color.BLUE, Color.CYAN, 
                             Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
                             Color.MAGENTA, Color.ORANGE, Color.PINK,
                             Color.RED, Color.WHITE, Color.YELLOW};
    private int idCor;
    
    public Painel() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                formas.add(new Retângulo(0, 0, null, 0, 0)); //Dummy
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int tmpX = Math.min(x, e.getX());
                int tmpY = Math.min(y, e.getY());
                largura = Math.abs(x - e.getX());
                altura = Math.abs(y - e.getY());
                Retângulo formaNova = new Retângulo(tmpX, tmpY, cores[idCor], largura, altura);
                /**switch (tipoForma) {
                    case "Circunferência": formaNova = (Circunferência) formaNova;
                    case "Elipse": formaNova = (Elipse) formaNova;
                    case "Triângulo": formaNova = (Triângulo) formaNova;
                } **/
                formas.set(formas.size() - 1, formaNova);
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
            }
        });
    }
    
    public void mudarCor(int i) {
       idCor = i;
    }
    
    public void mudarForma(String f) {
        tipoForma = f;
    }

    /**public void removerForma(int x, int y) {
     * Fix
        Retângulo[] novasFormas = new Retângulo[100];
        for (Object object : col) {
            
        }
        }
            if (x < formas[i].getX() && x > (formas[i].getX() + formas[i].getLargura()))
                novasFormas[i] = formas[i];
        formas = novasFormas;
        repaint();
    } **/
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Forma forma : formas) {
            forma.Desenhar(g);
        }
    }
}

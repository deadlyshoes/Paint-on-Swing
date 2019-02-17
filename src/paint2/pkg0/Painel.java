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
  
        
public class Painel extends JPanel {
    private int x;
    private int y;
    private int altura;
    private int largura;
    private Forma[] formas = new Forma[100];
    private int ultimoObj;
    private Color[] cores = {color.BLACK, color.BLUE};
    private int idCor;
    
    public Painel() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                //Adicionar preview
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                largura = Math.abs(x - e.getX());
                altura = Math.abs(y - e.getY());
                x = Math.min(x, e.getX());
                y = Math.min(y, e.getY());
                formas[ultimoObj] = new Retângulo(x, y, cores[idCor], largura, altura);
                ultimoObj += 1;
                repaint();
            }
        });
    }
    
    public void mudarCor(int i) {
        idCor = i;
    }

    public void removerForma(int x, int y) {
        Retângulo[] novasFormas = new Retângulo[100];
        for (int i=0; i < ultimoObj; i++)
            if (x < formas[i].getX() && x > (formas[i].getX() + formas[i].getLargura()))
                novasFormas[i] = formas[i];
        formas = novasFormas;
        repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i=0; i < ultimoObj; i++)
            if (formas[i] != null)
                formas[i].Desenhar(g);
    }
}

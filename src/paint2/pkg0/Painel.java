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
    private int idTool = 1;
    private int idSelecionado = 0;
    
    public Painel() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                if (idTool == 1)
                    formas.add(new Retangulo(0, 0, null, 0, 0)); //Dummy
                else if (idTool == 2) { 
                    for (int i = formas.size() - 1; i >= 0; i--) {
                        if (regiaoOcupada(formas.get(i), x, y)) {
                            idSelecionado = i;
                            //Mover a forma selecionada para cima
                            Forma tmp = formas.get(idSelecionado);
                            formas.remove(idSelecionado);
                            formas.add(tmp);
                            idSelecionado = formas.size() - 1;
                            repaint();
                            //
                            break;
                        }
                    }
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                int tmpX = Math.min(x, e.getX());
                int tmpY = Math.min(y, e.getY());
                largura = Math.abs(x - e.getX());
                altura = Math.abs(y - e.getY());
                if (idTool == 1) {
                    Retangulo formaNova = new Retangulo(
                            tmpX, tmpY, cores[idCor], largura, altura);
                    /**switch (tipoForma) {
                    case "Circunferência": formaNova = (Circunferência) formaNova;
                    case "Elipse": formaNova = (Elipse) formaNova;
                    case "Triângulo": formaNova = (Triângulo) formaNova;
                    } **/
                    formas.set(formas.size() - 1, formaNova);
                }
                else if (idTool == 2) {
                    formas.get(idSelecionado).setX(
                            formas.get(idSelecionado).getX() + (e.getX() - x));
                    formas.get(idSelecionado).setY(
                            formas.get(idSelecionado).getY() + (e.getY() - y));
                    x = e.getX();
                    y = e.getY();
                }            
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (idTool == 1)
                    idSelecionado = formas.size() - 1;
            }
        });
    }
    
    public void mudarCor(int i) {
       idCor = i;
    }
    
    public void mudarForma(String f) {
        tipoForma = f;
    }
    
    public void mudarTool(int i) {
        idTool = i;
    }

    public void removerForma() {
        formas.remove(idSelecionado);
        idSelecionado --;
        repaint();
    }
    
    public boolean regiaoOcupada(Forma f, int x, int y) {
        return f.noLimite(x, y);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Forma forma : formas) {
            forma.Desenhar(g);
        }
    }
}
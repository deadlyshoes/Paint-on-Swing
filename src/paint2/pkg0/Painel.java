package paint2.pkg0;

/**
 *
 * @author João Neto
 * @author José Ilmar
 */

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
        
public class Painel extends JPanel {
    private Editor editor;
    private JListCustom jl;
    private int x;
    private int y;
    private int altura; 
    private int largura;
    private String tipoForma;
    private Color[] cores = {Color.BLACK, Color.BLUE, Color.CYAN,
                             Color.GRAY, Color.GREEN, Color.LIGHT_GRAY,
                             Color.MAGENTA, Color.ORANGE, Color.PINK,
                             Color.RED, Color.WHITE, Color.YELLOW};
    private int idCor;
    private String tool;
    private int idSelecionado;
    private boolean[] mouseCoords;
    
    /**
     * 
     */
    public Painel() {
        editor = Editor.getEditor();
        tipoForma = "Retangulo";
        tool = "Desenhar";
        idSelecionado = -1;
        mouseCoords = new boolean[4]; //Leste, Oeste, Norte e Sul
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                switch (tool) {
                    case "Desenhar":
                        editor.getFormas().add(new Retangulo(0, 0, 0, 0, null)); //Dummy
                        idSelecionado = -1;
                        break;
                    case "Selecionar":
                        idSelecionado = -1;
                        repaint();
                        for (int i = editor.getFormas().size() - 1; i >= 0; i--) {
                            if (regiaoOcupada(editor.getFormas().get(i), x, y)) {
                                idSelecionado = i;
                                jl.setSelectedIndex(i);
                                repaint();
                                break;
                            }
                        }   break;
                    case "Redimensionar":
                        Forma fSelec = editor.getFormas().get(idSelecionado);
                        if (x < fSelec.getX() && x > fSelec.getX() - 10) {
                            mouseCoords[0] = true;
                        }
                        else if (x > fSelec.getX() + fSelec.getLargura()
                                && x < fSelec.getX() + fSelec.getLargura() + 10) {
                            mouseCoords[1] = true;
                        }   if (y < fSelec.getY() && y > fSelec.getY() - 10) {
                            mouseCoords[2] = true;
                        }   if (y > fSelec.getY() + fSelec.getAltura()
                                && y < fSelec.getY() + fSelec.getAltura() + 10) {
                            mouseCoords[3] = true;
                        }   break;
                    default:
                        break;
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                switch (tool) {
                    case "Desenhar":
                        int tmpX = Math.min(x, e.getX());
                        int tmpY = Math.min(y, e.getY());
                        largura = Math.abs(x - e.getX());
                        altura = Math.abs(y - e.getY());
                        Forma formaNova = null;
                        switch (tipoForma) {
                            case "Retangulo": formaNova = new Retangulo(tmpX, tmpY, largura, altura, cores[idCor]);
                            break;
                            case "Elipse": formaNova = new Elipse(tmpX, tmpY, largura, altura, cores[idCor]);
                            break;
                            case "Triangulo": formaNova = new Triangulo(tmpX, tmpY, largura, altura, cores[idCor]);
                            break;
                            case "Circunferencia": 
                                if (Math.min(largura, altura) == altura) {
                                    if (e.getX() < x) {
                                        formaNova = new Elipse(x - altura, tmpY, altura, altura, cores[idCor]);
                                    }
                                    else {
                                        formaNova = new Elipse(tmpX, tmpY, altura, altura, cores[idCor]);
                                    }
                                }
                                else {
                                    if (e.getY() < y) {
                                        formaNova = new Elipse(tmpX, y - largura, largura, largura, cores[idCor]);
                                    }
                                    else {
                                        formaNova = new Elipse(tmpX, tmpY, largura, largura, cores[idCor]);
                                    }                                    
                                }
                                break;
                            case "TrianguloRet": formaNova = new TrianguloRet(tmpX, tmpY, largura, altura, cores[idCor]);
                            break;
                            case "Quadrado":
                                if (Math.min(largura, altura) == altura) {
                                    if (e.getX() < x) {
                                        formaNova = new Retangulo(x - altura, tmpY, altura, altura, cores[idCor]);
                                    }
                                    else {
                                        formaNova = new Retangulo(tmpX, tmpY, altura, altura, cores[idCor]);
                                    }
                                }
                                else {
                                    if (e.getY() < y) {
                                        formaNova = new Retangulo(tmpX, y - largura, largura, largura, cores[idCor]);
                                    }
                                    else {
                                        formaNova = new Retangulo(tmpX, tmpY, largura, largura, cores[idCor]);
                                    }                                    
                                }
                            break;
                        }   editor.adicionarForma(formaNova);
                        break;
                    case "Mover":
                        {
                            Forma fSelec = editor.getFormas().get(idSelecionado);
                            fSelec.setX(
                                    fSelec.getX() + (e.getX() - x));
                            fSelec.setY(
                                    fSelec.getY() + (e.getY() - y));
                            x = e.getX();
                            y = e.getY();
                            break;
                        }
                    case "Redimensionar":
                        {
                            Forma fSelec = editor.getFormas().get(idSelecionado);
                            if (mouseCoords[0] == true && x < fSelec.getX()) {
                                fSelec.setX(fSelec.getX() + (e.getX() - x));
                                fSelec.setLargura(fSelec.getLargura() + (x - e.getX()));
                            }
                            else if (mouseCoords[1] == true && x > fSelec.getX() + fSelec.getLargura()) {
                                fSelec.setLargura(fSelec.getLargura() + (x - e.getX()) * -1);
                            }       if (mouseCoords[2] == true && y < fSelec.getY()) {
                                fSelec.setY(fSelec.getY() + (e.getY() - y));
                                fSelec.setAltura(fSelec.getAltura() + (y - e.getY()));
                            }
                            else if (mouseCoords[3] == true && y > fSelec.getY() + fSelec.getAltura()) {
                                fSelec.setAltura(fSelec.getAltura() + (e.getY() - y));
                            }       x = e.getX();
                            y = e.getY();
                            break;
                        }
                    default:
                        break;
                }
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (tool == "Redimensionar")
                    mouseCoords = new boolean[4];
            }
        });
    }
    
    /**
     * 
     * @param i 
     */
    public void mudarCor(int i) {
       idCor = i;
    }
    
    public void Repintar() {
        editor.getFormas().get(idSelecionado).setCor(cores[idCor]);
        editor.atualizarFormasJL();
        repaint();
    }
    
    /**
     * 
     * @param f 
     */
    public void mudarForma(String f) {
        tipoForma = f;
    }
    
    /**
     * 
     * @param t
     */
    public void mudarTool(String t) {
        tool = t;
    }

    /**
     * 
     */
    public void Remover() {
        editor.removerForma(idSelecionado);
        idSelecionado = -1;
        repaint();
    }
    
    /**
     * 
     */
    public void Desfazer() {
        editor.restaurarForma();
        repaint();
    }
    
    /**
     * 
     * @param f
     * @param x
     * @param y
     * @return 
     */
    public boolean regiaoOcupada(Forma f, int x, int y) {
        return f.noLimite(x, y);
    }
    
    /**
     * 
     * @param id 
     */
    public void setId(int id) {
        idSelecionado = id;
    }
    
    /**
     * 
     * @return 
     */
    public int getId() {
        return idSelecionado;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        editor.getFormas().forEach((forma) -> {
            forma.Desenhar(g);
        });
        if (idSelecionado != -1) {
            Forma fSelec = editor.getFormas().get(idSelecionado);
            if (fSelec.getCor() == Color.BLACK) {
                g.setColor(Color.ORANGE);
            }
            else if (fSelec.getCor() == Color.BLUE) {
                g.setColor(Color.BLACK);
            }
            else {
                g.setColor(Color.GREEN);
            }
            g.drawRect(fSelec.getX(), fSelec.getY(), fSelec.getLargura(), fSelec.getAltura());
        }
    }
    
    /**
     * 
     * @param jl 
     */
    public void Atualizar(JListCustom jl) {
        this.jl = jl;
    }
}

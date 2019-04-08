package paint2.pkg0;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
        
/**
 *
 * @author João Neto
 * @author José Ilmar
 */

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
     * Eventos de clique e a instanciação das variáveis
     */
    public Painel() {
        editor = Editor.getEditor();
        tipoForma = "Retangulo"; //A forma inicial é o retângulo
        tool = "Desenhar"; //A ferramenta inicial é Desenhar
        idSelecionado = -1; //Inicialmente, não deve haver nenhuma forma selecionada
        mouseCoords = new boolean[4]; //Leste, Oeste, Norte e Sul
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                switch (tool) {
                    case "Desenhar":
                        //Adiciona um retângulo sem dimensão e cor e muda para nenhuma forma selecionada
                        editor.getFormas().add(new Retangulo(0, 0, 0, 0, null)); //Dummy
                        idSelecionado = -1;
                        break;
                    case "Selecionar":
                        //Muda para nenhuma forma selecionada e seleciona a que está nos limites
                        //A prioridade será a que vem por último, i. e., a última forma desenhada
                        idSelecionado = -1;
                        repaint();
                        for (int i = editor.getFormas().size() - 1; i >= 0; i--) {
                            if (regiaoOcupada(editor.getFormas().get(i), x, y)) {
                                idSelecionado = i;
                                jl.setSelectedIndex(i); //Seleciona na JList
                                repaint();
                                break;
                            }
                        }   break;
                    case "Redimensionar":
                        //Detecta a coordenada do clique
                        //Norte, Sul, Leste ou Oeste, sendo possível até duas coordenadas 
                        try {
                            Forma fSelec = editor.getFormas().get(idSelecionado);
                            if (x < fSelec.getX() && x > fSelec.getX() - 10) {
                                mouseCoords[0] = true;
                            }
                            else if (x > fSelec.getX() + fSelec.getLargura()
                                     && x < fSelec.getX() + fSelec.getLargura() + 10) {
                                mouseCoords[1] = true;
                            }   
                            if (y < fSelec.getY() && y > fSelec.getY() - 10) {
                                mouseCoords[2] = true;
                            }
                            if (y > fSelec.getY() + fSelec.getAltura()
                                && y < fSelec.getY() + fSelec.getAltura() + 10) {
                                mouseCoords[3] = true;
                            }   
                        } catch (IndexOutOfBoundsException ex) {
                        } finally { break; }
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
                        //O menor x e o menor y serão a origem da forma
                        //As final serão dadas pela soma com a largura e com a altura
                        //A circunferência e o quadrado são casos especiais de
                        //elipse e retângulo, respectivamente
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
                        //Muda a origem da formas
                        try {
                            Forma fSelec = editor.getFormas().get(idSelecionado);
                            fSelec.setX(
                                    fSelec.getX() + (e.getX() - x));
                            fSelec.setY(
                                    fSelec.getY() + (e.getY() - y));
                            x = e.getX();
                            y = e.getY();
                            editor.setStatusSalvo(false); 
                            break;
                        } catch (IndexOutOfBoundsException ex) {
                        } finally { break; }
                    case "Redimensionar":
                        //Muda a origem, a largura ou a altura de acordo com as coordenadas
                        //no evento de clique
                        try {
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
                            editor.setStatusSalvo(false);
                            break;
                        } catch (IndexOutOfBoundsException ex) {
                        } finally { break; }
                    default:
                        break;
                }
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //Resetar as coordenadas do redimensionar
                if (tool == "Redimensionar")
                    mouseCoords = new boolean[4];
            }
        });
    }
    
    /**
     * 
     * @param i id da cor no array de cores
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
     * @param f tipo da forma a ser desenhada a partir do momento
     */
    public void mudarForma(String f) {
        tipoForma = f;
    }
    
    /**
     * 
     * @param t nome da forma a ser adotada
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
    
    public void Limpar() {
        editor.limparFormas();
        idSelecionado = -1;
        repaint();
    }
    
    /**
     * 
     * @param f forma que será analizada
     * @param x coordenada x do clique
     * @param y coordenada y do clique
     * @return um booleano que indica se o clique está dentro(True) ou não(False)
     */
    public boolean regiaoOcupada(Forma f, int x, int y) {
        return f.noLimite(x, y);
    }
    
    /**
     * 
     * @param id utilizado na mudança da forma selecionada
     */
    public void setId(int id) {
        idSelecionado = id;
    }
    
    /**
     * 
     * @return id no array da forma selecionada
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
     * @param jl JList necessária para a ferramenta selecionar
     */
    public void Atualizar(JListCustom jl) {
        this.jl = jl;
    }
}

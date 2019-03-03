/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint2.pkg0;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author ilmar
 */
public class JListCustom<T> extends JList {
    //O campo model no design JFrame deve estar em branco
    DefaultListModel listModel = new DefaultListModel();
    ArrayList<T> objetos = new ArrayList();
    
    public void moverCima() {
        int i = getSelectedIndex();
        
        if (i > 0) {
            T itemSelecionado = objetos.get(i);
            objetos.remove(i);
            objetos.add(i - 1, itemSelecionado);
            Atualizar(objetos);
            setSelectedIndex(i - 1);
        }
    }
    
    public void moverBaixo() {   
        int i = getSelectedIndex();
        
        if (i < listModel.getSize() - 1) {
            T itemSelecionado = objetos.get(i);
            objetos.remove(i);
            objetos.add(i + 1, itemSelecionado);
            Atualizar(objetos);
            setSelectedIndex(i + 1);
        }
    }
    
    public void Atualizar(ArrayList<T> alist) {
        objetos = alist;
        listModel.clear();
        for (T objeto : objetos) {
            listModel.addElement(objeto);
        }
        setModel(listModel);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint2.pkg0;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author ilmar
 * @param <Forma>
 */
public class JListCustom<Forma> extends JList {
    DefaultListModel listModel;
    ArrayList<Forma> formas;
    
    public void Atualizar(Editor ed) {
        formas = (ArrayList<Forma>) ed.getFormas();
        listModel = ed.getFormasJL();
        setModel(listModel);
    }
    
    public boolean moverCima() {
        int i = getSelectedIndex();
        
        if (i > 0) {
            Forma itemSelecionado = formas.get(i);
            formas.remove(i);
            formas.add(i - 1, itemSelecionado);
            listModel.clear();
            for (Forma forma : formas) {
                listModel.addElement(forma);
            }
            setSelectedIndex(i - 1);
            return true;
        }
        return false;
    }
    
    public boolean moverBaixo() {   
        int i = getSelectedIndex();
        
        if (i < listModel.getSize() - 1) {
            Forma itemSelecionado = formas.get(i);
            formas.remove(i);
            formas.add(i + 1, itemSelecionado);
            listModel.clear();
            for (Forma forma : formas) {
                listModel.addElement(forma);
            }
            setSelectedIndex(i + 1);
            return true;
        }
        return false;
    }
}

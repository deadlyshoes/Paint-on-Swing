/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint2.pkg0;

import javax.swing.AbstractListModel;
import java.util.ArrayList;

/**
 *
 * @author ilmar
 */
public class MyListModel extends AbstractListModel {
    private ArrayList<Forma> formas;
    
    public MyListModel() {
        formas = new ArrayList();
    }
    
    public void adicionarForma(int i, Forma el) {
        formas.add(i, el);
        fireIntervalAdded(this, formas.size() - 1, formas.size() - 1);
    }
    
    public void removerForma(int i) {
        formas.remove(i);
        fireIntervalRemoved(this, formas.size() - 1, formas.size() - 1);
    }

    @Override
    public int getSize() {
        return formas.size();
    }

    @Override
    public Forma getElementAt(int i) {
        return formas.get(i); 
    }
}

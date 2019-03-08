/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint2.pkg0;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author ilmar
 */
public class Editor {
    private ArrayList<Forma> formas;
    private ArrayList<Forma> formasBackup;
    private DefaultListModel formasJL;
    
    public Editor() {
        formas = new ArrayList();
        formasBackup = new ArrayList();
        formasJL = new DefaultListModel();
    }
    
    public void adicionarForma(Forma forma) {
        formas.add(forma);
        atualizarFormasJL();
    }
    
    public void removerForma(int i) {
        formasBackup.add(formas.get(i));
        formas.remove(i);
        atualizarFormasJL();
    }
    
    public void restaurarForma() {
        if (formasBackup.size() > 0) {
            formas.add(formasBackup.get(formasBackup.size() - 1));
            formasBackup.remove(formasBackup.size() - 1);
        }
    }
    
    private void atualizarFormasJL() {
        formasJL.clear();
        for (Forma forma : formas) {
            formasJL.addElement(forma);
        }
    }
    
    public ArrayList<Forma> getFormas() {
        return formas;
    }
    
    public DefaultListModel getFormasJL() {
        return formasJL;
    }
    
    public void Salvar(String arquivo) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        for (Forma forma : formas) {
            oos.writeObject(forma);
        }
        
        oos.flush();
        oos.close();
    }
    
    public void Carregar(String arquivo) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(arquivo);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        formas.clear();
        formasBackup.clear();
        
        try {
            while (true) {
                formas.add((Forma) ois.readObject());
            }
        } catch (EOFException err) {
        } finally { ois.close(); }
    }
}
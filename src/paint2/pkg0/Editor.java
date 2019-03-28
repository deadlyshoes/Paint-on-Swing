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
 * @author João Neto
 * @author José Ilmar
 */
public class Editor {
    private ArrayList<Forma> formas;
    private ArrayList<Forma> formasBackup;
    private DefaultListModel formasJL;
    private int idSelecionado;
    private static Editor instance;
    
    private Editor() {
        formas = new ArrayList();
        formasBackup = new ArrayList();
        formasJL = new DefaultListModel();
    }
    
    public static Editor getEditor() {
        if (instance == null)
            instance = new Editor();
        return instance;
    }
    
    /**
     * 
     * @param i 
     */
    public void setId(int i) {
        idSelecionado = i;
    }
    
    public int getForma() {
        return idSelecionado;
    }
    
    /**
     * 
     * @param forma 
     */
    public void adicionarForma(Forma forma) {
        formas.add(forma);
        atualizarFormasJL();
    }
    
    /**
     * 
     */
    public void removerForma() {
        formasBackup.add(formas.get(idSelecionado));
        formas.remove(idSelecionado);
        atualizarFormasJL();
    }
    
    /**
     * 
     */
    public void restaurarForma() {
        if (formasBackup.size() > 0) {
            formas.add(formasBackup.get(formasBackup.size() - 1));
            formasBackup.remove(formasBackup.size() - 1);
        }
    }
    
    /**
     * 
     */
    private void atualizarFormasJL() {
        formasJL.clear();
        formas.forEach((forma) -> {
            formasJL.addElement(forma);
        });
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Forma> getFormas() {
        return formas;
    }
    
    /**
     * 
     * @return 
     */
    public DefaultListModel getFormasJL() {
        return formasJL;
    }
    
    /**
     * 
     * @param arquivo
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void Salvar(String arquivo) throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(arquivo);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Forma forma : formas) {
            oos.writeObject(forma);
        }
        oos.flush();
    }
    
    /**
     * 
     * @param arquivo
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void Carregar(String arquivo) throws FileNotFoundException, IOException, ClassNotFoundException {
        formas.clear();
        formasBackup.clear();
        
        FileInputStream fis = new FileInputStream(arquivo);
        ObjectInputStream ois = new ObjectInputStream(fis);     
        
        try {
            while (true) {
                formas.add((Forma) ois.readObject());
            }
        } catch (EOFException err) {
        } finally { ois.close(); }
    }
}

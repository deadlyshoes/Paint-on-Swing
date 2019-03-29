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
    private boolean salvo; //Alguma mudança nas formasJL torna essa flag false
    private static Editor instance;
    
    private Editor() {
        formas = new ArrayList();
        formasBackup = new ArrayList();
        formasJL = new DefaultListModel();
        salvo = false;
    }
    
    public static Editor getEditor() {
        if (instance == null)
            instance = new Editor();
        return instance;
    }
    
    /**
     * 
     * @param forma 
     */
    public void adicionarForma(Forma forma) {
        formas.set(formas.size() - 1, forma);
        atualizarFormasJL(); //Fix retangulo null
    }

    /**
     * 
     */
    public void removerForma(int i) {
        try {
            formasBackup.add(formas.get(i));
            formas.remove(i);
            atualizarFormasJL();
        } catch (IndexOutOfBoundsException ex) {}
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
    public void atualizarFormasJL() {
        formasJL.clear();
        for (Forma forma : formas) {
            formasJL.add(0, forma);
        }
        salvo = false;
    }
    
    public void limparFormas() {
        formas.clear();
        formasBackup.clear();
        atualizarFormasJL();
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
    
    public void setStatusSalvo(boolean s) {
        salvo = s;
    }
    
    public boolean getStatusSalvo() {
        return salvo;
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
        salvo = true;
    }
    
    /**
     * 
     * @param arquivo
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void Carregar(String arquivo) throws FileNotFoundException, IOException, ClassNotFoundException {
        limparFormas();
        
        FileInputStream fis = new FileInputStream(arquivo);
        ObjectInputStream ois = new ObjectInputStream(fis);
        
        try {
            while (true) {
                formas.add((Forma) ois.readObject());
            }
        } catch (EOFException err) {
            atualizarFormasJL();
            salvo = true;
        } finally { ois.close(); }
    }
}

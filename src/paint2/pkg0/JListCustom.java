package paint2.pkg0;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author João Neto
 * @author José Ilmar
 * @param <Forma>
 */
public class JListCustom<Forma> extends JList {
    DefaultListModel listModel;
    ArrayList<Forma> formas;
    
    public JListCustom() {
        formas = (ArrayList<Forma>) Editor.getEditor().getFormas();
        listModel = Editor.getEditor().getFormasJL();
        setModel(listModel);
    }
    
    /**
     * 
     * @return 
     */
    public boolean moverCima() {
        int i = getSelectedIndex();
        
        if (i > 0) {
            Forma itemSelecionado = formas.get(i);
            formas.remove(i);
            formas.add(i - 1, itemSelecionado);
            listModel.clear();
            formas.forEach((forma) -> {
                listModel.add(0, forma);
            });
            setSelectedIndex(i - 1);
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @return 
     */
    public boolean moverBaixo() {   
        int i = getSelectedIndex();
        
        if (i < listModel.getSize() - 1) {
            Forma itemSelecionado = formas.get(i);
            formas.remove(i);
            formas.add(i + 1, itemSelecionado);
            listModel.clear();
            formas.forEach((forma) -> {
                listModel.add(0, forma);
            });
            setSelectedIndex(i + 1);
            return true;
        }
        return false;
    }
}
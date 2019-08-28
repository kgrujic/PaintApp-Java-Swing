package paint.strategy.exportfiles;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ExportLog implements ExportStrategy {

    private DefaultListModel<String> listModel;

    public ExportLog(DefaultListModel<String> listModel){
        this.listModel = listModel;

    }

    public void SaveLogToTextFile(List<String> text)  throws IOException {


        FileOutputStream f = null;
        DataOutputStream h = null;
        FileDialog d = new FileDialog(new JFrame(), "Save", FileDialog.SAVE);
        d.setVisible(true);
        String dir;
        dir = d.getDirectory();
        File oneFile = new File(dir + d.getFile() + ".txt");
        try {
            oneFile.createNewFile();
        } catch (IOException e1) {

            e1.printStackTrace();
        }
        try {
            f = new FileOutputStream(oneFile);
            h = new DataOutputStream(f);
            for (String de : text) {
                h.writeBytes(de);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }finally {
            try {
                h.close();
                f.close();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }


    @Override
    public void exportFile() {


            try {
                List<String> elements = new ArrayList<>();

                for (int i = 0; i < listModel.getSize(); i++) {
                    String item = listModel.getElementAt(i);
                    elements.add(item);
                }

                SaveLogToTextFile(elements);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }




package paint.strategy.importfiles;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportLog implements ImportStrategy {

    private DefaultListModel<String> listModel;

    public ImportLog(DefaultListModel<String> listModel){
        this.listModel = listModel;

    }



    public String chooseFile() {

        FileDialog dialog = new FileDialog((Frame) null, "Selected File to Open");

        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        String file = dialog.getFile();

        return file;
    }

    @Override
    public void importFile() {
        BufferedReader reader;
        String fileName = chooseFile();

        if(fileName != null) {

            try {
                reader = new BufferedReader(new FileReader(fileName));
                String line = reader.readLine();
                while (line != null) {

                    listModel.addElement(line);
                    // read next line
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

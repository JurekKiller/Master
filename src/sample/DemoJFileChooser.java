package sample;

import javafx.application.Application;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;




public abstract class DemoJFileChooser extends Application
             {

    private String choosertitle;

    public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Images", ImageIO.getReaderFileSuffixes());

            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle(choosertitle);
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setFileFilter(fileNameExtensionFilter);
            chooser.setAcceptAllFileFilterUsed(true);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try {
                    System.out.println("getCurrentDirectory(): "
                            + chooser.getCurrentDirectory());
                    System.out.println("getSelectedFile() : "
                            + chooser.getSelectedFile());
                    File aa= chooser.getSelectedFile();
                    JComponent aaa =chooser.createToolTip();
                    String a = "D" ;
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar a imagem!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }


    public Dimension getPreferredSize(){
            return new Dimension(200, 200);
        }

        public static void main(String s[]) {
            JFrame frame = new JFrame("");
         //   DemoJFileChooser panel = new DemoJFileChooser();
            frame.addWindowListener(
                    new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    }
            );
         //   frame.getContentPane().add(panel,"Center");
         //   frame.setSize(panel.getPreferredSize());
            frame.setVisible(true);
        }
    }




package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;


public class DemoJFileChooser extends JPanel
            implements ActionListener {
        JButton go;

        JFileChooser chooser;
        String choosertitle;

        public DemoJFileChooser() {
            go = new JButton("Do it");
            go.addActionListener(this);
            add(go);
        }

        public void actionPerformed(ActionEvent e) {
            int result;

            chooser = new JFileChooser();
           String v= Dictionary.extensions.get(0);
            FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("Images", ImageIO.getReaderFileSuffixes());

    //    String h =    Dictionary.extensions.stream().collect(Collectors.joining(",","",""));



            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle(choosertitle);
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setFileFilter(fileNameExtensionFilter);
            chooser.setAcceptAllFileFilterUsed(true);
            //
            // disable the "All files" option.
            //


            //
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
               File a =  chooser.getSelectedFile();
                System.out.println("getCurrentDirectory(): "
                        +  chooser.getCurrentDirectory());
                System.out.println("getSelectedFile() : "
                        +  chooser.getSelectedFile());
            }
            else {
                System.out.println("No Selection ");
            }
        }

        public Dimension getPreferredSize(){
            return new Dimension(200, 200);
        }

        public static void main(String s[]) {
            JFrame frame = new JFrame("");
            DemoJFileChooser panel = new DemoJFileChooser();
            frame.addWindowListener(
                    new WindowAdapter() {
                        public void windowClosing(WindowEvent e) {
                            System.exit(0);
                        }
                    }
            );
            frame.getContentPane().add(panel,"Center");
            frame.setSize(panel.getPreferredSize());
            frame.setVisible(true);
        }
    }




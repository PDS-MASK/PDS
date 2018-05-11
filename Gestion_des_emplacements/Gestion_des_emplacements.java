
package gestion_des_emplacements;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author Sami
 */
public class Gestion_des_emplacements {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

                EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new LocationForm());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    
}

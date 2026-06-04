package sistembangungeo;

import java.awt.HeadlessException;
import javax.swing.*;
import java.awt.event.*;

public class SistemBangunGeo {
    public static void main(String[] args) {
        try{
            SwingUtilities.invokeLater(() -> {
                new View().setVisible(true);
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author Alejandro Valdes
 */

public class Main {
    public static void main(String[] args){
        BrickingBad jueJuego = new BrickingBad();
        jueJuego.setTitle("Bricking Bad");
        jueJuego.setVisible(true);
        jueJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

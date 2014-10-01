
import java.awt.Image;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro Valdes
 */
public class PowerUp extends Objeto {
    
    private int iPoder;
    /**
     * PowerUp metodo constructor
     * @param iX posicion en x
     * @param iY posicion en y
     * @param imaImagen imagen a usar sera random
     */
    
    PowerUp(int iX, int iY, Image imaImagen, int iPoder) {
        super(iX, iY, imaImagen);
        this.setVelocidad(2);  
        this.iPoder = iPoder;
    }
    
    public void muevete(){
        this.setY(this.getY() - this.getVelociad());
    }
    
    public int getPoder(){
        return this.iPoder;
    }
    
    
    
}

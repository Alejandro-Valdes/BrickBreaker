
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro Valdes
 */
public class Boss extends Brick{ 
    
    private ArrayList arrFases;
    private int iVidas;
    private int iGolpes;
    /**
     * Boss
     * Metodod constructor en la clase Boss
     * extends Brick el cual extiende objeto
     * 
     */
    
    public Boss(int iX, int iY, int iVidas, Image imaImagen) {
       super(iX,iY,iVidas,imaImagen); 
       arrFases = new ArrayList();
       arrFases.add(imaImagen);
       //this.iGolpes = 0;
    }
   
    /*
    /checar
    */
    public void actualizaFase(int iGolpes){
        if(!this.estaDestruido()){
            this.setImagen((Image) arrFases.get(iGolpes));
        }
    }
    
    public synchronized void agregaFase(Image imaImagen){
        arrFases.add(imaImagen);
    }
    
    /*@Override
    public void agregarGolpe(){
        iGolpes++;
        if(iGolpes == iVidas){
            setDestruido(true);
        }
    }*/
   
}


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
   
    /**
     * actualizaFase
     * metodo que va cambiando las imagenes dependiendo de los golpes
     *
     * @param iGolpes es el numeor de golpes que a recibido
    */
    public void actualizaFase(int iGolpes){
        if(!this.estaDestruido()){
            this.setImagen((Image) arrFases.get(iGolpes));
        }
    }
    /**
     * revive
     * metodo que revive al villano para que tome otra cara
     */
    public void revive(){
        if(this.estaDestruido()){
            setDestruido(false);
        }
    }
    
    /**
     * agregaFase
     * metodo para agregar imagenes
     * @param imaImagen es la imagen a agregar
     */
    public synchronized void agregaFase(Image imaImagen){
        arrFases.add(imaImagen);
    }
    
    /**
     * borraTodo
     * metodo que borra todas las fases del arrylist para empezar otro nivel
     */
    public void borraTodo(){
        int Ii = 0;
        this.iGolpes = 0;
        while(arrFases.size() != 0){
            arrFases.remove(Ii);
        }
    }
   
}

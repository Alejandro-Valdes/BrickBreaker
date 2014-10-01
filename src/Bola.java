
import java.awt.Image;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro Valdes
 */
public class Bola extends Objeto{
    
    private int iDirX;
    private int iDirY;
    /**
     * Bola
     * 
     * Metodo constructor usado para crear el objeto Bola
     * creando el icono a partir de una imagen
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * @param iY es la <code>posicion en y</code> del objeto.
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public Bola(int iX, int iY, Image imaImagen) {
        super(iX, iY, imaImagen); 
    }
    
    /**
     * muevete
     * 
     * Metodo para que se mueva la bola
     */
    
    public void muevete() {
        this.setX(this.getX() + this.getDirX());
        this.setY(this.getY() + this.getDirY());
    }
    
    /**
     * setDirX
     * Metodo para asignar dirX
     * @param iDirX dir en x
     */
    
    public void setDirX(int iDirX) {
        this.iDirX = iDirX;
    }
    
    /**
     * setDirY
     * Metodo para asignar direccion en y
     * @param iDirY
     */
    
    public void setDirY (int iDirY){
        this.iDirY = iDirY;
    }
    
    /**
     * getDirX
     * Metodo para obtener dirX
     * @return iDirX 
     */
    
    public int getDirX() {
        return this.iDirX;
    }
    
    /**
     * getDirY
     * Metodo para obtener direccion en y
     * @return iDirY
     */
    
    public int getDirY (){
         return this.iDirY;
    }
    
    
}

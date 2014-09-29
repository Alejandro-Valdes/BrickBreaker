
import java.awt.Image;
import java.awt.Rectangle;
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
public class Paddle extends Objeto{
    
    /**
     * Paddle
     * 
     * Metodo constructor usado para crear el objeto Paddle
     * creando el icono a partir de una imagen
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * @param iY es la <code>posicion en y</code> del objeto.
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public Paddle(int iX, int iY, Image imaImagen) {
        super(iX, iY, imaImagen);  
        this.setVelocidad(5);
    }

    /** 
     * colisionaPorAbajo
     * 
     * Metodo para revisar si un objeto <code>Personaje</code> colisiona con otro
     * esto se logra con un objeto temporal de la clase <code>Rectangle</code>
     * 
     * @param objParametro es el objeto <code>Personaje</code> con el que se compara
     * @return  un valor true si esta colisionando y false si no
     * 
     */
    public boolean colisionaPorAbajo(Objeto objParametro){
        // creo un objeto rectangulo a partir de este objeto
        Rectangle recObjeto = new Rectangle(this.getX(), 
                this.getY() + this.getAlto() - 10,
                this.getAncho(), 10);
        
        // creo un objeto rectangulo a partir del objeto Animal parametro
        Rectangle recParametro = new Rectangle(objParametro.getX(),
                objParametro.getY(), objParametro.getAncho(),
                objParametro.getAlto());
        
        // si se colisionan regreso verdadero, sino regreso falso
        return recObjeto.intersects(recParametro);
    }
}



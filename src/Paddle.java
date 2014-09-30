
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *NO SE USA
 * @author Alejandro Valdes
 */
public class Paddle extends Animacion {
    private int iX;
    private int iY;
    private int iVelocidad;
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
    public Paddle(int iX, int iY) {
        super(iX, iY);  
        this.setVelocidad(5);
    }

    /**
        * setX
        * 
        * Metodo modificador usado para cambiar la posicion en x del objeto
        * 
        * @param iX es la <code>posicion en x</code> del objeto.
        * 
        */
        public void setX(int iX) {
            this.iX = iX;
        }

        /**
         * getX
         * 
         * Metodo de acceso que regresa la posicion en x del objeto 
         * 
         * @return iX es la <code>posicion en x</code> del objeto.
         * 
         */
        public int getX() {
            return iX;
        }

        /**
         * setY
         * 
         * Metodo modificador usado para cambiar la posicion en y del objeto 
         * 
         * @param iY es la <code>posicion en y</code> del objeto.
         * 
         */
        public void setY(int iY) {
                this.iY = iY;
        }

        /**
         * getY
         * 
         * Metodo de acceso que regresa la posicion en y del objeto 
         * 
         * @return posY es la <code>posicion en y</code> del objeto.
         * 
         */
        public int getY() {
            return iY;
        }
        
        /**
         * setVelocidad
         * 
         * Metodo modificador usado para cambiar la velocidad del objeto 
         * 
         * @param iVelocidad es la <code>velocidad</code> del objeto.
         * 
         */
        public void setVelocidad(int iVelocidad) {
                this.iVelocidad= iVelocidad;
        }

        /**
         * getVelociad
         * 
         * Metodo de acceso que regresa la velocidad del objeto 
         * 
         * @return iVelocidad es la <code>velocidad</code> del objeto.
         * 
         */
        public int getVelociad() {
            return iVelocidad;
        }

        public boolean colisionaPorAbajo(Objeto objParametro){
            // creo un objeto rectangulo a partir de este objeto
             Rectangle recObjeto = new Rectangle(this.getX(), 
                     this.getY() + this.getImagen().getHeight(null) - 10,
                     this.getImagen().getWidth(null), 10);

             // creo un objeto rectangulo a partir del objeto Animal parametro
             Rectangle recParametro = new Rectangle(objParametro.
                     getX(), objParametro.getY(), 
                     objParametro.getAncho(),objParametro.getAlto());

              // si se colisionan regreso verdadero, sino regreso falso
              return recObjeto.intersects(recParametro);
        }
        
}



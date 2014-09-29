/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Alejandro Valdes
 */
public class Objeto {
    //variables
    private int iX;
    private int iY;
    private int iVelocidad;   // velocidad
    private ImageIcon imiIcono;	//icono.
    
    /**
     * Objeto
     * 
     * Metodo constructor usado para crear el objeto Objeto
     * creando el icono a partir de una imagen
     * 
     * @param iX es la <code>posicion en x</code> del objeto.
     * @param iY es la <code>posicion en y</code> del objeto.
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public Objeto(int iX, int iY, Image imaImagen) {
        this.iX = iX;
        this.iY = iY;
        imiIcono = new ImageIcon(imaImagen);
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
     * set iVelocidad
     * 
     * Metodo de acceso que regresa la posicion en y del objeto 
     * 
     * @param iVelocidad es la <code>velocidad</code> del objeto.
     * 
     */
    
    public void setVelocidad(int iVelocidad){
        this.iVelocidad = iVelocidad;
    }
    
    /**
     * getVelocidad
     * 
     * Metodo de acceso que regresa la velocidad  
     * 
     * @return un <code>entero</code> que es la velocidad.
     * 
     */
    public int getVelociad() {
        return iVelocidad;
    }
    
    /**
     * getAncho
     * 
     * Metodo de acceso que regresa el ancho del icono 
     * 
     * @return un <code>entero</code> que es el ancho del icono.
     * 
     */
    public int getAncho() {
        return imiIcono.getIconWidth();
    }
    
    /**
     * getAlto
     * 
     * Metodo de acceso que regresa el alto del icono 
     * 
     * @return un <code>entero</code> que es el alto del icono.
     * 
     */
    public int getAlto() {
        return imiIcono.getIconHeight();
    }
    
    /**
     * setImageIcon
     * 
     * Metodo modificador usado para cambiar el icono del objeto
     * 
     * @param imiIcono es el <code>icono</code> del objeto.
     * 
     */
    public void setImageIcon(ImageIcon imiIcono) {
        this.imiIcono = imiIcono;
    }

    /**
     * getImageIcon
     * 
     * Metodo de acceso que regresa el icono del objeto 
     * 
     * @return imiIcono es el <code>icono</code> del objeto.
     * 
     */
    public ImageIcon getImageIcon() {
        return imiIcono;
    }

    /**
     * setImagen
     * 
     * Metodo modificador usado para cambiar el icono de imagen del objeto
     * tomandolo de un objeto imagen
     * 
     * @param imaImagen es la <code>imagen</code> del objeto.
     * 
     */
    public void setImagen(Image imaImagen) {
        this.imiIcono = new ImageIcon(imaImagen);
    }

    /**
     * agen
     * 
     * Metodo de acceso que regresa la imagen que representa el icono del objeto
     * 
     * @return la imagen a partide del <code>icono</code> del objeto.
     * 
     */
    public Image getImagen() {
        return imiIcono.getImage();
    }
    /** 
     * colisiona
     * 
     * Metodo para revisar si un objeto <code>Personaje</code> colisiona con 
     * otro
     * esto se logra con un objeto temporal de la clase <code>Rectangle</code>
     * 
     * @param objParametro es el objeto <code>Personaje</code> con el que se
     * compara
     * @return  un valor true si esta colisionando y false si no
     * 
     */
    public boolean colisiona(Objeto objParametro){
        // creo un objeto rectangulo a partir de este objeto
        Rectangle recObjeto = new Rectangle(this.getX(), 
                this.getY(),
                this.getAncho(), this.getAlto());
        
        // creo un objeto rectangulo a partir del objeto Animal parametro
        Rectangle recParametro = new Rectangle(objParametro.getX(),
                objParametro.getY(), objParametro.getAncho(),
                objParametro.getAlto());
        
        // si se colisionan regreso verdadero, sino regreso falso
        return recObjeto.intersects(recParametro);
    }
}


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
public class Brick extends Objeto{
    private int iX;
    private int iY;
    private int iVidas;
    private int iGolpes;
    private boolean bDestruido;
    private ImageIcon imiIcono;	//icono.

    /**
     * Brick
     * Metodo constructor de la clase Brick
     * @param iX es la pos en x
     * @param iY es la pos en y
     * @param iVidas es cuantas vidas
     * @param imaImagen es la imagen escogida entre el banco de imagenes
     * 
     */
    
    public Brick(int iX, int iY, int iVidas, Image imaImagen){
        super(iX,iY,imaImagen);
        this.iVidas = iVidas;
        this.iGolpes = 0;
        this.bDestruido = false;     
    }
    
    /**
     * agregarGolpe
     * Metodo para agregar golpes
     */
    
    public void agregarGolpe(){
        iGolpes++;
        if(iGolpes == iVidas) {
            setDestruido(true);
        }
    }
    /**
     * setDestruido
     * Saber si ya se destruyo el bloque
     * @param bDestruido parametro de entrada
     */
    public void setDestruido(boolean bDestruido) {
        this.bDestruido = bDestruido;
    }
    
    /**
     * estaDestruido
     * Metodo booleano que verifica si ya se destruyo
     * @return bDestruido
     */
    public boolean estaDestruido() {
        return bDestruido;
    }
    
    /**
     * colisionaPorArriba metodo que checa si choco por arriba
     * @param bolBola con este objeto se hace el rectangulo de bola
     * @return booleano que indica si ocurre o no
     */
    
    public boolean colisionaPorArriba(Bola bolBola){
        //Creo un rectangulo de la parte superior del brick
        Rectangle recBrick = new Rectangle(this.getX(),
        this.getY(),this.getAncho(), 2);
        
         // creo un objeto rectangulo a partir del objeto Bola parametro
        Rectangle recParametro = new Rectangle(bolBola.getX(),
                bolBola.getY(), bolBola.getAncho(),
                bolBola.getAlto());
        
        return recBrick.intersects(recParametro);
    }
    
    /**
     * colisionaPorAbajo metodo que checa si choco por abajo
     * @param bolBola con este objeto se hace el rectangulo de bola
     * @return booleano que indica si ocurre o no
     */
    public boolean colisionaPorAbajo(Bola bolBola){
        //Creo un rectangulo de la parte inferior del brick
        Rectangle recBrick = new Rectangle(this.getX(),
        this.getY() + this.getAlto() - 2,this.getAncho(), 2);
        
         // creo un objeto rectangulo a partir del objeto Bola parametro
        Rectangle recParametro = new Rectangle(bolBola.getX(),
                bolBola.getY(), bolBola.getAncho(),
                bolBola.getAlto());
        
        return recBrick.intersects(recParametro);
    }
    
    /**
     * colisionaPorIzq metodo que checa si choco por izq
     * @param bolBola con este objeto se hace el rectangulo de bola
     * @return booleano que indica si ocurre o no
     */
    
    public boolean colisionaPorIzq(Bola bolBola){
        //Creo un rectangulo de la parte inferior del brick
        Rectangle recBrick = new Rectangle(this.getX(),
        this.getY(), 2, this.getAlto());
        
         // creo un objeto rectangulo a partir del objeto Bola parametro
        Rectangle recParametro = new Rectangle(bolBola.getX(),
                bolBola.getY(), bolBola.getAncho(),
                bolBola.getAlto());
        
        return recBrick.intersects(recParametro);
    }
    
    /**
     * colisionaPorDer metodo que checa si choco por der
     * @param bolBola con este objeto se hace el rectangulo de bola
     * @return booleano que indica si ocurre o no
     */
    public boolean colisionaPorDer(Bola bolBola){
        //Creo un rectangulo de la parte inferior del brick
        Rectangle recBrick = new Rectangle(this.getX() + this.getAncho(),
        this.getY(), 1, this.getAlto());
        
         // creo un objeto rectangulo a partir del objeto Bola parametro
        Rectangle recParametro = new Rectangle(bolBola.getX(),
                bolBola.getY(), bolBola.getAncho(),
                bolBola.getAlto());
        
        return recBrick.intersects(recParametro);
    }
}


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JList;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro Valdes
 */
class BrickingBad extends JFrame implements Runnable, KeyListener {
//Cargar todas las variables que se necesitaran
    private int iVidas;
    private int iScore;
    private int iVelocidad;
    private boolean bIzq;
    private boolean bDer;
    private boolean bJuega;
    private Image imaImagenJFrame; 
    private Graphics graGraficaJFrame;
    private Bola bolBola;
    private LinkedList lnkBricks;
    private Boss bossTuco;
    private boolean bPausa;
    
    //animacion clase Paddle extiende case animacion
    private Paddle padDrone;
    private Paddle padDroneIzq;
    private Paddle padDroneDer;
    
    //Variables de control de tiempo de la animacion
    private long tiempoActual;
    private long tiempoInicial;
    //posicion de la animacion
    int iPosX, iPosY;
    
    public BrickingBad(){
        init();
        start();
    }

    /** 
     * init
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se inizializan las variables o se crean los objetos
     * a usarse en el <code>Applet</code> y se definen funcionalidades.
     */

    public void init() {
        setSize(500,700);
        iScore = 0;
        iVidas = 5;
        iVelocidad = 3;
        bIzq = false;
        bDer = false;
        lnkBricks = new LinkedList();
        bJuega = false;
        bPausa = false;
                
        URL urlImagenBola = this.getClass().getResource("dea.png");
     
        // Creo el objeto bola
        bolBola = new Bola(getWidth() / 2, 150, 
                Toolkit.getDefaultToolkit().getImage(urlImagenBola));
        
        bolBola.setDirX(iVelocidad);
        bolBola.setDirY(iVelocidad);

        //se cargan las imagenes de la animacions
        Image imaDrone1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneBase01.png"));
        
        Image imaDrone2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneBase02.png"));
        
        Image imaDrone3 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneBase03.png"));

        Image imaDroneIzq1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneBaseLeft01.png"));
        
        Image imaDroneIzq2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneBaseLeft02.png"));
        
        Image imaDroneIzq3 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneBaseLeft03.png"));
               
        Image imaDroneDer1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneBaseRight01.png"));
        
        Image imaDroneDer2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneBaseRight02.png"));
        
        Image imaDroneDer3 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneBaseRight03.png"));
        //se crea la animacion
        padDrone = new Paddle(0, 0);
        padDrone.sumaCuadro(imaDrone1, 50);
        padDrone.sumaCuadro(imaDrone2, 50);
        padDrone.sumaCuadro(imaDrone3, 50);
        padDrone.setX((getWidth() / 2) - 50);
        padDrone.setY(50);
        
        //se crea animacion izq
        padDroneIzq = new Paddle(0,0);
        padDroneIzq.sumaCuadro(imaDroneIzq1, 50);
        padDroneIzq.sumaCuadro(imaDroneIzq2, 50);
        padDroneIzq.sumaCuadro(imaDroneIzq3, 50);
        
        //se crea animacion der
        padDroneDer = new Paddle(0,0);
        padDroneDer.sumaCuadro(imaDroneDer1, 50);
        padDroneDer.sumaCuadro(imaDroneDer2, 50);
        padDroneDer.sumaCuadro(imaDroneDer3, 50);

        bolBola.setY(padDrone.getY() + padDrone.getImagen().getHeight(null) 
                + 45);
        bolBola.setX((getWidth() / 2) - (bolBola.getAncho() / 2));
        
        //creo imagen de Walt
        URL urlImagenWalt = this.getClass().getResource("walt.png");
        //creo imagen de Jesse PinkMan
        URL urlImagenPinkman = this.getClass().getResource("pinkman.png");
        //creo imagen de Meth
        URL urlImagenMeth = this.getClass().getResource("Brick_BlueMethBaggie.png");
        //creo imagen de lab
        URL urlImagenLab = this.getClass().getResource("lab.png");
        
        /*Creo 4 filas de 10 bloques
        cada la primeras dos filas se matan con un golpes las otras con 
        3
        */
        for(int iI = 0; iI < 10; iI++){
            Brick briBloque = new Brick(50 * iI, (getHeight() / 2) ,
                    3, Toolkit.getDefaultToolkit().getImage(urlImagenMeth));
            lnkBricks.add(briBloque);
            
            Brick briBloque2 = new Brick(50 * iI, (getHeight() / 2) + 50 + 10,
                    3, Toolkit.getDefaultToolkit().getImage(urlImagenLab));
            lnkBricks.add(briBloque2);
            
            if(iI % 2 != 0){
            Brick briBloque3 = new Brick(50 * iI, (getHeight() / 2) + 100 + 20,
                    3, Toolkit.getDefaultToolkit().getImage(urlImagenPinkman));
            lnkBricks.add(briBloque3);
            }
            
            if(iI % 2 == 0){
            Brick briBloque4 = new Brick(50 * iI, (getHeight() / 2) + 100 + 20,
                    3, Toolkit.getDefaultToolkit().getImage(urlImagenWalt));
            lnkBricks.add(briBloque4);
            }
            
        }
        
        //Agrego imagenes de bosses
        URL urlTuco1 = this.getClass().getResource("Character_Tuco01.png");
        URL urlTuco2 = this.getClass().getResource("Character_Tuco02.png");
        URL urlTuco3 = this.getClass().getResource("Character_Tuco03.png");
        URL urlTuco4 = this.getClass().getResource("Character_Tuco04.png");
        URL urlTuco5 = this.getClass().getResource("Character_Tuco05.png");

        bossTuco = new Boss(getWidth() / 2 -25, 200,
            5, Toolkit.getDefaultToolkit().getImage(urlTuco1));
       
        bossTuco.agregaFase(Toolkit.getDefaultToolkit().getImage(urlTuco2));
        bossTuco.agregaFase(Toolkit.getDefaultToolkit().getImage(urlTuco3));
        bossTuco.agregaFase(Toolkit.getDefaultToolkit().getImage(urlTuco4));
        bossTuco.agregaFase(Toolkit.getDefaultToolkit().getImage(urlTuco5));
        
        addKeyListener(this);
    }
    
    /** 
     * start
     * 
     * Metodo sobrescrito de la clase <code>Applet</code>.<P>
     * En este metodo se crea e inicializa el hilo
     * para la animacion este metodo es llamado despues del init o 
     * cuando el usuario visita otra pagina y luego regresa a la pagina
     * en donde esta este <code>Applet</code>
     * 
     */
    public void start () {
        // Declaras un hilo
        Thread th = new Thread (this);
        // Empieza el hilo
        th.start ();
    }
    /** 
     * run
     * 
     * Metodo sobrescrito de la clase <code>Thread</code>.<P>
     * En este metodo se ejecuta el hilo, que contendrá las instrucciones
     * de nuestro juego.
     * 
     */
    public void run () {
    
         //Guarda el tiempo actual del sistema 
        tiempoActual = System.currentTimeMillis();
        //Ciclo principal del Applet. Actualiza y despliega en pantalla la 
        //animación hasta que el Applet sea cerrado.
        
        while(iVidas > 0){
            
            if(!bPausa){
                actualiza();
                checaColision();
                repaint();
            }
           
            try	{  
                // El thread se duerme.
                Thread.sleep (20);
            }
            catch (InterruptedException iexError)	{
                System.out.println("Hubo un error en el juego " + 
                        iexError.toString());
            }	
        }
    }
    /** 
     * actualiza
     * 
     * Metodo que actualiza la posicion de los objetos  
     * 
     */
    public void actualiza(){  
            
        //Controlo la barra
        if(bIzq){
            padDrone.setX(padDrone.getX() - 3);
            if(!bJuega){
                bolBola.setX(bolBola.getX() - 3);
            }
        }
        if(bDer) {
            padDrone.setX(padDrone.getX() + 3);
            if(!bJuega){
                bolBola.setX(bolBola.getX() + 3);
            }
        }
        
        if(bJuega){
            //la bola se mueve
            bolBola.muevete();     
        }

        //Determina el tiempo que ha transcurrido desde que el Applet 
        //inicio su ejecución
        long tiempoTranscurrido=System.currentTimeMillis() - tiempoActual;
        //Guarda el tiempo actual
        tiempoActual += tiempoTranscurrido;
        //Actualiza la animación en base al tiempo transcurrido
        padDrone.actualiza(tiempoTranscurrido);
        padDroneIzq.actualiza(tiempoTranscurrido);
        padDroneDer.actualiza(tiempoTranscurrido);

        bossTuco.actualizaFase(bossTuco.getGolpes());
        
    }

    /**
     * checaColision
     * 
     * Metodo usado para checar la colision del objeto elefante
     * con las orillas del <code>JFrame</code>.
     * 
     */
    public void checaColision(){
        //checa la posicion del drone para que no se salga
        if(padDrone.getX()<0) {
            padDrone.setX(0);
        }
        else if(padDrone.getX() + padDrone.getImagen().getWidth(this) > 
                getWidth()){
            padDrone.setX(getWidth() - padDrone.getImagen().getWidth(this));
        }
        
        //Si choca la bola con el drone cambia de dir en y
        if(padDrone.colisionaPorAbajo(bolBola)) {
            bolBola.setDirY(iVelocidad);
        }
        
        //si se va a salir la bola por izq cambia de dir en x
        if(bolBola.getX() < 0){
            bolBola.setDirX(iVelocidad);
        }
        //si se va a salir la bola por la der cambio dir x
        else if(bolBola.getX() > getWidth() - bolBola.getAncho()){
            bolBola.setDirX(iVelocidad * -1);
        }
        //si se le va la bola al drone
        if(bolBola.getY() < padDrone.getY() + padDrone.getImagen().
                getHeight(null) - 10){
            iVidas--; //resto vidas
            //acomodo como al principio
            bolBola.setY(padDrone.getY()+padDrone.getImagen().getHeight(null));
            bolBola.setX((getWidth() / 2) - (bolBola.getAncho() / 2));
            padDrone.setX((getWidth() / 2) - 50);
            padDrone.setY(50);
            //empiezas a jugar una vez mas con space
            bJuega = false;      
        }
        //si se va a slair la bola por abajo rebota
        else if(bolBola.getY() > getHeight() - bolBola.getAlto()){
            bolBola.setDirY(iVelocidad * -1);
        }
        
        for(Object briBloque:lnkBricks) {
            Brick briBrick = (Brick) briBloque;
            if(!briBrick.estaDestruido()){                    
                    
                    if(briBrick.colisionaPorArriba(bolBola)){
                        bolBola.setDirY(iVelocidad * -1);
                        briBrick.agregarGolpe();
                        iScore += 20;

                    }
                    
                    else if(briBrick.colisionaPorAbajo(bolBola)){
                        bolBola.setDirY(iVelocidad);
                        briBrick.agregarGolpe();
                        iScore += 20;        
                    }
                    
                    else if(briBrick.colisionaPorIzq(bolBola)){
                        bolBola.setDirX(iVelocidad * - 1);
                        briBrick.agregarGolpe();
                        iScore += 20;

                    }
                    
                    else if(briBrick.colisionaPorDer(bolBola)){
                        bolBola.setDirX(iVelocidad);
                        briBrick.agregarGolpe();
                        iScore += 20;

                    }
            }
        }
        
        if(!bossTuco.estaDestruido()){
                if(bossTuco.colisionaPorArriba(bolBola)){
                    bolBola.setDirY(iVelocidad * -1);
                    bossTuco.agregarGolpe();
                    iScore += 40;

                }
                    
                else if(bossTuco.colisionaPorAbajo(bolBola)){
                    bolBola.setDirY(iVelocidad);
                    bossTuco.agregarGolpe();
                    iScore += 40;
                }

                else if(bossTuco.colisionaPorIzq(bolBola)){
                    bolBola.setDirX(iVelocidad * - 1);
                    bossTuco.agregarGolpe();
                    iScore += 40;
                }

                else if(bossTuco.colisionaPorDer(bolBola)){
                    bolBola.setDirX(iVelocidad);
                    bossTuco.agregarGolpe();
                    iScore += 40;
                }
            }
    }
    
    /**
     * paint
     * 
     * Metodo sobrescrito de la clase <code>BrikingBad</code>,
     * heredado de la clase Container.<P>
     * 
     * En este metodo lo que hace es actualizar el contenedor y 
     * define cuando usar ahora el paint
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     */
    
    public void paint(Graphics g){
        //iniciliazn el DoubleBuffer
        if(imaImagenJFrame == null) {
            imaImagenJFrame = createImage (this.getSize().width,
                    this.getSize().height);
            graGraficaJFrame = imaImagenJFrame.getGraphics();
        }
        
        // creo imagen para el backgorund
        URL urlImagenFondo = this.getClass().getResource("breakingbad.jpg");
        Image imaImagenFondo = Toolkit.getDefaultToolkit().
                getImage(urlImagenFondo);
        
        // creo imagen GAME OVER
        URL urlImagenGameOver = this.getClass().getResource("gameover.png");
        Image imaImagenGameOver = Toolkit.getDefaultToolkit().
                getImage(urlImagenGameOver);
        
        //Despliego la imagen
        graGraficaJFrame.drawImage(imaImagenFondo, 0, 0, 
                getWidth(), getHeight(), this);
        
        // Despliego Game Over si ya no hay vidas
        if (iVidas == 0) {
            graGraficaJFrame.drawImage(imaImagenGameOver, 0, 0, 
                getWidth(), getHeight(), this);   
        }
            
        //Actualiza el Foreground
        graGraficaJFrame.setColor(getForeground());
        paint1(graGraficaJFrame);
        
        //Dibuja la imagen actualizada
        g.drawImage(imaImagenJFrame, 0, 0, this);
    }
    
    /**
     * En este metodo se dibuja la imagen con la posicion actualizada,
     * ademas que cuando la imagen es cargada te despliega una advertencia.
     * @param g es el <code>objeto grafico</code> usado para dibujar.
     * 
     */    
    public void paint1(Graphics g) 
    {
        if(iVidas != 0){
            
            //Dibuja la imagen de la bola y barra
            g.drawImage(bolBola.getImagen(), bolBola.getX(),
                bolBola.getY(), this);
        
            // Muestra en pantalla el cuadro actual de la animación
            if (padDrone != null && padDroneIzq != null &&
                padDroneDer != null) { 
   
                if(!bIzq && !bDer){
                    g.drawImage(padDrone.getImagen(), padDrone.getX(),
                            padDrone.getY() , this);
                }
                //TEMPORAL FIX
                else if(bIzq){
                    g.drawImage(padDroneDer.getImagen(), padDrone.getX(),
                        padDrone.getY() , this);
                }
                else if(bDer) {
                    g.drawImage(padDroneIzq.getImagen(), padDrone.getX(),
                        padDrone.getY() , this);
                }
            }
                  
            //dibujo bloques
            for(Object briBloque:lnkBricks) {
                Brick briBrick = (Brick) briBloque;
                if(!briBrick.estaDestruido()){
                    g.drawImage(briBrick.getImagen(), briBrick.getX(), 
                          briBrick.getY(), this);
                }
            }

            //Dibujo de boss
            if(!bossTuco.estaDestruido()){
                g.drawImage(bossTuco.getImagen(), bossTuco.getX(), 
                          bossTuco.getY(), this);
            }
            
        }
        
        //despliego informacion necesaria
        if(iVidas > 0){
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g.drawString("S C O R E: " + iScore, 10, getHeight() - 20);
            g.drawString("L I V E S: " + iVidas, 10, getHeight() - 40);
        }
            
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //variables para saber si esta presionado o no
        if(ke.getKeyCode() == ke.VK_LEFT){
            bIzq = true;
        }
        else if(ke.getKeyCode() == ke.VK_RIGHT){
            bDer = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //dejo de moverme cuando se suelta la flecha
        if(ke.getKeyCode() == ke.VK_LEFT){
            bIzq = false;
        }
        else if(ke.getKeyCode() == ke.VK_RIGHT){
            bDer = false;
        }
        else if(ke.getKeyCode() == ke.VK_SPACE){
            bJuega = true;
        }
        //si presiono P pausa
        else if(ke.getKeyCode() == KeyEvent.VK_P){ 
            bPausa = !bPausa;
        }
    }
    
    /**
     * Metodo que agrega la informacion del juego al archivo.
     *
     * @throws IOException
     */
     public void grabarArchivo() throws IOException {
         
     }
     
    /**
     * Metodo que lee a informacion de un archivo y lo agrega a un vector.
     *
     * @throws IOException
     */
    public void leeArchivo() throws IOException {
        
    }
}

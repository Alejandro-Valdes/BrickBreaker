
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;
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
    private int iVidas; //variables para media la vida del jugador
    private int iScore; //variable que mide el score
    private int iVelocidad; //velocidades de la bola
    private int iNivel;     //en que nivel estoy
    private int iCada5;    //cada 5 te da un powerup;
    private int iRandom;
    private int iContador;  //contador de tiempo
    private int iPoder;     //variable para saber que poder;
    private boolean bPoder;     //variable apra saber si ocupo poder;
    private boolean bIzq;  //mov a izq
    private boolean bDer;  //mov a der
    private boolean bJuega;  //variable para poder moverse sin empezar el juego
    private boolean bInicio;  //variable para empezas en otra pantalla
    private int iInicio;  //variable para ver en que pantalla de inicio vas
    private boolean bGano;
    private Image imaImagenJFrame; //imagen del frame
    private Graphics graGraficaJFrame;  //grafica dle frane
    private Bola bolBola;   //objeto de la clase bola
    private LinkedList lnkBricks;   //lista de bircks
    private LinkedList lnkPowerUps;  //lista de power ups
    private Boss bossVillano;  //boss del nivel
    private boolean bPausa;     //variable apra saber si esta en pausa
    private boolean bCambioNivel;   //variable para el traslado entre niveles
    //powerUps
    private boolean bShrink;
    private boolean bGrow;
    private boolean bFast;
    private boolean bUnaSolaVez;        //solo la haga una vez
    
    //imagenes de malos para todos metodos 
    //TUCO
    private URL urlTuco1;
    private URL urlTuco2;
    private URL urlTuco3;
    private URL urlTuco4;
    private URL urlTuco5;
    //PRIMOS
    private URL urlPrimos1;
    private URL urlPrimos2;
    private URL urlPrimos3;
    private URL urlPrimos4;
    private URL urlPrimos5;
    //GUS
    private URL urlGus1;
    private URL urlGus2;
    private URL urlGus3;
    private URL urlGus4;
    private URL urlGus5;
    //WELKER
    private URL urlWelker1;
    private URL urlWelker2;
    private URL urlWelker3;
    private URL urlWelker4;
    private URL urlWelker5;
    //WALT
    //WELKER
    private URL urlWalter1;
    private URL urlWalter2;
    private URL urlWalter3;
    private URL urlWalter4;
    private URL urlWalter5;
                
    //Imagenes de power ups 
    private URL urlPowerS;      //power small
    private URL urlPowerG;      //power grow
    private URL urlPowerF;      //power fast
    private URL urlPowerL;      //power LifeUp
    private ArrayList arrOpciones;   //opciones de poder
            
    //imagenes para todos
    private URL urlImagenMeth;
    private URL urlImagenOso;
    private URL urlImagenBarril;
    //animacion clase Paddle extiende case animacion
    private Paddle padDrone;    //animacion sin moverse
    private Paddle padDroneIzq;     //animcacion si se mueve a la izq
    private Paddle padDroneDer;     //animacion si se mueve a la der
    
    //animacion paddle con power up small
    private Paddle padDroneSmall;    //animacion sin moverse
    private Paddle padDroneSmallIzq;     //animcacion si se mueve a la izq
    private Paddle padDroneSmallDer;     //animacion si se mueve a la der
    
    //animacion paddle con power up Large
    private Paddle padDroneLarge;    //animacion sin moverse
    private Paddle padDroneLargeIzq;     //animcacion si se mueve a la izq
    private Paddle padDroneLargeDer;     //animacion si se mueve a la der
    
    //sonidos
    private SoundClip soundHit;
    private SoundClip soundEnlarge;
    private SoundClip soundGroan;
    private SoundClip soundLifeUp;
    private SoundClip soundShrink;
    private SoundClip soundSpeedUp;
    private SoundClip soundGameOver;
    
    /**** CHEAT CODE*****/
    private boolean bCheat;
     
    //Variables de control de tiempo de la animacion
    private long tiempoActual;

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
        setSize(500,700);  //inicio con tama;o 500 x 700
        iScore = 0;     //score de 0
        iVidas = 4;     //con 10 vidas
        iVelocidad = 4;     //velocidad inicia con 4
        iCada5 = 0;         //empiezas con 0
        iNivel = 1;
        iRandom = 0;
        iContador = 0;      //contador en 0
        bIzq = false;       //no me muevo
        bDer = false;       //no me muevo
        lnkBricks = new LinkedList();       //creo la lista encadenada de bricks
        lnkPowerUps = new LinkedList();     //creo la lista de powerups
        bJuega = false;     //no estoy jugando
        bPausa = false;     //no estoy pausado
        bCambioNivel = false;   //no e cambiado de nivle
        bGano = false;
        arrOpciones = new ArrayList();
        
        //PowerUps
        bPoder = false;
        iPoder = 0;
        bShrink = false;
        bUnaSolaVez = false;
        
        //CHEAT
        bCheat = false;
        
        //Pantalla iniciol
        bInicio = true;
        iInicio = 1;
        
        //cargo imagen de bola       
        URL urlImagenBola = this.getClass().getResource("dea.png");
     
        // Creo el objeto bola
        bolBola = new Bola(getWidth() / 2, 150, 
                Toolkit.getDefaultToolkit().getImage(urlImagenBola));
        
        //asigno la velociada a la bola ligeramente cargada a la y
        bolBola.setDirX(iVelocidad + 1);
        bolBola.setDirY(iVelocidad + 2);

        /***********se cargan las imagenes del paddle normal*******************/
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

        /***********se cargan las imagenes del paddle chico*******************/
        Image imaDroneSmall1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneSmall01.png"));
        Image imaDroneSmall2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneSmall02.png")); 
        Image imaDroneSmall3 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneSmall03.png"));
        Image imaDroneSmallIzq1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneSmallLeft01.png"));
        Image imaDroneSmallIzq2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneSmallLeft02.png"));
        Image imaDroneSmallIzq3 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneSmallLeft03.png"));     
        Image imaDroneSmallDer1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneSmallRight01.png"));
        Image imaDroneSmallDer2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneSmallRight02.png"));
        Image imaDroneSmallDer3 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneSmallRight03.png"));
        
        //se crea la animacion
        padDroneSmall = new Paddle(0, 0);
        padDroneSmall.sumaCuadro(imaDroneSmall1, 50);
        padDroneSmall.sumaCuadro(imaDroneSmall2, 50);
        padDroneSmall.sumaCuadro(imaDroneSmall3, 50);
        padDroneSmall.setX((getWidth() / 2) - 50);
        padDroneSmall.setY(50);
        
        //se crea animacion izq
        padDroneSmallIzq = new Paddle(0,0);
        padDroneSmallIzq.sumaCuadro(imaDroneSmallIzq1, 50);
        padDroneSmallIzq.sumaCuadro(imaDroneSmallIzq2, 50);
        padDroneSmallIzq.sumaCuadro(imaDroneSmallIzq3, 50);
        
        //se crea animacion der
        padDroneSmallDer = new Paddle(0,0);
        padDroneSmallDer.sumaCuadro(imaDroneSmallDer1, 50);
        padDroneSmallDer.sumaCuadro(imaDroneSmallDer2, 50);
        padDroneSmallDer.sumaCuadro(imaDroneSmallDer3, 50);
        
        /***********se cargan las imagenes del paddle Grande*******************/
        Image imaDroneLarge1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneLarge01.png"));
        Image imaDroneLarge2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneLarge02.png")); 
        Image imaDroneLarge3 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneLarge03.png"));
        Image imaDroneLargeIzq1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneLargeLeft01.png"));
        Image imaDroneLargeIzq2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneLargeLeft02.png"));
        Image imaDroneLargeIzq3 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneLargeLeft03.png"));     
        Image imaDroneLargeDer1 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneLargeRight01.png"));
        Image imaDroneLargeDer2 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneLargeRight02.png"));
        Image imaDroneLargeDer3 = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getResource("Player_DroneLargeRight03.png"));
        
        //se crea la animacion
        padDroneLarge = new Paddle(0, 0);
        padDroneLarge.sumaCuadro(imaDroneLarge1, 50);
        padDroneLarge.sumaCuadro(imaDroneLarge2, 50);
        padDroneLarge.sumaCuadro(imaDroneLarge3, 50);
        padDroneLarge.setX((getWidth() / 2) - 50);
        padDroneLarge.setY(50);
        
        //se crea animacion izq
        padDroneLargeIzq = new Paddle(0,0);
        padDroneLargeIzq.sumaCuadro(imaDroneLargeIzq1, 50);
        padDroneLargeIzq.sumaCuadro(imaDroneLargeIzq2, 50);
        padDroneLargeIzq.sumaCuadro(imaDroneLargeIzq3, 50);
        
        //se crea animacion der
        padDroneLargeDer = new Paddle(0,0);
        padDroneLargeDer.sumaCuadro(imaDroneLargeDer1, 50);
        padDroneLargeDer.sumaCuadro(imaDroneLargeDer2, 50);
        padDroneLargeDer.sumaCuadro(imaDroneLargeDer3, 50);
        
        
        //se acomoda la bola debajo de la barra
        bolBola.setY(padDrone.getY() + padDrone.getImagen().getHeight(null) 
                + 40);
        bolBola.setX((getWidth() / 2) - (bolBola.getAncho() / 2));
        
        //creo imagen de Barril
        urlImagenBarril = this.getClass().getResource
                ("Brick_GoldenMothBarrel.png");
        //creo imagen de Oso
        urlImagenOso = this.getClass().getResource("Brick_PinkBear.png");
        //creo imagen de Meth
        urlImagenMeth = this.getClass().getResource("Brick_BlueMethBaggie.png");
       
        //Creo imagenes de powerups
        urlPowerS = this.getClass().getResource("PowerUp_Shrink.png");
        urlPowerF = this.getClass().getResource("PowerUp_Speed.png");
        urlPowerG = this.getClass().getResource("PowerUp_Enlarge.png");
        urlPowerL = this.getClass().getResource("PowerUp_LifeUp.png");
        
        //pos 0 shrink--1 fast---2 grow---3 life up
        arrOpciones.add(Toolkit.getDefaultToolkit().getImage(urlPowerS));
        arrOpciones.add(Toolkit.getDefaultToolkit().getImage(urlPowerF));
        arrOpciones.add(Toolkit.getDefaultToolkit().getImage(urlPowerG));
        arrOpciones.add(Toolkit.getDefaultToolkit().getImage(urlPowerL));
        
        //SONIDOS
        //sonidos
        soundHit = new SoundClip("Hit.wav");
        soundEnlarge = new SoundClip("Enlarge.wav");
        soundGroan = new SoundClip("Groan.wav");
        soundLifeUp = new SoundClip("LifeUp.wav");
        soundShrink = new SoundClip("Shrink.wav");
        soundSpeedUp = new SoundClip("SpeedUp.wav");
        soundGameOver = new SoundClip("GameOver.wav");

        /*********NIVEL 1********/
        //Creo 5 filas de meth y luego a tuco
        
        for(int iI = 0; iI < 10; iI++){
            Brick briBloque1 = new Brick(50 * iI, (getHeight() / 2) , 1, 
                    Toolkit.getDefaultToolkit().getImage(urlImagenMeth));
            lnkBricks.add(briBloque1);
            
            Brick briBloque2 = new Brick(50 * iI, (getHeight() / 2) + 20 , 3, 
                    Toolkit.getDefaultToolkit().getImage(urlImagenBarril));
            lnkBricks.add(briBloque2);
            
            Brick briBloque3 = new Brick(50 * iI, (getHeight() / 2) + 100 , 2, 
                    Toolkit.getDefaultToolkit().getImage(urlImagenOso));
            lnkBricks.add(briBloque3);
            
            Brick briBloque4 = new Brick(50 * iI, (getHeight() / 2) + 140, 1, 
                    Toolkit.getDefaultToolkit().getImage(urlImagenMeth));
            lnkBricks.add(briBloque4);
        }
        
        //Agrego imagenes de boss TUCO
        urlTuco1 = this.getClass().getResource("Character_Tuco01.png");
        urlTuco2 = this.getClass().getResource("Character_Tuco02.png");
        urlTuco3 = this.getClass().getResource("Character_Tuco03.png");
        urlTuco4 = this.getClass().getResource("Character_Tuco04.png");
        urlTuco5 = this.getClass().getResource("Character_Tuco05.png");

        //agrego a tuto
        bossVillano = new Boss(getWidth() / 2 - 50, getHeight() - 110,
            5, Toolkit.getDefaultToolkit().getImage(urlTuco1));
       
        //se agrega cada una de las Fases
        bossVillano.agregaFase(Toolkit.getDefaultToolkit().getImage(urlTuco2));
        bossVillano.agregaFase(Toolkit.getDefaultToolkit().getImage(urlTuco3));
        bossVillano.agregaFase(Toolkit.getDefaultToolkit().getImage(urlTuco4));
        bossVillano.agregaFase(Toolkit.getDefaultToolkit().getImage(urlTuco5));
         
        //Agrero imagenes de boss Primos
        urlPrimos1 = this.getClass().getResource("Character_Primos01.png");
        urlPrimos2 = this.getClass().getResource("Character_Primos02.png");
        urlPrimos3 = this.getClass().getResource("Character_Primos03.png");
        urlPrimos4 = this.getClass().getResource("Character_Primos04.png");
        urlPrimos5 = this.getClass().getResource("Character_Primos05.png");
        
        //Agrero imagenes de boss Primos
        urlGus1 = this.getClass().getResource("Character_GusFring01.png");
        urlGus2 = this.getClass().getResource("Character_GusFring02.png");
        urlGus3 = this.getClass().getResource("Character_GusFring03.png");
        urlGus4 = this.getClass().getResource("Character_GusFring04.png");
        urlGus5 = this.getClass().getResource("Character_GusFring05.png");
        
        //Agrego imagenes de boss Welker
        urlWelker1 = this.getClass().getResource("Character_Welker01.png");
        urlWelker2 = this.getClass().getResource("Character_Welker02.png");
        urlWelker3 = this.getClass().getResource("Character_Welker03.png");
        urlWelker4 = this.getClass().getResource("Character_Welker04.png");
        urlWelker5 = this.getClass().getResource("Character_Welker05.png");
        
        //Agrego imagenes de boss Walter
        urlWalter1 = this.getClass().getResource("Character_Heisenberg01.png");
        urlWalter2 = this.getClass().getResource("Character_Heisenberg02.png");
        urlWalter3 = this.getClass().getResource("Character_Heisenberg03.png");
        urlWalter4 = this.getClass().getResource("Character_Heisenberg04.png");
        urlWalter5 = this.getClass().getResource("Character_Heisenberg05.png");
        
        //agrego opcion de ser escuchado por el teclado
        addKeyListener(this);
    }
    
    /**
     * cambioNivel
     * este metodo empezara cada cambio de nivel.
     * @param iLevel es el nivel en el que vas
     */
    public void cambioNivel(){
        bUnaSolaVez = false;
        padDrone.setX((getWidth() / 2) - 50);
        padDrone.setY(50);
        bJuega = false;
        iVelocidad = 4;
        //se acomoda la bola debajo de la barra
        bolBola.setY(padDrone.getY() + padDrone.getImagen().
                getHeight(null));
        bolBola.setX((getWidth() / 2) - (bolBola.getAncho() / 2));
        
        switch(iNivel){
            case 2:
                /*********NIVEL 2********/
                //Creo villano primos y lo acomodo
                bossVillano = new Boss(getWidth() / 2 - 100, getHeight() - 110,
                        5, Toolkit.getDefaultToolkit().getImage(urlPrimos1));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlPrimos2));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlPrimos3));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlPrimos4));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlPrimos5));
            
                //Creo una piramide de bricks y luego a primos
                for(int iI = 0; iI < 10; iI++){

                    if(iI < 10 & iI >= 0){
                        Brick briBloque = new Brick(50 * iI, (getHeight() / 2) ,
                                2, Toolkit.getDefaultToolkit().
                                        getImage(urlImagenOso));
                        lnkBricks.add(briBloque);
                    }
                    if(iI < 9 & iI >= 1){
                        Brick briBloque2 = new Brick(50 * iI, 
                                (getHeight() / 2) + 40, 3, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenBarril));
                        lnkBricks.add(briBloque2);
                    }
                    if(iI < 8 & iI >= 2){
                        Brick briBloque3 = new Brick(50 * iI, 
                                (getHeight() / 2) + 120, 1, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenMeth));
                        lnkBricks.add(briBloque3);   
                    }
                    if(iI < 7 & iI >= 3){
                        Brick briBloque3 = new Brick(50 * iI, 
                                (getHeight() / 2) + 140, 2, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenOso));
                        lnkBricks.add(briBloque3);   
                    }
                    if(iI < 6 & iI >= 4){
                        Brick briBloque4 = new Brick(50 * iI, 
                                (getHeight() / 2) + 180, 1, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenMeth));
                        lnkBricks.add(briBloque4);   
                    }  
                }
                                
                 break;
            
            case 3:
                /*****NIVEL 3*********/
                //creo villano gus
                bossVillano = new Boss(getWidth() / 2 - 50, getHeight() - 110,
                        5, Toolkit.getDefaultToolkit().getImage(urlGus1));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlGus2));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlGus3));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlGus4));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlGus5));
                
                //creo arreglo de bricks
                for(int iI = 0; iI < 5; iI++){
                    Brick briBloque1 = new Brick(40 * iI, (getHeight() - 80) ,
                                3, Toolkit.getDefaultToolkit().
                                        getImage(urlImagenBarril));
                        lnkBricks.add(briBloque1);
                        
                    Brick briBloque2 = new Brick((getWidth() - 40) - (40 * iI), 
                            (getHeight() - 80) ,
                                3, Toolkit.getDefaultToolkit().
                                        getImage(urlImagenBarril));
                        lnkBricks.add(briBloque2);
                     
                        Brick briBloque3 = new Brick(40 * iI, 
                                (getHeight() - 160) ,
                                    3, Toolkit.getDefaultToolkit().
                                        getImage(urlImagenBarril));
                        lnkBricks.add(briBloque3);
                        
                    Brick briBloque4 = new Brick((getWidth() - 40) - (40 * iI), 
                            (getHeight() - 160) ,
                                3, Toolkit.getDefaultToolkit().
                                        getImage(urlImagenBarril));
                    lnkBricks.add(briBloque4);
                        
                     Brick briBloque5 = new Brick((getWidth() - 50) - (50 * iI), 
                             (getHeight() - 200) ,
                                2, Toolkit.getDefaultToolkit().
                                        getImage(urlImagenOso));
                    lnkBricks.add(briBloque5);
                    
                    Brick briBloque6 = new Brick(50 * iI,(
                        getHeight() - 200) ,
                            2, Toolkit.getDefaultToolkit().
                                    getImage(urlImagenOso));
                    lnkBricks.add(briBloque6);
                    
                }
        
                for(int iI =0; iI < 2; iI++){
                    Brick briBloque1 = new Brick((getWidth() / 2 - (iI * 50)), 
                            (getHeight() - 150) , 3, Toolkit.getDefaultToolkit().
                                    getImage(urlImagenOso));
                    lnkBricks.add(briBloque1);
                }
                //termino arreglo de bricks
                break;
                
            case 4:
                /*********NIVEL 4********/
                //creo villano welker
                bossVillano = new Boss(getWidth() / 2 - 50, getHeight() - 110,
                        5, Toolkit.getDefaultToolkit().getImage(urlWelker1));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlWelker2));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlWelker3));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlWelker4));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlWelker5));
            
                //Creo una piramide de bricks y luego a Welker
                for(int iI = 0; iI < 10; iI++){

                    if(iI < 10 & iI >= 0){
                        Brick briBloque = new Brick(50 * iI, (getHeight() / 2) 
                                + 180,
                                2, Toolkit.getDefaultToolkit().
                                        getImage(urlImagenOso));
                        lnkBricks.add(briBloque);
                    }
                    if(iI < 9 & iI >= 1){
                        Brick briBloque2 = new Brick(50 * iI, 
                                (getHeight() / 2) + 100, 3, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenBarril));
                        lnkBricks.add(briBloque2);
                    }
                    if(iI < 8 & iI >= 2){
                        Brick briBloque3 = new Brick(50 * iI, 
                                (getHeight() / 2) + 80, 1, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenMeth));
                        lnkBricks.add(briBloque3);   
                    }
                    if(iI < 7 & iI >= 3){
                        Brick briBloque3 = new Brick(50 * iI, 
                                (getHeight() / 2) + 40, 2, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenOso));
                        lnkBricks.add(briBloque3);   
                    }
                    if(iI < 6 & iI >= 4){
                        Brick briBloque4 = new Brick(50 * iI, 
                                (getHeight() / 2) + 20, 1, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenMeth));
                        lnkBricks.add(briBloque4);   
                    }  
                }
                //termino piramide bricks
            break;
                    
            case 5:
                /*********NIVEL 5********/
                //creo villano walt
                bossVillano = new Boss(getWidth() / 2 - 50, getHeight() - 110,
                        5, Toolkit.getDefaultToolkit().getImage(urlWalter1));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlWalter2));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlWalter3));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlWalter4));
                bossVillano.agregaFase(Toolkit.getDefaultToolkit().
                        getImage(urlWalter5));
                //creo ultimo nivel y luego a walt
                for(int iI = 0; iI < 10; iI++){

                    if(iI < 10 & iI >= 0){
                        Brick briBloque = new Brick(50 * iI, (getHeight() / 2) 
                                + 180,
                                2, Toolkit.getDefaultToolkit().
                                        getImage(urlImagenOso));
                        lnkBricks.add(briBloque);
                    }
                    if(iI < 9 & iI >= 1){
                        Brick briBloque2 = new Brick(50 * iI, 
                                (getHeight() / 2) + 100, 3, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenBarril));
                        lnkBricks.add(briBloque2);
                    }
                    if(iI < 8 & iI >= 2){
                        Brick briBloque3 = new Brick(50 * iI, 
                                (getHeight() / 2) + 80, 1, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenMeth));
                        lnkBricks.add(briBloque3);   
                    }
                    if(iI < 7 & iI >= 3){
                        Brick briBloque3 = new Brick(50 * iI, 
                                (getHeight() / 2) + 40, 2, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenOso));
                        lnkBricks.add(briBloque3);   
                    }
                    if(iI < 6 & iI >= 4){
                        Brick briBloque4 = new Brick(50 * iI, 
                                (getHeight() / 2) + 20, 1, Toolkit.
                                        getDefaultToolkit().
                                                getImage(urlImagenMeth));
                        lnkBricks.add(briBloque4);   
                    }  
                }
                for(int iI = 0; iI < 5; iI++){
                    Brick briBloque1 = new Brick(40 * iI, (getHeight() - 80) ,
                                3, Toolkit.getDefaultToolkit().
                                        getImage(urlImagenBarril));
                        lnkBricks.add(briBloque1);
                    Brick briBloque2 = new Brick((getWidth() - 40) - (40 * iI), 
                        (getHeight() - 80) ,
                            3, Toolkit.getDefaultToolkit().
                                    getImage(urlImagenBarril));
                    lnkBricks.add(briBloque2);
                    
                    Brick briBloque3 = new Brick(50 * iI, (getHeight() - 120) ,
                                3, Toolkit.getDefaultToolkit().
                                        getImage(urlImagenOso));
                        lnkBricks.add(briBloque3);
                        
                    Brick briBloque4 = new Brick((getWidth() - 50) - (50 * iI), 
                        (getHeight() - 120) ,
                            3, Toolkit.getDefaultToolkit().
                                    getImage(urlImagenOso));
                    lnkBricks.add(briBloque4);
                }
            break;
                
            default: 
                bGano = true;               
        }
                
        
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
        
        while(true){
            
            if(!bPausa){
                if(!bInicio){
                    actualiza();    
                    checaColision();
                }
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
           
        //Controlo la barra small
        if(bShrink){
            //control barra forma small
            if(bIzq){
            padDroneSmall.setX(padDroneSmall.getX() - padDroneSmall.getVelociad());
                if(!bJuega){
                    bolBola.setX(bolBola.getX() - padDroneSmall.getVelociad());
                }
            }
            if(bDer) {
                padDroneSmall.setX(padDroneSmall.getX() + padDroneSmall.getVelociad());
                if(!bJuega){
                    bolBola.setX(bolBola.getX() + padDroneSmall.getVelociad());
                }
            }
        }
        
        //Controlo la barra Grande
        else if(bGrow){
            //control barra forma grande
            if(bIzq){
            padDroneLarge.setX(padDroneLarge.getX() - padDroneLarge.getVelociad());
                if(!bJuega){
                    bolBola.setX(bolBola.getX() - padDroneLarge.getVelociad());
                }
            }
            if(bDer) {
                padDroneLarge.setX(padDroneLarge.getX() + padDroneLarge.getVelociad());
                if(!bJuega){
                    bolBola.setX(bolBola.getX() + padDroneLarge.getVelociad());
                }
            }
        }
        //juego de manera normal
        else{
            if(bIzq){
                padDrone.setX(padDrone.getX() - padDrone.getVelociad());
                if(!bJuega){
                    bolBola.setX(bolBola.getX() - padDrone.getVelociad());
                }
            }
            if(bDer) {
                padDrone.setX(padDrone.getX() + padDrone.getVelociad());
                if(!bJuega){
                    bolBola.setX(bolBola.getX() + padDrone.getVelociad());
                }
            }
        }
        
        //si empieza el juego
        if(bJuega){
            //la bola se mueve
            bolBola.muevete();     
        }
        
        //remueve los bricks que estan destruidos
        for(Iterator<Brick> iter = lnkBricks.iterator(); iter.hasNext();){
            Brick briBrick = iter.next();
            if(briBrick.estaDestruido()){
                iter.remove();
            }        
        }
        
        //mueve powerups
        if(lnkPowerUps.size() != 0){
            for(Object pwrUp:lnkPowerUps) {
                PowerUp pwrPoder = (PowerUp) pwrUp;
                    pwrPoder.muevete();
            }       
        }
        
        //implemento powerups
        if(bPoder){
            switch(iPoder){
                case 0: 
                    //me encojo
                    bShrink = true;
                    if(!bUnaSolaVez){
                        //me acomodo como estaaba
                        padDroneSmall.setX(padDrone.getX());
                        soundShrink.play();
                    }
                    bUnaSolaVez = true;
           
                    //contador para powerup
                    if(iContador < 360){
                        iContador++;
                    }
                    //termina power up
                    else{
                        iContador = 0;
                        bShrink = false;
                        //regreso donde estaba
                        padDrone.setX(padDroneSmall.getX());
                        bPoder = false;
                        bUnaSolaVez = false;
                    }
                    break;
                case 1:  //fast
                    if(!bUnaSolaVez){
                        //agrego velocidad
                        soundSpeedUp.play();
                        iVelocidad += 2;
                        padDrone.setVelocidad(padDrone.getVelociad() + 2);
                    }
                    bUnaSolaVez = true;
           
                    //contador
                    if(iContador < 360){
                        iContador++;
                    }
                    //termina powr up regreso a estado original
                    else{
                        iContador = 0;
                        iVelocidad -= 2;
                        padDrone.setVelocidad(padDrone.getVelociad() - 2);
                        bPoder = false;
                        bUnaSolaVez = false;
                    }
                    break;
                    
                case 2:  //grow
                    bGrow= true;
                    if(!bUnaSolaVez){
                        //una sola vez me acomodo
                        padDroneLarge.setX(padDrone.getX());
                        soundEnlarge.play();
                    }
                    bUnaSolaVez = true;
           
                    if(iContador < 360){
                        iContador++;
                    }
                    //regreso a lo normal
                    else{
                        iContador = 0;
                        padDrone.setX(padDroneLarge.getX());
                        bGrow= false;
                        bPoder = false;
                        bUnaSolaVez = false;
                    }
                    break;
                case 3:  //life
                    //agrego una vida
                    soundLifeUp.play();
                    iVidas++;
                    bPoder = false;
                    break;
                    
            }
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
        
        //Actualiza la animación small en base al tiempo transcurrido
        padDroneSmall.actualiza(tiempoTranscurrido);
        padDroneSmallIzq.actualiza(tiempoTranscurrido);
        padDroneSmallDer.actualiza(tiempoTranscurrido);
        
        //Actualiza la animación large en base al tiempo transcurrido
        padDroneLarge.actualiza(tiempoTranscurrido);
        padDroneLargeIzq.actualiza(tiempoTranscurrido);
        padDroneLargeDer.actualiza(tiempoTranscurrido);

        //actualizo la imagen del villano dependiendo de los golpes
        if(bossVillano.getGolpes() < 5){
            bossVillano.actualizaFase(bossVillano.getGolpes());
        }
        
        //si ya veniciste el nivel puedes cambiar
        if(lnkBricks.size() == 0 && bossVillano.estaDestruido()){
            bCambioNivel = true;
        }
        
        //cambio de nivel
        if(bCambioNivel){
            bCambioNivel = false;
            if(iNivel < 5){
                iNivel++;
                cambioNivel();
            }
            else{
                bGano = true;
                iNivel = 1;
            }
            
        }
        
        //aplico cheat
        if(bCheat)
        {
            bCheat = false;
            lnkBricks.clear();
            while(!bossVillano.estaDestruido()){
                bossVillano.agregarGolpe();
            }
        }
        
        //sonido game over solo una vez
        if(iVidas == 0 & !bUnaSolaVez){
            soundGameOver.play();
            bUnaSolaVez = true;
        }
    }

    /**
     * checaColision
     * 
     * Metodo usado para checar la colision del objeto elefante
     * con las orillas del <code>JFrame</code>.
     * 
     */
    public void checaColision(){
        
        //checa la posicion del drone CHICO para que no se salga
        if(bShrink){
            if(padDroneSmall.getX()<0) {
                padDroneSmall.setX(0);
                if(!bJuega){
                    bolBola.setX(20);
                }
            }
            else if(padDroneSmall.getX() + padDroneSmall.getImagen().
                    getWidth(this) > getWidth()){
                padDroneSmall.setX(getWidth() - padDroneSmall.getImagen().
                        getWidth(this));
                if(!bJuega){
                    bolBola.setX(getWidth() - 30);
                }
            }

            //Si choca la bola con el drone cambia de dir en y
            if(padDroneSmall.colisionaPorAbajo(bolBola)) {
                bolBola.setDirY(iVelocidad);
                soundHit.play();
            }
        }
        //checa la posicion del drone GRANDE para que no se salga
        
        else if(bGrow){
            if(padDroneLarge.getX()<0) {
                padDroneLarge.setX(0);
                if(!bJuega){
                    bolBola.setX(65);
                }
            }
            else if(padDroneLarge.getX() + padDroneLarge.getImagen().
                    getWidth(this) > getWidth()){
                padDroneLarge.setX(getWidth() - padDroneLarge.getImagen().
                        getWidth(this));
                if(!bJuega){
                    bolBola.setX(getWidth() - 75);
                }
            }

            //Si choca la bola con el drone cambia de dir en y
            if(padDroneLarge.colisionaPorAbajo(bolBola)) {
                bolBola.setDirY(iVelocidad);
                soundHit.play();
            }
        }
        
        else{
            //checa la posicion del drone NORMAL para que no se salga
            if(padDrone.getX()<0) {
                padDrone.setX(0);
                if(!bJuega){
                    bolBola.setX(45);
                }
            }
            else if(padDrone.getX() + padDrone.getImagen().getWidth(this) > 
                    getWidth()){
                padDrone.setX(getWidth() - padDrone.getImagen().getWidth(this));
                if(!bJuega){
                    bolBola.setX(getWidth() - 55);
                }
            }

            //Si choca la bola con el drone cambia de dir en y
            if(padDrone.colisionaPorAbajo(bolBola)) {
                bolBola.setDirY(iVelocidad);
                soundHit.play();
            }
        }
            
        //si se va a salir la bola por izq cambia de dir en x
        if(bolBola.getX() < 0){
            bolBola.setDirX(iVelocidad);
            soundHit.play();
        }
        //si se va a salir la bola por la der cambio dir x
        else if(bolBola.getX() > getWidth() - bolBola.getAncho()){
            bolBola.setDirX(iVelocidad * -1);
            soundHit.play();
        }
        
        //si se le va la bola al drone
        if(bShrink){
            if(bolBola.getY() < padDroneSmall.getY() + padDroneSmall.getImagen().
                    getHeight(null) - 10){
                soundGroan.play();
                iVidas--; //resto vidas
                //acomodo como al principio
                bolBola.setY(padDroneSmall.getY()+padDroneSmall.getImagen().getHeight(null));
                bolBola.setX((getWidth() / 2) - (bolBola.getAncho() / 2));
                padDroneSmall.setX((getWidth() / 2) - 50);
                padDroneSmall.setY(50);
                //empiezas a jugar una vez mas con space
                bJuega = false;

            }
        }
        //si se le va la bola al drone
        else if(bGrow){
            if(bolBola.getY() < padDroneLarge.getY() + padDroneLarge.getImagen().
                    getHeight(null) - 10){
                soundGroan.play();
                iVidas--; //resto vidas
                //acomodo como al principio
                bolBola.setY(padDroneLarge.getY()+padDroneLarge.getImagen().getHeight(null));
                bolBola.setX((getWidth() / 2) - (bolBola.getAncho() / 2));
                padDroneLarge.setX((getWidth() / 2) - 50);
                padDroneLarge.setY(50);
                //empiezas a jugar una vez mas con space
                bJuega = false;

            }
        }
        else {
            //si se le va la bola al drone grande
            if(bolBola.getY() < padDrone.getY() + padDrone.getImagen().
                    getHeight(null) - 10){
                soundGroan.play();
                iVidas--; //resto vidas
                //acomodo como al principio
                bolBola.setY(padDrone.getY()+padDrone.getImagen().getHeight(null));
                bolBola.setX((getWidth() / 2) - (bolBola.getAncho() / 2));
                padDrone.setX((getWidth() / 2) - 50);
                padDrone.setY(50);
                //empiezas a jugar una vez mas con space
                bJuega = false;
            }
        }      
        
        //si se va a slair la bola por abajo rebota
        if(bolBola.getY() > getHeight() - bolBola.getAlto()){
            bolBola.setDirY(iVelocidad * -1);
            soundHit.play();
        }
        
        //checo la colision de todos los bloques.
        for(Object briBloque:lnkBricks) {
            Brick briBrick = (Brick) briBloque;
            if(!briBrick.estaDestruido()){ 
                if(briBrick.colisiona(bolBola)){
                    briBrick.agregarGolpe();
                    soundHit.play();
                    iScore += 20;
                    iCada5++;
                    
                    if(iCada5 > 5 & !bPoder){
                        //random entre 1 y 4
                        iRandom = ((int) (Math.random() * (5 - 1) + 1))-1;
                        PowerUp pwrUp = new PowerUp (briBrick.getX(),
                                briBrick.getY(), (Image) arrOpciones.
                                        get(iRandom), iRandom);
                        lnkPowerUps.add(pwrUp);
                        iCada5 = 0;
                    }
     
                    //pego por arriba agrego golpe y cambio dirY a abajo
                    if(briBrick.colisionaPorArriba(bolBola)){
                        bolBola.setDirY(iVelocidad * -1);
                    }     

                    //pego por abajo agrego golpe y cambio dirY a arriba
                    if(briBrick.colisionaPorAbajo(bolBola)){
                        bolBola.setDirY(iVelocidad);
                    }
                    
                    //pego por izq agrego golpe cambio dirX a der
                    if(briBrick.colisionaPorIzq(bolBola)){
                        bolBola.setDirX(iVelocidad * - 1);
                    }
                    
                    //pego por der agrego golpe cambio dirX a izq
                    if(briBrick.colisionaPorDer(bolBola)){
                        bolBola.setDirX(iVelocidad);
                    }
                }
            }
        }
        
        //checo la colision del villano de la misma manera que los bricks
        if(!bossVillano.estaDestruido()){
            if(bossVillano.colisiona(bolBola)){
                bossVillano.agregarGolpe();
                iScore += 40;
                soundGroan.play();
                
                if(bossVillano.colisionaPorArriba(bolBola)){
                    bolBola.setDirY(iVelocidad * -1);
                }

                if(bossVillano.colisionaPorAbajo(bolBola)){
                    bolBola.setDirY(iVelocidad);;
                }

                if(bossVillano.colisionaPorIzq(bolBola)){
                    bolBola.setDirX(iVelocidad * - 1);
                }

                if(bossVillano.colisionaPorDer(bolBola)){
                    bolBola.setDirX(iVelocidad);
                }
            }
        }
        
        //checo la colision del paddle y los power ups
        if(lnkPowerUps.size() != 0){
            for(Iterator<PowerUp> iter = lnkPowerUps.iterator(); iter.
                    hasNext();){
                PowerUp pwrPoder = iter.next(); 
                //quita lo menor a cero
                if(pwrPoder.getY() < 0){
                    iter.remove();
                }
                //version shrink
                if(bShrink){
                    if(padDroneSmall.colisionaPorAbajo(pwrPoder)){
                        iPoder = pwrPoder.getPoder();
                        bPoder = true;
                        iter.remove();
                    }
                }
                //version grow
                if(bGrow){
                    if(padDroneLarge.colisionaPorAbajo(pwrPoder)){
                        iPoder = pwrPoder.getPoder();
                        bPoder = true;
                        iter.remove();
                    }
                }
                //version normal
                else {
                    if(padDrone.colisionaPorAbajo(pwrPoder)){
                        iPoder = pwrPoder.getPoder();
                        bPoder = true;
                        iter.remove();
                    }      
                }        
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
        URL urlImagenFondo = this.getClass().getResource("breakingbad.png");
        Image imaImagenFondo = Toolkit.getDefaultToolkit().
                getImage(urlImagenFondo);
        
        // creo imagen GAME OVER
        URL urlImagenGameOver = this.getClass().getResource("gameover.png");
        Image imaImagenGameOver = Toolkit.getDefaultToolkit().
                getImage(urlImagenGameOver);
        
        //creo imagen WIN
        URL urlImagenWin = this.getClass().getResource("win.jpg");
        Image imaImagenWin = Toolkit.getDefaultToolkit().
                getImage(urlImagenWin);
        
        //creo imagen inicio
        URL urlImagenInicio = this.getClass().
                getResource("IntroScreen_Title_Screen.jpg");
        Image imaImagenInicio = Toolkit.getDefaultToolkit().
                getImage(urlImagenInicio);
        
        URL urlImagenIntro = this.getClass().
                getResource("IntroScreen_Intro.jpg");
        Image imaImagenIntro = Toolkit.getDefaultToolkit().
                getImage(urlImagenIntro);
        
        if(bInicio){
            switch(iInicio){
                case 1:graGraficaJFrame.drawImage(imaImagenInicio, 0, 0, 
                            getWidth(), getHeight(), this);
                    break;
                case 2:graGraficaJFrame.drawImage(imaImagenIntro, 0, 0, 
                            getWidth(), getHeight(), this);
                    break;     
                    
            }
                
        }
        // Despliego Game Over si ya no hay vidas
        else if (iVidas == 0) {
            graGraficaJFrame.drawImage(imaImagenGameOver, 0, 0, 
                getWidth(), getHeight(), this);   
        }
         
        //Si ya gane despliego victoria
        else if(bGano) {
            graGraficaJFrame.drawImage(imaImagenWin, 0, 0, 
                getWidth(), getHeight(), this);
        }
        
        else{
            //Despliego la imagen
            graGraficaJFrame.drawImage(imaImagenFondo, 0, 0, 
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
        if(iVidas != 0 & !bPausa & bolBola != null & padDrone != null
                & lnkBricks != null & !bCambioNivel & !bGano & !bInicio){
            
            //Dibuja la imagen de la bola y barra
            g.drawImage(bolBola.getImagen(), bolBola.getX(),
                bolBola.getY(), this);
            //muestra en pantalla version shrink
            if(bShrink){
                if (padDroneSmall != null && padDroneSmallIzq != null &&
                    padDroneSmallDer != null) { 

                    if(!bIzq && !bDer){
                        g.drawImage(padDroneSmall.getImagen(), 
                                padDroneSmall.getX(), padDroneSmall.getY() , 
                                    this);
                    }
                    //TEMPORAL FIX
                    else if(bIzq){
                        g.drawImage(padDroneSmallDer.getImagen(), 
                                padDroneSmall.getX(), padDroneSmall.getY() , 
                                    this);
                    }
                    else if(bDer) {
                        g.drawImage(padDroneSmallIzq.getImagen(),
                                padDroneSmall.getX(), padDroneSmall.getY() , 
                                    this);
                    }
                }
            }
            
            //Muestra en pantalla version grow
            else if(bGrow){
                if (padDroneLarge != null && padDroneLargeIzq != null &&
                    padDroneLargeDer != null) { 

                    if(!bIzq && !bDer){
                        g.drawImage(padDroneLarge.getImagen(), 
                                padDroneLarge.getX(), padDroneLarge.getY() , 
                                    this);
                    }
                    //TEMPORAL FIX
                    else if(bIzq){
                        g.drawImage(padDroneLargeDer.getImagen(), 
                                padDroneLarge.getX(), padDroneLarge.getY() , 
                                    this);
                    }
                    else if(bDer) {
                        g.drawImage(padDroneLargeIzq.getImagen(),
                                padDroneLarge.getX(), padDroneLarge.getY() , 
                                    this);
                    }
                }
            }
            // Muestra en pantalla el cuadro actual de la animación
            else {
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
            }
                  
            //dibujo bloques
            for(Object briBloque:lnkBricks) {
                Brick briBrick = (Brick) briBloque;
                if(!briBrick.estaDestruido()){
                    g.drawImage(briBrick.getImagen(), briBrick.getX(), 
                          briBrick.getY(), this);
                }
            }
            
            //Dibujo powerUps
            if(lnkPowerUps.size() != 0){
                for(Object pwrUp:lnkPowerUps) {
                    PowerUp pwrPoder = (PowerUp) pwrUp;
                    g.drawImage(pwrPoder.getImagen(), pwrPoder.getX(), 
                          pwrPoder.getY(), this);                }       
            }

            //Dibujo de boss
            if(!bossVillano.estaDestruido()){
                g.drawImage(bossVillano.getImagen(), bossVillano.getX(), 
                          bossVillano.getY(), this);
            }
            
            
            //despliego informacion necesaria
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g.drawString("Score: " + iScore, 10, getHeight() - 20);
            g.drawString("Lives: " + iVidas, 10, getHeight() - 40);
            g.drawString("Bricks to go: " + lnkBricks.size(),10,getHeight() 
                    - 60);            
        }  
        
        if(!bJuega & iVidas > 0 & !bGano & !bInicio){
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g.drawString("NIVEL " + iNivel, (getWidth() / 2) - 40, 200);
            switch(iNivel){
                //quien es el boss
                case 1:
                    g.drawString("DEA VS TUCO ", (getWidth() / 2) - 65, 220);
                    break;
                case 2:
                    g.drawString("DEA VS PRIMOS ", (getWidth() / 2) - 65, 220);
                    break;
                case 3:
                    g.drawString("DEA VS GUS FRING ", (getWidth() / 2) - 85, 
                            220);
                    break;
                case 4:
                    g.drawString("DEA VS JACK WELKER ", (getWidth() / 2) - 95, 
                            220);
                    break;
                case 5:
                    g.drawString("DEA VS HEISENBERG ", (getWidth() / 2) - 85, 
                            220);
                    break;
            }
            g.drawString("Presione Barra Espaciadora",
                (getWidth() / 2) - 135, 250);
        }
        
        //si muero dibujp opcion de iniciar
        if(iVidas == 0){
            g.drawString("Presiona ENTER para reiniciar ", 
                    (getWidth() / 2) - 135, getHeight() / 2);
        }
            
        //si gano opcion de reiniciar
        if(bGano){
            g.setColor(Color.black);
            g.drawString("Presiona ENTER para reiniciar ", 
                    (getWidth() / 2) - 135, getHeight() / 2);
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
            if(bInicio & iInicio < 2){
                iInicio++;
            }
            else if(iInicio == 2){
                bInicio = false;
                iInicio = 1;
            }
            else{
                bJuega = true;
            }

        }
        //si presiono P pausa
        else if(ke.getKeyCode() == KeyEvent.VK_P){ 
            bPausa = !bPausa;
        }
        
        else if(ke.getKeyCode() == KeyEvent.VK_ENTER){
            if(iVidas == 0){
                init();
            }     
            if(bGano){
                init();
            }
        }
        
        //CHEAT
        else if(ke.getKeyCode() == KeyEvent.VK_N){
            bCheat = true;
        }
    }
    
}

package Pruebas;

import Aplicacion.*; 
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
 
public class ArchivosPoobthogenTest{
   
    private static String NOEXCEPCION="No se espera una excepcion";
    private static String EXCEPCION="Se espera una excepcion";
    private static String EXCEPCION_INESPERADA= "La excepcion no se trata dentro del programa";
    
    public ArchivosPoobthogenTest(){
    }

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }
    
    @Test
    public void deberiaGuardarElTablero(){
    	String[][] texample = {{"B1"}};
        Tablero t = new Tablero(1, 1);
        File archivo = new File("archivo.dat");
        try{
            t.agregaJugador(new Jugador("1"));
            t.agregarElemento(1, 1, 1, "NivelDos", false);
            PoobthogenArchivos.guardar(archivo, t); 
        }catch(Exception e){
                fail(NOEXCEPCION+e.getMessage());
        }
    } 

    @Test
    public void deberiaDarErrorSiNoHayArchivo(){
        Tablero d = new Tablero(1, 1);
        try{
            PoobthogenArchivos.guardar(null, d); 
            fail(EXCEPCION);
        }catch(Exception e){
            if(!e.getMessage().equals("Error al guardar: "+PoobthogenExcepcion.ARCHIVO_INVALIDO)){
                fail(EXCEPCION_INESPERADA);
            }
        }
    }

    @Test
    public void deberiaDarErrorSiNoHayTablero(){
        File archivo = new File("archivo.dat");
        try{
            PoobthogenArchivos.guardar(archivo, null); 
            fail(EXCEPCION);
        }catch(Exception e){
            if(!e.getMessage().equals("Error al guardar: "+PoobthogenExcepcion.TABLERO_INVALIDO)){
                fail(EXCEPCION_INESPERADA);
            }
        }
    }

    @Test
    public void deberiaDarErrorSiElFormatoDelArchivoNoEsCorrecto(){
        File archivo = new File("archivo.txt");
        Tablero d = new Tablero(1, 1);
        try{
            PoobthogenArchivos.guardar(archivo, d); 
            fail(EXCEPCION);
        }catch(Exception e){
            if(!e.getMessage().equals("Error al guardar: "+PoobthogenExcepcion.FORMATO_INVALIDO+". Se esperaba un archivo .dat")){
                fail(EXCEPCION_INESPERADA);
            }
        }
    }

    @Test
    public void deberiaDarErrorSiElFormatoDelArchivoNoEsCorrectoAlAbrir(){
        File archivo = new File("archivo.txt");
        try{
            Tablero d = PoobthogenArchivos.abrir(archivo); 
            fail(EXCEPCION);
        }catch(Exception e){
            if(!e.getMessage().equals("Error al abrir: "+PoobthogenExcepcion.FORMATO_INVALIDO+". Se esperaba un archivo .dat")){
                fail(EXCEPCION_INESPERADA);
            }
        }
    }

    @Test
    public void deberiaDarErrorSiElArchivoNoExiste(){ 
        try{
            Tablero d = PoobthogenArchivos.abrir(null); 
            fail(EXCEPCION);
        }catch(Exception e){
            if(!e.getMessage().equals("Error al abrir: "+PoobthogenExcepcion.ARCHIVO_INVALIDO)){
                fail(EXCEPCION_INESPERADA);
            }
        }
    }

    @Test
    public void deberiaAbrirElTablero(){
        Tablero d = new Tablero(1, 1);
        String[][] texample = {{"B1"}};
        Tablero t = new Tablero(1, 1);
        File archivo = new File("archivo.dat");
        try{
            t.agregaJugador(new Jugador("1"));
            t.agregarElemento(1, 1, 1, "NivelDos", false);
            PoobthogenArchivos.guardar(archivo, d); 
            Tablero n = PoobthogenArchivos.abrir(archivo); 
        }catch(Exception e){
            fail(NOEXCEPCION);
        }
    } 
}
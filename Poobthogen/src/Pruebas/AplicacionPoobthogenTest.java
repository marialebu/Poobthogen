package Pruebas;

import Aplicacion.*; 
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
 
public class AplicacionPoobthogenTest{
   
    private static String NOEXCEPCION="No se espera una excepcion";
    private static String EXCEPCION="Se espera una excepcion";
    private static String EXCEPCION_INESPERADA= "La excepcion no se trata dentro del programa";
    
    public AplicacionPoobthogenTest(){
    }

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }
    
    @Test
    public void deberiaCrearFichaDeNivelUno()throws PoobthogenExcepcion{
    	String[][] prueba = 
    			{{"-", "-", "-", "-", "-",},
    			{"-", "-", "-", "-", "-",},
    			{"-", "-", "U1", "-", "-",}, 
    			{"-", "-", "-", "-", "-",},
    			{"-", "-", "-", "-", "-",}};
    	Tablero t = new Tablero(5, 5, false);
    	t.agregaJugador(new Jugador('1'));
		t.agregaJugador(new Jugador('2'));
		t.agregarElemento(1, 2, 2, "NivelUno", true);
		assertEquals("Deberian ser iguales", prueba, t.obtener());
    } 
    
    @Test
    public void deberiaEvolucionarFichasDeNivelUno()throws PoobthogenExcepcion{
    	String[][] prueba = 
    			{{"-", "-", "-", "-", "-",},
    			{"-", "U1", "U1", "U1", "-",},
    			{"U1", "D1", "D1", "D1", "U1",}, 
    			{"-", "U1", "U1", "U1", "-",},
    			{"-", "-", "-", "-", "-",}};
    	Tablero t = new Tablero(5, 5, false);
    	t.agregaJugador(new Jugador('1'));
		t.agregaJugador(new Jugador('2'));
		t.agregarElemento(1, 2, 2, "NivelUno", true);
		t.agregarElemento(1, 2, 1, "NivelUno", true);
		t.agregarElemento(1, 2, 3, "NivelUno", true);
		t.agregarElemento(1, 2, 2, "NivelDos", true);
		assertEquals("Deberian ser iguales", prueba, t.obtener());
    } 
    
    
    
}
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
    public void deberiaCrearFichaDeNivelUno(){
    	try{
    		String[][] prueba = 
    			{{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"},
    			{"-", "-", "U1", "-", "-"}, 
    			{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelUno", true);
			assertEquals("Deberian ser iguales", prueba, t.obtener());
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    	
    } 
    
    @Test
    public void deberiaEvolucionarFichasDeNivelUnoConDos(){
    	try{
    		String[][] prueba = 
    			{{"-", "-", "-", "-", "-"},
    			{"-", "U1", "U1", "U1", "-"},
    			{"U1", "D1", "D1", "D1", "U1"}, 
    			{"-", "U1", "U1", "U1", "-"},
    			{"-", "-", "-", "-", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 1, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 3, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 2, "NivelDos", true);
			t.cambiarTurno();
			assertEquals("Deberian ser iguales", prueba, t.obtener());	
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    }
    
    @Test
    public void deberiaEvolucionarFichasDeNivelUnoConUno(){
    	try{
    		String[][] prueba = 
    			{{"-", "-", "-", "-", "-"},
    			{"-", "U1", "U1", "U1", "-"},
    			{"U1", "D1", "D1", "D1", "U1"}, 
    			{"-", "U1", "U1", "U1", "-"},
    			{"-", "-", "-", "-", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 1, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 3, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 2, "NivelUno", true);
			t.cambiarTurno();
			assertEquals("Deberian ser iguales", prueba, t.obtener());	
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    }
    
    @Test
    public void deberiaEvolucionarFichasDeNivelUnoConTres(){
    	try{
    		String[][] prueba = 
    		{{"-", "-","U1","-","-"}, 
    		{"-","U1","D1","U1","-"}, 
    		{"U1","D1","T1","D1","U1"}, 
    		{"-","U1","D1","U1","-"},
    		{"-", "-","U1","-","-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 1, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 3, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 2, "NivelTres", true);
			t.cambiarTurno();
			assertEquals("Deberian ser iguales", prueba, t.obtener());
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    	
    }
    
    @Test
    public void deberiaDestruirFichasDeNivelUno()throws PoobthogenExcepcion{
    	try{
    		String[][] prueba = 
    			{{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"},
    			{"-", "G1", "G1", "G1", "-"}, 
    			{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"}};
	        	Tablero t = new Tablero(5, 5, false);
	        	t.agregaJugador(new Jugador('1', t));
				t.agregaJugador(new Jugador('2', t));
	    		t.agregarElemento(2, 2, 2, "NivelUno", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(2, 2, 1, "NivelUno", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(2, 2, 3, "NivelUno", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(1, 2, 2, "Destructor", true);
	    		t.cambiarTurno();
	    		assertEquals("Deberian ser iguales", prueba, t.obtener());
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    }
    
    @Test
    public void deberiaLanzarExcepcionAlTratarDConquistarUnoConUno(){
    	try{
    		String[][] prueba = 
    			{{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"},
    			{"-", "U2", "U2", "U2", "-"}, 
    			{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"}};
	        	Tablero t = new Tablero(5, 5, false);
	        	t.agregaJugador(new Jugador('1', t));
				t.agregaJugador(new Jugador('2', t));
	    		t.agregarElemento(2, 2, 2, "NivelUno", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(2, 2, 1, "NivelUno", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(2, 2, 3, "NivelUno", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(1, 2, 2, "NivelUno", true);
	    		t.cambiarTurno();
	    		assertEquals("Deberian ser iguales", prueba, t.obtener());
	    		fail(EXCEPCION); 
    	}catch (PoobthogenExcepcion e){
    		
    	}
    }
    
    @Test
    public void deberiaConquistarFichasDeNivelUnoConDos(){
    	try{
    		String[][] prueba = 
    			{{"-", "-", "-", "-", "-"},
    			{"-", "U2", "U2", "U2", "-"},
    			{"U2", "D2", "D2", "D2", "U2"}, 
    			{"-", "U2", "U2", "U2", "-"},
    			{"-", "-", "-", "-", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 1, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 3, "NivelUno", true);
			t.cambiarTurno();
			t.agregarElemento(2, 2, 2, "NivelDos", true);
			t.cambiarTurno();
			assertEquals("Deberian ser iguales", prueba, t.obtener());	
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}    	
    }
    
    @Test
    public void deberiaCrearFichaDeNivelDos(){
    	try{
    		String[][] prueba = 
    			{{"-", "-", "-", "-", "-"},
    			{"-", "-", "U1", "-", "-"},
    			{"-", "U1", "D1", "U1", "-"}, 
    			{"-", "-", "U1", "-", "-"},
    			{"-", "-", "-", "-", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelDos", true);
			assertEquals("Deberian ser iguales", prueba, t.obtener());
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    	
    } 
    
    @Test
    public void deberiaEvolucionarFichasDeNivelDosConTres(){
    	try{
    		String[][] prueba = 
    			{{"-", "U1", "U1", "U1", "-"},
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"D1", "T1", "T1", "T1", "D1"}, 
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"-", "U1", "U1", "U1", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelDos", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 1, "NivelDos", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 3, "NivelDos", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 2, "NivelTres", true);
			t.cambiarTurno();
			assertEquals("Deberian ser iguales", prueba, t.obtener());	
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    }
    
    @Test
    public void deberiaEvolucionarFichasDeNivelDosConDos(){
    	try{
    		String[][] prueba = 
    			{{"-", "U1", "U1", "U1", "-"},
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"D1", "T1", "T1", "T1", "D1"}, 
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"-", "U1", "U1", "U1", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelDos", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 1, "NivelDos", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 3, "NivelDos", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 2, "NivelDos", true);
			t.cambiarTurno();
			assertEquals("Deberian ser iguales", prueba, t.obtener());	
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    }
    
    @Test
    public void deberiaDestruirFichasDeNivelDos() throws PoobthogenExcepcion{
    	//try{
    		String[][] prueba = 
    			{{"-", "-", "-", "-", "-"},
    			{"-", "U2", "U2", "U2", "-"},
    			{"U2", "G1", "G1", "G1", "U2"}, 
    			{"-", "U2", "U2", "U2", "-"},
    			{"-", "-", "-", "-", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(2, 2, 2, "NivelDos", true);
			t.cambiarTurno();
			t.agregarElemento(2, 2, 1, "NivelDos", true);
			t.cambiarTurno();
			t.agregarElemento(2, 2, 3, "NivelDos", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 2, "Destructor", true);
			t.cambiarTurno();
			assertEquals("Deberian ser iguales", prueba, t.obtener());	
    	//}
    }
    @Test
    public void deberiaLanzarExcepcionAlTratarDConquistarDosConDos(){
    	try{
    		String[][] prueba = 
    			{{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"},
    			{"-", "U1", "U1", "U1", "-"}, 
    			{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"}};
	        	Tablero t = new Tablero(5, 5, false);
	        	t.agregaJugador(new Jugador('1', t));
				t.agregaJugador(new Jugador('2', t));
	    		t.agregarElemento(1, 2, 2, "NivelDos", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(1, 2, 1, "NivelDos", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(1, 2, 3, "NivelDos", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(2, 2, 2, "NivelDos", true);
	    		t.cambiarTurno();
	    		assertEquals("Deberian ser iguales", prueba, t.obtener());
	    		fail(NOEXCEPCION); 
    	}catch (PoobthogenExcepcion e){
    		
    	}
    }
    
    @Test
    public void deberiaLanzarExcepcionAlTratarDConquistarDosConUno(){
    	try{
    		String[][] prueba = 
    			{{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"},
    			{"-", "U1", "U1", "U1", "-"}, 
    			{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"}};
	        	Tablero t = new Tablero(5, 5, false);
	        	t.agregaJugador(new Jugador('1', t));
				t.agregaJugador(new Jugador('2', t));
	    		t.agregarElemento(1, 2, 2, "NivelDos", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(1, 2, 1, "NivelDos", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(1, 2, 3, "NivelDos", true);
	    		t.cambiarTurno();
	    		t.agregarElemento(2, 2, 2, "NivelUno", true);
	    		t.cambiarTurno();
	    		assertEquals("Deberian ser iguales", prueba, t.obtener());
	    		fail(EXCEPCION); 
    	}catch (PoobthogenExcepcion e){
    		
    	}
    }
    
    @Test
    public void deberiaCrearFichaDeNivelTres(){
    	try{
    		String[][] prueba = 
    			{{"-", "-", "U1", "-", "-"},
    			{"-", "U1", "D1", "U1", "-"},
    			{"U1", "D1", "T1", "D1", "U1"}, 
    			{"-", "U1", "D1", "U1", "-"},
    			{"-", "-", "U1", "-", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelTres", true);
			assertEquals("Deberian ser iguales", prueba, t.obtener());
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    	
    } 
    
    @Test
    public void deberiaEvolucionarFichasDeNivelTresConTres(){
    	try{
    		String[][] prueba = 
    			{{"-", "U1", "U1", "U1", "-"},
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"D1", "B1", "B1", "B1", "D1"}, 
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"-", "U1", "U1", "U1", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 1, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 3, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 2, "NivelTres", true);
			t.cambiarTurno();
			assertEquals("Deberian ser iguales", prueba, t.obtener());	
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    }
    
    @Test
    public void deberiaLanzarExcepcionAlEvolucionarFichasDeNivelTresConDos(){
    	try{
    		String[][] prueba = 
    			{{"-", "U1", "U1", "U1", "-"},
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"D1", "T1", "T1", "T1", "D1"}, 
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"-", "U1", "U1", "U1", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 1, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 3, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 2, "NivelDos", true);
			t.cambiarTurno();
			assertEquals("Deberian ser iguales", prueba, t.obtener());
			fail(EXCEPCION);
    	}catch (PoobthogenExcepcion e){
    	}
    }
    
    @Test
    public void deberiaLanzarExcepcionAlEvolucionarFichasDeNivelTresConUno(){
    	try{
    		String[][] prueba = 
    			{{"-", "U1", "U1", "U1", "-"},
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"D1", "T1", "T1", "T1", "D1"}, 
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"-", "U1", "U1", "U1", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 1, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 3, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 2, "NivelUno", true);
			t.cambiarTurno();
			assertEquals("Deberian ser iguales", prueba, t.obtener());
			fail(EXCEPCION);
    	}catch (PoobthogenExcepcion e){
    	}
    }
    
    @Test
    public void deberiaDestruirFichasDeNivelTres(){
    	try{
    		String[][] prueba = 
    			{{"-", "U1", "U1", "U1", "-"},
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"D1", "G2", "G2", "G2", "D1"}, 
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"-", "U1", "U1", "U1", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 1, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 3, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(2, 2, 2, "Destructor", true);
			t.cambiarTurno();
			assertEquals("Deberian ser iguales", prueba, t.obtener());	
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    }
    
    @Test
    public void deberiaLanzarExcepcionAlDestruirBloques(){
    	try{
    		String[][] prueba = 
    			{{"-", "U1", "U1", "U1", "-"},
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"D1", "B1", "B1", "B1", "D1"}, 
    			{"U1", "D1", "D1", "D1", "U1"},
    			{"-", "U1", "U1", "U1", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(1, 2, 2, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 1, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 3, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(1, 2, 2, "NivelTres", true);
			t.cambiarTurno();
			t.agregarElemento(2, 2, 2, "Destructor", true);
			assertEquals("Deberian ser iguales", prueba, t.obtener());
			fail(EXCEPCION);
    	}catch (PoobthogenExcepcion e){
    		
    	}
    }
    
    @Test
    public void deberiaPonerVirusSobreDestructor(){
    	try{
    		String[][] prueba = 
    			{{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"},
    			{"-", "-", "U2", "-", "-"}, 
    			{"-", "-", "-", "-", "-"},
    			{"-", "-", "-", "-", "-"}};
	    	Tablero t = new Tablero(5, 5, false);
	    	t.agregaJugador(new Jugador('1', t));
			t.agregaJugador(new Jugador('2', t));
			t.agregarElemento(2, 2, 2, "NivelUno", true);
			t.agregarElemento(1, 2, 2, "Destructor", true);
			t.agregarElemento(2, 2, 2, "NivelUno", true);
			assertEquals("Deberian ser iguales", prueba, t.obtener());
    	}catch (PoobthogenExcepcion e){
    		fail(NOEXCEPCION);
    	}
    	
    } 
    
}
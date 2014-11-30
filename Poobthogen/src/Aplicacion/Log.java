package Aplicacion;
import java.util.*;
import java.io.*;

public class Log {
	
	private static final String NAME_LOGFILE="poobthogen.log";
    private static File LOG_FILE = new File(NAME_LOGFILE);
    
    /**
     * Registra el error en el log. 
     * @param excepcion
     */
    public static void registreError(Exception excepcion){
        try{
           FileWriter out = new FileWriter( LOG_FILE, true );
            PrintWriter log = new PrintWriter( out );
            log.println( "---------------------------------------" );
            log.println( "Registro :" + new Date( ).toString( ) );
            log.println( "---------------------------------------" );
            excepcion.printStackTrace( log );
            log.close( );
            out.close( ); 
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase Servidor_Interfaz que extiende de Thread. 
 * Se encarga de generar los métodos de control usados por el Servidor HTTP 
 * atendiendo peticiones de tipo 'GET' recibidas por el puerto 8066 para
 * atender a los Clientes conectados a la aplicación.
 *
 * @author Odei
 * @version 30.03.2016
 */
public class Servidor extends Thread {
    /**
     * Variable entera usada para almacenar el Puerto de Conexión a la escucha.
     */
    protected static final int Puerto = 8066;
    
    /**
     * Variable de tipo cadena usada para almacenar la Ruta del Servidor 
     * donde estan almacenados los ficheros que usaremos durante la ejecución.
     */
    protected static final String ruta = "src/recursos/";
    
    /**
     * Variable usada para almacenar la cabecera de una petición correcta.
     */
    protected final String cabecera_OK = "HTTP/1.1 200 OK";
    
    /**
     * Variable usada para almacenar la cabecera de una petición no encontrada.
     */
    protected final String cabecera_NotFound = "HTTP/1.1 404 Not Found";
    
    /**
     * Variable usada para almacenar la cabecera de una petición denegada.
     */
    protected final String cabecera_Forbidden = "HTTP/1.1 403 Forbidden";
    
    /**
     * Variable usada para almacenar la cabecera de una petición mal formada.
     */
    protected final String cabecera_BadRequest = "HTTP/1.1 400 Bad Request";
    
    /**
     * Variable usada para almacenar el Socket usado por el Cliente.
     */
    protected Socket sCliente;
    
    /**
     * Constructor de la Clase Servidor.
     * Asigna el Socket del Cliente para atender su petición usando Hilos.
     * 
     * @param sCliente Socket: Socket del Cliente a atender
     */
    public Servidor(Socket sCliente) {
        this.sCliente = sCliente;
    }
    
    /**
     * Método que simula la lógica de la aplicación.
     * Realiza un bucle infinito donde se atienden las peticiones enviadas
     * desde los Clientes y se procesan las mismas en el Servidor HTTP.
     */
    @Override
    public void run(){
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(sCliente.getInputStream()));          // Obtenemos el buffer del flujo del Cliente
            String peticion = br.readLine();                                    // y extraemos las peticiones del mismo
            
            System.out.println(peticion);
            peticion = peticion.replaceAll(" ","");                             // Compactamos la petición para facilitar su análisis
            if (peticion.startsWith("GET")) {                                   // Procesamos las peticiones GET
                String url = "index.html";                                      // e inicializamos la página inicial a servir como el index.html
                PrintWriter pw=new PrintWriter(sCliente.getOutputStream(),true);// Creamos el objeto que contendrá el flujo de salida
                peticion = peticion.substring(3, peticion.lastIndexOf("HTTP")); // y extraemos la subcadena entre 'GET' y 'HTTP/1.1'
                peticion = peticion.toLowerCase();                              // convirtiendola a minúscila
                if (!peticion.equals("/favicon.ico")) {                         // Si el motor del navegador es de Google requerirá en la cabecera un fichero favicon.ico por lo que no procesamos estas peticiones
                    if (peticion.length() == 0 || peticion.equals("/")) {       // Si la petición se realiza sobre la dirección principal o  de inicio
                        pw.println(cabecera_OK);                                // procesamos la cabecera concreta
                    } else {
                        if (peticion.contains("../")) {                         // Si la petición contiene los caracteres ../ asignamos la página correspondiente
                            pw.println(cabecera_Forbidden);
                            url = "403.html";
                        } else if (!peticion.endsWith(".html")) {               // Si la petición no acaba en .html asignamos la página correspondiente
                            pw.println(cabecera_BadRequest);
                            url = "400.html";
                        } else if (!new File(ruta+"www/"+peticion).exists()) {  // Si la petición se realiza sobre una página que no existe asignamos la página correspondiente
                            pw.println(cabecera_NotFound);
                            url = "404.html";
                        } else {                                                // En caso contrario aisgnamos la página solicitada
                            pw.println(cabecera_OK);
                            url = peticion;
                        }
                    }
                    File fic = new File(ruta + "www/" + url); 
                    pw.println("Content-Type: text/html; charset=UTF-8;");
                    pw.println("Content-Length: " + fic.length() + "\n");       // Escribiendo los datos oporunos en la cabecera de la respuesta
                    mandarFichero(fic, pw);                                     // y enviando al Cliente la página asignada
                    Servidor_Interfaz.taLog.setText(Servidor_Interfaz.taLog.
                        getText() + "Servido "+url+" ->\t" + getFecha() + "\n");
                }   
            }
            sCliente.close();                                                   // Cerramos el socket
        } catch (IOException e) { }
    }
        
    /**
     * Método usado para guardar un fichero log en el Servidor.
     * Crea un fichero con la fecha de cierre del Servidor que contiene
     * todas las operaciones realizadas en el mismo durante su ejecución.
     */
    protected static void guardarLog() {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(ruta + "server "+getFecha()+".log"));        // Creamos el fichero log
            bw.write(Servidor_Interfaz.taLog.getText());                        // con los valores contenidos en el Text Area
            bw.flush();                                                         // y lo escribimos
        } catch (IOException ex) { }
    }
    
    /**
     * Método usado para escribir el contenido del Fichero recibido.
     * Muestra al Cliente el contenido del fichero recibido como parámetro.
     *
     * @param fic File: nombre del fichero a buscar y leer
     * @param pw PrintWriter: objeto usado para imprimir la lectura del fichero
     */
    protected static void mandarFichero(File fic, PrintWriter pw) {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fic))) { // Creamos un Buffer para capturar los datos contenidos dentro del fichero enviado
                String linea;
                while ((linea = br.readLine())!=null){                          // lo recorremos de principio a fin
                    pw.println(linea);                                          // escribiendo su contenido en el Cliente
                    pw.flush();
                }
            }
        } catch (IOException ex) { }                                            // Comprobamos si existen errores y los procesamos
    }
    
    /**
     * Método usado para obtener la fecha actual en forma de cadena formateada..
     *
     * @return String: cadena con la fecha actual formateada
     */
    protected static String getFecha() {
        return new SimpleDateFormat("HH.mm.ss dd-MM-yyyy")
                .format(new Date().getTime());                                  // Devolvemos la fecha y hora actual formateada
    }
    
    /**
     * Método Principal de la Clase Servidor.
     * Asigna y procesa cada petición entrante de Clientes con un Hilo de Socket
     *
     * @param args String[]: argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        try {
            Servidor_Interfaz interfaz = new Servidor_Interfaz();               // Lanzamos una Instancia de la Interfaz Gráfica para el Servidor HTTP
            ServerSocket skServidor = new ServerSocket(Puerto);                 // Iniciamos la escucha del servidor en el puerto preestablecido
            while (true) {                                                      // Repetimos indefinidamente la escucha de peticiones hasta que cerremos la Interfaz
               Socket sCliente = skServidor.accept();                           // Esperamos a que se conecte un cliente y creamos un nuevo socket para el mismo
               new Servidor(sCliente).start();                                  // Atendemos dicho Socket mediante un Hilo
            }
        } catch(IOException e) {
            if (e.getMessage().equals("Address already in use: JVM_Bind")) {    // Comprobamos si existen errores y los procesamos
                Servidor_Interfaz.taLog.setText("El Servidor ya está arrancado");
            }
        }  
    }
}
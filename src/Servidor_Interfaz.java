import java.awt.Color;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase Servidor_Interfaz que extiende de JFrame. 
 * Se encarga de generar la Interfaz que usaremos para implementar los métodos
 * de control usados para el Servidor HTTP de la aplicación.
 *
 * @author Odei
 * @version 30.03.2016
 */
public class Servidor_Interfaz extends JFrame {
    /**
     * Variable usada para mostrar los eventos producidos durante la ejecución.
     */
    protected static TextArea taLog;

    /**
     * Variable usada para mostrar las Instrucciones durante la ejecución.
     */
    protected static TextArea taIns;
        
    /**
     * Constructor de la Interfaz Gráfica implementada para el Servidor.
     * Genera e inicializa la Interfaz y los elementos utilizados
     * para visualizar de forma interactiva la ejecución de la aplicación.
     */
    public Servidor_Interfaz() {
        JPanel panel = new JPanel(null);                                        // Creamos un panel para dibujar la interfaz gráfica
        JLabel lbLog = new JLabel("Log");                                  // Agregamos etiquetas y demás elementos a la Interfaz
        JLabel lbIns = new JLabel("Intrucciones");
        taLog = new TextArea("Servidor Arrancado ->\t"+Servidor.getFecha()+"\n");
        taIns = new TextArea("El Servidor Web se está ejecutando y permanece\n"
            + "a la escucha por el puerto 8066. Para solicitar la\npágina de "
            + "Inicio escribe en la barra de direcciones \nde tu explorador "
            + "preferido:\n\nhttp://localhost:"+Servidor.Puerto+"\n");
        panel.add(lbLog).setBounds(150, 7, 80, 20);                             // Los posicionamos en el panel
        panel.add(lbIns).setBounds(430, 7, 80, 20); 
        panel.add(taLog).setBounds(25, 35, 290, 230);
        panel.add(taIns).setBounds(320, 35, 305, 230);
        lbLog.setForeground(Color.black);                                       // Cambiamos el color del label del título
        taLog.setEditable(false);                                               // Imposibilitamos la edición de los Text Area
        taIns .setEditable(false); 
        
        JFrame frame = new JFrame("Servidor HTTP");                             // Creamos JFrame y le ponemos título
        frame.add(panel);                                                       // agregando el panel previamente creado
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(Toolkit.getDefaultToolkit().createImage(
                Servidor_Interfaz.class.getResource("recursos/server.png")));   // Le ponemos una imágen de icono a la ventana
        frame.setSize(655, 320);                                                // y le asignamos tamaño y demás parámetros
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {                           // Añadimos un escuchador de eventos para capturar el cierre de la ventana
            @Override
            public void windowClosing(WindowEvent e) {
                Servidor.guardarLog();                                          // en dicho caso almacenamos en un fichero log el contenido de la sesión actual
                System.exit(0);
            }
        });
    }
}
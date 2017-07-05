Servidor HTTP 0.9
================================

Aplicación que implementa un servidor web HTTP desarrollado en Java. El programa nos permite recibir
y gestionar peticiones controladas mediante el uso de hilos y Sockets, realizando funciones multi-hilo 
de manera concurrente.

La aplicación habilita una interfaz gráfica para capturar todos los eventos producidos en el servidor,
a su vez, cada vez que éste procesa una orden, se genera un registro en el mismo que será capturado y
exportado a un fichero .log al cerrar el programa.

El servidor atenderá peticiones desde la url: http://localhost:8066

Para facilitar la puesta en marcha de la aplicación, se proporciona un ejecutable .jar con el proyecto 
construido y listo para ser ejecutado de manera gráfica.

## Requisitos
- [Java] 7 o superior (para ejecutar la Aplicación)
- Navegador Web [Chrome], [Firefox], [Opera], [Microsoft Edge], etc.., para conectarse al servidor

## Entorno de desarrollo
La aplicación ha sido desarrollada utilizando el IDE [NetBeans] pero también es posible su importanción 
en [Eclipe] y demás IDE's.

## Licencia
Esta aplicación se ofrece bajo licencia [GPL versión 3].

[Chrome]: https://www.google.es/chrome/browser/desktop/index.html
[Firefox]: https://www.mozilla.org/es-ES/firefox/new/
[Opera]: http://www.opera.com/es
[Microsoft Edge]: https://www.microsoft.com/es-es/windows/microsoft-edge
[GPL versión 3]: https://www.gnu.org/licenses/gpl-3.0.en.html
[NetBeans]: https://netbeans.org/
[Eclipe]: https://eclipse.org/
[Java]: https://www.java.com/

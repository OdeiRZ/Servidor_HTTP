Servidor HTTP 0.9
================================

Aplicaci�n que implementa un servidor web HTTP desarrollado en Java. El programa nos permite recibir
y gestionar peticiones controladas mediante el uso de hilos y Sockets, por lo que el software realiza
funciones multi-hilo de manera concurrente.

La aplicaci�n habilita una interfaz gr�fica para capturar todos los eventos producidos en el servidor,
a su vez, cada vez que este procesa una orden, se genera un registro en el mismo que ser� capturado y
exportado a un fichero .log al cerrar el programa.

El servidor atender� peticiones desde la siguiente url: http://localhost:8066

## Requisitos
- [Java] 7 o superior (para ejecutar la Aplicaci�n)
- Navegador Web [Chrome], [Firefox], [Opera], [Microsoft Edge], etc.., para conectarse al servidor

## Entorno de desarrollo
La Aplicaci�n ha sido desarrollada utilizando el IDE [NetBeans] pero tambi�n es posible su importanci�n 
en [Eclipe] y dem�s IDE's.

## Licencia
Esta aplicaci�n se ofrece bajo licencia [GPL versi�n 3].

[Chrome]: https://www.google.es/chrome/browser/desktop/index.html
[Firefox]: https://www.mozilla.org/es-ES/firefox/new/
[Opera]: http://www.opera.com/es
[Microsoft Edge]: https://www.microsoft.com/es-es/windows/microsoft-edge
[GPL versi�n 3]: https://www.gnu.org/licenses/gpl-3.0.en.html
[NetBeans]: https://netbeans.org/
[Eclipe]: https://eclipse.org/
[Java]: https://www.java.com/
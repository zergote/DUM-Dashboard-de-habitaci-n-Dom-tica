# DUM-Dashboard-de-habitacion-Domotica
Panel de control para el proyecto DUM de Una habitación Domótica. 

## Vista Del Diseño
Para el diseño se opto por utilizar un diseño flotante y para el look and feel se utilizo la biblioteca para Java Swing [BeautyEye](https://github.com/JackJiang2011/beautyeye)

![Dashboard](/Vista%20final.png)

## Funciones
En el panel se establece una conexión con el microcontrolador Teensy 3.1 pudiendo leer las variables de este o modificarlas permitiendo de este modo poder tener total control sobre los sensores y actuadores conectados a el.

### Mediante el panel se puede acceder a la siguiente información sobre la habitación:
- Temperatura.
- Humedad.
- Humo o gas presentes.
- Intrusos en el área.
- Fuego.
- Estado de luces.

### Se puede controlar mediante el panel las siguientes funciones:
- Las luces y ventiladores de la habitación.
- Deshabilitar o habilitar sensores.
- Ajustar los disparadores para regular la temperatura y la humedad del área.

### Caracteristicas adicionales del panel de control:
- Registro de todos los eventos que ocurren.
- Notificación por medio de correo electrónico en caso de producirse fuego o detectarse intrusos en el área.

## Bibliotecas utilizadas
- [BeautyEye](https://github.com/JackJiang2011/beautyeye)
- [JFreeChart](http://www.jfree.org/jfreechart/)
- [jSSC (Java Simple Serial Connector)](https://github.com/scream3r/java-simple-serial-connector)
- [JavaMail](https://javaee.github.io/javamail/)
- [SQLite](https://github.com/xerial/sqlite-jdbc)

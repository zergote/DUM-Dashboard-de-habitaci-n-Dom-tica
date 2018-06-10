# DUM Dashboard of Domotic Room
Control panel for the DUM project of One Room Domotics. 

## Design View
For the design it was chosen to use floating windows and for the look and feel the Java Swing library was used [BeautyEye](https://github.com/JackJiang2011/beautyeye)

![Dashboard](/Vista%20final.png)

## Functions
In the panel a connection is established with the microcontroller Teensy 3.1 being able to read the variables of this one or to modify them allowing this way to be able to have total control on the sensors and actuators connected to it.

### The following information about the room can be accessed via the panel
- Temperature.
- Moisture.
- Smoke or gas present.
- Intruders in the area.
- Fire.
- Status of lights.

### The following functions can be controlled via the panel
- The lights and fans in the room.
- Disable or enable sensors.
- Adjust the shutters to regulate the temperature and humidity of the area.

### Additional control panel features
- Recording of all events that occur.
- Notification by e-mail in case of fire or intruders are detected in the area.

## Libraries used
- [BeautyEye](https://github.com/JackJiang2011/beautyeye)
- [JFreeChart](http://www.jfree.org/jfreechart/)
- [jSSC (Java Simple Serial Connector)](https://github.com/scream3r/java-simple-serial-connector)
- [JavaMail](https://javaee.github.io/javamail/)
- [SQLite](https://github.com/xerial/sqlite-jdbc)

# Fire alarm system and security sensor system, implemented in Java Swing

This security system uses the file "alarm.wav" to
create the alarm sound. A "password.txt" file is the persistence object used
to store and load the alarm system's password. The Security System
automatically appends to a log file "alarm-log.dat" when the system is
triggered, or when it is reset from a trigger state.

An inner class in AdvancedArmingMenu.java, ButtonColumn, is the work of Rob
Camick and can be obtained at the following URL:

https://tips4java.wordpress.com/2009/07/12/table-button-column/

All other code was the original work of the author.

## Class diagram
![Class diagram](https://github.com/thalatta/alarmsystem/blob/master/project-class-diagram.png
)

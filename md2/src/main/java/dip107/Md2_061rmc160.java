
package dip107;

import java.io.InputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

public class Md2_061rmc160 {
    // region utils
    private static String makeFloatString(String inputString) {
        DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance();
        DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
        char sep = symbols.getDecimalSeparator();
        if (inputString.indexOf(sep) > -1)
            return inputString;
        else {
            char otherSep = sep == ',' ? '.' : ',';
            return inputString.replace(otherSep, sep);
        }
    }

    // no passing by reference possible in Java at all?? aww...
    private static float getInput(Scanner sc, PrintStream outputStream, char varName) {
        outputStream.print(varName + "=");
        // wtf-cross thread write happening?? private to class not object - omg wth
        system_exit = false;
        // infinity is an invalid value legal float value example for coordinates!
        if (sc.hasNext("[+-]?[\\d]+([\\.,]\\d+)?")) {
            return Float.parseFloat(makeFloatString(sc.next()));
        } else {
            sc.next();
            outputStream.println();
            outputStream.println("input-output error");
            system_exit = true;
            return -11111111.222222f;
        }
    }
    // endregion

    // region main
    public static void main(String[] args) {
        testableMain(System.in, System.out);
    }

    private static Boolean system_exit = false;

    public static void testableMain(InputStream inputStream, PrintStream outputStream) {
        Scanner sc = new Scanner(inputStream);
        // Pēdējie divi Brīvas Kaujas lauka Sākuma Leņķis Laika
        // studenta Kaujas vieta krišanas numurs ātrums a intervāls
        // apliecības paātrinājums g v0 (grādos) (solis) delta(t)
        // numura cipari (m/s^2) (m/s) (s)

        // 60 Zeme 9.81 B 12 @in 0.05
        double v0 = 12, t = 0, x = 0, y = 0, a, g = 9.81;
        Boolean hitTarget = false;
        outputStream.println("061RMC160 Oskars Grauzis 4");
        a = getInput(sc, outputStream, 'a');
        // TODO: pajautaat kaa jaabuut - ka enter no usera (un steramaa taatad kopa
        // prompt a=result:)
        //outputStream.println();
        if (system_exit) {
            sc.close();
            return;
        }
        // Trešais no beigām studenta apliecības numura cipars 1 vai 6:
        outputStream.println("result:");
        outputStream.println("t \t x \t y");
        t = 0.05;
        // Ir jāizmanto operators do while. Ir aizliegts izmantot operatoru break
        do {
            x = v0 * t * Math.cos(Math.toRadians(a));
            y = v0 * t * Math.sin(Math.toRadians(a)) - g * Math.pow(t, 2) / 2;
            if (x >= 17 && x <= 20 && y <= -2 && y >= -7)
                hitTarget = true;
            outputStream.println(String.format("%1$.2f\t%2$.3f\t%3$.3f", t, x, y));
            t += 0.05;
        } while ((x <= 11 && y > 0) ? true
                : (x > 11 && x < 17 && y > -7) ? true : (x >= 17 && x <= 20 && y > -2) ? true : (x > 20 && y > -7));
        if (hitTarget)
            outputStream.println("the target was destroyed");
        else
            outputStream.println("shot off the target");
    }
    // endregion
}

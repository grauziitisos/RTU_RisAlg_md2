
package dip107;

import java.io.InputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Scanner;

public class Md1_061rmc160 {
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
        // oriģināli es liku ka 3 iespējas lietotājam dotas lai ievadītu pareiza formāta datus ja
        // kļūdījās!!!
        // for (int i = 0; i < 4; i++) {
        outputStream.print(varName + "=");
        // infinity is an invalid value legal float value example for coordinates!
        if (sc.hasNext("[+-]?[\\d]+([.,]\\d+)*")) {
            return Float.parseFloat(makeFloatString(sc.next()));
        } else {
            sc.next();
            outputStream.println();
            outputStream.println("input-output error");
            system_exit = true;
            // System.exit(-1);
            // outputStream.println("nepareiza formāta ievade! Lūdzu ievadiet reālu skaitli - "
            // + varName + " koordinātu!");
            // outputStream.println("Mēģinājums #" + (i + 1) + " no 3");
        }
        // }
        // System.exit(-1);
        // system is exiting.. But the function must have return statement for lint.. therefore
        return -11111111.222222f;
    }
    // endregion

    // region main
    public static void main(String[] args) {
        testableMain(System.in, System.out);
    }

    private static Boolean system_exit = false;

    public static void testableMain(InputStream inputStream, PrintStream outputStream) {
        Scanner sc = new Scanner(inputStream);
        float x = 0, y = 0;
        outputStream.println("061RMC160 Oskars Grauzis 4");
        x = getInput(sc, outputStream, 'x');
        if (system_exit) {
            sc.close();
            return;
        }
        y = getInput(sc, outputStream, 'y');
        if (system_exit) {
            sc.close();
            return;
        }
        outputStream.println();
        //Trešais no beigām studenta apliecības numura cipars 1 vai 6:
        outputStream.println("result:");
        // sarkana un zaļa (vai sarkana, zaļa un balta) - sarkana; tātad visi neieskaitot
        if (((x - 4) * (x - 4) + (y - 8) * (y - 8) < 1 && y > 8) ? true
                // 2. zaļais pusaplis (pa labi)
                : (x - 8) * (x - 8) + (y - 8) * (y - 8) < 1 && y > 8)
            outputStream.println("green");
        // sarkana un zila(vai sarkana, zila un balta) - zila; tātad visi ieskaitot
        else if (y >= 10 - x ? y >= x - 2 ? y <= 6 : false : false)
            outputStream.println("blue");
        // balta un sarkana - sarkana; - tātad visi ieskaitot
        else if (x >= 2 && x <= 10 && y >= 3 && y <= 11)
            outputStream.println("red");
        else
            outputStream.println("white");
    }
    // endregion
}

package dip107;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class Md2_061rmc160OutputTests {
    private ByteArrayOutputStream byteArrayOutputStream;

    @ParameterizedTest
    @ValueSource(floats={1})
    public void shouldPrintAplnrVardsUzvardsGrupasNr(float input) throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput(input+""), "dip107.Md2_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
        assertEquals("061RMC160 Oskars Grauzis 4", output[0]);
    }

    @ParameterizedTest
    @ValueSource(floats={1,2.5f,-3.3f,-4.4f})
    public void shouldPrintAnglePrompt(float input) throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput(input+""), "dip107.Md2_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
        assertEquals("a=", output[1]);
    }

    @ParameterizedTest
    @ValueSource(strings = {"fas", "-+1", "š", "0.0.0.0", "8k8"})
    public void shouldTellWrongInput(String input) throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput(input+""), "dip107.Md2_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
        assertEquals("input-output error", output[output.length-1], "on error should output 'input-output error'");
    }

    @ParameterizedTest
    @ValueSource(floats={1})
    public void shouldPrintResultTitle(float input) throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput(input+""), "dip107.Md2_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
        assertEquals("result:", output[2]);
    }

    @ParameterizedTest
    @ValueSource(floats={1})
    public void shouldPrintResultsHeader(float input) throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput(input+""), "dip107.Md2_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
                assertTrue(output[3].matches("t\\s+x\\s+y"), "should have t x y - seperated with whitespace!");
    }

    @ParameterizedTest
    @ValueSource(floats={1})
    public void shouldPrintFormattedResultOrFinalResultAtFirstDataLine(float input) throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput(input+""), "dip107.Md2_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
                assertTrue((output[4]=="the target was destroyed" ||
                output[4]=="shot off the target") ? true: 
                output[4].matches("\\d[\\.,]\\d{2}\\s+\\d[\\.,]\\d{3}\\s+\\d[\\.,]\\d{3}"), "Should output result or should have t with 2 decimal places x and y with 3!");
    }

    @ParameterizedTest
    @ValueSource(floats={45, 70})
    public void shouldPrintFinalResultDescription(float input) throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput(input+""), "dip107.Md2_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
                assertTrue(output[output.length-1]=="the target was destroyed" ||
                output[output.length-1]=="shot off the target"
                , "should report either target destroyed or shot off!");
    }

    // endregion
    // region utils
    private String getSimulatedUserInput(String... inputs) {
        return String.join(System.getProperty("line.separator"), inputs)
                + System.getProperty("line.separator");
    }

    private void runTest(final String data, final String className) throws Exception {

        final InputStream input = new ByteArrayInputStream(data.getBytes("UTF-8"));;

        final Class<?> cls = Class.forName(className);
        final Method meth =
                cls.getDeclaredMethod("testableMain", InputStream.class, PrintStream.class);
        meth.invoke(null, input, new PrintStream(byteArrayOutputStream));
    }
    // endregion
}

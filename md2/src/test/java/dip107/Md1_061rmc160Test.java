package dip107;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class Md1_061rmc160Test {

    private ByteArrayOutputStream byteArrayOutputStream;

    @Test
    public void shouldPrintAplnrVardsUzvardsGrupasNr() throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput("1", "1"), "dip107.Md1_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
        assertEquals("061RMC160 Oskars Grauzis 4", output[0]);
    }

    /* oriģināli es liku ka 3 iespējas lietotājam dotas lai ievadītu pareiza formāta datus ja kļūdījās!!!
    @Test
    public void shouldRetryOnWrongInput() throws Exception {
        // String classpathStr = System.getProperty("java.class.path");
        // System.out.print(classpathStr);

        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput("a", "1", "1"), "dip107.Md1_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
        assertEquals(output[1], "x=");
        assertEquals(output[output.length - 1], "red");
    }*/

    // region valTests
    @ParameterizedTest
    @CsvFileSource(resources = "positive-tests.csv", numLinesToSkip = 1)
    void shouldPassAllColorMappingTests(String x, String y, String expect) throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput(x, y), "dip107.Md1_061rmc160");
        String[] output =
                byteArrayOutputStream.toString().split(System.getProperty("line.separator"));
        assertEquals(expect, output[output.length - 1]);
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

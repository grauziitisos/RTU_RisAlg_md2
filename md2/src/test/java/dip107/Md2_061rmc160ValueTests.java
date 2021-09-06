package dip107;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
public class Md2_061rmc160ValueTests {



    private ByteArrayOutputStream byteArrayOutputStream;

    // region valTests
    @ParameterizedTest
    @CsvFileSource(resources = "positive-tests.csv", numLinesToSkip = 1)
    void shouldPassAllExcelResultsMappingTests(String x, String expect) throws Exception {
        byteArrayOutputStream = new ByteArrayOutputStream();
        runTest(getSimulatedUserInput(x), "dip107.Md2_061rmc160");
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

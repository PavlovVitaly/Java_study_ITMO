package packageOfTests.IntegrationTests;

import com.company.input.inputProgram.InputArgs;
import com.company.input.exception.NotExistingDirectory;
import com.company.input.exception.NotExistingFile;
import com.company.input.exception.NotValidSizeArgString;
import junit.framework.TestCase;

/**
 * Created by user on 01.05.2017.
 */
public class TestValidationArgsString extends TestCase {

    public final String outputPath = ".\\Output";
    public final String inputPath = ".\\Input";

    private String[] validArgs = new String[]{"2", outputPath, inputPath + "\\test.txt"};
    private String[] NotValidNumberOfThreadsArgs = new String[]{"2", inputPath + "\\test.txt"};
    private String[] NotValidRefFileArgs = new String[]{"2", outputPath, inputPath + "\\wrongFile.txt"};
    private String[] NotValidDirectoryArgs = new String[]{"2", "J:\\JavaPrj\\FilesDownloader\\src\\com\\company", inputPath + "\\test.txt"};
    private int numberThreads = 2;

    public void testValidNumberOfThreads(){
        InputArgs inputArgs = null;
        try {
            inputArgs = createInputArgs(validArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(numberThreads, inputArgs.getNumberOfThreads());
    }

    public void testValidFileWithReferences(){
        InputArgs inputArgs = null;
        try {
            inputArgs = createInputArgs(validArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(validArgs[1].equals(inputArgs.getOutputFolder().toString()));
    }

    public void testValidOutputFolder(){
        InputArgs inputArgs = null;
        try {
            inputArgs = createInputArgs(validArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(validArgs[2].equals(inputArgs.getFileWithReferences().getPath()));
    }

    //@Test(expected = NotValidSizeArgString.class)
    public void testNotValidSizeArgString(){
        try {
            createInputArgs(NotValidNumberOfThreadsArgs);
        } catch (NotValidSizeArgString e) {
            assertTrue(true);
        }catch (Exception e) {
            assertTrue(false);
        }
    }

    //@Test(expected = NotExistingFile.class)
    public void testNotExistingFile(){
        try {
            createInputArgs(NotValidRefFileArgs);
        } catch (NotExistingFile e) {
            assertTrue(true);
        }catch (Exception e) {
            assertTrue(false);
        }
    }

    //@Test(expected = NotExistingDirectory.class)
    public void testNotExistingDirectory(){
        try {
            createInputArgs(NotValidDirectoryArgs);
        } catch (NotExistingDirectory e) {
            assertTrue(true);
        }catch (Exception e) {
            assertTrue(false);
        }
    }

    private InputArgs createInputArgs(String[] args) throws Exception {
        InputArgs inputArgs = null;
        inputArgs = new InputArgs(args);
        return inputArgs;
    }
}
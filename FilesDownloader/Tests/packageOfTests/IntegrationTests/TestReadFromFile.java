package packageOfTests.IntegrationTests;

import com.company.Reader.ReferencesReaderFromFile.ReferencesAndNamesNewFilesReaderFromFile;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 29.04.2017.
 */
public class TestReadFromFile extends TestCase {

    public final String outputPath = ".\\Output";
    public final String inputPath = ".\\Input";

    private String addressReadingFile = inputPath + "\\SmallTest.txt";
    private String wrongAddressReadingFile = "G:\\JavaPrj\\FilesDownloader\\src\\com\\company\\notExistFile.txt";

    public void testNumberReadStrings(){
        ReferencesAndNamesNewFilesReaderFromFile reader = null;
        File file = new File(addressReadingFile);
        try {
            reader = new ReferencesAndNamesNewFilesReaderFromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> readMap = reader.getReferencesAndNamesNewFiles();
        assertEquals(3, readMap.size());
    }

    public void testValidRead(){
        String[] expectedReferences = {
                "https://www.motto.net.ua/old_site/img/unbelievable/1189283425_4672616374616C20417274203331.jpg",
                "https://observerondeck.files.wordpress.com/2012/09/pattern-design-by-vasare-nar-750x506.png",
                "https://megaobzor.com/uploads/stories/79356/2.jpg"};
        String[] expectedNamesNewFiles = {
                "bb.jpg",
                "dd.png",
                "cc.jpg"};
        ReferencesAndNamesNewFilesReaderFromFile reader = null;
        File file = new File(addressReadingFile);
        try {
            reader = new ReferencesAndNamesNewFilesReaderFromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> readMap = reader.getReferencesAndNamesNewFiles();
        Set<Map.Entry<String, String>> entries = readMap.entrySet();

        int i = 0;
        for(Map.Entry<String, String> item: entries){
            assertTrue(expectedReferences[i].equals(item.getKey()));
            assertTrue(expectedNamesNewFiles[i].equals(item.getValue()));
            i++;
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void testErrorReadFile(){
        ReferencesAndNamesNewFilesReaderFromFile reader = null;
        File file = new File(wrongAddressReadingFile);
        try {
            reader = new ReferencesAndNamesNewFilesReaderFromFile(file);
        } catch (Exception e) {}
    }
}

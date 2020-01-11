import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the methods of my ContentInfo class, by running the three files
 * that were provided to us via URLS. The tests compare the provided expected results
 * to my class' actual results.
 *
 * @author luna
 */
public class TestContentInfo {
    /**
     * tests the 12lines.pdf URL
     * @throws IOException when URL is dead
     */
    @Test
    public void testURL1() throws IOException {
        String url = "http://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2020/Spring/resources/assignment-1/12lines.pdf";
        ContentInfo info = new ContentInfo(url);

        assertEquals(12811, info.getContentLength());
        assertEquals("application/pdf",info.getContentType());
        assertTrue(info.getLastModified().toString().startsWith("Thu Jan 09"));

        //additional testing
        assertTrue(info.isAvailable()); //site is live
        assertEquals("www.ccis.northeastern.edu",info.getLocation());


    }

    /**
     * tests the 12lines.txt URL
     * @throws IOException when URL is dead
     */
    @Test
    public void testURL2() throws IOException {
        String url = "http://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2020/Spring/resources/assignment-1/12lines.txt";
        ContentInfo info = new ContentInfo(url);

        assertEquals(649, info.getContentLength());
        assertTrue(info.getContentType().startsWith("text/plain"));
        assertTrue(info.getLastModified().toString().startsWith("Thu Jan 09"));
        assertEquals(12, info.getLineCount());

        //additional testing
        assertTrue(info.isAvailable()); //site is live
        assertEquals("www.ccis.northeastern.edu",info.getLocation());
    }

    /**
     * tests the 500x200.png URL
     * @throws IOException when URL is dead
     */
    @Test
    public void testURL3() throws IOException {
        String url = "http://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2020/Spring/resources/assignment-1/500x200.png";
        ContentInfo info = new ContentInfo(url);

        assertEquals(68643, info.getContentLength());
        assertTrue(info.getContentType().startsWith("image/png"));
        assertTrue(info.getLastModified().toString().startsWith("Thu Jan 09"));
        assertEquals(500, info.getImageSize().width);
        assertEquals(200, info.getImageSize().height);

        //additional testing
        assertTrue(info.isAvailable()); //site is live
        assertEquals("www.ccis.northeastern.edu",info.getLocation());
    }

}
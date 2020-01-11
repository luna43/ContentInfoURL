import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * This class gets info about a URL
 * @author luna
 *
 */
public class ContentInfo {
    /**
     * the URL that will be used for all of the functions
     */
    URL targetURL;

    /**
     * Constructor using a URL
     * @param url a url in URL form
     * @throws IOException when URL is dead
     */
    public ContentInfo(URL url) throws IOException {
        targetURL=url;
    }

    /**
     * Constructor using a String
     * @param url a url in String form
     * @throws IOException when URL is dead
     */
    public ContentInfo(String url) throws IOException {
        URL thisURL= new URL(url);
        targetURL=thisURL;

    }

    /**
     * Returns the content length
     * @return content length as an int
     * @throws IOException when URL is dead
     */
    public int getContentLength() throws IOException {
        URLConnection targetConnection = targetURL.openConnection();
        return targetConnection.getContentLength();
    }

    /**
     * Returns content type asa String (i.e. image/png)
     * @return content type as a string
     * @throws IOException when URL is dead
     */
    public String getContentType() throws IOException {
        URLConnection targetConnection = targetURL.openConnection();
        return targetConnection.getContentType();
    }

    /**
     * Returns true if the content type of the URL is an image, false
     * if it does not contain an image.
     * @return true or false
     * @throws IOException when URL is dead
     */
    public boolean isImage() throws IOException {
        String type = getContentType();
        if (type.startsWith("image/")){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the content type of the URL is a text, false
     * if it does not contain text
     * @return true or false
     * @throws IOException when URL is dead
     */
    public boolean isText() throws IOException {
        String type = getContentType();
        if (type.startsWith("text/")){
            return true;
        } else {
            return false;
        }
    }

    /**
     * If URL is an image this returns the dimensions of the image as a Dimension class
     * @return dimensions as a Dimension object (length and width in int)
     * @throws IOException when URL is dead
     * @throws IllegalStateException when is not an image
     */
    public Dimension getImageSize() throws IOException, IllegalStateException {
        if (isImage()==false){
            throw new IllegalStateException();
        } else{
            BufferedImage image = ImageIO.read(targetURL);
            int height = image.getHeight();
            int width = image.getWidth();
            Dimension dim = new Dimension();
            dim.height=height;
            dim.width=width;
            return dim;
        }

    }

    /**
     * Returns the last modified date as a Date class
     * @return the date as a Date class
     * @throws IOException when URL is dead
     */
    public Date getLastModified() throws IOException {
        URLConnection targetConnection = targetURL.openConnection();
        Date date = new Date(targetConnection.getLastModified());
        return date;
    }

    /**
     * If the file is a text this returns the # of lines in the file
     * @return # of lines as an int
     * @throws IOException when URL is dead
     * @throws IllegalStateException when  is not text file
     */
    public int getLineCount() throws IOException, IllegalStateException{
        if (isText()==false){
            throw new IllegalStateException();
        } else {
            URLConnection targetConnection = targetURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(targetConnection.getInputStream()));
            int count = (int)in.lines().count();
            in.close();
            return count;
        }
    }

    /**
     * Returns the host name of the URL
     * @return host name as String
     */
    public String getLocation(){
        return targetURL.getHost();
    }

    /**
     * Returns true if the website is available and false if it is not
     * @return true or false
     */
    public boolean isAvailable(){
        try {
            URLConnection targetConnection = targetURL.openConnection();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Main function that runs a quick smoke test.
     * @param args no arguments
     * @throws IOException when URL is dead
     */
    public static void main(String[] args) throws IOException {
        String url = "http://www.ccis.northeastern.edu/home/pgust/classes/cs5500/2020/Spring/resources/assignment-1/12lines.pdf";
        ContentInfo info = new ContentInfo(url);

        System.out.println("type: " + info.getContentType());
        System.out.println("length: " + info.getContentLength());
        System.out.println("date: " + info.getLastModified());

    }
}

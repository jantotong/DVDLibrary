/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDLibrary.com.sg.dvdlibrary;


//import DVDLibrary.com.sg.dvdlibrary.DVD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author junha
 */
public class DVDLibraryDaoImpl implements DVDLibraryDao {
    
    private Map<String, DVD> dvds = new HashMap<>();
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";
    
    private DVD unmarshallDVD(String dvdAsText){
        
        String[] dvdTokens = dvdAsText.split(DELIMITER);

        // Given the pattern above, create DVD with constructor
        DVD dvdFromFile = new DVD(
                dvdTokens[0], 
                dvdTokens[1],
                Double.parseDouble(dvdTokens[2]),
                dvdTokens[3],
                dvdTokens[4],
                dvdTokens[5]
        );

        return dvdFromFile;
    }
    private void loadRoster() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(LIBRARY_FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDVD holds the most recent DVD unmarshalled
        DVD currentDVD;
        // Go through LIBRARY_FILE line by line, decoding each line into a 
        // DVD object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentDVD = unmarshallDVD(currentLine);

            
            dvds.put(currentDVD.getTitle(), currentDVD);
        }
        // close scanner
        scanner.close();
    }
    private String marshallDVD(DVD aDVD){
        // We need to turn a DVD object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 

        // Start with the DVD title, since that's supposed to be first.
        String dvdAsText = aDVD.getTitle() + DELIMITER;

        // releaseDate
        dvdAsText += aDVD.getReleaseDate() + DELIMITER;

        // mpaaRating
        dvdAsText += aDVD.getMpaaRating() + DELIMITER;

        // director
        dvdAsText += aDVD.getDirector() + DELIMITER;
        
        // studio
        dvdAsText += aDVD.getStudio() + DELIMITER;
        
        // userRating
        dvdAsText += aDVD.getUserRating();
        
        // We have now turned a DVD to text! Return it!
        return dvdAsText;
    }
    /**
    * Writes all dvds in the library out to a LIBRARY_FILE.  See loadRoster
    * for file format.
    * 
    * @throws DVDLibraryDaoException if an error occurs writing to the file
    */
   private void writeRoster() throws DVDLibraryDaoException {
       // NOTE FOR APPRENTICES: We are not handling the IOException - but
       // we are translating it to an application specific exception and 
       // then simple throwing it (i.e. 'reporting' it) to the code that
       // called us.  It is the responsibility of the calling code to 
       // handle any errors that occur.
       PrintWriter out;

       try {
           out = new PrintWriter(new FileWriter(LIBRARY_FILE));
       } catch (IOException e) {
           throw new DVDLibraryDaoException(
                   "Could not save dvd data.", e);
       }

       // Write out the DVD objects to the linrary file.
       // NOTE TO THE APPRENTICES: We could just grab the dvd map,
       // get the Collection of Dvds and iterate over them but we've
       // already created a method that gets a List of DVDs so
       // we'll reuse it.
       String dvdAsText;
       List<DVD> dvdList = this.getAllDVDs();
       for (DVD currentDVD : dvdList) {
           // turn a DVD into a String
           dvdAsText = marshallDVD(currentDVD);
           // write the DVD object to the file
           out.println(dvdAsText);
           // force PrintWriter to write line to the file
           out.flush();
       }
       // Clean up
       out.close();
   }

    @Override
    public List<DVD> getAllDVDs() throws DVDLibraryDaoException {
        loadRoster();
        return new ArrayList(dvds.values());
    }

    @Override
    public DVD getDVD(String dvdTitle) throws DVDLibraryDaoException {
        loadRoster();
        return dvds.get(dvdTitle);
    }

    @Override
    public DVD editDVD(String dvdTitle, DVD dvd) throws DVDLibraryDaoException {
        loadRoster();
        DVD removedDVD = dvds.remove(dvdTitle);
        DVD newDVD = dvds.put(dvdTitle, dvd);
        writeRoster();
        return newDVD;
    }

    @Override
    public DVD removeDVD(String dvdTitle) throws DVDLibraryDaoException {
        loadRoster();
        DVD removedDVD = dvds.remove(dvdTitle);
        writeRoster();
        return removedDVD;
    }

    @Override
    public DVD addDVD(String dvdTitle, DVD dvd) throws DVDLibraryDaoException {
        loadRoster();
        DVD newDVD = dvds.put(dvdTitle, dvd);
        writeRoster();
        return newDVD;
    }
}

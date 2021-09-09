package DVDLibrary.com.sg;

import DVDLibrary.com.sg.dvdlibrary.DVDLibraryDao;
import DVDLibrary.com.sg.dvdlibrary.DVDLibraryDaoImpl;
import DVDLibrary.com.sg.dvdlibrary.DVDLibraryView;
import DVDLibrary.com.sg.dvdlibrary.UserIOConsoleImpl;
import DVDLibrary.com.sg.dvdlibrary.UserIO;
import DVDLibrary.com.sg.dvdlibrary.DVDLibraryController;


public class App {

    public static void main(String[] args){
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoImpl();
        DVDLibraryController controller = new DVDLibraryController(myDao, myView);
        controller.run();
    }
}
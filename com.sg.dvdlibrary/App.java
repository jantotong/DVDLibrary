import com.sg.dvdlibrary.DVDLibraryDao;
import com.sg.dvdlibrary.DVDLibraryDao;

public class App {

    public static void main(String[] args){
    UserIO myIo = new UserIOConsoleImpl();
    DVDLibraryView myView = new DVDLibraryView(myIo);
    DVDLibraryDao myDao = new DVDLibraryDao();
    Controller controller =
    new Controller(myDao, myView);
    controller.run();
    }
}
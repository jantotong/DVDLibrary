import java.util.List;

import com.sg.DVDLibrary.dto.DVD;
import com.sg.DVDLibrary.dao;
import com.sg.DVDLibrary.ui;


public class DVDLibraryController {

    private DVDLibraryView view;// = new ClassRosterView();
    private DVDLibraryDao dao;// = new ClassRosterDaoFileImpl();
    private UserIO io = new UserIOConsoleImpl();
    

    public DVDLibraryController(){}

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
    
                menuSelection = getMenuSelection();
    
                switch (menuSelection) {
                    case 1:
                        addDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editInfo();
                        break;
                    case 4:
                        listShelf();
                        break;
                    case 5:
                        viewDvd();
                        break;
                    case 6:
                        search();
                        break;
                    case 7:
                        loadFile();
                        break;
                    case 8:
                        saveDvds();
                        break;
                    case 9:
                        changeMany();
                        break;
                    case 10:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
    
            }
            exitMessage();
        } catch (DVDLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDvd(){
        view.displayAddDvdBanner();
        DVD temp = view.getDvdInfo();
        dao.addDvd(temp.getTitle(), temp.getReleaseDate(),temp.getMpaaRating(), temp.getDirector(),temp.getStudio(),temp.getUserRaiting());
        view.displayCreateSuccessBanner();
    }

    public void displayDVDList(List<DVD> Shelf) {
        for (DVD cur : Shelf) {
            String DVDInfo = String.format("%s\n %s\n %d\n %s\n %s\n %s\n\n",
                  cur.getTitle(),
                  cur.getReleaseDate(),
                  cur.getMpaaRating(),
                  cur.getDirector(),
                  cur.getStudio(),
                  cur.getUserRaiting()
                  );
            io.print(DVDInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    private void listShelf(){
        view.displayDisplayAllBanner();
        List<DVD> shelf = dao.getAllDVDs();
        view.displayStudentList(shelf);
    }

    private void viewDvd(){
        view.displayDisplayStudentBanner();
        String DvdName = view.getDvdChoice();
        DVD temp = dao.getStudent(DvdName);
        view.displayStudent(temp);
    }

    private void removeDvd(){
        view.displayRemoveDVDBanner();
        String DvdName = view.getDVDChoice();
        DVD temp = dao.getDVD(DvdName);
        view.displayRemoveResult(DvdName);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }

    private void editInfo(){
        view.displayEditBanner();
        String DvdName = view.getDVDIdChoice();
        dao.editDVD(DvdName, dao.getDVD(DvdName));
        view.displayEditresult(DvdName);
    }

    private void search(){
        view.displaySearchBanner();
        String DvdName = view.getDVDIdChoice();
        view.searchDvd(DvdName);

    }

    private void loadFile(){
        view.displayLoadBanner();
        dao.loadFile();

    }

    private void saveDvds(){
        view.displaySaveBanner();
        marsha
        dao.saveFile();
        
    }

    private void changeMany(){
        view.displayChangeBanner();
        dao.changeMany();
        //String DvdName = view.getDVDIdChoice();
    }

}

/*
Allow the user to add a DVD to the collection
Allow the user to remove a DVD from the collection
Allow the user to edit the information for an existing DVD in the collection
Allow the user to list the DVDs in the collection
Allow the user to display the information for a particular DVD
Allow the user to search for a DVD by title
Load the DVD library from a file
Save the DVD library back to the file when the program completes
Allow the user to add, edit, or delete many DVDs in one session
*/
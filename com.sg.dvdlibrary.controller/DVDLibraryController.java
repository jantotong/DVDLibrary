package DVDLibrary.com.sg.dvdlibrary;

import java.util.List;

import DVDLibrary.com.sg.dvdlibrary.DVD;
import DVDLibrary.com.sg.dvdlibrary.DVDLibraryDaoException;



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
                        changeMany();
                        break;
                    case 8:
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
    /**
     * SELECTION MENU : Allow user to select which action to perform
     * @return 
     */
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    /**
     * CASE 1 : Allow the user to add a DVD to the collection
     * @throws DVDLibraryDaoException 
     */
    private void addDvd() throws DVDLibraryDaoException{
        view.displayCreateDVDBanner();
        DVD temp = view.getNewDVDInfo();
        dao.addDVD(temp.getTitle() ,temp);
        view.displayCreateSuccessBanner();
    }
    /**
     * CASE 2 : Allow the user to remove a DVD from the collection
     * @throws DVDLibraryDaoException 
     */
    private void removeDvd() throws DVDLibraryDaoException{
        view.displayRemoveDVDBanner();
        String DvdName = view.getDVDTitleChoice();
        DVD temp = dao.removeDVD(DvdName);
        view.displayRemoveResult(temp);
    }
    /**
     * CASE 3 : Allow the user to edit the information for an existing DVD in the collection
     * @throws DVDLibraryDaoException 
     */
    private void editInfo() throws DVDLibraryDaoException{
        String DvdName;        
        DVD oldDVD;
        while(true){
            try {
                DvdName = view.getDVDTitleChoice();
                oldDVD = dao.getDVD(DvdName);
                if (oldDVD != null) break;
            } catch (NullPointerException e) {
                view.displayNoDVD();
            }
        }
        DVD newDVD = view.editDVDInfo(oldDVD);
        dao.editDVD(DvdName, newDVD);
    }
    /**
     * CASE 4 : Allow the user to list the DVDs in the collection
     * @throws DVDLibraryDaoException 
     */
    private void listShelf() throws DVDLibraryDaoException{
        view.displayDisplayAllBanner();
        List<DVD> shelf = dao.getAllDVDs();
        view.displayDVDList(shelf);
    }
    /**
     * CASE 5 : Allow the user to display the information for a particular DVD
     * @throws DVDLibraryDaoException 
     */
    private void viewDvd()throws DVDLibraryDaoException{
        view.displayDisplayDVDBanner();
        String DvdName = view.getDVDTitleChoice();
        DVD temp = dao.getDVD(DvdName);
        view.displayDVD(temp);
    }
    /**
     * CASE 6 : Allow the user to search for a DVD by title
     * @throws DVDLibraryDaoException 
     */
    private void search() throws DVDLibraryDaoException{
        view.displaySearchDVDBanner();
        String DvdName = view.getDVDTitleChoice();
        DVD temp = dao.getDVD(DvdName);
        view.displayDVD(temp);
    }
    /**
     * CASE 7 : Allow the user to add, edit, or delete many DVDs in one session
     * @throws DVDLibraryDaoException 
     */
    private void changeMany() throws DVDLibraryDaoException{
        view.displayChangeManyDVDBanner();
        int action = view.getManyAction();
        int frequency = view.getManyActionFreq();
        
        switch(action){
            case 1:
                for (int i = 0; i < frequency; i++) {
                    addDvd();
                }
                break;
            case 2:
                for (int i = 0; i < frequency; i++) {
                    removeDvd();
                }
                break;
            case 3: 
                for (int i = 0; i < frequency; i++) {
                    editInfo();
                }
                break;
        }
    }
    /**
     * DEFAULT CASE : DISPLAY INVALID INPUT WAS ENTERED  
     */
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    // Notify the user that the program is about to exit
    private void exitMessage() {
        view.displayExitBanner();
    }
}
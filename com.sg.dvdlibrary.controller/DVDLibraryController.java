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

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void addDvd() throws DVDLibraryDaoException{
        view.displayCreateDVDBanner();
        DVD temp = view.getNewDVDInfo();
        dao.addDVD(temp.getTitle() ,temp);
        view.displayCreateSuccessBanner();
    }

    private void listShelf() throws DVDLibraryDaoException{
        view.displayDisplayAllBanner();
        List<DVD> shelf = dao.getAllDVDs();
        view.displayDVDList(shelf);
    }

    private void viewDvd()throws DVDLibraryDaoException{
        view.displayDisplayDVDBanner();
        String DvdName = view.getDVDTitleChoice();
        DVD temp = dao.getDVD(DvdName);
        view.displayDVD(temp);
    }

    private void removeDvd() throws DVDLibraryDaoException{
        view.displayRemoveDVDBanner();
        String DvdName = view.getDVDTitleChoice();
        DVD temp = dao.removeDVD(DvdName);
        view.displayRemoveResult(temp);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }

    private void editInfo() throws DVDLibraryDaoException{
//        view.editInfoBanner();
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

    private void search() throws DVDLibraryDaoException{
        view.displaySearchDVDBanner();
        String DvdName = view.getDVDTitleChoice();
        DVD temp = dao.getDVD(DvdName);
        view.displayDVD(temp);
    }

    private void loadFile(){
        view.displayLoadBanner();
    }

    private void saveDvds(){
        view.displaySaveBanner();
    }

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
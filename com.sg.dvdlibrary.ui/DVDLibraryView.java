package DVDLibrary.com.sg.dvdlibrary;

import java.util.*;

public class DVDLibraryView {

    private UserIO io;

    public DVDLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add DVD"); //
        io.print("2. Remove DVD");
        io.print("3. Edit DVD information");
        io.print("4. List all DVDs");
        io.print("5. Display DVD's Info");
        io.print("6. Search DVD by Title");
        io.print("7. Multiple ADD/REMOVE/EDIT");
        io.print("8. Exit");
        return io.readInt("Please select from the above choices.", 1, 8);

    }

    public DVD getNewDVDInfo() {
        String title = io.readString("Please enter title");
        String releaseDate = io.readString("Please enter release date");
        double mpaaRating = io.readDouble("Please enter MPAA Rating (Numbers 0 to 10)", 0.0, 10.0);
        String director = io.readString("Please enter name of director");
        String studio = io.readString("Please enter name of studio");
        String userRating = io.readString("Please enter any user rating or notes (Optional)");

        //Construct DVD with information gathered
        DVD currentDVD = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);

        return currentDVD;
    }
    
    public int getManyAction(){
        return io.readInt("What would you like to perform many actions? (1: Add DVD, 2: Remove DVD, 3: Edit DVD)");
    }
    
    public int getManyActionFreq(){
        return io.readInt("Please enter how many times you want to perform your action: ");
    }
    
    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }

    public void displayDVDList(List<DVD> DVDList) {
        for (DVD currentDVD : DVDList) {
            String DVDInfo = String.format("#%s: \nRelease Date: %s\nMPAA Rating: %.2f\nDirector: %s\nStudio: %s\n User Rating: %s\n",
                    currentDVD.getTitle(),
                    currentDVD.getReleaseDate(),
                    currentDVD.getMpaaRating(),
                    currentDVD.getDirector(),
                    currentDVD.getStudio(),
                    currentDVD.getUserRating()
            );
            io.print(DVDInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD ===");
    }
    
    public void displayChangeManyDVDBanner() {
        io.print("=== Change Many DVD ===");
    }
    
    public void displaySearchDVDBanner() {
        io.print("=== Searching DVD ===");
    }


    public String getDVDTitleChoice() {
        return io.readString("Please enter title of existing DVD.");
    }

    public void displayDVD(DVD DVD) {
        if (DVD != null) {
            String DVDInfo = String.format("#%s: \nRelease Date: %s\nMPAA Rating: %.2f\nDirector: %s\nStudio: %s\n User Rating: %s",
                    DVD.getTitle(),
                    DVD.getReleaseDate(),
                    DVD.getMpaaRating(),
                    DVD.getDirector(),
                    DVD.getStudio(),
                    DVD.getUserRating()
            );
            io.print(DVDInfo);
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayNoDVD(){
        io.print("No such DVD.");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }
   

    public void displayRemoveResult(DVD DVDRecord) {
        if (DVDRecord != null) {
            io.print("DVD successfully removed.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }


    ///START OF BANNERS
    public void displayExitBanner() {
        io.print("Good Bye from DVD Library!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }

    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayLoadBanner() {
        io.print("=== Load File ===");
    }

    public void displaySaveBanner() {
        io.print("=== Save File ===");
    }

    public void displayChangeBanner() {
        io.print("=== Change File ===");
    }


    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }


    public DVD editDVDInfo(DVD DVD) {
        if (DVD != null) {
            String title = io.readString("Please enter new tiltle of the DVD");
            String releaseDate = io.readString("Please enter release date");
            double mpaaRating = io.readDouble("Please enter MPAA Rating (Numbers 0 to 10)", 0.0, 10.0);
            String director = io.readString("Please enter name of director");
            String studio = io.readString("Please enter name of studio");
            String userRating = io.readString("Please enter any user rating or notes (Optional)");
            DVD currentDVD = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);
            io.print("=== Edited DVD ===");
            return currentDVD;
        } else {
            io.print("No such DVD.");
            return DVD;
        }
        
    }
}

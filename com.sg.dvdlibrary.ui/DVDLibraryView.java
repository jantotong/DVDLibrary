import java.util.*;

public class ClassRosterView {

    private UserIO io;

    public ClassRosterView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add DVD"); //
        io.print("2. Remove DVD");
        io.print("3. Edit DVD information");
        io.print("4. List all DVDs");
        io.print("5. Search DVD by Title");
        io.print("6. Load DVD from a file");
        io.print("7. Save DVD to file when program completes");
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

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }

    public void displayDVDList(List<DVD> DVDList) {
        for (DVD currentDVD : DVDList) {
            String DVDInfo = String.format("#%s: \nRelease Date: %s\nMPAA Rating: %.2f\nDirector: %s\nStudio: %s\n User Rating: %s",
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

    public String getDVDTitleChoice() {
        return io.readString("Please enter the DVD Title.");
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

    public void displayExitBanner() {
        io.print("Good Bye from DVD Library!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }

    public DVD editDVDInfo(DVD DVD) {
        if (DVDRecord != null) {
            String title = io.readString("Please enter title of existing DVD");
            String releaseDate = io.readString("Please enter release date");
            double mpaaRating = io.readDouble("Please enter MPAA Rating (Numbers 0 to 10)", 0.0, 10.0);
            String director = io.readString("Please enter name of director");
            String studio = io.readString("Please enter name of studio");
            String userRating = io.readString("Please enter any user rating or notes (Optional)");
            DVD currentDVD = new DVD(title, releaseDate, mpaaRating, director, studio, userRating);
            return currentDVD;
        } else {
            io.print("No such DVD.");
            return DVD;
        }
    }
}

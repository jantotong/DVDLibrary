

public class App {

    public static void main(String[] args){
    UserIO myIo = new UserIOConsoleImpl();
    AddressBookView myView = new AddressBookView(myIo);
    AddressBookDao myDao = new AddressBookFileImpl();
    Controller controller =
    new Controller(myDao, myView);
    controller.run();

    }
}
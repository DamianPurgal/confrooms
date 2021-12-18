package pl.polsl.confrooms.model.Exceptions;

//WYJATEK ZGLASZANY W PRZYPADKU NIEZNALEZIENIA ODPOWIEDNICH PODMIOTOW
public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }
}

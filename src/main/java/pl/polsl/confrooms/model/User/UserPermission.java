package pl.polsl.confrooms.model.User;
//PERMISJE WYSTEPUJACE W APLIKACJI
public enum UserPermission {
    //TUTAJ MOŻNA DODAWAC PERMISJE WYSTEPUAJCE W APLIKACJI
    GET_ALL_USERS("user:getall"),
    PERMISSION_TWO("COSTAM:WRITE");

    private String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

package pl.polsl.confrooms.model.User;


import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

//ROLE UZYTKOWNIKOW WYSTEPUJACE W APLIKACJI
public enum UserRole {
    //TUTAJ MOŻNA DODAWAC ROLE UZYTKOWNIKA ORAZ PERMISJE DLA DANEJ ROLI.
    //PERMISJE MOZESZ DODAWAC W PLIKU UserPermissions
    TENANT(Sets.newHashSet()),
    OWNER(Sets.newHashSet(UserPermission.GET_ALL_USERS));

    private Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> authorities =
                getPermissions()
                .stream()
                .map(perm -> new SimpleGrantedAuthority(perm.getPermission()))
                .collect((Collectors.toSet()));
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}

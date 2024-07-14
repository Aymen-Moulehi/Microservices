package tn.esprit.security;

import lombok.Getter;

@Getter
public enum JwtAttribute {
    PREFERRED_USERNAME_ATTRIBUTE("preferred_username"),
    REALM_ACCESS_ATTRIBUTE("realm_access"),
    ROLES_ATTRIBUTE("roles");

    private final String attribute;

    JwtAttribute(String attribute) {
        this.attribute = attribute;
    }
}

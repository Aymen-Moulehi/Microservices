package tn.esprit.security;

import lombok.Getter;

@Getter
public enum Role {
    PLANET_QUERY("planet-query"),
    PLANET_COMMAND("planet-command");


    private final String code;

    Role(String code) {
        this.code = code;
    }
}

package tn.esprit.security;

import lombok.Getter;

@Getter
public enum Role {
    GAlAXY_QUERY("galaxy-query"),
    GALAXY_COMMAND("galaxy-command");

    private final String code;

    Role(String code) {
        this.code = code;
    }
}

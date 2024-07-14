package tn.esprit.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@SuppressWarnings("unchecked")
public class JwtAuthConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
    private final String ROLE_SUFFIX = "ROLE_";

    @Override
    public Collection<GrantedAuthority> convert(@NonNull Jwt source) {
        return Stream.concat(
            jwtGrantedAuthoritiesConverter.convert(source).stream(),
                extractResourceRoles(source).stream()
        ).collect(Collectors.toSet());

    }

    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt source) {
        Map<String, Object> resourceAccess;
        Collection<String> resourceRoles;

        if (source.getClaim(JwtAttribute.REALM_ACCESS_ATTRIBUTE.getAttribute()) != null) {
            resourceAccess = source.getClaim(JwtAttribute.REALM_ACCESS_ATTRIBUTE.getAttribute());
            if (resourceAccess.get(JwtAttribute.ROLES_ATTRIBUTE.getAttribute()) != null) {
                try {
                    resourceRoles = (Collection<String>) resourceAccess.get(JwtAttribute.ROLES_ATTRIBUTE.getAttribute());
                    return resourceRoles.stream()
                            .map(role -> new SimpleGrantedAuthority(ROLE_SUFFIX + role))
                            .collect(Collectors.toSet());
                } catch (Exception exception) {
                    return Set.of();
                }

            }
        }
        return Set.of();
    }
}

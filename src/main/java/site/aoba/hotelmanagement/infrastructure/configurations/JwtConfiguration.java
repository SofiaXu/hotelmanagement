package site.aoba.hotelmanagement.infrastructure.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import site.aoba.hotelmanagement.infrastructure.filters.JwtAuthorizationFilter;

@EnableWebSecurity
public class JwtConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${site.aoba.authorization.jwt.key}")
    private String jwtKey = "773C542543664C65B30E713CFD5845E6";
    @Value("${site.aoba.authorization.jwt.issuer}")
    private String jwtIssuer = "site.aoba.authorization";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthorizationFilter filter = new JwtAuthorizationFilter(authenticationManager());
        filter.setJwtIssuer(jwtIssuer);
        filter.setJwtKey(jwtKey);
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/api/actuator/**").hasRole("1")
                .and().cors()
                .and()
                .addFilter(filter)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}

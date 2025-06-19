//package com.Ecommerce.App.SecurityConfig;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//@Configuration
//@EnableWebSecurity
//public class AppConfig {
//
//    @Bean
//    public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> {
//                    auth
//                            .requestMatchers(HttpMethod.POST, "/ecom/admin", "/ecom/customers").permitAll()
//                            .requestMatchers(HttpMethod.DELETE, "/ecom/orders/users/**").permitAll()
//                            .requestMatchers(HttpMethod.GET, "/ecom/signIn", "/ecom/product-reviews/**", "/ecom/products/**").permitAll()
//
//                            // ADMIN-only endpoints
//                            .requestMatchers(HttpMethod.POST, "/ecom/product/**", "/ecom/order-shippers/**").hasRole("ADMIN")
//                            .requestMatchers(HttpMethod.PUT, "/ecom/admin/**", "/ecom/products/**").hasRole("ADMIN")
//                            .requestMatchers(HttpMethod.DELETE, "/ecom/products/**", "/ecom/product-reviews/**", "/ecom/customer-addresses/delete/**", "/ecom/order-shipping/**", "/ecom/order-shippers/**").hasRole("ADMIN")
//
//                            // USER-only endpoints
//                            .requestMatchers(HttpMethod.POST, "/ecom/product-reviews/**", "/ecom/customer-addresses/**", "/ecom/cart/**", "/ecom/orders/**", "/ecom/order-shipping/**").hasRole("USER")
//                            .requestMatchers(HttpMethod.PUT, "/ecom/admin/**", "/ecom/product-reviews/**", "/ecom/customer-addresses/update/**", "/ecom/cart/**", "/ecom/order-shipping/**").hasRole("USER")
//                            .requestMatchers(HttpMethod.DELETE, "/ecom/cart/remove-product/**").hasRole("USER")
//
//                            // Shared between ADMIN and USER
//                            .requestMatchers(HttpMethod.GET, "/ecom/customer-addresses/**", "/ecom/cart/products/**", "/ecom/orders/**", "/ecom/order-shippers", "/ecom/order-payments/**").hasAnyRole("ADMIN", "USER");
//                })
//
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
//                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
//                .httpBasic(Customizer.withDefaults());
//
//////        http
//////                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//////                .cors(Customizer.withDefaults()) // Simplified for testing
//////                .authorizeHttpRequests(auth -> {
//////                    auth.requestMatchers(HttpMethod.GET, "/ecom/signIn").permitAll() // Only this for now
//////                            .anyRequest().authenticated(); // All others require authentication
//////                })
//////                .csrf(csrf -> csrf.disable())
//////                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
//////                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
//////                .httpBasic(Customizer.withDefaults());
////
//        return http.build();
//    }
//
//    //
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOriginPatterns(Arrays.asList(
//                "http://localhost:3000"
//        ));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Collections.singletonList("*"));
//        configuration.setExposedHeaders(Collections.singletonList("Authorization"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    //
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Secure password encoder
//    }
//
//
//}

package com.Ecommerce.App.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity // Enables Spring Security's web security support
public class AppConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. CORS Configuration: Applies your CORS settings
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // 2. CSRF Protection: Disabled for stateless APIs using JWT. Re-enable for session-based apps.
                .csrf(csrf -> csrf.disable())
                // 3. Authorization Rules: Define which requests are allowed/denied based on roles/authentication
                .authorizeHttpRequests(auth -> {
                    auth
                            // Public Endpoints (No authentication or roles required)
                            // Allows new customer registration and admin creation (if applicable and safe)
                            .requestMatchers(HttpMethod.POST, "/ecom/admin", "/ecom/customers").permitAll()
                            // Allows users to sign in and view public products/reviews
                            .requestMatchers(HttpMethod.GET, "/ecom/signIn", "/ecom/product-reviews/**", "/ecom/products/**").permitAll()

                            // Admin-Specific Endpoints (Require 'ADMIN' role)
                            .requestMatchers(HttpMethod.POST, "/ecom/product/**", "/ecom/order-shippers/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/ecom/admin/**", "/ecom/products/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE,
                                    "/ecom/products/**",
                                    "/ecom/product-reviews/**",
                                    "/ecom/customer-addresses/delete/**", // Admin can delete any address
                                    "/ecom/order-shipping/**",
                                    "/ecom/order-shippers/**",
                                    "/ecom/orders/users/**" // Admin can delete orders for any user
                            ).hasRole("ADMIN")

                            // User-Specific Endpoints (Require 'USER' role)
                            // Note: POST /ecom/customer-addresses/** is here as it creates an address for the *authenticated* user.
                            .requestMatchers(HttpMethod.POST,
                                    "/ecom/product-reviews/**",
                                    "/ecom/customer-addresses/**", // User can add their own address
                                    "/ecom/cart/**",
                                    "/ecom/orders/**",
                                    "/ecom/order-shipping/**"
                            ).hasRole("USER")
                            .requestMatchers(HttpMethod.PUT,
                                    "/ecom/admin/**", // This path seems unusual for a USER. Re-evaluate if users can PUT to /ecom/admin.
                                    "/ecom/product-reviews/**",
                                    "/ecom/customer-addresses/update/**", // User can update their own address
                                    "/ecom/cart/**",
                                    "/ecom/order-shipping/**"
                            ).hasRole("USER")
                            .requestMatchers(HttpMethod.DELETE, "/ecom/cart/remove-product/**").hasRole("USER")

                            // Endpoints Accessible by both ADMIN and USER
                            // This includes GET for customer addresses, cart products, orders, etc.
                            .requestMatchers(HttpMethod.GET,
                                    "/ecom/customer-addresses/**", // User or Admin can get addresses
                                    "/ecom/cart/products/**",
                                    "/ecom/orders/**",
                                    "/ecom/order-shippers", // Should this be public or require a role? Re-evaluate.
                                    "/ecom/order-payments/**"
                            ).hasAnyRole("ADMIN", "USER")

                            // Any other request not matched above will require authentication
                            // This is a crucial "catch-all" rule, placed at the end.
                            .anyRequest().authenticated();
                })
                // 4. Session Management: Stateless for JWT-based authentication
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 5. JWT Filters: Order matters!
                //    - JwtTokenValidatorFilter: Runs first to validate incoming JWT and set Authentication.
                //    - JwtTokenGeneratorFilter: Runs after, typically for generating a new token (e.g., on login success).
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                // 6. HTTP Basic Authentication: Allows for basic auth (username/password in header) in addition to JWT.
                //    Often kept for testing or specific scenarios. Remove if you only want JWT.
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // CORS Configuration Bean
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Sets allowed origins. Using allowedOriginPatterns for more flexibility or specific origins.
        configuration.setAllowedOriginPatterns(Arrays.asList(
                "http://localhost:5173", // Your frontend URL
                "http://127.0.0.1:3000"  // Common alternative for localhost
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")); // Add HEAD for completeness
        configuration.setAllowCredentials(true); // Allow sending cookies/auth headers
        configuration.setAllowedHeaders(Collections.singletonList("*")); // Allow all headers
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type")); // Explicitly expose headers if frontend needs to read them

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply this CORS config to all paths
        return source;
    }

    // Password Encoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCryptPasswordEncoder for secure password hashing.
        // Never use NoOpPasswordEncoder in production.
        return new BCryptPasswordEncoder();
    }
}
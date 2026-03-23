//package com.hexaware.simplyfly.security;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
////	
////	  @Autowired 
////	  private CustomUserDetailsService userDetailsService;
////	  
////	  @Autowired 
////	  private JwtRequestFilter jwtRequestFilter;
////	  
////	  
////	  @Bean
////	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////	      http
////	          .cors() // ✅ Enable CORS using your CorsConfigurationSource bean
////	          .and()
////	          .csrf(csrf -> csrf.disable())
////	          .authorizeHttpRequests(auth -> auth
////	        				    .anyRequest().permitAll()
////	        				)
//////	              .requestMatchers("/register", "/authenticate", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//////	              .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
//////	              .requestMatchers("/api/flights/**").hasAnyRole("ADMIN", "FLIGHT_OWNER")
//////	              .requestMatchers("/api/bookings/**").hasAnyRole("ADMIN", "USER")
//////	              .requestMatchers("/api/routes/**").hasAnyRole("ADMIN", "FLIGHT_OWNER")
//////	              .requestMatchers("/api/**").authenticated()
//////	              .anyRequest().authenticated()
//////	          )
////	          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
////	      http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////
////	      return http.build();
////	  }
////
////	  
////		/*
////		 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
////		 * throws Exception { http .csrf(csrf -> csrf.disable()).authorizeHttpRequests
////		 * (auth -> auth // Public endpoints .requestMatchers( "/register",
////		 * "/authenticate", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"
////		 * ).permitAll()
////		 * 
////		 * // /api/user - accessible by USER and ADMIN
////		 * .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
////		 * 
////		 * // /api/flight - accessible by ADMIN and FLIGHT_OWNER
////		 * .requestMatchers("/api/flights/**").hasAnyRole("ADMIN", "FLIGHT_OWNER")
////		 * 
////		 * // /api/booking - accessible by ADMIN and USER
////		 * .requestMatchers("/api/bookings/**").hasAnyRole("ADMIN", "USER")
////		 * 
////		 * // /api/route - accessible by ADMIN and FLIGHT_OWNER
////		 * .requestMatchers("/api/routes/**").hasAnyRole("ADMIN", "FLIGHT_OWNER")
////		 * 
////		 * // All other /api/** endpoints require authentication
////		 * .requestMatchers("/api/**").authenticated()
////		 * 
////		 * // Other requests must be authenticated .anyRequest().authenticated() )
////		 * .sessionManagement(session -> session
////		 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS) );
////		 * 
////		 * // Add JWT filter before username/password filter
////		 * http.addFilterBefore(jwtRequestFilter,
////		 * UsernamePasswordAuthenticationFilter.class);
////		 * 
////		 * return http.build(); }
////		 */
////	  
////	  @Bean public PasswordEncoder passwordEncoder() { return new
////	  BCryptPasswordEncoder(); }
////	  
////	  @Bean public AuthenticationManager
////	  authenticationManager(AuthenticationConfiguration config) throws Exception {
////	  return config.getAuthenticationManager(); }
////	 
////	
//////	@Bean
//////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//////        http
//////            .cors() // enable CORS
//////            .and()
//////            .csrf().disable()
//////            .authorizeHttpRequests()
//////            .anyRequest().permitAll();
//////
//////        return http.build();
//////    }
//
//
//
//	    @Autowired private JwtAuthFilter jwtFilter;
//	    @Autowired private UserDetailsServiceImpl userDetailsService;
//
//	    @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        http.csrf().disable()
//	            .authorizeHttpRequests(auth -> auth
//	                .requestMatchers("/api/auth/**").permitAll()
//	                .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN", "FLIGHT_OWNER")
//	                .requestMatchers("/api/admin/**").permitAll()
//	                .requestMatchers("/api/flight-owner/**").permitAll()
//	                .requestMatchers("/api/user/**").permitAll()
//	                .anyRequest().authenticated()
//	            )
//	            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//	        return http.build();
//	    }
//
//	    @Bean
//	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//	        return config.getAuthenticationManager();
//	    }
//
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	
//
//	    @Bean
//	    public CorsConfigurationSource corsConfigurationSource() {
//	        CorsConfiguration config = new CorsConfiguration();
//	        config.setAllowedOrigins(List.of("http://localhost:4200")); // ✅ Your Angular app origin
//	        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // ✅ Allowed methods
//	        config.setAllowedHeaders(List.of("*")); // ✅ Allow all headers
//	        config.setAllowCredentials(true); // ✅ If you're using cookies or auth headers
//
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", config); // ✅ Apply to all endpoints
//	        return source;
//	    }
//}
//
//
//////package com.hexaware.simplyfly.security;
//////
//////import java.util.List;
//////
//////import org.springframework.beans.factory.annotation.Autowired;
//////import org.springframework.context.annotation.Bean;
//////import org.springframework.context.annotation.Configuration;
//////import org.springframework.security.authentication.AuthenticationManager;
//////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//////import org.springframework.security.config.http.SessionCreationPolicy;
//////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//////import org.springframework.security.crypto.password.PasswordEncoder;
//////import org.springframework.security.web.SecurityFilterChain;
//////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//////import org.springframework.web.cors.CorsConfiguration;
//////import org.springframework.web.cors.CorsConfigurationSource;
//////import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//////
//////@Configuration
//////@EnableWebSecurity
//////public class SecurityConfig {
//////
//////    @Autowired
//////    private JwtAuthFilter jwtFilter;
//////
//////    @Bean
//////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//////        http
//////            .cors() 
//////            .and()
//////            .csrf().disable()
//////            .authorizeHttpRequests(auth -> auth
//////                .requestMatchers("/api/auth/**", "/register", "/authenticate").permitAll()
//////                .requestMatchers("/api/login/**").permitAll()
//////                .requestMatchers("/api/users/**").permitAll()
//////                .requestMatchers("/api/flights/**").permitAll()
//////                .requestMatchers("/api/routes/**").permitAll()
//////                .requestMatchers("/api/bookings/**").permitAll()
//////
//////                .requestMatchers("/api/admin/**", "/api/flight-owner/**", "/api/user/**").permitAll()
//////                .anyRequest().authenticated()
//////            )
//////            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//////        
//////
//////        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//////        return http.build();
//////    }
//////
//////    @Bean
//////    public CorsConfigurationSource corsConfigurationSource() {
//////        CorsConfiguration config = new CorsConfiguration();
//////        //config.setAllowedOrigins(List.of("http://localhost:4200"));
//////        config.setAllowedOrigins(List.of(
//////                "http://localhost:4200",  // Angular
//////                "http://localhost:3000"  // React (CRA)
//////              ));
//////        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//////        config.setAllowedHeaders(List.of("*"));
//////        config.setAllowCredentials(true); 
//////
//////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//////        source.registerCorsConfiguration("/**", config); 
//////        return source;
//////    }
//////
////////    @Bean
////////    public CorsConfigurationSource corsConfigurationSource() {
////////        CorsConfiguration config = new CorsConfiguration();
////////
////////        config.setAllowedOrigins(List.of(
////////            "http://localhost:3000", // React
////////            "http://localhost:4200"  // Angular (optional)
////////        ));
////////
////////        config.setAllowedMethods(List.of(
////////            "GET", "POST", "PUT", "DELETE", "OPTIONS"
////////        ));
////////
////////        config.setAllowedHeaders(List.of("*"));
////////        config.setAllowCredentials(true);
////////
////////        UrlBasedCorsConfigurationSource source =
////////                new UrlBasedCorsConfigurationSource();
////////        source.registerCorsConfiguration("/**", config);
////////
////////        return source;
////////    }
//////
//////    @Bean
//////    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//////        return config.getAuthenticationManager();
//////    }
//////
//////    @Bean
//////    public PasswordEncoder passwordEncoder() {
//////        return new BCryptPasswordEncoder();
//////    }
//////}
//////
////
////
////package com.hexaware.simplyfly.security;
////
////import java.util.List;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.AuthenticationManager;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.http.SessionCreationPolicy;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////import org.springframework.web.cors.CorsConfiguration;
////import org.springframework.web.cors.CorsConfigurationSource;
////import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
////
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Autowired
////    private JwtAuthFilter jwtFilter;
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////            .cors() // enable CORS
////            .and()
////            .csrf().disable()
////            .authorizeHttpRequests(auth -> auth
////                .requestMatchers("/api/auth/**", "/register", "/authenticate").permitAll()
////                .anyRequest().authenticated()
////            )
////            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
////        // JWT filter
////        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
////
////    @Bean
////    public CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowedOrigins(List.of(
////            "http://localhost:4200", // Angular
////            "http://localhost:3000"  // React
////        ));
////        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
////        config.setAllowedHeaders(List.of("*"));
////        config.setAllowCredentials(true);
////
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", config);
////        return source;
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
////        return config.getAuthenticationManager();
////    }
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////}




//package com.hexaware.simplyfly.security;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
////	
////	  @Autowired 
////	  private CustomUserDetailsService userDetailsService;
////	  
////	  @Autowired 
////	  private JwtRequestFilter jwtRequestFilter;
////	  
////	  
////	  @Bean
////	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////	      http
////	          .cors() // ✅ Enable CORS using your CorsConfigurationSource bean
////	          .and()
////	          .csrf(csrf -> csrf.disable())
////	          .authorizeHttpRequests(auth -> auth
////	        				    .anyRequest().permitAll()
////	        				)
//////	              .requestMatchers("/register", "/authenticate", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//////	              .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
//////	              .requestMatchers("/api/flights/**").hasAnyRole("ADMIN", "FLIGHT_OWNER")
//////	              .requestMatchers("/api/bookings/**").hasAnyRole("ADMIN", "USER")
//////	              .requestMatchers("/api/routes/**").hasAnyRole("ADMIN", "FLIGHT_OWNER")
//////	              .requestMatchers("/api/**").authenticated()
//////	              .anyRequest().authenticated()
//////	          )
////	          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
////
////	      http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////
////	      return http.build();
////	  }
////
////	  
////		/*
////		 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
////		 * throws Exception { http .csrf(csrf -> csrf.disable()).authorizeHttpRequests
////		 * (auth -> auth // Public endpoints .requestMatchers( "/register",
////		 * "/authenticate", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"
////		 * ).permitAll()
////		 * 
////		 * // /api/user - accessible by USER and ADMIN
////		 * .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
////		 * 
////		 * // /api/flight - accessible by ADMIN and FLIGHT_OWNER
////		 * .requestMatchers("/api/flights/**").hasAnyRole("ADMIN", "FLIGHT_OWNER")
////		 * 
////		 * // /api/booking - accessible by ADMIN and USER
////		 * .requestMatchers("/api/bookings/**").hasAnyRole("ADMIN", "USER")
////		 * 
////		 * // /api/route - accessible by ADMIN and FLIGHT_OWNER
////		 * .requestMatchers("/api/routes/**").hasAnyRole("ADMIN", "FLIGHT_OWNER")
////		 * 
////		 * // All other /api/** endpoints require authentication
////		 * .requestMatchers("/api/**").authenticated()
////		 * 
////		 * // Other requests must be authenticated .anyRequest().authenticated() )
////		 * .sessionManagement(session -> session
////		 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS) );
////		 * 
////		 * // Add JWT filter before username/password filter
////		 * http.addFilterBefore(jwtRequestFilter,
////		 * UsernamePasswordAuthenticationFilter.class);
////		 * 
////		 * return http.build(); }
////		 */
////	  
////	  @Bean public PasswordEncoder passwordEncoder() { return new
////	  BCryptPasswordEncoder(); }
////	  
////	  @Bean public AuthenticationManager
////	  authenticationManager(AuthenticationConfiguration config) throws Exception {
////	  return config.getAuthenticationManager(); }
////	 
////	
//////	@Bean
//////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//////        http
//////            .cors() // enable CORS
//////            .and()
//////            .csrf().disable()
//////            .authorizeHttpRequests()
//////            .anyRequest().permitAll();
//////
//////        return http.build();
//////    }
//
//
//
//	    @Autowired private JwtAuthFilter jwtFilter;
//	    @Autowired private UserDetailsServiceImpl userDetailsService;
//
//	    @Bean
//	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	        http.csrf().disable()
//	            .authorizeHttpRequests(auth -> auth
//	                .requestMatchers("/api/auth/**").permitAll()
//	                .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN", "FLIGHT_OWNER")
//	                .requestMatchers("/api/admin/**").permitAll()
//	                .requestMatchers("/api/flight-owner/**").permitAll()
//	                .requestMatchers("/api/user/**").permitAll()
//	                .anyRequest().authenticated()
//	            )
//	            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//	        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//	        return http.build();
//	    }
//
//	    @Bean
//	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//	        return config.getAuthenticationManager();
//	    }
//
//	    @Bean
//	    public PasswordEncoder passwordEncoder() {
//	        return new BCryptPasswordEncoder();
//	    }
//	
//
//	    @Bean
//	    public CorsConfigurationSource corsConfigurationSource() {
//	        CorsConfiguration config = new CorsConfiguration();
//	        config.setAllowedOrigins(List.of("http://localhost:4200")); // ✅ Your Angular app origin
//	        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // ✅ Allowed methods
//	        config.setAllowedHeaders(List.of("*")); // ✅ Allow all headers
//	        config.setAllowCredentials(true); // ✅ If you're using cookies or auth headers
//
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/**", config); // ✅ Apply to all endpoints
//	        return source;
//	    }
//}
//

package com.hexaware.simplyfly.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtFilter;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//            .cors() // ✅ enable CORS
//            .and()
//            .csrf().disable()
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/api/auth/**", "/register", "/authenticate").permitAll()
//                .requestMatchers("/api/users/**").permitAll()
//                .requestMatchers("/api/flights/**").permitAll()
//                .requestMatchers("/api/routes/**").permitAll()
//                .requestMatchers("/api/bookings/**").permitAll()
//
//                .requestMatchers("/api/admin/**", "/api/flight-owner/**", "/api/user/**").permitAll()
//                .anyRequest().authenticated()
//            )
//            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        
//
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // allow login/register
                .requestMatchers("/api/auth/**", "/register", "/authenticate", "/api/login/**").permitAll()
                .requestMatchers("/api/users").permitAll() 
                // allow preflight requests
                .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll()

                // other APIs
              //  .requestMatchers("/api/users/**").permitAll()
                //.requestMatchers("/api/flights/**").permitAll()
              //  .requestMatchers("/api/routes/**").permitAll()
             //   .requestMatchers("/api/bookings/**").permitAll()
                .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN", "FLIGHT_OWNER")
                .requestMatchers("/api/flights/**").hasAnyRole("ADMIN", "FLIGHT_OWNER","USER")
                .requestMatchers("/api/bookings/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/routes/**").hasAnyRole("ADMIN", "FLIGHT_OWNER")
                .anyRequest().authenticated()
            )
            .sessionManagement(sess ->
                sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(List.of(
//            "http://localhost:4200", // Angular
//            "http://localhost:3000"  // React
//        ));
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//        config.setAllowedHeaders(List.of("*"));
//        config.setAllowCredentials(true); // Important for JWT headers
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config); // Apply to all paths
//        return source;
//    }

    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
            "http://localhost:3000",
            "http://localhost:4200"
        ));

        config.setAllowedMethods(List.of(
            "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));

        //config.setAllowedHeaders(List.of("*"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setExposedHeaders(List.of("Authorization"));

        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


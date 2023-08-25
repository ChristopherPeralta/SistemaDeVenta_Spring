/*package idat.edu.pe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	//Configuration 1
/*	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				//.csrf().disable()  solo se habilita cuando no se usara formularios
				.authorizeHttpRequests() //configura la urls protegidas y cuales no
				//	.requestMatchers(null).permitAll()   //Peticiones que no quieras que sean protegidas
					.anyRequest().authenticated()
				.and()
				.formLogin().permitAll() //permitele a todos los usuarios que accedan al formulario
				.and()
				.build();
	}	*/
	
	// Configuration 2
	/*@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
		.authorizeHttpRequests( auth-> {
			//auth.requestMatcher().permitAll();    //url que vamos a permitir
			auth.anyRequest().authenticated();		//Peticiones que no quieras que sean protegidas
		})
		.formLogin()
			.successHandler(succesHandler())		//URL donde se va redirigir al iniciar sesion
			.permitAll()
		.and()
		.sessionManagement()						//Configura el comportamiento de las sesiones
			.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)	//ALWAYS - IF_REQUIRED - NEVER - STATELESS
			.invalidSessionUrl("/login")			//Si falla regrese a el url seleccionado
			.maximumSessions(1)						//Cuantas personas pueden estar logeadas a la misma cuenta
			.expiredUrl("/login") 					//Que pasa si se expira la sesion a donde se va redirigir
			.sessionRegistry(sessionRegistry())
		.and()
		.sessionFixation()							//Vulnerabilidad de inicar sesion usando ID de sesion
			.migrateSession() //Soluciones: 1.migrateSession: genera nuevos ID sesion cada vez que logeas/ se púede guardar informacion del usuario - 2.newSession - 3.none:no es recomendable
		
		.and()
		.build();
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	
	public AuthenticationSuccessHandler succesHandler() {	//-----METODO SUCCESHANDLER
		return ((request, response, authentication) -> {
			response.sendRedirect("/menu");					//Redirige si se logra autenticar
		});
	}

}*/
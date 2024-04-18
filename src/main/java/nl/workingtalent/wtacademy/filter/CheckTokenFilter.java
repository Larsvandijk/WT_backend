package nl.workingtalent.wtacademy.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nl.workingtalent.wtacademy.user.IUserRepository;
import nl.workingtalent.wtacademy.user.User;

@Component
public class CheckTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private IUserRepository repo;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Hier gaan we de token uitlezen en dan de user ophalen die bij de token hoort.
		
		// Haal de token op uit de header
		String token = request.getHeader("Authorization");

		// Is de token wel meegestuurd
		if (token != null && token.length() > 100) {
			/* 
			 * Elke token stuurt de volgende tekst mee:
			 * 	Bearer TOKEN
			 *
			 * Wij moeten de Bearer tekst eraf halen zodat we alleen de token overhouden
			 */
			token = token.substring(7);

			// Vraag aan de repository of die de token kent
			Optional<User> optionalUser = repo.findByToken(token);

			// Check of de token is gevonden
			if (optionalUser.isEmpty()) {
				// Token is niet gevonden
				response.sendError(401);
				return;
			} else {
				User foundUser = optionalUser.get();

				// Plaats de gevonden user in de request attributes
				request.setAttribute("WT_USER", foundUser);
			}
		}

		// Voer hier de filter uit
		filterChain.doFilter(request, response);
	}
	
}

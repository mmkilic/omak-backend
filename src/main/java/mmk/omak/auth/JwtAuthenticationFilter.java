package mmk.omak.auth;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private static final String[] WHITE_LIST_URL = {"/auth/",
			"/enums/regions",
            "/v2/api-docs",
            "/v3/api-docs",
            "/swagger-resources",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/",
            "/webjars/"};
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	private final HandlerExceptionResolver handlerExceptionResolver;
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, 
									@NonNull HttpServletResponse response, 
									@NonNull FilterChain filterChain) throws ServletException, IOException {
		
		try {
			for (String url : WHITE_LIST_URL) {
				if(request.getServletPath().startsWith(url)) {
					filterChain.doFilter(request, response);
					return;
				}
			}
			
			final String authHeader = request.getHeader("Authorization");
			final String token;
			final String userEmail;
			
			if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
				throw new MalformedJwtException("Token Failed");
				//filterChain.doFilter(request, response);
				//return;
			}
				//throw new MalformedJwtException("Token Failed");
			
			token = authHeader.substring(7);
			userEmail = jwtService.extractUsername(token);
	    
			if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
				if (jwtService.isTokenValid(token, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				} else throw new MalformedJwtException("Token Failed");
			} else throw new MalformedJwtException("Token Failed");
			
			filterChain.doFilter(request, response);
			
		} catch (AccessDeniedException ex) {
			handlerExceptionResolver.resolveException(request, response, null, ex);
		} catch (ExpiredJwtException ex) {
			handlerExceptionResolver.resolveException(request, response, null, ex);
		} catch (MalformedJwtException ex) {
			handlerExceptionResolver.resolveException(request, response, null, ex);
		} catch (SignatureException ex) {
			handlerExceptionResolver.resolveException(request, response, null, ex);
		}
		
		
	}
}
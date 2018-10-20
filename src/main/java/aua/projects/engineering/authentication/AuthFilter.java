package aua.projects.engineering.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Autowired
    private AuthProvider authProvider;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpRequest httpRequest = (HttpRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String token = httpRequest.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(token != null && !token.isEmpty()){
            boolean verified = authProvider.verifyToken(token);
            if(verified){
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpServletResponse.sendError(403);
            }
        }
    }

    @Override
    public void destroy() {

    }
}

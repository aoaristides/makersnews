package br.com.makersweb.makersnews.security.interceptor;

import br.com.makersweb.makersnews.core.AuthUser;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @author aaristides
 */
@Component
@AllArgsConstructor
public class UserSessionInvalidatorRequestInterceptor implements WebRequestInterceptor {

    private final UserSessionInvalidator userSessionInvalidator;

    @Override
    public void preHandle(WebRequest request) throws Exception {
        String sessionId = request.getSessionId();
        UsernamePasswordAuthenticationToken userPrincipal = (UsernamePasswordAuthenticationToken) request.getUserPrincipal();

        if (userPrincipal == null)
            return;

        if (isNotUserAuth(userPrincipal))
            return;

        userSessionInvalidator.invalidateSessionIfNecessary(sessionId, userPrincipal.getName());
    }

    private boolean isNotUserAuth(UsernamePasswordAuthenticationToken userPrincipal) {
        return !(userPrincipal.getPrincipal() instanceof AuthUser);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {

    }
}

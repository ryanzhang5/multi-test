package com.poona.security;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.FilterInvocation;

public class AccessDecisionVoter extends RoleVoter {

    protected final Log log = LogFactory.getLog(getClass());

    private boolean enabled;

    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> config) {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String requestUrl = filterInvocation.getRequestUrl();

        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            if (!enabled) {
                return ACCESS_GRANTED;
            }
            User userDetails = (User) principal;
            Collection<GrantedAuthority> grantedPermissions = userDetails.getAuthorities();
            for (GrantedAuthority grantedPermission : grantedPermissions) {
            	System.out.println(grantedPermission + "------------->" + requestUrl);
                if (requestUrl.indexOf(grantedPermission.getAuthority()) >=0) {
                    return ACCESS_GRANTED;
                }
            }
            return ACCESS_DENIED;
        } else {
            return super.vote(authentication, object, config);
        }
    }

    /**
     * Set if this voter enabled. If not, returns ACCESS_GRANTED for any url.
     * 
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}

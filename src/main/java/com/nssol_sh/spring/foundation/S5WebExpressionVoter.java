package com.nssol_sh.spring.foundation;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebExpressionVoter;

public class S5WebExpressionVoter extends WebExpressionVoter {

	@Override
	public int vote(Authentication authentication, FilterInvocation fi,
			Collection<ConfigAttribute> attributes) {
		int rtnVote = ACCESS_DENIED;
		
		String url = fi.getRequestUrl();
		if (url.startsWith("/dashboard")) {

			int dsEndIndex = url.indexOf("/", 14);
			String ds = url.substring(14, dsEndIndex);
			int projectEndIndex = url.indexOf("/", dsEndIndex + 9);
			String project;
			if (projectEndIndex > 0) {
				project = url.substring(dsEndIndex + 9, projectEndIndex);
			} else {
				project = url.substring(dsEndIndex + 9);
			}

			String role = ds + "|" + project;

			Collection c = authentication.getAuthorities();
			for (Object object : c) {
				GrantedAuthority ga = (GrantedAuthority) object;
				if (role.equals(ga.getAuthority())) {
					rtnVote = ACCESS_GRANTED;
					break;
				}
			}
		} else {
			rtnVote = super.vote(authentication, fi, attributes);
		}
				
		return rtnVote;
	}
}

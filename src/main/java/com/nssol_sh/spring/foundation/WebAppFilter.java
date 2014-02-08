package com.nssol_sh.spring.foundation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.Authentication;

public class WebAppFilter extends CasAuthenticationFilter {

	@Override
	@Deprecated
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException {	
		super.successfulAuthentication(request, response, authResult);
	}
}

/**
 * 
 */
package com.liuly.security.browser.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuly.security.browser.session.AbstractSessionStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;


public class LiulyInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

	public LiulyInvalidSessionStrategy(String invalidSessionUrl) {
		super(invalidSessionUrl);
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		onSessionInvalid(request, response);
	}

}

package com.spring.training.customer;

import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.spring.training.rest.ErrorObj;

@Aspect
@Component
public class SecurityEnforcer {

	@Around("within(com.spring.training.customer.*) && @annotation(sec)")
	public Object around2(final ProceedingJoinPoint pjp,
	                      final MySecurity sec) {
		try {
			String role = sec.role();
			Authentication authentication = SecurityContextHolder.getContext()
			                                                     .getAuthentication();
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			boolean cont = false;
			for (GrantedAuthority grantedAuthority : authorities) {
				if (role.equals(grantedAuthority.getAuthority())) {
					cont = true;
					break;
				}
			}
			if (cont) {
				Object proceed = pjp.proceed();
				return proceed;
			} else {
				ErrorObj errorObj = new ErrorObj();
				errorObj.setCode(2929);
				errorObj.setDesc("Giremezsin");
				return ResponseEntity.badRequest()
				                     .body(errorObj);
				// throw new WebServiceException("Giremezsin");
			}
		} catch (Throwable e) {
			return null;
		}
	}
}

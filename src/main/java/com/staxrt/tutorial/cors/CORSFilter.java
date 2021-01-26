package com.staxrt.tutorial.cors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest reqToUse= (HttpServletRequest)req;
		HttpServletResponse respToUser= (HttpServletResponse) res;
		
		List<String> headerList =new ArrayList<>();
				headerList.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN);
		headerList.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS);
		
		//header.setAccessControlExposeHeaders(headerList);
		respToUser.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
		respToUser.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PATCH, PUT, DELETE, OPTIONS");
		respToUser.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Origin, content-type,Access-Control-Allow-Origin,Access-Control-Allow-Methods,X-Requested-With,Accept,Authorization");
		respToUser.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Origin, content-type,Access-Control-Allow-Origin,Access-Control-Allow-Methods,X-Requested-With,Accept,Authorization");
		respToUser.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		chain.doFilter(reqToUse, respToUser);
    }



    @Override
    public void destroy() {
    }
}
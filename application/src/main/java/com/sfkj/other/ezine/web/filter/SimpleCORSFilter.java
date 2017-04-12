package com.sfkj.other.ezine.web.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleCORSFilter extends OncePerRequestFilter implements Ordered {

  private static final String ORIGIN_FILTER_PATTERN = "[^0-9a-zA-Z$-_.+!*'()$&+,/:;=?@]";

  public static final String IE_CONTENT_TYPE = "text/plain";
  public static final String IE_ENCODING = "UTF-8";
  private String corsAllowMethods;
  private String corsAllowHeaders;
  private String corsAllowCredentials;
  private String corsExposeHeaders;
  private String corsMaxAge;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException,
      IOException {

    if (log.isTraceEnabled()) {
      log.trace("CORS filter in effect. Adding required header values.");
    }

    // Get the origin of the request... all origins will be allowed
    String origin = request.getHeader("Origin");

    // Use Host header if Origin is blank
    if (StringUtils.isBlank(origin)) {
      origin = request.getHeader("Host");
    }

    // If origin, set allow
    if (StringUtils.isNotBlank(origin)) {
      // Url encode origin to avoid header splitting cross origin response hacks
      origin = origin.replaceAll(ORIGIN_FILTER_PATTERN, "");
      response.setHeader("Access-Control-Allow-Origin", origin);
    }

    if (corsAllowMethods != null) {
      response.setHeader("Access-Control-Allow-Methods", corsAllowMethods);
    }

    if (corsAllowHeaders != null) {
      response.setHeader("Access-Control-Allow-Headers", corsAllowHeaders);
    }

    if (corsAllowCredentials != null) {
      response.setHeader("Access-Control-Allow-Credentials", corsAllowCredentials);
    }

    if (corsExposeHeaders != null) {
      response.setHeader("Access-Control-Expose-Headers", corsExposeHeaders);
    }

    if (corsMaxAge != null) {
      response.setHeader("Access-Control-Max-Age", corsMaxAge);
    }

    // IE 8 & 9 Crossdomain request doesn't pass content-type correctly
    if ("POST".equals(request.getMethod()) && request.getContentType() == null) {
      request = new IEXDomainRequest(request);
    }

    // to bypass options checks
    if (request.getMethod().equals("OPTIONS")) {
      if (log.isTraceEnabled()) {
        log.trace("OPTIONS called");
      }
      response.getWriter().print("OK");
      response.getWriter().flush();
    } else {
      // Pass on to the other filters
      filterChain.doFilter(request, response);
    }

    if (log.isTraceEnabled()) {
      log.trace("CORS filter successful!");
    }
  }

  // Inner class helper
  private static class IEXDomainRequest extends HttpServletRequestWrapper {
    public IEXDomainRequest(HttpServletRequest request) throws IOException {
      super(request);
    }

    @Override
    public String getCharacterEncoding() {
      return IE_ENCODING;
    }

    @Override
    public String getContentType() {
      return IE_CONTENT_TYPE;
    }
  }

  @Override
  public int getOrder() {
    return -1;
  }

}

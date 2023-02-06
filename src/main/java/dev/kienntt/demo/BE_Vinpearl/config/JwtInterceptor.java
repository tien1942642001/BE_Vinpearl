package dev.kienntt.demo.BE_Vinpearl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Pre Handle method is Calling");
        if (!(request.getRequestURI().contains("login") || request.getRequestURI().contains("register"))) {
            String auth = request.getHeader("Authorization");
            System.out.println("auth: " +auth);
//            jwtTokenProvider.verify(auth);
//            jwtTokenProvider.parseJwt(auth);
//            return true;
        }


//        if ((request.getRequestURI().contains("login") || request.getRequestURI().contains("register"))) {
//            System.out.println("request.getRequestURI(): " +request.getRequestURI());
//            return true;
//        }
//        String token = request.getHeader("authorization");
//        if (token == null || !token.startsWith("Bearer ")) {
//            JwtTokenProvider.parseJwt(token);
//            return true;
//        }
//        String newToken = JwtTokenProvider.createJwtSignedHMAC();
//        response.setHeader("Authorization", newToken);
//        response.setHeader("Access-control-expose-headers", "Authorization");
//        System.out.println("newToken: " +newToken);
//        return true;
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
        System.out.println("Request and Response is completed");
    }
}

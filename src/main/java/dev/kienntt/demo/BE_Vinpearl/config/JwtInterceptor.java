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
        if (!(request.getRequestURI().contains("login") || request.getRequestURI().contains("register") || request.getRequestURI().contains("images"))) {
            String auth = request.getHeader("Authorization");
            // Kiểm tra token ở header của request
            if (auth == null || auth.isEmpty() || !auth.startsWith("Bearer ")) {
                // Nếu không có token hoặc không đúng định dạng thì trả về lỗi
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return false;
            }
            String token = auth.substring(7, auth.length());
            jwtTokenProvider.verify(token);
        }
        return true;
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

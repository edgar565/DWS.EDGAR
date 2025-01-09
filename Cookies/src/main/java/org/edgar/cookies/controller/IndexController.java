package org.edgar.cookies.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.edgar.cookies.entity.Counter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class IndexController {

    @GetMapping("/counter")
    public ResponseEntity<Integer> getCounter(HttpSession session) {
        Counter counter = (Counter) session.getAttribute("counter");
        if (counter == null) {
            counter = new Counter();
        }
        counter.increment();
        session.setAttribute("counter", counter);
        return ResponseEntity.ok(counter.getCount());
    }

    // API para guardar el nombre en una cookie
    @PostMapping("/name")
    public ResponseEntity<String> saveName(@RequestParam("name") String name, HttpServletResponse response) {
        Cookie cookie = new Cookie("username", name);
        cookie.setMaxAge(60 * 60 * 24); // 1 día
        response.addCookie(cookie);
        return ResponseEntity.ok("Nombre guardado exitosamente");
    }

    // API para obtener el nombre guardado
    @GetMapping("/name")
    public ResponseEntity<String> showName(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    return ResponseEntity.ok("Tu nombre es: " + cookie.getValue());
                }
            }
        }
        // Redirigir al formulario si no hay cookie
        return ResponseEntity.status(302).header("Location", "/login.html").build();
    }

}
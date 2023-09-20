package br.com.makersweb.makersnews.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author aaristides
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "pages/login";
    }

}

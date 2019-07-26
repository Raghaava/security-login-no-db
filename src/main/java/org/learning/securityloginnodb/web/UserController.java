package org.learning.securityloginnodb.web;

import java.security.Principal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
  @GetMapping
  public String user(@AuthenticationPrincipal Principal principal, Model model) {
    model.addAttribute("username", principal.getName());
    return "user/user";
  }
}

package org.jeapf.web.springmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("username", "test");
		return mv;
	}
}

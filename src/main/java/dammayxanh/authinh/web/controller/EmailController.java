package dammayxanh.authinh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {

	@RequestMapping("/home")
	public String homePage(){
		return "home";
	}
}

package ENSI.projetjava.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ENSI.projetjava.User;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage() {
		return "login";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}

	@RequestMapping(value = "registerdone", method = RequestMethod.POST)
	public String processRegister(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepo.save(user);
		return "register_done";
	}

}

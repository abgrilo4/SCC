package SCC_Project;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class ForumController {

	@RequestMapping("/")
	public String index() {
		return "Greetings from Azure Spring Cloud!";
	}

}
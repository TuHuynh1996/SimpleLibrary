@Controller
public class MainController {
	
	
	@GetMapping("/")
	public String index() {
			return "index";
	}
	
	
	@GetMapping("/signin")
	public String signIn() {
			return "signin";
	}
	
	
	@PostMapping("/signin")
	public String signIn(@Valid SignInForm signInForm, BindingResult bindingResult) {

		// Check validate and return message error

		try {
			return "redirect:/";
		} catch (Exception e) {
			return "signin";
		}
	}
}

public class SignInForm {

	@NotBlank(message = "message")
    @Pattern(regexp = "^(([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+)?$", message = "message")
    private String email;

    @NotBlank(message = "Chưa nhập user id")
    @Pattern(regexp = "^([A-Z0-9_-][A-Z0-9_-]*)?$", message = "message")
    private String id;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])([^\\s]{6,20})$", message = "message")
    private String passWord;

    private String passWordClone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWordClone() {
        return passWordClone;
    }

    public void setPassWordClone(String passWordClone) {
        this.passWordClone = passWordClone;
    }
}
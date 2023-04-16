package hh.backend.MovieRatingApp.webcontroller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import hh.backend.MovieRatingApp.domain.CategoryRepository;
import hh.backend.MovieRatingApp.domain.Review;
import hh.backend.MovieRatingApp.domain.ReviewRepository;
import hh.backend.MovieRatingApp.domain.User;
import hh.backend.MovieRatingApp.domain.UserRepository;
import jakarta.validation.Valid;

@Controller
public class AppController {

		@Autowired
		private ReviewRepository rrepository;
		@Autowired
		private CategoryRepository crepository;
		@Autowired
		private UserRepository urepository;
		
		@RequestMapping(value = "/login")
		public String login() {
			return "login";
		}
		
		@RequestMapping (value = "/signup")
		public String addUser (Model model) {
			model.addAttribute("user", new User());
			return "signup";
		}
		
		@RequestMapping(value ="/saveuser", method = RequestMethod.POST)
		public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingresult, Model model) {
			String password = user.getPasswordHash();
			String password2 = user.getpasswordConfirmation();
			boolean passwordsEqual = password.equals(password2);
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			String hashedPassword = bcrypt.encode(password);
			User u1 = new User();
			u1.setRole("USER");
			u1.setPasswordHash(hashedPassword);
			u1.setUsername(user.getUsername());
			u1.setEmail(user.getEmail());
			boolean userExists = urepository.findByUsername(u1.getUsername()) != null;
			if (bindingresult.hasErrors() || !passwordsEqual || userExists) {			
				if(!passwordsEqual) {	
					bindingresult.rejectValue("passwordConfirmation", "err.passwordConfirmation", "Your password and confirmation password must match.");
				}
				if(userExists) {
					bindingresult.rejectValue("username", "err.username", "Someone already has that username. Try again?");
				}
				return "signup.html";
			}
			urepository.save(u1);
			model.addAttribute("successMessage", "User created successfully");
			return "redirect:/login";
		}
		
	    @RequestMapping(value="jsonmovies", method = RequestMethod.GET)
	    public @ResponseBody List<Review> movieListRest() {
	        return (List<Review>) rrepository.findAll();
	    }

	    @RequestMapping (value ="jsonmovies/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional <Review> findMovieRest(@PathVariable("id") Long id){
	        return rrepository.findById(id);
	    }
	    
	    @GetMapping("/users")
	    public @ResponseBody Iterable<User> getUsers() {
	        return urepository.findAll();
	    }

		@GetMapping("/")
		public String reviewListController(Model model, Principal principal) {
			model.addAttribute("reviews", rrepository.findAll());
			model.addAttribute("user", principal.getName());
			Authentication authentication = (Authentication) principal;
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
			model.addAttribute("isAdmin", isAdmin);
			return "index";
		}

		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteReview(@PathVariable("id") Long ReviewId, Model model, Principal principal) {
			Authentication authentication = (Authentication) principal;
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
			Optional<Review> reviewOptional = rrepository.findById(ReviewId);
			if(reviewOptional.isPresent() && (isAdmin || reviewOptional.get().getUser().getUsername().equals(principal.getName()))) {
				rrepository.deleteById(ReviewId);
			}
			return "redirect:../";
		}
		
		@RequestMapping(value = "/addreview", method = RequestMethod.GET)
		public String addReview(Model model) {
			model.addAttribute("review", new Review());
			model.addAttribute("categories", crepository.findAll());
			return "addreview";
		}
		
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String saveReview(Review review, Principal principal) {
			String username = principal.getName();
			User user = urepository.findByUsername(username);
			review.setUser(user);
			rrepository.save(review);
			return "redirect:/";
		}
		
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
		public String editReview(@PathVariable("id") Long ReviewId, Model model, Principal principal) {
			Authentication authentication = (Authentication) principal;
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
			Optional<Review> reviewOptional = rrepository.findById(ReviewId);
			if(isAdmin || reviewOptional.get().getUser().getUsername().equals(principal.getName())) {
				model.addAttribute("review", rrepository.findById(ReviewId));
				model.addAttribute("categories", crepository.findAll());
				model.addAttribute("reviewId", ReviewId);
				return "editreview";
			}
			return "redirect:/";
		}
		
		@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
		public String updateReview(@PathVariable("id") Long ReviewId, Review review, Principal principal) {
			Authentication authentication = (Authentication) principal;
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			boolean isAdmin = userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
			Optional<Review> reviewOptional = rrepository.findById(ReviewId);
			if(reviewOptional.isPresent() && (isAdmin || reviewOptional.get().getUser().getUsername().equals(principal.getName()))) {
				review.setUser(reviewOptional.get().getUser());
				rrepository.save(review);
			}
			return "redirect:../";
		}
	}
package hh.backend.MovieRatingApp.webcontroller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.backend.MovieRatingApp.domain.CategoryRepository;
import hh.backend.MovieRatingApp.domain.Review;
import hh.backend.MovieRatingApp.domain.ReviewRepository;
import hh.backend.MovieRatingApp.domain.User;
import hh.backend.MovieRatingApp.domain.UserRepository;

@Controller
public class MovieController {

		@Autowired
		private ReviewRepository rrepository;
		@Autowired
		private CategoryRepository crepository;
		@Autowired
		private UserRepository urepository;
		
		@GetMapping("/index")
		public String IndexController() {
			return "index.html";
		}

	    @RequestMapping(value="jsonmovies", method = RequestMethod.GET)
	    public @ResponseBody List<Review> movieListRest() {
	        return (List<Review>) rrepository.findAll();
	    }

	    @RequestMapping (value ="jsonmovies/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional <Review> findMovieRest(@PathVariable("id") Long id){
	        return rrepository.findById(id);
	    }

		@GetMapping("/reviewlist")
		public String movielistController(Model model) {
			model.addAttribute("reviews", rrepository.findAll());
			return "reviewlist.html";
		}

		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		@PreAuthorize("hasRole('ADMIN')")
		public String deleteMovie(@PathVariable("id") Long MovieId, Model model) {
			rrepository.deleteById(MovieId);
			return "redirect:../reviewlist";
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
			return "redirect:reviewlist";
		}
		
		
		
	}
package in.aesl.burlyeducation.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.aesl.burlyeducation.entity.User;
import in.aesl.burlyeducation.repository.UserRepository;
import in.aesl.burlyeducation.response.ResponseHandler;
import in.aesl.burlyeducation.service.UserService;
import in.aesl.burlyeducation.utils.AppConstants;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService service;

    private PasswordEncoder encoder;

    public UserController(UserService service, PasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    /**
     * Any user can access this API - No Authentication required
     * @param user
     * @return
     */

    @PostMapping("/register")
    public User registerUser(@RequestBody User student) {
        User student1 = new User();
        student1.setSname(student.getSname());
        student1.setPassword(encoder.encode(student.getPassword()));
        student1.setSrole(student.getSrole());
        return service.register(student1);
    }

    @Autowired
    private UserRepository userRepository;

 // get all questions
 @GetMapping("/users")
 public ResponseEntity < Object > getAllUsers(
     @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
     @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
     @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy
) {
     try {
         Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
         Page<User> pagedResult = this.userRepository.findAll(paging);
                  
       return ResponseHandler.generateResponse("success", HttpStatus.OK, pagedResult);
     } catch (Exception e) {
         return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
     }
 }

    /**
     * User who has logged in successfully can access this API
     * @param username
     * @return
     */
    @GetMapping("/userInfo")
    public User getUserInfo(@RequestParam("sname") String username) {
        return service.getDetails(username);
    }

    /**
     * User who has the role ROLE_WRITE can only access this API
     * @param username
     * @return
     */
    @GetMapping("/getUserRoles")
    public String getUserRoles(@RequestParam("sname") String username) {
        return service.getUserRoles(username);
    }

}

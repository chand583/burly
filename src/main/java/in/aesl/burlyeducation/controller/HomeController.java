package in.aesl.burlyeducation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.aesl.burlyeducation.entity.QuestionEntity;
import in.aesl.burlyeducation.repository.QuestionRepository;

@Controller
public class HomeController {
@Autowired
QuestionRepository questionRepository;  


  @GetMapping("/dashboard")
  public String welcome(Model model, @Param("keyword") String keyword) {
    model.addAttribute("tutorial", keyword);
    model.addAttribute("pageTitle", "Home  Page");
    model.addAttribute("name", "Chandrapal Singh");
    Pageable paging = PageRequest.of(0, 5, Sort.by("id"));
    Page<QuestionEntity> pagedResult = this.questionRepository.findAll(paging);
    model.addAttribute("qlist", pagedResult);
    return "dashboard";
  }

}

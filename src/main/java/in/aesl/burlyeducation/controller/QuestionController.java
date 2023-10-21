package in.aesl.burlyeducation.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.aesl.burlyeducation.entity.QuestionEntity;
import in.aesl.burlyeducation.entity.User;
import in.aesl.burlyeducation.exception.ResourceNotFoundException;
import in.aesl.burlyeducation.repository.QuestionRepository;
import in.aesl.burlyeducation.response.ResponseHandler;
import in.aesl.burlyeducation.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    // get all questions
    @GetMapping
    public ResponseEntity < Object > getAllQuestions(
        @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy
 ) {
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            Page<QuestionEntity> pagedResult = this.questionRepository.findAll(paging);
            return ResponseHandler.generateResponse("success", HttpStatus.OK, pagedResult);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // get user by id
    @GetMapping("/{id}")
    public ResponseEntity < Object > getUserById(@PathVariable(value = "id") long questionId) {
        Optional<QuestionEntity> queObj= this.questionRepository.findById(questionId);
       // userObj.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        return   ResponseHandler.generateResponse("success", HttpStatus.OK, queObj);
            }

    // create user
    @PostMapping
    public QuestionEntity createQuestion(@RequestBody QuestionEntity question) {
        return this.questionRepository.save(question);
    }

    // update user
    @PutMapping("/{id}")
    public QuestionEntity updateQuestion(@RequestBody QuestionEntity que, @PathVariable("id") long quid) {
        QuestionEntity existingQue = this.questionRepository.findById(quid)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + quid));
            existingQue.setHtml(que.getHtml());
            existingQue.setSubjectId(que.getSubjectId());
            existingQue.setUserId(que.getUserId());
        return this.questionRepository.save(existingQue);
    }

    // delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity < User > deleteUser(@PathVariable("id") long userId) {
        QuestionEntity existingQue = this.questionRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
        this.questionRepository.delete(existingQue);
        return ResponseEntity.ok().build();
    }
}

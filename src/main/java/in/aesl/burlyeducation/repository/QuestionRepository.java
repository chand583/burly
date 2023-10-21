package in.aesl.burlyeducation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.aesl.burlyeducation.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long>{

    
}

package in.aesl.burlyeducation.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.aesl.burlyeducation.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

    public User findByUname(String username);
}

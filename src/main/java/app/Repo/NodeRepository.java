package app.Repo;

import app.Model.Node;
import app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node,Long>{
    List<Node> findAllByUser(User user);
}

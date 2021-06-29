package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.model.Webcontent;

@Repository
public interface WebcontentRepository extends JpaRepository<Webcontent, Long> {

}

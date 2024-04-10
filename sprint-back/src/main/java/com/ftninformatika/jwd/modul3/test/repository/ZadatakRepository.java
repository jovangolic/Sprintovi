package com.ftninformatika.jwd.modul3.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.Zadatak;

@Repository
public interface ZadatakRepository extends JpaRepository<Zadatak, Long> {
	
	Zadatak findOneById(Long id);

	@Query("SELECT z FROM Zadatak z WHERE "
			+ "(:imeZadatka IS NULL OR z.ime LIKE %:imeZadatka%) AND (:sprintId IS NULL OR z.sprint.id = :sprintId)")
	Page<Zadatak> search(@Param("imeZadatka") String imeZadatka, @Param("sprintId") Long sprintId, Pageable pageable);
	
	
	/*@Query("SELECT COALESCE(SUM(t.points),0) FROM Task t WHERE t.sprint.id = :sprintId")
	Long sumPoints(Long sprintId);*/
	
	
	/*Page<Zadatak> findBySprintId(Long sprintId, PageRequest of);
	Page<Zadatak> findByIme(String ime, PageRequest of);
	Page<Zadatak> findByImeAndSprintId(String ime, Long sprintId, PageRequest of);*/

}

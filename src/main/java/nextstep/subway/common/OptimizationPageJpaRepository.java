package nextstep.subway.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface OptimizationPageJpaRepository<T, ID> extends JpaRepository<T, ID> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.id > :offset")
    Page<T> findAll(@Param("offset") long offset, Pageable pageable);

    @Override
    default Page<T> findAll(Pageable pageable) {
        return findAll(pageable.getOffset(), PageRequest.of(0, pageable.getPageSize()));
    }
}

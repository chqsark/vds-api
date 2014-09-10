package vds.tag.repository;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import vds.tag.domain.Page;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface PageRepository extends Repository<Page, Integer> {
    Page findByUrl(String url);

    @Transactional
    Page save(Page page);

    @Transactional
    void delete(Integer id);
}


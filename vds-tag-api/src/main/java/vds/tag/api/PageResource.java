package vds.tag.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vds.tag.domain.Page;
import vds.tag.repository.PageRepository;

import javax.validation.Valid;

@RestController
@RequestMapping("/pages")
public class PageResource {
    private final PageRepository pageRepository;

    @Autowired
    public PageResource(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Page create(@Valid @RequestBody Page page) {
        return pageRepository.save(page);
    }

    @RequestMapping
    @Cacheable("pages")
    public Page findByUrl(@RequestParam("url") String url) {
        return pageRepository.findByUrl(url);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @CacheEvict(value = "pages", key = "'#page.url'")
    public void update(@PathVariable Integer id, @Valid @RequestBody Page page) {
        page.setId(id);
        pageRepository.save(page);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        pageRepository.delete(id);
    }
}

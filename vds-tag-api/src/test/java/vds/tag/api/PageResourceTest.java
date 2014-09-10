package vds.tag.api;

import org.junit.Test;

public class PageResourceTest extends AbstractResourceTest {
    @Test
    public void findByUrl() throws Exception {
        Object[][] expects = {{"url", "/test"}, {"tags[0].selector", "a[href=http://yahoo.com]"}};
        performGet("/pages?url=/test", expects);
    }

    @Test
    public void create() throws Exception {
        Object[][] expects = {};
        performPost("/pages", "{\"url\":\"/test_new\",\"tags\":[{\"selector\":\"img\"},{\"selector\":\"a\"}]}", expects);
    }

    @Test
    public void update() throws Exception {
        performPut("/pages/1", "{\"url\":\"/test_updated\",\"tags\":[{\"selector\":\"a[href='http://apple.com']\"},{\"selector\":\"a[href='http://google.com']\"}]}");
    }

    @Test
    public void delete() throws Exception {
        performDelete("/pages/1");
    }
}

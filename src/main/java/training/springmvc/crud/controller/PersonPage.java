package training.springmvc.crud.controller;

import training.springmvc.crud.model.Person;

import java.util.List;

/**
 * Minimal in-memory pager for the person list, replacing
 * org.springframework.beans.support.PagedListHolder (deprecated for
 * removal in Spring Framework 7.0.3, superseded by Spring Data Commons --
 * not worth pulling in as a dependency for a single flat in-memory list).
 * Property names match what PagedListHolder exposed, so index.jsp's EL
 * expressions (personList.firstPage, personList.pageList, etc.) needed
 * no changes.
 */
public class PersonPage {

    private static final int PAGE_SIZE = 10;

    private final List<Person> source;
    private int page;

    public PersonPage(List<Person> source) {
        this.source = source;
    }

    public int getPage() {
        if (page >= getPageCount()) {
            page = getPageCount() - 1;
        }
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void nextPage() {
        if (!isLastPage()) {
            page++;
        }
    }

    public void previousPage() {
        if (!isFirstPage()) {
            page--;
        }
    }

    public boolean isFirstPage() {
        return getPage() == 0;
    }

    public boolean isLastPage() {
        return getPage() == getPageCount() - 1;
    }

    public int getNrOfElements() {
        return source.size();
    }

    public List<Person> getPageList() {
        int from = getPage() * PAGE_SIZE;
        int to = Math.min(from + PAGE_SIZE, source.size());
        return source.subList(from, to);
    }

    private int getPageCount() {
        return Math.max(1, (source.size() + PAGE_SIZE - 1) / PAGE_SIZE);
    }
}

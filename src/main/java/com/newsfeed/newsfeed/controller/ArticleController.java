package com.newsfeed.newsfeed.controller;

import com.newsfeed.newsfeed.model.*;
import com.newsfeed.newsfeed.service.ArticleService;
import com.newsfeed.newsfeed.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

/**
 * Controller that handles operations over articles, and creating new ones
 * Created by bjakupovic
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    private static final String NEW_ARTICLE = "newarticle";
    private static final String ARTICLE_LIST = "articles";
    private static final String HOME = "home";

    private ArticleService articleService;

    private VotingService votingService;

    @Autowired
    public ArticleController(ArticleService articleService, VotingService votingService) {
        this.articleService = articleService;
        this.votingService = votingService;
    }

    @ModelAttribute("username")
    public String populateUsername() {
        return getAuthanticatedUser().getUsername();
    }

    /**
     * Controller shows the list of all articles
     *
     * @param page     page number
     * @param pageSize maximum number of items on page, default = 5
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String all(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "articleName") String sortBy, Model model) {
        model.addAttribute("searchForm", new SearchForm());
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortBy.equals("votes")) {
            direction = Sort.Direction.DESC;
        }
        Page<Article> articlePage = articleService.findAll(new PageRequest(page, pageSize, direction, sortBy));
        model.addAttribute("articlePage", articlePage);
        model.addAttribute("userArticles", new PageImpl<>(Collections.emptyList()));
        return ARTICLE_LIST;
    }

    /**
     * Controller shows only articles added by current user
     *
     * @param page     page number
     * @param pageSize maximum number of items on page, default = 5
     */
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String userArticles(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize, @RequestParam(defaultValue = "articleName") String sortBy, @RequestParam(defaultValue = "0") int deleted, Model model) {

        Sort.Direction direction = Sort.Direction.ASC;
        if (sortBy.equals("votes")) {
            direction = Sort.Direction.DESC;
        }
        if (deleted != 0) {
            String msg = format("You have deleted %d article(s)!", deleted);
            model.addAttribute("numDeleted", msg);
        }
        Page<Article> articlePage = articleService.getByNewsfeedUser(getAuthanticatedUser(), new PageRequest(page, pageSize, direction, sortBy));
        model.addAttribute("userArticles", articlePage);
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("articlePage", new PageImpl<Article>(Collections.emptyList()));
        model.addAttribute("sortBy", sortBy);
        return ARTICLE_LIST;
    }

    /**
     * Search
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchedArticles(Model model) {
        model.addAttribute("searchForm", new SearchForm());
        return ARTICLE_LIST;
    }

    /**
     * Controller shows searched articles
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchedArticles(@Valid SearchForm searchForm, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize,
                                   Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("searchForm", searchForm);
            return ARTICLE_LIST;
        }
        Sort.Direction direction = Sort.Direction.ASC;
        Page<Article> articlePage = articleService.getByArticleNameContainingIgnoreCase(searchForm.getArticleName(), new PageRequest(page, pageSize, direction, "articleName"));
        model.addAttribute("articlePage", articlePage);
        model.addAttribute("userArticles", new PageImpl<>(Collections.emptyList()));
        return ARTICLE_LIST;
    }

    /**
     * Controller redirects user to new article form page
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newArticle(Model model) {
        model.addAttribute("articleForm", new ArticleForm());
        return NEW_ARTICLE;
    }

    /**
     * Controller in which we create new Article
     *
     * @param articleForm form object in which we have all information necessary for creation of new article
     * @param result      redirection to list of articles
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newArticle(@Valid ArticleForm articleForm, Model model, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("articleForm", articleForm);
            return NEW_ARTICLE;
        }
        NewsfeedUser newsfeedUser = getAuthanticatedUser();
        Article article = new Article(articleForm.getArticleName(), articleForm.getLink(), articleForm.getAuthor(), 0, newsfeedUser);
        articleService.save(article);

        return "redirect:/article/all";
    }

    /**
     * Controller redirects user to home page
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize, Model model) {
        Page<Article> topArticles = articleService.getByVotesIsGreaterThanEqual(10, new PageRequest(page, pageSize, Sort.Direction.ASC, "votes"));
        model.addAttribute("topArticles", topArticles);
        return HOME;
    }


    /**
     * Controller handles deletion of the selected article
     *
     * @param ids  ids of the article to be deleted
     * @param page page from which it will be displayed the list of articles
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@RequestParam("idChecked") List<Long> ids, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize) {
        ids.forEach(id -> articleService.delete(id));
        return format("redirect:/article/my?page=%d&pageSize=%d&deleted=%d", page, pageSize, ids.size());
    }

    /**
     * Handles vote of the article
     *
     * @param id   id of the article to be voted
     * @param page page from which it will be displayed the list of articles
     */
    @RequestMapping(value = "/vote", method = RequestMethod.GET)
    public String vote(@RequestParam("id") Long id, @RequestParam("vote") Vote vote, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pageSize, Model model) {
        Article article = articleService.getByArticleId(id);

        String msg = votingService.castVote(getAuthanticatedUser(), article, vote);

        Page<Article> articlePage = articleService.findAll(new PageRequest(page, pageSize, Sort.Direction.ASC, "articleName"));
        model.addAttribute("articlePage", articlePage);
        model.addAttribute("searchForm", new SearchForm());
        model.addAttribute("userArticles", new PageImpl<>(Collections.emptyList()));
        model.addAttribute("voteMsg", msg);
        return ARTICLE_LIST;
    }
}

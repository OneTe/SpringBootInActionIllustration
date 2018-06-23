package com.manning.readinglist.controller;

import com.manning.readinglist.entity.Book;
import com.manning.readinglist.properties.AmazonProperties;
import com.manning.readinglist.repository.ReadingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by wangcheng  on 2018/6/22.
 */
@Controller
@RequestMapping("/readingList")
public class ReadingListController {

    private ReadingListRepository readingListRepository;

    //不建议这里使用Autowired，建议用构造器注入
    private AmazonProperties amazonProperties;


    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository, AmazonProperties amazonProperties){
        this.readingListRepository = readingListRepository;
        this.amazonProperties = amazonProperties;
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.GET)
    public String readerBooks(@PathVariable("reader") String reader,Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList != null){
            model.addAttribute("books",readingList);
            model.addAttribute("reader",reader);
            model.addAttribute("amazonID",amazonProperties.getAssociateId());
        }
        return "readingList";
    }
    @RequestMapping(value = "/{reader}",method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader,Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/readingList/{reader}";
    }
}

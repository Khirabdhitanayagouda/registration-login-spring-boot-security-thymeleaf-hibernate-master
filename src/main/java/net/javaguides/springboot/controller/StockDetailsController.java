package net.javaguides.springboot.controller;


import net.javaguides.springboot.model.Stock;
import net.javaguides.springboot.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StockDetailsController {
    @Autowired
    private StockService service;
    @RequestMapping(value = "/stock-details", method = RequestMethod.GET)
    public String viewStocks(Model model) {
        List<Stock> liststock = service.listAll();
        for(Stock stock: liststock) {
            System.out.println(stock.getId() + ", " + stock.getStockname() + ", " + stock.getValue());
        }
        model.addAttribute("liststock", liststock);
        System.out.print("Get /");
        return "/stock-details";
    }

    @RequestMapping(value = "/add-stock", method = RequestMethod.GET)
    public String addStock(Model model) {
        model.addAttribute("stock", new Stock());
        return "add-stock";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStock(@ModelAttribute("stock") Stock stk) {
        service.save(stk);
        System.out.println(stk.getId() + ", " + stk.getStockname() + ", " + stk.getValue());
        return "redirect:/stock-details";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditStockPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Stock stk = service.get(id);
        mav.addObject("stock", stk);
        return mav;

    }
    @RequestMapping("/delete/{id}")
    public String deletestock(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/stock-details";
    }
}
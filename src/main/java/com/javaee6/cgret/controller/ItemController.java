package com.javaee6.cgret.controller;

import com.javaee6.cgret.service.IItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 **/

@RequestMapping("/items")
@Controller
public class ItemController {

    @Resource
    private IItemService itemService;

    /**
     * 商品详情页
     * @param itemId
     * @param model
     * @return
     */
    @GetMapping("/info/{itemId}")
    public String getItemList(@PathVariable("itemId") Long itemId, Model model){

        Map<String, Object> map = this.itemService.getData(itemId);

        model.addAllAttributes(map);

        return "item";
    }

}

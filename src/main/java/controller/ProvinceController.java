package controller;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ProvinceService;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {

    @Autowired
    ProvinceService provinceService;
//  Bước 1:
    @GetMapping
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/province/list");
        modelAndView.addObject("provinces", provinceService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Province province) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/province/edit");
        Province province = provinceService.findById(id).get();
        modelAndView.addObject("pro", province);
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public ModelAndView upDate(@PathVariable Long id, Province province) {
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
//        Province oldprovince = provinceService.findById(id).get();
//        oldprovince.setDescription(province.getDescription());
//        oldprovince.setName(province.getName());
        provinceService.save(province);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/province/delete");
        Province province = provinceService.findById(id).get();
        modelAndView.addObject("pro", province);
        return modelAndView;
    }
    @PostMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/provinces");
        provinceService.remove(id);
        return modelAndView;
    }
    
}
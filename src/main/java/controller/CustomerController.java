package controller;


import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.customer.CustomerService;
import service.province.ProvinceService;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    ProvinceService provinceService;

    @Autowired
    CustomerService customerService;


    // khai báo một biến mà sẽ dùng ở nhiều view, mỗi khi view gọi đến "provinces" dữ liệu sẽ tự đổ ra
    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }

    @GetMapping
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;
    }


    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(@ModelAttribute("customer") Customer customer, Long provinceId) {
//        Optional<Province> optionalProvince = provinceService.findById(provinceId);
//        customer.setProvince(optionalProvince.get());
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        Customer customer = customerService.findById(id).get();
        modelAndView.addObject("cus", customer);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView upDate(@RequestParam Long provinceId, Customer customer) {
        Optional<Province> optionalProvince = provinceService.findById(provinceId);
        customer.setProvince(optionalProvince.get());
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        return modelAndView;
    }

//    @GetMapping("/delete/{id}")
//    public ModelAndView showDeleteForm(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("/customer/delete");
//        Customer customer = customerService.findById(id).get();
//        modelAndView.addObject("cus", customer);
//        return modelAndView;
//    }

//    @PostMapping("/delete/{id}")
//    public ModelAndView delete (@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
//        customerService.remove(id);
//        return modelAndView;
//    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete (@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        customerService.remove(id);
        return modelAndView;
    }

}

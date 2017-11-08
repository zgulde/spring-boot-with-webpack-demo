package com.zgulde.springbootwithwebpack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/data")
    public @ResponseBody List<Datum> data() {
        return Arrays.asList(
            new Datum("hello", 1),
            new Datum("from", 2),
            new Datum("spring", 3),
            new Datum("boot!", 4)
        );
    }
}

package cn.zhgliu.ezdp.genal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/easydp")
public class PublicHttpInterfaceController {

    @ResponseBody
    @RequestMapping("/matchingMode")
    public Object matchingMode(@RequestParam String subsystem, @RequestParam String queryCode) {
        return "success";
    }

    @ResponseBody
    @RequestMapping("/permissions")
    public Object permissions(@RequestParam String subsystem, @RequestParam String queryCode) {
        return "success";
    }

    @ResponseBody
    @RequestMapping("/matchingModeAndPermissions")
    public Object matchingModeAndPermissions(@RequestParam String subsystem, @RequestParam String queryCode) {
        return "success";
    }




}

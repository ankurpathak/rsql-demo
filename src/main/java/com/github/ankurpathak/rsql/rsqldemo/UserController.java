package com.github.ankurpathak.rsql.rsqldemo;


import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class UserController {
    @GetMapping("/users/hello")
    public String hello(HttpServletRequest request){

        String restOfTheUrl = (String) request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);


        /*We can use UriTemplate to map the restOfTheUrl*/
        UriTemplate template = new UriTemplate("/{id}/*");
        Map<String, String> values = template.match(request.getRequestURI());

        return "" + values.size();
    }
}

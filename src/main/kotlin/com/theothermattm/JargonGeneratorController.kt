package com.theothermattm

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*

@Controller
class JargonGeneratorController {

    val viewName = "jargongenerator"

    @RequestMapping(value=["/api/jargon"], method=[RequestMethod.GET], produces=["application/json"])
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    fun generateJargon() : JargonResponse {
        return JargonGenerator.generateJargon();
    }

    @GetMapping("/")
    fun generateJargonView(model: Model) : String {
        model.addAttribute("jargon", generateJargon().jargon)
        return viewName
    }
}
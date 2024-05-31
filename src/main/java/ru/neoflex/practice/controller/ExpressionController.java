package ru.neoflex.practice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.practice.model.Expression;
import ru.neoflex.practice.service.MinusService;
import ru.neoflex.practice.service.PlusService;
import ru.neoflex.practice.util.swaggerCustomAnnotation.AllWithoutParams;

@RestController
@RequestMapping("/expressions")
@Tag(name = "all", description = "endpoints for providing calculation memory")
public class ExpressionController {
    private final MinusService minusService;
    private final PlusService plusService;
    @Autowired
    public ExpressionController(MinusService minusService, PlusService plusService) {
        this.minusService = minusService;
        this.plusService = plusService;
    }

    @AllWithoutParams
    @GetMapping("")
    public ResponseEntity<String> all(){
        String jSONresult = "{\"expressions\": [ ";
        StringBuilder builder = new StringBuilder();
        for (Expression a : plusService.getAll()){
            builder.append(a.toString()).append(", ");
        }
        String getAllString = builder.toString();
        jSONresult += getAllString.substring(0, getAllString.length() > 2 ? getAllString.length()-2 : 0);
        builder = new StringBuilder();
        for (Expression a : minusService.getAll()){
            builder.append(a.toString()).append(", ");
        }
        getAllString = builder.toString();
        jSONresult += (getAllString.length() > 2 && jSONresult.length() > 20? ", ":"");
        jSONresult += getAllString.substring(0, getAllString.length() > 2 ? getAllString.length()-2 : 0);
        jSONresult += (" ] }");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jSONresult);
    }
}

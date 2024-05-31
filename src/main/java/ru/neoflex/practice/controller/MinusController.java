package ru.neoflex.practice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.practice.model.Expression;
import ru.neoflex.practice.service.MinusService;
import ru.neoflex.practice.util.swaggerCustomAnnotation.MinusWithParams;
import ru.neoflex.practice.util.swaggerCustomAnnotation.MinusWithoutParams;

@RestController
@RequestMapping("/minus")
@Tag(name = "minus", description = "endpoints for subtraction and providing subtraction memory")
public class MinusController {
    private final MinusService service;
    @Autowired
    public MinusController(MinusService service) {
        this.service = service;
    }

    @MinusWithoutParams
    @GetMapping("")
    public ResponseEntity<String> minus(){
        String jSONresult = "{\"minusExpressions\": [ ";
        StringBuilder builder = new StringBuilder();
        for (Expression a : service.getAll()){
            builder.append(a.toString()).append(", ");
        }
        String getAllString = builder.toString();
        jSONresult += getAllString.substring(0, getAllString.length() > 2 ? getAllString.length()-2 : 0);
        jSONresult += (" ] }");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jSONresult);
    }

    @MinusWithParams
    @GetMapping("/{diminutive}/{deductible}")
    public ResponseEntity<String> minus(@PathVariable("diminutive") String diminutive,
                                        @PathVariable("deductible") String deductible){
        Expression expression;
        try {
            expression = new Expression(diminutive + " - " + deductible, Integer.parseInt(diminutive)-Integer.parseInt(deductible));
        }
        catch(Exception exception){
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("{ \"message\": \"unable to parse your expression\" }");
        }
        service.insert(expression);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(expression.toString());
    }
}

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
import ru.neoflex.practice.service.PlusService;
import ru.neoflex.practice.util.swaggerCustomAnnotation.PlusWithParams;
import ru.neoflex.practice.util.swaggerCustomAnnotation.PlusWithoutParams;

@RestController
@RequestMapping("/plus")
@Tag(name = "plus", description = "endpoints for summation and providing summation memory")
public class PlusController {
    private final PlusService service;
    @Autowired
    public PlusController(PlusService service) {
        this.service = service;
    }

    @PlusWithoutParams
    @GetMapping("")
    public ResponseEntity<String> plus(){
        String jSONresult = "{\"plusExpressions\": [ ";
        StringBuilder builder = new StringBuilder();
        for (Expression a : service.getAll()){
            builder.append(a.toString()).append(", ");
        }
        String getAllString = builder.toString();
        jSONresult += getAllString.substring(0, getAllString.length() > 2 ? getAllString.length()-2 : 0);
        jSONresult += (" ] }");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jSONresult);
    }

    @PlusWithParams
    @GetMapping("/{summand1}/{summand2}")
    public ResponseEntity<String> plus(@PathVariable("summand1") String summand1,
                                       @PathVariable("summand2") String summand2){
        Expression expression;
        try {
            expression = new Expression(summand1 + " + " + summand2, Integer.parseInt(summand1)+Integer.parseInt(summand2));
        }
        catch(Exception exception){
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("{ \"message\": \"unable to parse your expression\" }");
        }
        service.insert(expression);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(expression.toString());
    }
}

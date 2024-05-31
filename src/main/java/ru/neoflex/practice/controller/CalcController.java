package ru.neoflex.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.practice.model.Expression;
import ru.neoflex.practice.service.MinusService;
import ru.neoflex.practice.service.PlusService;
import ru.neoflex.practice.util.swaggerCustomAnnotation.*;

import java.util.List;

@RestController
public class CalcController {
    private final PlusService plusService;
    private final MinusService minusService;
    @Autowired
    public CalcController(PlusService plusService, MinusService minusService) {
        this.plusService = plusService;
        this.minusService = minusService;
    }

    public String expressionReader(List<Expression> list){
        StringBuilder builder = new StringBuilder();
        for (Expression a : list){
            builder.append(a.toString()).append(", ");
        }
        return builder.toString();
    }

    @AllWithoutParams
    @GetMapping("/expressions")
    public ResponseEntity<String> all(){
        String jSONresult = "{\"expressions\": [ ";
        String getAllString = expressionReader(minusService.getAll());
        jSONresult += getAllString.substring(0, getAllString.length() > 2 ? getAllString.length()-2 : 0);
        getAllString = expressionReader(plusService.getAll());
        jSONresult += getAllString.substring(0, getAllString.length() > 2 ? getAllString.length()-2 : 0);
        jSONresult += (" ] }");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jSONresult);
    }


    @PlusWithoutParams
    @GetMapping("/plus")
    public ResponseEntity<String> plus(){
        String jSONresult = "{\"plusExpressions\": [ ";
        String getAllString = expressionReader(plusService.getAll());
        jSONresult += getAllString.substring(0, getAllString.length() > 2 ? getAllString.length()-2 : 0);
        jSONresult += (" ] }");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jSONresult);
    }

    @MinusWithoutParams
    @GetMapping("/minus")
    public ResponseEntity<String> minus(){
        String jSONresult = "{\"minusExpressions\": [ ";
        String getAllString = expressionReader(minusService.getAll());
        jSONresult += getAllString.substring(0, getAllString.length() > 2 ? getAllString.length()-2 : 0);
        jSONresult += (" ] }");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jSONresult);
    }

    @PlusWithParams
    @GetMapping("/plus/{summand1}/{summand2}")
    public ResponseEntity<String> plus(@PathVariable("summand1") String summand1,
                                       @PathVariable("summand2") String summand2){
        Expression expression;
        try {
            expression = new Expression(summand1 + " + " + summand2, Integer.parseInt(summand1)+Integer.parseInt(summand2));
        }
        catch(Exception exception){
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("{ \"message\": \"unable to parse your expression\" }");
        }
        plusService.insert(expression);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(expression.toString());
    }

    @MinusWithParams
    @GetMapping("/minus/{diminutive}/{deductible}")
    public ResponseEntity<String> minus(@PathVariable("diminutive") String diminutive,
                                @PathVariable("deductible") String deductible){
        Expression expression;
        try {
            expression = new Expression(diminutive + " - " + deductible, Integer.parseInt(diminutive)-Integer.parseInt(deductible));
        }
        catch(Exception exception){
            return ResponseEntity.badRequest().contentType(MediaType.APPLICATION_JSON).body("{ \"message\": \"unable to parse your expression\" }");
        }
        minusService.insert(expression);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(expression.toString());
    }
}

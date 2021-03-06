package com.example.memnuniyetanketi.Controllers;


import com.example.memnuniyetanketi.Error.ApiError;
import com.example.memnuniyetanketi.Model.Anket;
import com.example.memnuniyetanketi.Services.AnketService;
import com.example.memnuniyetanketi.Shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class AnketController {

    @Autowired
    private AnketService anketService;


    @GetMapping
    public String getRoot() {
        return "index";
    }

    //@RequestMapping(value = "/anket/create", method = RequestMethod.POST)
    @PostMapping(value = "/api/anket/create")
    public Anket createAnket(@Valid @RequestBody Anket anket) {
        //return anket;
        Anket Yenianket = anketService.createAnket(anket);
        return Yenianket;
    }

    @GetMapping(value = "/api/anket/list")
    public List<Anket> listAnket() {
        return anketService.listAnket();
    }

    @GetMapping(value = "/api/anket/{id}")
    public Anket getAnket(@PathVariable Long id) {
        return anketService.getAnket(id);
    }

    @PutMapping(value = "/api/anket/{id}")
    public Anket updateAnket(@PathVariable Long id, @Valid @RequestBody Anket anket) {
        Anket yenianket = anketService.getAnket(id);

        yenianket.setId(id);
        yenianket.setName(anket.getName());
        yenianket.setSurname(anket.getSurname());
        yenianket.setEmail(anket.getEmail());
        yenianket.setMessage(anket.getMessage());


        return anketService.updateAnket(yenianket);
    }

    @DeleteMapping(value = "/api/anket/{id}")
    public void deleteAnket(@PathVariable Long id) {
        anketService.deleteAnket(id);
    }

    @GetMapping(value = "/api/anket/search/")
    public List<Anket> searchAnket(@RequestParam(required=false, name = "search" ,defaultValue="unknown") String search) {
        List<Anket> anketList = anketService.searchAnket(search);
        return anketList;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException (MethodArgumentNotValidException ex) {
        ApiError error = new ApiError(400, "Validation error","/api/anket/create");
        Map<String, String> ValidationErrors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            ValidationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        error.setValidationErrors(ValidationErrors);
        return error;
     }

}

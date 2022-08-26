package fan.company.codingbatspringboot.controller;

import fan.company.codingbatspringboot.entity.CategoryGlobal;
import fan.company.codingbatspringboot.entity.Language;
import fan.company.codingbatspringboot.payload.ApiResult;
import fan.company.codingbatspringboot.services.LanguageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/language")
public class LanguageController {

    @Autowired
    private LanguageServices services;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestParam Language dto){
        ApiResult apiResult = services.add(dto);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.CREATED:HttpStatus.BAD_REQUEST).body(apiResult);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update (@Valid @PathVariable Long id, @RequestParam Language dto){
        ApiResult apiResult = services.update(id, dto);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.BAD_REQUEST).body(apiResult);
    }

    @GetMapping
    public ResponseEntity<Page<Language>> getAll(@RequestParam Integer page) {
        Page<Language> all = services.getAll(page);
        return ResponseEntity.status(all.hasContent() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(all);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Language  language = services.getById(id);
        return ResponseEntity.status(language != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(language);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResult apiResult = services.delete(id);
        return ResponseEntity.status(apiResult.isSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.BAD_REQUEST).body(apiResult);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }




}

package fan.company.codingbatspringboot.controller;

import fan.company.codingbatspringboot.entity.CategoryGlobal;
import fan.company.codingbatspringboot.payload.ApiResult;
import fan.company.codingbatspringboot.payload.CategoryGlobalDto;
import fan.company.codingbatspringboot.services.CategoryGlobalServices;
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
@RequestMapping("api/categoryGlobal")
public class CategoryGlobalController {

    @Autowired
    private CategoryGlobalServices services;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestParam CategoryGlobalDto dto){
        ApiResult apiResult = services.add(dto);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.CREATED:HttpStatus.BAD_REQUEST).body(apiResult);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update (@Valid @PathVariable Long id, @RequestParam CategoryGlobalDto dto){
        ApiResult apiResult = services.update(id, dto);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.BAD_REQUEST).body(apiResult);
    }

    @GetMapping("/getall")
    public ResponseEntity<Page<CategoryGlobal>> getAll(@RequestParam Integer page) {
        Page<CategoryGlobal> all = services.getAll(page);
        return ResponseEntity.status(all.hasContent() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(all);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        CategoryGlobal  CategoryGlobal = services.getById(id);
        return ResponseEntity.status(CategoryGlobal != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(CategoryGlobal);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResult apiResult = services.delete(id);
        return ResponseEntity.status(apiResult.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(apiResult);
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

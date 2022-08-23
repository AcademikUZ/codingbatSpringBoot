package fan.company.codingbatspringboot.controller;

import fan.company.codingbatspringboot.entity.Users;
import fan.company.codingbatspringboot.payload.ApiResult;
import fan.company.codingbatspringboot.payload.UserDto;
import fan.company.codingbatspringboot.services.UserServices;
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
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserServices services;

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestParam UserDto dto){
        ApiResult apiResult = services.add(dto);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.CREATED:HttpStatus.BAD_REQUEST).body(apiResult);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update (@Valid @PathVariable Long id, @RequestParam UserDto dto){
        ApiResult apiResult = services.update(id, dto);
        return ResponseEntity.status(apiResult.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.BAD_REQUEST).body(apiResult);
    }

    @GetMapping("/getall")
    public ResponseEntity<Page<Users>> getAll(@RequestParam Integer page) {
        Page<Users> all = services.getAll(page);
        return ResponseEntity.status(all.hasContent() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(all);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Users  users = services.getById(id);
        return ResponseEntity.status(users != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(users);
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

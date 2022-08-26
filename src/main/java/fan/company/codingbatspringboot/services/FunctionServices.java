package fan.company.codingbatspringboot.services;

import fan.company.codingbatspringboot.entity.Category;
import fan.company.codingbatspringboot.entity.Function;
import fan.company.codingbatspringboot.payload.ApiResult;
import fan.company.codingbatspringboot.payload.FunctionDto;
import fan.company.codingbatspringboot.repositories.CategoryRepository;
import fan.company.codingbatspringboot.repositories.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FunctionServices {

    @Autowired
    private FunctionRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;


    public ApiResult add(FunctionDto dto) {
        Function function = new Function();
        function.setName(dto.getName());
        repository.save(function);
        return new ApiResult("Function muvoffaqiyatli qo'shildi", true);
    }

    public Page<Function> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public Function getById(Long id) {
        Optional<Function> byId = repository.findById(id);
        if (!byId.isPresent())
            return null;
        return byId.get();
    }

    public ApiResult update(Long id, FunctionDto dto) {

        Optional<Function> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday Function mavjud emas", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ApiResult("Bunday Category mavjud emas", false);
        }

        Function function = optional.get();
        function.setName(dto.getName());
        function.setCategory(optionalCategory.get());
        repository.save(function);
        return new ApiResult("Function muvoffaqiyatli taxrirlandi", true);
    }

    public ApiResult delete(Long id) {

        Optional<Function> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday Function mavjud emas", false);
        }
        try {
            repository.deleteById(id);
            return new ApiResult("Muvoffaqiyatli o'chirildi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }



}

package fan.company.codingbatspringboot.services;

import fan.company.codingbatspringboot.entity.CategoryGlobal;
import fan.company.codingbatspringboot.entity.Users;
import fan.company.codingbatspringboot.payload.ApiResult;
import fan.company.codingbatspringboot.payload.CategoryGlobalDto;
import fan.company.codingbatspringboot.payload.UserDto;
import fan.company.codingbatspringboot.repositories.CategoryGlobalRepository;
import fan.company.codingbatspringboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryGlobalServices {

    @Autowired
    private CategoryGlobalRepository repository;

    public ApiResult add(CategoryGlobalDto dto) {
        CategoryGlobal categoryGlobal = new CategoryGlobal();
        categoryGlobal.setName(dto.getName());
        repository.save(categoryGlobal);
        return new ApiResult("CategoryGlobal muvoffaqiyatli qo'shildi", true);
    }

    public Page<CategoryGlobal> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public CategoryGlobal getById(Long id) {
        Optional<CategoryGlobal> byId = repository.findById(id);
        if (!byId.isPresent())
            return null;
        return byId.get();
    }

    public ApiResult update(Long id, CategoryGlobalDto dto) {

        Optional<CategoryGlobal> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday CategoryGlobal mavjud emas", false);
        }
        CategoryGlobal categoryGlobal = optional.get();
        categoryGlobal.setName(dto.getName());
        repository.save(categoryGlobal);
        return new ApiResult("CategoryGlobal muvoffaqiyatli taxrirlandi", true);
    }

    public ApiResult delete(Long id) {

        Optional<CategoryGlobal> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday categoryGlobal mavjud emas", false);
        }
        try {
            repository.deleteById(id);
            return new ApiResult("Muvoffaqiyatli o'chirildi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }



}

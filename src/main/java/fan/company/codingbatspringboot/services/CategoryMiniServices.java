package fan.company.codingbatspringboot.services;

import fan.company.codingbatspringboot.entity.Category;
import fan.company.codingbatspringboot.entity.CategoryMini;
import fan.company.codingbatspringboot.payload.ApiResult;
import fan.company.codingbatspringboot.payload.CategoryMiniDto;
import fan.company.codingbatspringboot.repositories.CategoryMiniRepository;
import fan.company.codingbatspringboot.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryMiniServices {

    @Autowired
    private CategoryMiniRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;


    public ApiResult add(CategoryMiniDto dto) {
        CategoryMini categoryMini = new CategoryMini();
        categoryMini.setName(dto.getName());
        repository.save(categoryMini);
        return new ApiResult("CategoryMini muvoffaqiyatli qo'shildi", true);
    }

    public Page<CategoryMini> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public CategoryMini getById(Long id) {
        Optional<CategoryMini> byId = repository.findById(id);
        if (!byId.isPresent())
            return null;
        return byId.get();
    }

    public ApiResult update(Long id, CategoryMiniDto dto) {

        Optional<CategoryMini> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday CategoryMini mavjud emas", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ApiResult("Bunday Category mavjud emas", false);
        }

        CategoryMini categoryMini = optional.get();
        categoryMini.setName(dto.getName());
        categoryMini.setCategory(optionalCategory.get());
        repository.save(categoryMini);
        return new ApiResult("CategoryMini muvoffaqiyatli taxrirlandi", true);
    }

    public ApiResult delete(Long id) {

        Optional<CategoryMini> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday categoryMini mavjud emas", false);
        }
        try {
            repository.deleteById(id);
            return new ApiResult("Muvoffaqiyatli o'chirildi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }



}

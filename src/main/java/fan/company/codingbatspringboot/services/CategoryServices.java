package fan.company.codingbatspringboot.services;

import fan.company.codingbatspringboot.entity.Category;
import fan.company.codingbatspringboot.entity.CategoryGlobal;
import fan.company.codingbatspringboot.payload.ApiResult;
import fan.company.codingbatspringboot.payload.CategoryDto;
import fan.company.codingbatspringboot.repositories.CategoryGlobalRepository;
import fan.company.codingbatspringboot.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServices {

    @Autowired
    private CategoryRepository repository;
    @Autowired
    private CategoryGlobalRepository categoryGlobalRepository;


    public ApiResult add(CategoryDto dto) {
        Optional<CategoryGlobal> categoryGlobalRepositoryById = categoryGlobalRepository.findById(dto.getCategoryGlobalId());
        if(!categoryGlobalRepositoryById.isPresent())
            return new ApiResult("Bunday CategoryGlobal mavjud emas", false);

        Category category = new Category();
        category.setAnnatation(dto.getAnnatation());
        category.setRating(dto.getRating());
        category.setText(dto.getText());
        category.setCategoryGlobal(categoryGlobalRepositoryById.get());
        repository.save(category);
        return new ApiResult("Category muvoffaqiyatli qo'shildi", true);
    }

    public Page<Category> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public Category getById(Long id) {
        Optional<Category> byId = repository.findById(id);
        if (!byId.isPresent())
            return null;
        return byId.get();
    }

    public ApiResult update(Long id, CategoryDto dto) {


        Optional<Category> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday Category mavjud emas", false);
        }
        Optional<CategoryGlobal> categoryGlobalRepositoryById = categoryGlobalRepository.findById(dto.getCategoryGlobalId());
        if(!categoryGlobalRepositoryById.isPresent())
            return new ApiResult("Bunday CategoryGlobal mavjud emas", false);
        Category category = optional.get();
        category.setAnnatation(dto.getAnnatation());
        category.setRating(dto.getRating());
        category.setText(dto.getText());
        category.setCategoryGlobal(categoryGlobalRepositoryById.get());
        repository.save(category);
        return new ApiResult("Category muvoffaqiyatli taxrirlandi", true);
    }

    public ApiResult delete(Long id) {

        Optional<Category> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday category mavjud emas", false);
        }
        try {
            repository.deleteById(id);
            return new ApiResult("Muvoffaqiyatli o'chirildi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }



}

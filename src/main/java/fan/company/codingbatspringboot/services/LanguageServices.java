package fan.company.codingbatspringboot.services;

import fan.company.codingbatspringboot.entity.Language;
import fan.company.codingbatspringboot.payload.ApiResult;
import fan.company.codingbatspringboot.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LanguageServices {

    @Autowired
    private LanguageRepository repository;
   
    public ApiResult add(Language language) {
        if(language.getLanguage().isEmpty() || language.getLanguage() == null)
            new ApiResult("Language muvoffaqiyatli qo'shildi", true);
        repository.save(language);
        return new ApiResult("Language muvoffaqiyatli qo'shildi", true);
    }

    public Page<Language> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public Language getById(Long id) {
        Optional<Language> byId = repository.findById(id);
        if (!byId.isPresent())
            return null;
        return byId.get();
    }

    public ApiResult update(Long id, Language language) {

        Optional<Language> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday Language mavjud emas", false);
        }
        if(language.getLanguage().isEmpty() || language.getLanguage() == null)
            new ApiResult("Language muvoffaqiyatli qo'shildi", true);
        repository.save(language);
        return new ApiResult("Language muvoffaqiyatli taxrirlandi", true);
    }

    public ApiResult delete(Long id) {

        Optional<Language> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday Language mavjud emas", false);
        }
        try {
            repository.deleteById(id);
            return new ApiResult("Muvoffaqiyatli o'chirildi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }



}

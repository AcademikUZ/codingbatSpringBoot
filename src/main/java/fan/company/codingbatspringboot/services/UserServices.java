package fan.company.codingbatspringboot.services;

import fan.company.codingbatspringboot.entity.Users;
import fan.company.codingbatspringboot.payload.ApiResult;
import fan.company.codingbatspringboot.payload.UserDto;
import fan.company.codingbatspringboot.repositories.UserRepository;
import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository repository;

    public ApiResult add(UserDto dto) {
        Users users = new Users();
        users.setPassword(dto.getPassword());
        users.setEmail(dto.getEmail());
        Users save = repository.save(users);
        return new ApiResult(save.getId()+" idlik user muvoffaqiyatli qo'shildi", true);
    }

    public Page<Users> getAll(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    public Users getById(Long id) {
        Optional<Users> byId = repository.findById(id);
        if (!byId.isPresent())
            return null;
        return byId.get();
    }

    public ApiResult update(Long id, UserDto dto) {

        Optional<Users> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday user mavjud emas", false);
        }
        Users users = optional.get();
        users.setPassword(dto.getPassword());
        users.setEmail(dto.getEmail());
        Users save = repository.save(users);
        return new ApiResult(save.getId()+" idlik user muvoffaqiyatli taxrirlandi", true);
    }

    public ApiResult delete(Long id) {

        Optional<Users> optional = repository.findById(id);
        if (!optional.isPresent()) {
            return new ApiResult("Bunday user mavjud emas", false);
        }
        try {
            repository.deleteById(id);
            return new ApiResult("Muvoffaqiyatli o'chirildi", true);
        } catch (Exception e) {
            return new ApiResult("Xatolik", false);
        }
    }



}

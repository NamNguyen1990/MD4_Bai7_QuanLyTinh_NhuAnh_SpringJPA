package service.province;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ProvinceRepository;

import java.util.Optional;

public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Optional<Province> findById(Long id) {
        return provinceRepository.findById(id);
    }

    @Override
    public void save(Province province) {
        provinceRepository.save(province);

    }

    @Override
    public void remove(Long id) {
        provinceRepository.deleteById(id);
    }
}

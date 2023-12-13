package mn.erdenet.springpetshop.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mn.erdenet.springpetshop.database.ChipRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chips")
@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ChipService {

    private final ChipRepository chipRepository;

    @GetMapping("/types")
    public List<String> getAllChipTypes() {
        return chipRepository.getAllChipTypes();
    }

}

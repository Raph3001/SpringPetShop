package mn.erdenet.springpetshop.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mn.erdenet.springpetshop.database.PetRepository;
import mn.erdenet.springpetshop.pojos.Pet;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pets")
@Slf4j
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Pet>> getAllPets(@RequestParam(name = "pageNo", required = false)Integer pageNo,
                                @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                @RequestParam(name = "petType", required = false) String petType) {

        List<Pet> allPets;

        allPets = petRepository.getAllPets();
        System.out.println("test: " + allPets);



        List<Pet> newList = List.copyOf(allPets);
        System.out.println(allPets.size());

        Pageable pageable = Pageable.unpaged();
        if (pageNo != null && pageSize != null) pageable = PageRequest.of(pageNo, pageSize, Sort.by("name"));

        Pet examplePet = petType != null ? Pet.builder().type("Cat").build() : new Pet();

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
        Example<Pet> example = Example.of(examplePet, exampleMatcher);


        return ResponseEntity.ok(petRepository.findAll(example, pageable));
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getPetTypes() {

        return ResponseEntity.ok(petRepository.getAllPetTypes());
    }

}

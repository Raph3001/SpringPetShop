package mn.erdenet.springpetshop.backend;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mn.erdenet.springpetshop.database.PetRepository;
import mn.erdenet.springpetshop.pojos.Pet;
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
    public List<Pet> getAllPets(@RequestParam(name = "pageNo", required = false)Integer pageNo,
                                @RequestParam(name = "pageSize", required = false) Integer pageSize,
                                @RequestParam(name = "petType", required = false) String petType) {
        List<Pet> allPets;

        allPets = petRepository.getAllPets();
        System.out.println("test: " + allPets);



        List<Pet> newList = List.copyOf(allPets);
        System.out.println(allPets.size());



        if (petType != null) {
            newList = allPets.stream().filter(p -> p.getType().equals(petType)).collect(Collectors.toList());
        }

        if (pageNo != null) {
            if (pageSize != null) {
                List<Pet> stuff = new ArrayList<>();
                for (int i = (pageNo-1)*pageSize; i<(pageNo-1)*pageSize+pageSize && i<newList.size(); i++) {
                    System.out.println(i);
                    stuff.add(newList.get(i));
                }
                newList = stuff;

            }
        }

        return newList;
    }

    @GetMapping("/types")
    public List<String> getPetTypes() {

        return petRepository.getAllPetTypes();
    }

}

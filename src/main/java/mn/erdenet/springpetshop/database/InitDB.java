package mn.erdenet.springpetshop.database;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mn.erdenet.springpetshop.pojos.Chip;
import mn.erdenet.springpetshop.pojos.Owner;
import mn.erdenet.springpetshop.pojos.Pet;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class InitDB {

    private final OwnerRepository ownerRepository;
    private final ChipRepository chipRepository;
    private final PetRepository petRepository;

    private List<Owner> owners = new ArrayList<>();

    @PostConstruct
    public void importDatasets() {

        String jsonPath = "C:\\Users\\Rapha\\springpetshop\\src\\main\\resources\\petstore.json";
        InputStream customerInputStream = this.getClass().getResourceAsStream("/petstore.json");
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        try {
            owners = mapper.readValue(customerInputStream, new TypeReference<List<Owner>>() {});
            //System.out.println("Balls " + mapper.readValue(customerInputStream, new TypeReference<List<Owner>>() {}));
            //owners = Arrays.asList(mapper.readValue(customerInputStream, Owner[].class));
            //owners = mapper.readerForListOf(Owner.class).readValue(customerInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        owners.forEach(o -> o.getPetList().forEach(p -> p.setOwner(o)));
        ownerRepository.saveAll(owners);

        System.out.println(owners);
    }

}

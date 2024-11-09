package com.faunus.api;

import com.faunus.api.core.owner.Owner;
import com.faunus.api.core.owner.OwnerPlant;
import com.faunus.api.core.owner.OwnerPlantRepository;
import com.faunus.api.core.owner.OwnerRepository;
import com.faunus.api.core.plant.Plant;
import com.faunus.api.core.plant.PlantRepository;
import com.faunus.api.core.watering.WateringMethod;
import com.faunus.api.core.watering.WateringMethodRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlphaTestingSetupService {

    private final OwnerRepository ownerRepository;
    private final PlantRepository plantRepository;
    private final WateringMethodRepository wateringMethodRepository;
    private final OwnerPlantRepository ownerPlantRepository;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        WateringMethod directWateringMethod = createWateringMethod("DIRECT_IRRIGATION",
                "آبیاری مستقیم",
                """
                        آبیاری مستقیم یک روش ساده و موثر برای آبیاری گیاهان است که شامل استفاده از آب‌پاش، لیوان یا دیگ آبیاری می‌شود.
                        در این روش، آب به صورت مستقیم و متناوب بر روی خاک گیاه پاشیده یا ریخته می‌شود،
                        که این کار به طور مستقیم به ریشه‌ها دسترسی دارد و برای حفظ رطوبت خاک و جلوگیری از خشک شدن سطح آن موثر است
                        """
        );

        WateringMethod subIrrigationWateringMethod = createWateringMethod("SUB_IRRIGATION",
                "آبیاری زیرکانونی",
                """
                        آبیاری زیرکانونی یک روش آبیاری موثر برای گیاهان است که در آن، آب از زیر گلدان به خاک گیاه منتقل می‌شود.
                        در این روش، گلدان‌ها بر روی یک ظرف یا پایه قرار داده می‌شوند که در آن آب جمع می‌شود.
                        سپس با استفاده از جاذب یا لوله‌های جانبی، آب به طور مداوم یا متناوب به خاک گیاه منتقل می‌شود.
                        این روش علاوه بر جلوگیری از خشک شدن سطح خاک، می‌تواند از مرگ زیری‌های گیاه و ایجاد محیط رطوبتی برای ریشه‌ها جلوگیری کند، که باعث رشد سالم‌تر گیاه می‌شود.
                        """
        );

        Plant ficusLirata = createPlant("Ficus Lirata", subIrrigationWateringMethod);
        log.info("Ficus created with id {}", ficusLirata.getId());

        Owner mohammad = createOwner("mohammad");
        Owner naeemeh = createOwner("naeemeh");
        Owner hosein = createOwner("hosein");
        log.info("Owner mohammad created with id {}", mohammad.getId());
        log.info("Owner naeemeh created with id {}", naeemeh.getId());
        log.info("Owner hosein created with id {}", hosein.getId());


        createOwnerPlant(mohammad, ficusLirata);
        createOwnerPlant(naeemeh, ficusLirata);
        createOwnerPlant(hosein, ficusLirata);
    }


    private Plant createPlant(String name, WateringMethod wateringMethod) {
        return plantRepository.save(new Plant(null, name, wateringMethod.getId()));
    }

    private WateringMethod createWateringMethod(String key, String title, String description) {
        return wateringMethodRepository.save(new WateringMethod(null, key, title, description));
    }

    private Owner createOwner(String userName) {
        var optionalOwner = ownerRepository.findByName(userName);
        return optionalOwner.orElseGet(() -> ownerRepository.save(new Owner(null, userName)));
    }

    private OwnerPlant createOwnerPlant(Owner owner, Plant plant) {
        return ownerPlantRepository.save(new OwnerPlant(null, owner.getId(), plant.getId()));
    }
}

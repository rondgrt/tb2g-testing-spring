package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    private ClinicService clinicService;

    @Mock
    private Map<String, Object> model;

    @InjectMocks
    private VetController controller;

    private List<Vet> vetsList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        //given
        Vet vet = new Vet();
        vet.setId(1);
        vetsList.add(vet);

        when(clinicService.findVets()).thenReturn(vetsList);
    }

    @Test
    void showVetList() {

        //when
        String result = controller.showVetList(model);

        //then
        assertThat(result).isEqualToIgnoringCase("vets/vetList");
        verify(model, times(1)).put(eq("vets"), any());
    }

    @Test
    void showResourcesVetList() {

        //when
        Vets vets = controller.showResourcesVetList();

        //then
        assertThat(vets).isNotNull();
        assertThat(vets.getVetList()).hasSize(1);
        assertThat(vets.getVetList().get(0).getId()).isEqualTo(1);
    }
}
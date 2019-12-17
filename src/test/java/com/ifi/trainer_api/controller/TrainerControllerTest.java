package com.ifi.trainer_api.controller;

import com.ifi.trainer_api.bo.Trainer;
import com.ifi.trainer_api.repository.TrainerRepository;
import com.ifi.trainer_api.service.TrainerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainerControllerTest {
    @Mock
    private TrainerService trainerService;

    @InjectMocks
    private TrainerController trainerController;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllTrainers_shouldCallTheService() {
        trainerController.getAllTrainers();

        verify(trainerService).getAllTrainers();
    }

    @Test
    void getTrainer_shouldCallTheService() {
        trainerController.getTrainer("Ash");

        verify(trainerService).getTrainer("Ash");
    }

    @Test
    void trainerController_shouldBeAnnotated(){
        var controllerAnnotation =
                TrainerController.class.getAnnotation(RestController.class);
        assertNotNull(controllerAnnotation);

        var requestMappingAnnotation =
                TrainerController.class.getAnnotation(RequestMapping.class);
        assertArrayEquals(new String[]{"/trainers"}, requestMappingAnnotation.value());
    }

    @Test
    void getAllTrainers_shouldBeAnnotated() throws NoSuchMethodException {
        var getAllTrainers =
                TrainerController.class.getDeclaredMethod("getAllTrainers");
        var getMapping = getAllTrainers.getAnnotation(GetMapping.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/"}, getMapping.value());
    }

    @Test
    void getTrainer_shouldBeAnnotated() throws NoSuchMethodException {
        var getTrainer =
                TrainerController.class.getDeclaredMethod("getTrainer", String.class);
        var getMapping = getTrainer.getAnnotation(GetMapping.class);

        var pathVariableAnnotation = getTrainer.getParameters()[0].getAnnotation(PathVariable.class);

        assertNotNull(getMapping);
        assertArrayEquals(new String[]{"/{name}"}, getMapping.value());

        assertNotNull(pathVariableAnnotation);
    }

    @Test
    void createTrainer_shouldBeAnnotated() throws NoSuchMethodException {
        var createTrainer = TrainerController.class.getDeclaredMethod("createTrainer", Trainer.class);
        var postMapping = createTrainer.getAnnotation(PostMapping.class);

        assertNotNull(postMapping);
        assertArrayEquals(new String[]{"/"}, postMapping.value());
    }

    @Test
    void deleteTrainer_shouldBeAnnotated() throws NoSuchMethodException {
        var deleteTrainer = TrainerController.class.getDeclaredMethod("deleteTrainer", String.class);
        var deleteMapping = deleteTrainer.getAnnotation(DeleteMapping.class);

        var pathVariableAnnotation = deleteTrainer.getParameters()[0].getAnnotation(PathVariable.class);

        assertNotNull(deleteMapping);
        assertArrayEquals(new String[]{"/{name}"}, deleteMapping.value());
        assertNotNull(pathVariableAnnotation);

    }

    @Test
    void updateTrainer_shouldBeAnnotated() throws NoSuchMethodException {
        var updateTrainer = TrainerController.class.getDeclaredMethod("updateTrainer", String.class, Trainer.class);
        var putMapping = updateTrainer.getAnnotation(PutMapping.class);

        var pathVariableAnnotation = updateTrainer.getParameters()[0].getAnnotation(PathVariable.class);

        assertNotNull(putMapping);
        assertArrayEquals(new String[]{"/{name}"}, putMapping.value());
        assertNotNull(pathVariableAnnotation);

    }


}

package com.ifi.trainer_api.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifi.trainer_api.bo.Trainer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static junit.framework.TestCase.assertNotNull;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrainerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TrainerController controller;

    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    private static JSONObject bugCatcherJSONObject;

    private static HttpHeaders headers;

    @Before
    void init() throws JSONException {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        bugCatcherJSONObject = new JSONObject();
        bugCatcherJSONObject.put("name", "Bug Catcher");

        JSONArray teams = new JSONArray();

        JSONObject json = new JSONObject();
        json.put("pokemonType", 13)
                .put("level", 6);

        JSONObject json2 = new JSONObject();
        json2.put("pokemonType", 10)
                .put("level", 6);

        teams.put((Object) json);
        teams.put((Object) json2);
        bugCatcherJSONObject.put("team", (Object) teams);
    }

    @Test
    void trainerController_shouldBeInstanciated(){
        assertNotNull(controller);
    }

    @Test
    void getTrainers_shouldThrowAnUnauthorized(){
        var responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/trainers/Ash", Trainer.class);
        assertNotNull(responseEntity);
        assertEquals(401, responseEntity.getStatusCodeValue());
    }

    @Test
    void getTrainer_withNameAsh_shouldReturnAsh() {
        var ash = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/Ash", Trainer.class);

        assertNotNull(ash);
        assertEquals("Ash", ash.getName());
        assertEquals(1, ash.getTeam().size());

        assertEquals(25, ash.getTeam().get(0).getPokemonType());
        assertEquals(18, ash.getTeam().get(0).getLevel());
    }

    @Test
    void getAllTrainers_shouldReturnAshAndMisty() {

        var trainers = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/", Trainer[].class);


        assertNotNull(trainers);
        assertEquals(2, trainers.length);

        assertEquals("Ash", trainers[0].getName());
        assertEquals("Misty", trainers[1].getName());
    }

    @Test
    void createTrainer_withNameBugCatcher_shouldReturnBugCatcher() throws JSONException {

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        bugCatcherJSONObject = new JSONObject();
        bugCatcherJSONObject.put("name", "Bug Catcher");

        JSONArray teams = new JSONArray();

        JSONObject json = new JSONObject();
        json.put("pokemonType", 13)
                .put("level", 6);

        JSONObject json2 = new JSONObject();
        json2.put("pokemonType", 10)
                .put("level", 6);

        teams.put((Object) json);
        teams.put((Object) json2);
        bugCatcherJSONObject.put("team", (Object) teams);

        HttpEntity<String> request = new HttpEntity<>(bugCatcherJSONObject.toString(), headers);

        Trainer bugcatcher = this.restTemplate
                .withBasicAuth(username, password)
                .postForObject("http://localhost:" + port + "/trainers/", request, Trainer.class);

        assertNotNull(bugcatcher);

        assertEquals("Bug Catcher", bugcatcher.getName());
        assertEquals(2, bugcatcher.getTeam().size());

        assertEquals(13, bugcatcher.getTeam().get(0).getPokemonType());
        assertEquals(6, bugcatcher.getTeam().get(0).getLevel());

        assertEquals(10, bugcatcher.getTeam().get(1).getPokemonType());
        assertEquals(6, bugcatcher.getTeam().get(1).getLevel());
    }

    @Test
    void updateTrainer_withNameBugCatcher_shouldReturnBugCatcher() throws JSONException {

        /*headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        bugCatcherJSONObject = new JSONObject();
        bugCatcherJSONObject.put("name", "Bug Catcher");

        JSONArray teams = new JSONArray();

        JSONObject json = new JSONObject();
        json.put("pokemonType", 13)
                .put("level", 6);

        JSONObject json2 = new JSONObject();
        json2.put("pokemonType", 10)
                .put("level", 6);

        teams.put((Object) json);
        teams.put((Object) json2);
        bugCatcherJSONObject.put("team", (Object) teams);*/

        HttpEntity<String> request = new HttpEntity<>(bugCatcherJSONObject.toString(), headers);

        Trainer bugcatcher = this.restTemplate
                .withBasicAuth(username, password)
                .postForObject("http://localhost:" + port + "/trainers/", request, Trainer.class);

        Trainer trainer = new Trainer();
        trainer.setName("Super Bug Catcher");

        HttpEntity<Trainer> request2 = new HttpEntity<>(trainer, headers);

        this.restTemplate
                .withBasicAuth(username, password)
                .exchange("http://localhost:" + port + "/trainers/Bug+Catcher", HttpMethod.PUT, request2, Trainer.class, trainer.getName());

        var bugcatcher2 = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/Bug+Catcher", Trainer.class);

        assertNull(bugcatcher2);

        var superbugcatcher = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/Super+Bug+Catcher", Trainer.class);

        assertNotNull(superbugcatcher);

        /*assertEquals("Super Bug Catcher", superbugcatcher.getName());
        assertEquals(2, superbugcatcher.getTeam().size());

        assertEquals(13, bugcatcher.getTeam().get(0).getPokemonType());
        assertEquals(6, bugcatcher.getTeam().get(0).getLevel());

        assertEquals(10, bugcatcher.getTeam().get(1).getPokemonType());
        assertEquals(6, bugcatcher.getTeam().get(1).getLevel());*/

    }

    @Test
    void deleteTrainer_withNameBugCatcher_shouldBeOk() throws JSONException {

       this.restTemplate
                .withBasicAuth(username, password)
                .delete("http://localhost:" + port + "/trainers/Super+Bug+Catcher");

        var bugcatcher = this.restTemplate
                .withBasicAuth(username, password)
                .getForObject("http://localhost:" + port + "/trainers/Super+Bug+Catcher", Trainer.class);

        assertNull(bugcatcher);

    }




}
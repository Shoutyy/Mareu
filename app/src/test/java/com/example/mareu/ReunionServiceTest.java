package com.example.mareu;

import com.example.mareu.di.DI;
import com.example.mareu.model.Reunion;
import com.example.mareu.service.DummyReunionGenerator;
import com.example.mareu.service.ReunionApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(JUnit4.class)
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ReunionServiceTest {

    private ReunionApiService service;
    private List<Reunion> reunionList;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }
    
    @Test
    public void getReunionsWithSuccess() {
        List<Reunion> reunions = service.getReunions("", "");
        List<Reunion> expectedReunions = DummyReunionGenerator.DUMMY_REUNIONS;
        assertThat(reunions, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedReunions.toArray()));
    }

    @Test
    public void deleteReunionWithSuccess() {
        Reunion reunionToDelete = service.getReunions("","").get(0);
        service.deleteReunion(reunionToDelete);
        assertFalse(service.getReunions("","").contains(reunionToDelete));
    }

    @Test
    public void addReunionWithSuccess() {
        List<Reunion> reunions = service.getReunions("", "");
        Reunion nReunion = new Reunion(reunions.size() + 1, "01/5/2020", " 16H50", "Salle A", "Marketing", new String[]{"amandine@lamzone.com", "anthony@lamzone.com", "stephanie@lamzone.com"});
        reunions.add(nReunion);
        assertTrue(reunions.contains(nReunion));
    }

    @Test
    public void filterReunionWithSuccess() {
       Reunion fReunions = service.getReunions("6/5/2020", "Salle A").get(0);
       assertSame("6/5/2020", fReunions.getDate());
    }
}
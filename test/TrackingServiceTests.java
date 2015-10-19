import ProteinTracker.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class TrackingServiceTests {

    private TrackingService service;


    @Test
    public void WhenGoalIsMetHistoryIsUpdated() throws InvalidGoalException
    {
        service.setGoal(5);
        service.addProtein(6);
        HistoryItem result = service.getHistory().get(1);
        assertEquals("sent:goal met", result.getOperation());
    }

    //@Test
    //This is an integration test
//    public void WhenGoalIsMetMailIsSent() throws InvalidGoalException
//    {
//        service = new TrackingService(new Emailer());
//        service.setGoal(5);
//        service.addProtein(6);
//        HistoryItem result = service.getHistory().get(1);
//        assertEquals("sent:goal met", result.getOperation());
//    }


}
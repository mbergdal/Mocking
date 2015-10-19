import ProteinTracker.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;

public class TrackingServiceTests {

    private TrackingService service;

    @Before
    public void setUp()
    {
        service = new TrackingService(new NotifierStub());
    }

    @Test
    public void WhenGoalIsMetHistoryIsUpdated() throws InvalidGoalException
    {
        service.setGoal(5);
        service.addProtein(6);
        HistoryItem result = service.getHistory().get(1);
        assertEquals("sent:goal met", result.getOperation());
    }

    @Test
    public void WhenGoalIsMetHistoryIsUpdated_withMockito() throws InvalidGoalException
    {
        Notifier mockitoNotifier = mock(Notifier.class);
        service = new TrackingService(mockitoNotifier);
        when(mockitoNotifier.send("goal met")).thenReturn(true);
        service.setGoal(5);

        service.addProtein(6);
        HistoryItem result = service.getHistory().get(1);

        assertEquals("sent:goal met", result.getOperation());
        verify(mockitoNotifier, times(1)).send("goal met");
    }

    @Test
    //This is an integration test
    public void WhenGoalIsMetMailIsSent() throws InvalidGoalException
    {
        service = new TrackingService(new Emailer());
        service.setGoal(5);
        service.addProtein(6);
        HistoryItem result = service.getHistory().get(1);
        assertEquals("sent:goal met", result.getOperation());
    }

    private class NotifierStub implements Notifier {
        @Override
        public boolean send(String message) {
            return true;
        }
    }
}
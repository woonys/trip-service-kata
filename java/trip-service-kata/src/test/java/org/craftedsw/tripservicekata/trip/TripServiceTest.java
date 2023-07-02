package org.craftedsw.tripservicekata.trip;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Test;

public class TripServiceTest {

    private User loggedInUser;
    @Test
    public void should_throw_UserNotLoggedInException() {
        // given
        TripService tripService = new TestableTripService();
        loggedInUser = null;
        //when
        assertThatThrownBy(() ->
            tripService.getTripsByUser(null)
        ).isInstanceOf(UserNotLoggedInException.class);
    }

    @Test
    public void return_emptyTripList_when_no_Friend() {
        User user = new User();
        loggedInUser = user;
        TripService tripService = new TestableTripService();
        List<Trip> tripResult = tripService.getTripsByUser(user);

        assertThat(tripResult.size()).isEqualTo(0);
    }


    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedInUser() {
            return loggedInUser;
        }
    }
	
}

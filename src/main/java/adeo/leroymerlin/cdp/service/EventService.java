package adeo.leroymerlin.cdp.service;

import java.util.List;

import adeo.leroymerlin.cdp.domain.Event;

public interface EventService {

	/**
	 * Get all events
	 * 
	 * @return list of events
	 */
	List<Event> getEvents();

	/**
	 * Get filtered events by search criteria
	 *
	 * @param query the search criteria
	 * 
	 * @return filtered list of events
	 */
	List<Event> getFilteredEvents(String query);

	/**
	 * Delete event by id
	 * 
	 * @param id the event id
	 * 
	 */
	void delete(Long id);

	/**
	 * Update event
	 * 
	 * @param id      the event id
	 * @param comment the comment
	 * @param nbStars the number of stars
	 * 
	 */
	void update(Long id, String comment, Integer nbStars);
}

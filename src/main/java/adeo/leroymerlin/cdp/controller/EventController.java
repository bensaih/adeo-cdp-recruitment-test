package adeo.leroymerlin.cdp.controller;

import org.springframework.web.bind.annotation.*;

import adeo.leroymerlin.cdp.controller.requestparameter.UpdateEventParameter;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.service.EventService;
import adeo.leroymerlin.cdp.viewmodel.EventViewModel;

import java.util.List;

@RestController
public class EventController implements EventApi {

	private final EventService eventService;

	private final EventMapper eventMapper;

	private static final String EVENT_ROOT_URL = "/api/events";
	private static final String GET_EVENTS_URL = EVENT_ROOT_URL + "/";
	private static final String SEARCH_EVENTS_URL = EVENT_ROOT_URL + "/search/{query}";
	private static final String DELETE_EVENT_URL = EVENT_ROOT_URL + "/{id}";
	private static final String UPDATE_EVENT_URL = EVENT_ROOT_URL + "/{id}";

	public EventController(final EventService eventService, final EventMapper eventMapper) {
		this.eventService = eventService;
		this.eventMapper = eventMapper;
	}

	@GetMapping(value = GET_EVENTS_URL)
	@Override
	public List<EventViewModel> findEvents() {
		return eventMapper.toEventViewModel(
				eventService.getEvents());
	}

	@GetMapping(value = SEARCH_EVENTS_URL)
	@Override
	public List<EventViewModel> findEvents(@PathVariable final String query) {
		return eventMapper.toEventViewModel(
				eventService.getFilteredEvents(query));
	}

	@DeleteMapping(value = DELETE_EVENT_URL)
	@Override
	public void deleteEvent(@PathVariable final Long id) {
		eventService.delete(id);
	}

	@PutMapping(value = UPDATE_EVENT_URL)
	@Override
	public void updateEvent(@PathVariable Long id, @RequestBody final UpdateEventParameter event) {
		eventService.update(
				id,
				event.getComment(),
				event.getNbStars());
	}
}

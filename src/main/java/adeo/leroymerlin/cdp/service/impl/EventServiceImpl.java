package adeo.leroymerlin.cdp.service.impl;

import org.springframework.stereotype.Service;

import adeo.leroymerlin.cdp.domain.Event;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.model.EventEntity;
import adeo.leroymerlin.cdp.repository.EventRepository;
import adeo.leroymerlin.cdp.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

	private final EventRepository eventRepository;

	private final EventMapper eventMapper;

	public EventServiceImpl(final EventRepository eventRepository, final EventMapper eventMapper) {
		this.eventRepository = eventRepository;
		this.eventMapper = eventMapper;
	}

	@Override
	public List<Event> getEvents() {
		return eventMapper.toEvents(eventRepository.findAll());
	}

	@Override
	public void delete(final Long id) {
		eventRepository.delete(id);
	}

	@Override
	public List<Event> getFilteredEvents(final String query) {
		return getEvents().stream()
				.filter(event -> event.getBands().stream().anyMatch(
						brand -> brand.getMembers().stream().anyMatch(
								member -> member.getName().contains(query))))
				.collect(Collectors.toList());
	}

	@Override
	public void update(final Long id, final String comment, final Integer nbStars) {
		final EventEntity event = eventRepository.findOne(id);
		event.setNbStars(nbStars);
		event.setComment(comment);

		eventRepository.save(event);
	}
}

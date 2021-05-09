package adeo.leroymerlin.cdp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import adeo.leroymerlin.cdp.domain.Band;
import adeo.leroymerlin.cdp.domain.Event;
import adeo.leroymerlin.cdp.domain.Member;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.model.BandEntity;
import adeo.leroymerlin.cdp.model.EventEntity;
import adeo.leroymerlin.cdp.model.MemberEntity;
import adeo.leroymerlin.cdp.repository.EventRepository;
import adeo.leroymerlin.cdp.service.EventService;
import adeo.leroymerlin.cdp.service.impl.EventServiceImpl;

@ExtendWith(MockitoExtension.class)
 class EventServiceTest {

	private EventMapper eventMapper;

	@Mock
	private EventRepository eventRepositoryMock;

	private EventService eventService;

	@BeforeEach
	public void before() {
		eventMapper = new EventMapper();
		eventService = new EventServiceImpl(eventRepositoryMock, eventMapper);
	}

	@Test
	void getEvents_shouldReturnEvents() {
		// Arrange
		when(eventRepositoryMock.findAll()).thenReturn(buildEventEntities());

		// Act
		List<Event> events = eventService.getEvents();

		// Assert
		assertThat(events).hasSize(2);
		assertEvents(events);
	}

	@Test
	void getFilteredEvents_shouldReturnFilteredEvents() {
		// Arrange
		when(eventRepositoryMock.findAll()).thenReturn(buildEventEntities());

		// Act
		List<Event> events = eventService.getFilteredEvents("Member 2");

		// Assert
		assertThat(events).hasSize(1);
		assertFiltredEvents(events);
	}
	
	@Test
	void getFilteredEvents_shouldReturnEmptyList_whenNoMatchingResult() {
		when(eventRepositoryMock.findAll()).thenReturn(buildEventEntities());

		// Act
		List<Event> events = eventService.getFilteredEvents("NOTFOUND");

		// Assert
		assertThat(events).isEmpty();
	}

	@Test
	void delete_shouldDeleteEvent() {
		// Arrange
		doNothing().when(eventRepositoryMock).delete(1L);

		// Act
		Throwable thrown = catchThrowable(() -> eventService.delete(1L));

		// Assert
		assertThat(thrown).isNull();
	}

	@Test
	void saveOrUpdate_shouldUpdateEvent() {
		// Arrange
		when(eventRepositoryMock.findOne(1L)).thenReturn(buildEventEntities().get(0));

		// Act
		Throwable thrown = catchThrowable(() -> eventService.update(1L, "new comment", 5));

		// Assert
		assertThat(thrown).isNull();
	}

	private List<EventEntity> buildEventEntities() {
		MemberEntity firstMember = new MemberEntity(1L, "Member 1");
		MemberEntity secondMember = new MemberEntity(2L, "Member 2");
		MemberEntity thirdMember = new MemberEntity(3L, "Member 3");

		Set<MemberEntity> membersA = new HashSet<>(Arrays.asList(firstMember, secondMember));
		Set<MemberEntity> membersB = new HashSet<>(Arrays.asList(thirdMember));
		

		BandEntity bandA = new BandEntity(1L, "Band A", membersA);
		Set<BandEntity> bandsA = new HashSet<>(Arrays.asList(bandA));

		BandEntity bandB = new BandEntity(2L, "Band B", membersB);
		Set<BandEntity> bandsB = new HashSet<>(Arrays.asList(bandB));

		return Arrays.asList(
				new EventEntity(1L, "Title A", "Image A", bandsA, 4, "Comment A"),
				new EventEntity(2L, "Title B", "Image B", bandsB, 3, "Comment B"));
	}

	private void assertFiltredEvents(List<Event> event) {
		assertFirstEvent(event);
	}
	
	private void assertEvents(List<Event> event) {
		assertFirstEvent(event);
		assertSecondEvent(event);
	}
	
	private void assertFirstEvent(List<Event> event) {
		Event firstEvent = event.get(0);
		
		assertThat(firstEvent.getId()).isEqualTo(1L);
		assertThat(firstEvent.getTitle()).isEqualTo("Title A");
		assertThat(firstEvent.getImgUrl()).isEqualTo("Image A");
		assertThat(firstEvent.getNbStars()).isEqualTo(4);
		assertThat(firstEvent.getComment()).isEqualTo("Comment A");

		Band firstBand = firstEvent.getBands().stream().findFirst().get();

		assertThat(firstBand.getId()).isEqualTo(1L);
		assertThat(firstBand.getName()).isEqualTo("Band A");

		List<Member> memberList = new ArrayList<>(firstBand.getMembers());
		memberList.sort(Comparator.comparing(Member::getId));
		
		Member firstMember = memberList.get(0);

		assertThat(firstMember.getId()).isEqualTo(1L);
		assertThat(firstMember.getName()).isEqualTo("Member 1");
		
		Member secondMember = memberList.get(1);
		
		assertThat(secondMember.getId()).isEqualTo(2L);
		assertThat(secondMember.getName()).isEqualTo("Member 2");
	}
	
	private void assertSecondEvent(List<Event> event) {
		Event firstEvent = event.get(1);

		assertThat(firstEvent.getId()).isEqualTo(2L);
		assertThat(firstEvent.getTitle()).isEqualTo("Title B");
		assertThat(firstEvent.getImgUrl()).isEqualTo("Image B");
		assertThat(firstEvent.getNbStars()).isEqualTo(3);
		assertThat(firstEvent.getComment()).isEqualTo("Comment B");

		Band firstBand = firstEvent.getBands().stream().findFirst().get();

		assertThat(firstBand.getId()).isEqualTo(2L);
		assertThat(firstBand.getName()).isEqualTo("Band B");

		Iterator<Member> memberIterator = firstBand.getMembers().iterator();

		Member firstMember = memberIterator.next();

		assertThat(firstMember.getId()).isEqualTo(3L);
		assertThat(firstMember.getName()).isEqualTo("Member 3");
	}
}

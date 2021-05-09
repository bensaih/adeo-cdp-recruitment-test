package adeo.leroymerlin.cdp.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import adeo.leroymerlin.cdp.controller.requestparameter.UpdateEventParameter;
import adeo.leroymerlin.cdp.domain.Band;
import adeo.leroymerlin.cdp.domain.Event;
import adeo.leroymerlin.cdp.domain.Member;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.service.EventService;

@SpringBootTest
class EventControllerTest {

	@Mock
	private EventService eventServiceMock;

	private EventMapper eventMapper;

	@Autowired
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	private EventController eventController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		eventMapper = new EventMapper();

		objectMapper = new ObjectMapper();

		eventController = new EventController(eventServiceMock, eventMapper);

		mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
	}

	@Test
	void findEvents_shouldSuccessAndReturnEvents() throws Throwable {
		when(eventServiceMock.getEvents()).thenReturn(buildEvent());

		ResultActions resultActions = mockMvc.perform(get("/api/events/"));
		assertEvents(resultActions);
	}

	@Test
	void findEvents_shouldSuccessAndReturnFilteredEvents() throws Throwable {
		when(eventServiceMock.getFilteredEvents("Member")).thenReturn(buildEvent());

		ResultActions resultActions = mockMvc.perform(get("/api/events/search/Member"));
		assertEvents(resultActions);
	}

	@Test
	void deleteEvent_shouldDeleteEvent() throws Throwable {
		doNothing().when(eventServiceMock).delete(1L);

		mockMvc.perform(delete("/api/events/1")).andExpect(status().isOk());
	}

	@Test
	void updateEvent_shouldUpdateEvent() throws Throwable {
		doNothing().when(eventServiceMock).update(1L, "new comment", 5);

		final String updateEventParameter = objectMapper.writeValueAsString(new UpdateEventParameter(5, "new comment"));

		mockMvc.perform(put("/api/events/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(updateEventParameter))
				.andExpect(status().isOk());
	}

	private List<Event> buildEvent() {
		Member firstMember = new Member(1L, "Member 1");
		Set<Member> membersA = new HashSet<>(Arrays.asList(firstMember));

		Band firstBand = new Band(1L, "Band A", membersA);
		Set<Band> bands = new LinkedHashSet<>(Arrays.asList(firstBand));

		return Arrays.asList(new Event(1L, "Title", "Image", bands, 4, "Comment"));
	}

	private void assertEvents(ResultActions resultActions) throws Throwable {
		resultActions.andExpect(status().isOk())
		        .andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].title").value("Title [1]"))
				.andExpect(jsonPath("$[0].imgUrl").value("Image"))
				.andExpect(jsonPath("$[0].nbStars").value(4))
				.andExpect(jsonPath("$[0].comment").value("Comment"))
				.andExpect(jsonPath("$[0].bands[0].id").value(1))
				.andExpect(jsonPath("$[0].bands[0].name").value("Band A [1]"))
				.andExpect(jsonPath("$[0].bands[0].members[0].id").value(1))
				.andExpect(jsonPath("$[0].bands[0].members[0].name").value("Member 1"));
	}

}

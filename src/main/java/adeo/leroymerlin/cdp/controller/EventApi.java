package adeo.leroymerlin.cdp.controller;

import java.util.List;

import adeo.leroymerlin.cdp.controller.requestparameter.UpdateEventParameter;
import adeo.leroymerlin.cdp.viewmodel.EventViewModel;

public interface EventApi {

	List<EventViewModel> findEvents();

	List<EventViewModel> findEvents(String query);

	void deleteEvent(Long id);

	void updateEvent(Long id, UpdateEventParameter event);
}
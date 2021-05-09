package adeo.leroymerlin.cdp.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import adeo.leroymerlin.cdp.domain.Band;
import adeo.leroymerlin.cdp.domain.Event;
import adeo.leroymerlin.cdp.domain.Member;
import adeo.leroymerlin.cdp.model.BandEntity;
import adeo.leroymerlin.cdp.model.EventEntity;
import adeo.leroymerlin.cdp.model.MemberEntity;
import adeo.leroymerlin.cdp.utils.ListUtils;
import adeo.leroymerlin.cdp.viewmodel.BandViewModel;
import adeo.leroymerlin.cdp.viewmodel.EventViewModel;
import adeo.leroymerlin.cdp.viewmodel.MemberViewModel;

/**
 * 
 * We can use generated mapping library like MapStruct instead of doing the mapping manually
 *
 */
@Component
public class EventMapper {

	public List<Event> toEvents(final List<EventEntity> source) {
		if (source == null || source.isEmpty()) {
			return new ArrayList<>();
		}

		return source.stream()
				.map(EventMapper::toEvent)
				.collect(Collectors.toList());
	}

	private static Event toEvent(final EventEntity source) {
		final Event event = new Event();
		event.setComment(source.getComment());
		event.setId(source.getId());
		event.setImgUrl(source.getImgUrl());
		event.setNbStars(source.getNbStars());
		event.setTitle(source.getTitle());

		final Set<BandEntity> bandsEntities = source.getBands();
		Set<Band> bands = new HashSet<>();

		if (bandsEntities != null && !bandsEntities.isEmpty()) {
			bands = bandsEntities.stream()
					.map(EventMapper::toBand)
					.collect(Collectors.toSet());
		}

		event.setBands(bands);

		return event;
	}

	private static Band toBand(final BandEntity source) {
		final Band brand = new Band();
		brand.setId(source.getId());
		brand.setName(source.getName());

		final Set<MemberEntity> membersEntities = source.getMembers();
		Set<Member> members = new HashSet<>();

		if (membersEntities != null && !membersEntities.isEmpty()) {
			members = membersEntities.stream()
					.map(EventMapper::toMember)
					.collect(Collectors.toSet());
		}

		brand.setMembers(members);

		return brand;
	}

	private static Member toMember(final MemberEntity source) {
		final Member member = new Member();
		member.setId(source.getId());
		member.setName(source.getName());

		return member;
	}

	public List<EventViewModel> toEventViewModel(final List<Event> source) {
		if(source == null || source.isEmpty()) {
			return new ArrayList<>();
		}
		
		List<EventViewModel> eventsViewModel = new ArrayList<>();
		
		source.forEach(ListUtils.withCounter((index, event) -> {
			final String titleWithIndex = event.getTitle() + " [" + index + "]";
			event.setTitle(titleWithIndex);
			
			eventsViewModel.add(
					toEventViewModel(event));
			 }));
		
		return eventsViewModel;
	}
	
	private static EventViewModel toEventViewModel(final Event source) {
		final EventViewModel event = new EventViewModel();
		event.setComment(source.getComment());
		event.setId(source.getId());
		event.setImgUrl(source.getImgUrl());
		event.setNbStars(source.getNbStars());
		event.setTitle(source.getTitle());

		final Set<Band> bands = source.getBands();
		List<BandViewModel> bandsViewModel = new LinkedList<>();

		if (bands != null && !bands.isEmpty()) {
			bands.forEach(ListUtils.withCounter((index, brand) -> {
				final String brandNameWithIndex = brand.getName() + " [" + index + "]";
				brand.setName(brandNameWithIndex);

				bandsViewModel.add(toBandViewModel(brand));
			}));
		}

		event.setBands(bandsViewModel);

		return event;
	}
	
	private static BandViewModel toBandViewModel(final Band source) {
		final BandViewModel brand = new BandViewModel();
		brand.setId(source.getId());
		brand.setName(source.getName());

		final Set<Member> members = source.getMembers();
		Set<MemberViewModel> membersViewModel = new HashSet<>();

		if (members != null && !members.isEmpty()) {
			membersViewModel = members.stream()
					.map(EventMapper::toMemberViewModel)
					.collect(Collectors.toSet());
		}

		brand.setMembers(membersViewModel);

		return brand;
	}
	
	private static MemberViewModel toMemberViewModel(final Member source) {
		final MemberViewModel member = new MemberViewModel();
		member.setId(source.getId());
		member.setName(source.getName());

		return member;
	}
}

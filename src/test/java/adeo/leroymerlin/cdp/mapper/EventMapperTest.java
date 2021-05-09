package adeo.leroymerlin.cdp.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import adeo.leroymerlin.cdp.domain.Band;
import adeo.leroymerlin.cdp.domain.Event;
import adeo.leroymerlin.cdp.domain.Member;
import adeo.leroymerlin.cdp.mapper.EventMapper;
import adeo.leroymerlin.cdp.model.BandEntity;
import adeo.leroymerlin.cdp.model.EventEntity;
import adeo.leroymerlin.cdp.model.MemberEntity;
import adeo.leroymerlin.cdp.viewmodel.BandViewModel;
import adeo.leroymerlin.cdp.viewmodel.EventViewModel;
import adeo.leroymerlin.cdp.viewmodel.MemberViewModel;

 class EventMapperTest {

	private EventMapper eventMapper;

	@BeforeEach
	public void before() {
		eventMapper = new EventMapper();
	}

	@Test
	void toEvents_shouldMapEventCorrectly() {
		List<Event> events = eventMapper.toEvents(buildEventEntities());

		Event firstEvent = events.get(0);
		EventEntity firstEventEntity = buildEventEntities().get(0);

		assertThat(firstEvent.getComment()).isEqualTo(firstEventEntity.getComment());
		assertThat(firstEvent.getId()).isEqualTo(firstEventEntity.getId());
		assertThat(firstEvent.getImgUrl()).isEqualTo(firstEventEntity.getImgUrl());
		assertThat(firstEvent.getNbStars()).isEqualTo(firstEventEntity.getNbStars());
		assertThat(firstEvent.getTitle()).isEqualTo(firstEventEntity.getTitle());

		Band firstBand = firstEvent.getBands().stream().findFirst().get();
		BandEntity firstBandEntity = firstEventEntity.getBands().stream().findFirst().get();

		assertThat(firstBand.getId()).isEqualTo(firstBandEntity.getId());
		assertThat(firstBand.getName()).isEqualTo(firstBandEntity.getName());

		Member firstMember = firstBand.getMembers().stream().findFirst().get();
		MemberEntity firstMemberEntity = firstBandEntity.getMembers().stream().findFirst().get();

		assertThat(firstMember.getId()).isEqualTo(firstMemberEntity.getId());
		assertThat(firstMember.getName()).isEqualTo(firstMemberEntity.getName());
	}

	@Test
	void toEventViewModel_shouldMapEventViewModelCorrectly() {
		List<EventViewModel> event = eventMapper.toEventViewModel(buildEvent());

		EventViewModel firstEventViewModel = event.get(0);
		Event firstEvent = buildEvent().get(0);

		assertThat(firstEventViewModel.getComment()).isEqualTo(firstEvent.getComment());
		assertThat(firstEventViewModel.getId()).isEqualTo(firstEvent.getId());
		assertThat(firstEventViewModel.getImgUrl()).isEqualTo(firstEvent.getImgUrl());
		assertThat(firstEventViewModel.getNbStars()).isEqualTo(firstEvent.getNbStars());
		assertThat(firstEventViewModel.getTitle()).isEqualTo("Title [1]");

		
		List<BandViewModel> bandViewModelList = new ArrayList<>(firstEventViewModel.getBands());
		bandViewModelList.sort(Comparator.comparing(BandViewModel::getId));
		
		List<Band> BandList = new ArrayList<>(firstEvent.getBands());
		BandList.sort(Comparator.comparing(Band::getId));

		BandViewModel firstBandViewModel = bandViewModelList.get(0);
		Band firstBand = BandList.get(0);

		assertThat(firstBandViewModel.getId()).isEqualTo(firstBand.getId());
		assertThat(firstBandViewModel.getName()).isEqualTo("Band A [1]");

		BandViewModel secondBandViewModel = bandViewModelList.get(1);
		Band secondBand = BandList.get(1);

		assertThat(secondBandViewModel.getId()).isEqualTo(secondBand.getId());
		assertThat(secondBandViewModel.getName()).isEqualTo("Band B [2]");

		MemberViewModel firstMemberViewModel = firstBandViewModel.getMembers().stream().findFirst().get();
		Member firstMember = firstBand.getMembers().stream().findFirst().get();

		assertThat(firstMemberViewModel.getId()).isEqualTo(firstMember.getId());
		assertThat(firstMemberViewModel.getName()).isEqualTo(firstMember.getName());
	}

	private List<EventEntity> buildEventEntities() {
		MemberEntity member = new MemberEntity(1L, "Member 1");
		Set<MemberEntity> members = new HashSet<>(Arrays.asList(member));

		BandEntity band = new BandEntity(1L, "Band 1", members);
		Set<BandEntity> bands = new HashSet<>(Arrays.asList(band));

		return Arrays.asList(new EventEntity(1L, "Title", "Image", bands, 4, "Comment"));
	}

	private List<Event> buildEvent() {
		Member member = new Member(1L, "Member 1");
		Set<Member> members = new HashSet<>(Arrays.asList(member));

		Band firstBand = new Band(1L, "Band A", members);
		Band secondBand = new Band(2L, "Band B", members);

		Set<Band> bands = new LinkedHashSet<>(Arrays.asList(firstBand, secondBand));

		return Arrays.asList(new Event(1L, "Title", "Image", bands, 4, "Comment"));
	}
}

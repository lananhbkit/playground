package fr.salvadordiaz.gwt.client.app.homework;

import javax.inject.Singleton;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;

import fr.salvadordiaz.gwt.client.activity.ActivityMapping;
import fr.salvadordiaz.gwt.client.activity.ApplicationActivityMapper;
import fr.salvadordiaz.gwt.client.activity.ApplicationHistoryMapper;
import fr.salvadordiaz.gwt.client.activity.DefaultPlace;
import fr.salvadordiaz.gwt.client.activity.PlaceAwareActivity;
import fr.salvadordiaz.gwt.client.repo.Repo;
import fr.salvadordiaz.gwt.client.repo.RepositoryActivity;
import fr.salvadordiaz.gwt.client.repo.RepositoryDisplay;
import fr.salvadordiaz.gwt.client.repo.RepositoryPlace;
import fr.salvadordiaz.gwt.client.repo.ui.RepositoryView;
import fr.salvadordiaz.gwt.client.search.Search;
import fr.salvadordiaz.gwt.client.search.SearchActivity;
import fr.salvadordiaz.gwt.client.search.SearchDisplay;
import fr.salvadordiaz.gwt.client.search.SearchPlace;
import fr.salvadordiaz.gwt.client.search.ui.SearchView;

public class HomeworkModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ActivityMapper.class).annotatedWith(ApplicationActivityMapper.class).to(HomeworkActivityMapper.class);
		bind(Place.class).annotatedWith(DefaultPlace.class).to(SearchPlace.class).in(Singleton.class);
		bind(PlaceHistoryMapper.class).annotatedWith(ApplicationHistoryMapper.class).to(HomeworkHistoryMapper.class).in(Singleton.class);

		bind(SearchActivity.class).in(Singleton.class);
		bind(SearchDisplay.class).to(SearchView.class).in(Singleton.class);

		bind(RepositoryActivity.class).in(Singleton.class);
		bind(RepositoryDisplay.class).to(RepositoryView.class).in(Singleton.class);
	}

	@Provides
	@Search
	ActivityMapping firstActivityMapping(final SearchActivity activity) {
		return new ActivityMapping() {
			@Override
			public Class<? extends Place> getPlaceType() {
				return SearchPlace.class;
			}

			@Override
			public PlaceAwareActivity getActivity() {
				return activity;
			}
		};
	}

	@Provides
	@Repo
	ActivityMapping repoActivityMapping(final RepositoryActivity activity) {
		return new ActivityMapping() {
			@Override
			public Class<? extends Place> getPlaceType() {
				return RepositoryPlace.class;
			}

			@Override
			public PlaceAwareActivity getActivity() {
				return activity;
			}
		};
	}
}
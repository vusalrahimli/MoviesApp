package com.example.moviesapp.di

import com.example.moviesapp.domain.repository.MovieRepository
import com.example.moviesapp.domain.usecase.*
import com.example.moviesapp.domain.usecase.local.*
import com.example.moviesapp.domain.usecase.local.nowplaying.DeleteNowPlayingTableUseCase
import com.example.moviesapp.domain.usecase.local.nowplaying.GetAllNowPlayingUseCase
import com.example.moviesapp.domain.usecase.local.nowplaying.InsertNowPlayingListUseCase
import com.example.moviesapp.domain.usecase.local.popular.DeletePopularTableUseCase
import com.example.moviesapp.domain.usecase.local.popular.GetAllPopularUseCase
import com.example.moviesapp.domain.usecase.local.popular.InsertPopularListUseCase
import com.example.moviesapp.domain.usecase.local.toprated.DeleteTopRatedTableUseCase
import com.example.moviesapp.domain.usecase.local.toprated.GetAllTopRatedUseCase
import com.example.moviesapp.domain.usecase.local.toprated.InsertTopRatedListUseCase
import com.example.moviesapp.domain.usecase.local.upcoming.DeleteUpcomingTableUseCase
import com.example.moviesapp.domain.usecase.local.upcoming.GetAllUpcomingUseCase
import com.example.moviesapp.domain.usecase.local.upcoming.InsertUpcomingListUseCase
import com.example.moviesapp.domain.usecase.local.watchlist.*
import com.example.moviesapp.domain.usecase.remote.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideUseCase(movieRepository: MovieRepository): UseCases {
        return UseCases(
            // Remote
            GetNowPlayingUseCase(movieRepository = movieRepository),
            GetUpcomingUseCase(movieRepository = movieRepository),
            GetTopRatedUseCase(movieRepository = movieRepository),
            GetPopularUseCase(movieRepository = movieRepository),
            GetMovieTrailerUseCase(movieRepository = movieRepository),
            GetMovieReviewsUseCase(movieRepository = movieRepository),
            GetMovieCreditsUseCase(movieRepository = movieRepository),
            GetMovieGenresUseCase(movieRepository = movieRepository),
            GetMovieDurationUseCase(movieRepository = movieRepository),
            GetPersonDetailsUseCase(movieRepository = movieRepository),

            // Local
            // Now Playing
            GetAllNowPlayingUseCase(movieRepository = movieRepository),
            DeleteNowPlayingTableUseCase(movieRepository = movieRepository),
            InsertNowPlayingListUseCase(movieRepository = movieRepository),

            // Upcoming
            GetAllUpcomingUseCase(movieRepository = movieRepository),
            DeleteUpcomingTableUseCase(movieRepository = movieRepository),
            InsertUpcomingListUseCase(movieRepository = movieRepository),

            // Top Rated
            GetAllTopRatedUseCase(movieRepository = movieRepository),
            DeleteTopRatedTableUseCase(movieRepository = movieRepository),
            InsertTopRatedListUseCase(movieRepository = movieRepository),

            // Popular
            GetAllPopularUseCase(movieRepository = movieRepository),
            DeletePopularTableUseCase(movieRepository = movieRepository),
            InsertPopularListUseCase(movieRepository = movieRepository),

            // Watch List
            GetWatchListMoviesUseCase(movieRepository = movieRepository),
            InsertWatchListMoviesUseCase(movieRepository = movieRepository),
            RemoveWatchListMovieUseCase(movieRepository = movieRepository),
            GetWatchListMovieByIdUseCase(movieRepository = movieRepository),
            UpdateWatchListStatusUseCase(movieRepository = movieRepository),
            UpdateWatchListModelUseCase(movieRepository = movieRepository),
        )
    }
}

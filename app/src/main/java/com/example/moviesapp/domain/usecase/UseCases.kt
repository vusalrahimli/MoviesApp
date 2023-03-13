package com.example.moviesapp.domain.usecase

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

class UseCases(
    // Remote
    val getNowPlayingUseCase: GetNowPlayingUseCase,
    val getUpcomingUseCase: GetUpcomingUseCase,
    val getTopRatedUseCase: GetTopRatedUseCase,
    val getPopularUseCase: GetPopularUseCase,
    val getMovieTrailerUseCase: GetMovieTrailerUseCase,
    val getMovieReviewsUseCase: GetMovieReviewsUseCase,
    val getMovieCreditsUseCase: GetMovieCreditsUseCase,
    val getMovieGenresUseCase: GetMovieGenresUseCase,
    val getMovieDurationUseCase: GetMovieDurationUseCase,
    val getPersonDetailsUseCase: GetPersonDetailsUseCase,

    // Local
    // Now Playing
    val getAllNowPlayingUseCase: GetAllNowPlayingUseCase,
    val deleteNowPlayingTableUseCase: DeleteNowPlayingTableUseCase,
    val insertNowPlayingListUseCase: InsertNowPlayingListUseCase,

    // Upcoming
    val getAllUpcomingUseCase: GetAllUpcomingUseCase,
    val deleteUpcomingTableUseCase: DeleteUpcomingTableUseCase,
    val insertUpcomingUseCase: InsertUpcomingListUseCase,

    // Top Rated
    val getAllTopRatedUseCase: GetAllTopRatedUseCase,
    val deleteTopRatedTableUseCase: DeleteTopRatedTableUseCase,
    val insertTopRatedListUseCase: InsertTopRatedListUseCase,

    // Popular
    val getAllPopularUseCase: GetAllPopularUseCase,
    val deletePopularTableUseCase: DeletePopularTableUseCase,
    val insertPopularListUseCase: InsertPopularListUseCase,

    // Watch List
    val getWatchListMoviesUseCase: GetWatchListMoviesUseCase,
    val insertWatchListMoviesUseCase: InsertWatchListMoviesUseCase,
    val removeWatchListMovieUseCase: RemoveWatchListMovieUseCase,
    val getWatchListMovieByIdUseCase: GetWatchListMovieByIdUseCase,
    val updateWatchListStatusUseCase: UpdateWatchListStatusUseCase,
    val updateWatchListModelUseCase: UpdateWatchListModelUseCase,
)

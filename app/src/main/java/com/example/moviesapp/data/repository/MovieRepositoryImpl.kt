package com.example.moviesapp.data.repository

import androidx.lifecycle.LiveData
import com.example.moviesapp.data.model.dto.homepagedto.NowPlayingDto
import com.example.moviesapp.data.model.dto.homepagedto.PopularDto
import com.example.moviesapp.data.model.dto.homepagedto.TopRatedDto
import com.example.moviesapp.data.model.dto.homepagedto.UpcomingDto
import com.example.moviesapp.data.model.homepage.nowplaying.NowPlayingModel
import com.example.moviesapp.data.model.homepage.popular.PopularModel
import com.example.moviesapp.data.model.homepage.toprated.TopRatedModel
import com.example.moviesapp.data.model.homepage.upcoming.UpcomingModel
import com.example.moviesapp.data.model.moviedetailspage.cast.CastModel
import com.example.moviesapp.data.model.moviedetailspage.cast.persondetails.PersonDetailsResponse
import com.example.moviesapp.data.model.moviedetailspage.moviedetails.Genre
import com.example.moviesapp.data.model.moviedetailspage.moviedetails.MovieDetailsResponse
import com.example.moviesapp.data.model.moviedetailspage.reviews.ReviewModel
import com.example.moviesapp.data.model.moviedetailspage.trailer.TrailerModel
import com.example.moviesapp.data.model.watchlist.WatchListModel
import com.example.moviesapp.data.other.Constants
import com.example.moviesapp.data.repository.datasource.LocalDataSource
import com.example.moviesapp.data.repository.datasource.RemoteDataSource
import com.example.moviesapp.domain.repository.MovieRepository
import com.example.moviesapp.util.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : MovieRepository {

    // Remote
    override suspend fun getNowPlaying(): Resource<List<NowPlayingModel>> {
        val response = remoteDataSource.getNowPlaying(Constants.API_KEY)
        val result = response.body()
        result?.results?.let {
            return if (response.isSuccessful) {
                Resource.Success(it)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getUpcoming(): Resource<List<UpcomingModel>> {
        val response = remoteDataSource.getUpcoming(Constants.API_KEY)
        val responseResult = response.body()
        responseResult?.results?.let {
            return if (response.isSuccessful) {
                Resource.Success(it)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getTopRated(): Resource<List<TopRatedModel>> {
        val response = remoteDataSource.getTopRated(Constants.API_KEY)
        val responseResult = response.body()
        responseResult?.results?.let {
            return if (response.isSuccessful) {
                Resource.Success(it)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getPopular(): Resource<List<PopularModel>> {
        val response = remoteDataSource.getPopular(Constants.API_KEY)
        val responseResult = response.body()
        responseResult?.results?.let {
            return if (response.isSuccessful) {
                Resource.Success(it)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getMovieTrailer(movieID: Int): Resource<List<TrailerModel>> {
        val response = remoteDataSource.getMovieTrailer(movieID, Constants.API_KEY)
        val responseResult = response.body()
        responseResult?.results?.let {
            return if (response.isSuccessful) {
                Resource.Success(it)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getMovieReviews(movieID: Int): Resource<List<ReviewModel>> {
        val response = remoteDataSource.getMovieReviews(movieID, Constants.API_KEY)
        val responseResult = response.body()
        responseResult?.results?.let {
            return if (response.isSuccessful) {
                Resource.Success(it)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getMovieCredits(movieID: Int): Resource<List<CastModel>> {
        val response = remoteDataSource.getMovieCredits(movieID, Constants.API_KEY)
        val responseResult = response.body()
        responseResult?.cast?.let {
            return if (response.isSuccessful) {
                Resource.Success(it)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getMovieGenres(movieID: Int): Resource<List<Genre>> {
        val response = remoteDataSource.getMovieDetails(movieID, Constants.API_KEY)
        val responseResult = response.body()
        responseResult?.genres?.let {
            return if (response.isSuccessful) {
                Resource.Success(it)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getMovieDuration(movieID: Int): Resource<MovieDetailsResponse> {
        val response = remoteDataSource.getMovieDetails(movieID, Constants.API_KEY)
        val responseResult = response.body()
        responseResult?.let {
            return if (response.isSuccessful) {
                Resource.Success(it)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getPersonDetails(personID: Int): Resource<PersonDetailsResponse> {
        val response = remoteDataSource.getPersonDetails(personID, Constants.API_KEY)
        val responseResult = response.body()
        responseResult?.let {
            return if (response.isSuccessful) {
                Resource.Success(it)
            } else {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    // Local
    // Now Playing
    override fun getAllNowPlaying(): LiveData<List<NowPlayingDto>> =
        localDataSource.getAllNowPlaying()

    override suspend fun insertNowPlayingList(listNP: List<NowPlayingDto>) =
        localDataSource.insertNowPlayingList(listNP)

    override suspend fun updateWatchListStatus(dtoNP: NowPlayingDto) =
        localDataSource.updateWatchListStatus(dtoNP)

    override suspend fun deleteNowPlayingTable() =
        localDataSource.deleteNowPlayingTable()

    // Upcoming
    override fun getAllUpcoming(): LiveData<List<UpcomingDto>> = localDataSource.getAllUpcoming()

    override suspend fun insertUpcomingList(listUpcoming: List<UpcomingDto>) =
        localDataSource.insertUpcomingList(listUpcoming)

    override suspend fun updateWatchListStatus(dtoUpcoming: UpcomingDto) =
        localDataSource.updateWatchListStatus(dtoUpcoming)

    override suspend fun deleteUpcomingTable() = localDataSource.deleteUpcomingTable()

    // Top Rated
    override fun getAllTopRated(): LiveData<List<TopRatedDto>> = localDataSource.getAllTopRated()

    override suspend fun insertTopRatedList(listTopRated: List<TopRatedDto>) =
        localDataSource.insertTopRatedList(listTopRated)

    override suspend fun updateWatchListStatus(dtoTopRated: TopRatedDto) =
        localDataSource.updateWatchListStatus(dtoTopRated)

    override suspend fun deleteTopRatedTable() = localDataSource.deleteTopRatedTable()

    // Popular
    override fun getAllPopular(): LiveData<List<PopularDto>> = localDataSource.getAllPopular()

    override suspend fun insertPopularList(listPopular: List<PopularDto>) =
        localDataSource.insertPopularList(listPopular)

    override suspend fun updateWatchListStatus(dtoPopular: PopularDto) =
        localDataSource.updateWatchListStatus(dtoPopular)

    override suspend fun deletePopularTable() = localDataSource.deletePopularTable()

    // Watch List
    override fun getWatchListMovies(): LiveData<List<WatchListModel>> =
        localDataSource.getWatchListMovies()

    override suspend fun insertMovieToWatchList(watchListMovie: WatchListModel) =
        localDataSource.insertMovieToWatchList(watchListMovie)

    override suspend fun removeWatchListMovie(watchListModel: WatchListModel) =
        localDataSource.removeWatchListMovie(watchListModel)

    override suspend fun getWatchListMovieById(movieID: Int): WatchListModel =
        localDataSource.getWatchListMovieById(movieID)

    override suspend fun updateWatchListModel(watchListModel: WatchListModel) =
        localDataSource.updateWatchListModel(watchListModel)
}

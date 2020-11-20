package com.wind.deezerkmp.shared.data

import com.wind.deezerkmp.shared.data.model.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * Created by Phong Huynh on 11/4/2020
 */
interface Repository {
    suspend fun getGenreList(): RestResponse<NetworkGenre>
    suspend fun getArtistByGenreList(genreId: String): RestResponse<NetworkArtist>
    suspend fun getArtistDetail(id: String): NetworkArtistDetail
    suspend fun getArtistAlbumList(id: String): RestResponse<NetworkAlbum>
    suspend fun getArtistTopList(id: String): RestResponse<NetworkArtistTop>
    suspend fun getArtistCommentList(id: String): RestResponse<Any>
    suspend fun getArtistFanList(id: String): RestResponse<Any>
    suspend fun getArtistRelatedList(id: String): RestResponse<Any>
    suspend fun getArtistRadioList(id: String): RestResponse<Any>
    suspend fun getArtistRelatedPlaylist(id: String): RestResponse<Any>
    suspend fun getTrackListInAlbum(id: String): RestResponse<NetworkTrack>
}

private const val endpoint = "https://api.deezer.com/"
internal class RepositoryImpl internal constructor(private val client: HttpClient) : Repository {
    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom(endpoint)
            encodedPath = path
        }
    }

    override suspend fun getGenreList(): RestResponse<NetworkGenre> {
        return client.get {
            apiUrl("genre")
        }
    }

    override suspend fun getArtistByGenreList(genreId: String): RestResponse<NetworkArtist> {
        return client.get {
            apiUrl("genre/$genreId/artists")
        }
    }

    ////////////////////////// ARTIST //////////////////////////////
    override suspend fun getArtistDetail(id: String): NetworkArtistDetail {
        return client.get {
            apiUrl("artist/$id")
        }
    }

    override suspend fun getArtistAlbumList(id: String): RestResponse<NetworkAlbum> {
        return client.get {
            apiUrl("artist/$id/albums")
        }
    }

    override suspend fun getArtistTopList(id: String): RestResponse<NetworkArtistTop> {
        return client.get {
            apiUrl("artist/$id/top")
        }
    }

    override suspend fun getArtistCommentList(id: String): RestResponse<NetworkArtistComment> {
        return client.get {
            apiUrl("artist/$id/comments")
        }
    }

    override suspend fun getArtistFanList(id: String): RestResponse<NetworkArtistFan> {
        return client.get {
            apiUrl("artist/$id/fans")
        }
    }

    override suspend fun getArtistRelatedList(id: String): RestResponse<NetworkArtistRelated> {
        return client.get {
            apiUrl("artist/$id/related")
        }
    }

    override suspend fun getArtistRadioList(id: String): RestResponse<NetworkArtistRadio> {
        return client.get {
            apiUrl("artist/$id/radio")
        }
    }

    override suspend fun getArtistRelatedPlaylist(id: String): RestResponse<NetworkPlaylist> {
        return client.get {
            apiUrl("artist/$id/playlists")
        }
    }

    ////////////////////////// ARTIST //////////////////////////////
    override suspend fun getTrackListInAlbum(id: String): RestResponse<NetworkTrack> {
        return client.get {
            apiUrl("album/$id/tracks")
        }
    }

}
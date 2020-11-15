package com.wind.deezerkmp.shared.util

/**
 * Created by Phong Huynh on 10/8/2020
 */

sealed class LoadState(
    val endOfPaginationReached: Boolean
) {
    /**
     * Indicates the [PagingData] is not currently loading, and no error currently observed.
     *
     * @param endOfPaginationReached `false` if there is more data to load in the [LoadType] this
     * [LoadState] is associated with, `true` otherwise. This parameter informs [Pager] if it
     * should continue to make requests for additional data in this direction or if it should
     * halt as the end of the dataset has been reached.
     */
    class NotLoading(
        endOfPaginationReached: Boolean
    ) : LoadState(endOfPaginationReached) {

        override fun toString(): String {
            return "NotLoading(endOfPaginationReached=$endOfPaginationReached)"
        }

        override fun equals(other: Any?): Boolean {
            return other is NotLoading &&
                    endOfPaginationReached == other.endOfPaginationReached
        }

        override fun hashCode(): Int {
            return endOfPaginationReached.hashCode()
        }

        companion object {
            val Complete = NotLoading(endOfPaginationReached = true)
            val Incomplete = NotLoading(endOfPaginationReached = false)
        }
    }

    /**
     * Loading is in progress.
     */
    object Loading : LoadState(false) {
        override fun toString(): String {
            return "Loading(endOfPaginationReached=$endOfPaginationReached)"
        }

        override fun equals(other: Any?): Boolean {
            return other is Loading &&
                    endOfPaginationReached == other.endOfPaginationReached
        }

        override fun hashCode(): Int {
            return endOfPaginationReached.hashCode()
        }
    }

    /**
     * Loading hit an error.
     *
     * @param error [Throwable] that caused the load operation to generate this error state.
     *
     * @see androidx.paging.PagedList.retry
     */
    // TODO: 11/1/2020 handle suitable return message here
    class Error(
        val error: Throwable
    ) : LoadState(false) {
        override fun equals(other: Any?): Boolean {
            return other is Error &&
                    endOfPaginationReached == other.endOfPaginationReached &&
                    error == other.error
        }

        override fun hashCode(): Int {
            return endOfPaginationReached.hashCode() + error.hashCode()
        }

        override fun toString(): String {
            return "Error(endOfPaginationReached=$endOfPaginationReached, error=$error)"
        }
    }
}
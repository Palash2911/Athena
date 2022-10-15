package com.gdsc.athena

import com.gdsc.athena.models.SavedStories

sealed class DataState {
    class Success(val data: MutableList<SavedStories>) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}

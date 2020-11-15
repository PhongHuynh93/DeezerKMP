package com.wind.animelist.shared.base

import kotlinx.coroutines.CoroutineDispatcher

expect val mainDispatcher: CoroutineDispatcher
expect val ioDispatcher: CoroutineDispatcher
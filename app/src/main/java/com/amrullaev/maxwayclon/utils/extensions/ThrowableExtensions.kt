package com.amrullaev.maxwayclon.utils.extensions


fun Throwable.getMessage() = this.message ?: "Unknown error"
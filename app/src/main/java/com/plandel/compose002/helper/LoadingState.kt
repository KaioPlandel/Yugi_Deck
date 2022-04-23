package com.plandel.compose002.helper

class LoadingState private constructor(status: Status, msg: String? = null){

    companion object {
        val LOADING = LoadingState(Status.RUNNING)
        val LOADED = LoadingState(Status.SUCCEES)
        fun error(msg: String?) = LoadingState(Status.FAILED,msg)
    }


    enum class Status {
        RUNNING,
        SUCCEES,
        FAILED,
    }
}
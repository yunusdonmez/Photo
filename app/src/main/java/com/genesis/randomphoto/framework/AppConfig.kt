package com.genesis.randomphoto.framework

class AppConfig {
    companion object {
        var width = 500
        var height = 500
        var URL = "https://picsum.photos/$width/$height?image="
        fun setURL() {
            URL = "https://picsum.photos/$width/$height?image="
        }
    }
}
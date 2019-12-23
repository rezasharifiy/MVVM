package com.example.mvvm_artichecture_sample.base.model

class MessageModel {
    var title: String? = null
    var message: String? = null
    var isShow: Boolean = false

    constructor() {}

    constructor(show: Boolean?, title: String, message: String) {
        this.title = title
        this.message = message
        this.isShow = show!!
    }
}

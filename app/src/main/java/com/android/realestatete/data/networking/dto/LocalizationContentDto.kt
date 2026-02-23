package com.android.realestatete.data.networking.dto

data class LocalizationContentDto(
    val attachments: List<AttachmentDto>?,
    val text: TextDto?,
    val urls: List<UrlDto>?
)
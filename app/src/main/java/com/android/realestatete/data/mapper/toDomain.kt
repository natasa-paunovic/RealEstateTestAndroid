package com.android.realestatete.data.mapper


import com.android.realestatete.data.networking.dto.PropertyDto
import com.android.realestatete.domain.model.Price
import com.android.realestatete.domain.model.Property

fun PropertyDto.toDomain(): Property {
    val listing = listing
    val localization = listing?.localization?.de
    val attachments = localization?.attachments
    val price = listing?.prices?.buy?.price ?: 0
    val currency = listing?.prices?.currency ?: ""

    val street = listing?.address?.street ?: ""
    val formattedAddress = if (street.isEmpty()) {
        listing?.address?.locality ?: ""
    } else {
        street + ", " + listing?.address?.locality
    }

    val firstImageUrl = attachments
        ?.firstOrNull { it.type == "IMAGE" }
        ?.url ?: ""

    return Property(
        id = id,
        title = localization
            ?.text
            ?.title
            ?: "No title",
        imageUrl = firstImageUrl,
        price = Price(price, currency),
        address = formattedAddress
    )
}
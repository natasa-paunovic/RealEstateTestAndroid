package com.android.realestatete.mapper

import com.android.realestatete.data.mapper.toDomain
import com.android.realestatete.data.networking.dto.AddressDto
import com.android.realestatete.data.networking.dto.AttachmentDto
import com.android.realestatete.data.networking.dto.BuyPriceDto
import com.android.realestatete.data.networking.dto.GeoCoordinatesDto
import com.android.realestatete.data.networking.dto.ListingDto
import com.android.realestatete.data.networking.dto.LocalizationContentDto
import com.android.realestatete.data.networking.dto.LocalizationDto
import com.android.realestatete.data.networking.dto.PricesDto
import com.android.realestatete.data.networking.dto.PropertyDto
import com.android.realestatete.data.networking.dto.TextDto
import com.android.realestatete.data.networking.dto.UrlDto
import junit.framework.TestCase.assertEquals
import org.junit.Test

class PropertyMapperTest {

    @Test
    fun `maps full dto correctly toDomain `() {

        val dto = PropertyDto(
            id = "123",
            remoteViewing = false,
            listingType = null,
            listerBranding = null,
            listing = ListingDto(
                id = "1234",
                offerType = "buy",
                categories = arrayListOf(),
                prices = PricesDto(
                    currency = "CHF",
                    buy = BuyPriceDto(
                        area = "all",
                        price = 1000000L,
                        interval = null
                    )
                ),
                address = AddressDto(
                    country = "CH",
                    locality = "Zurich",
                    postalCode = "8000",
                    region = "ZH",
                    street = "Main Street 1",
                    geoCoordinates = GeoCoordinatesDto(
                        latitude = 47.3769,
                        longitude = 8.5417
                    )
                ),
                characteristics = null,
                localization = LocalizationDto(
                    primary = "de",
                    de = LocalizationContentDto(
                        attachments = listOf(
                            AttachmentDto(
                                type = "DOCUMENT",
                                url = "file.pdf",
                                file = "201705241056461331496.pdf"
                            ),
                            AttachmentDto(
                                type = "IMAGE",
                                url = "image1.jpg",
                                file = "201705241056461331496.jpg"
                            ),
                            AttachmentDto(
                                type = "IMAGE",
                                url = "image2.jpg",
                                file = "201705241056461331496.jpg"
                            )
                        ),
                        text = TextDto(
                            title = "Luxury Villa"
                        ),
                        urls = listOf(
                            UrlDto(
                                type = "DETAILS"
                            )
                        )
                    )
                )
            ),
            lister = null
        )

        val result = dto.toDomain()

        assertEquals("123", result.id)
        assertEquals("Luxury Villa", result.title)
        assertEquals("image1.jpg", result.imageUrl)
        assertEquals(1000000L, result.price?.amount)
        assertEquals("CHF", result.price?.currency)
        assertEquals("Main Street 1, Zurich", result.address)
    }


    @Test
    fun `handles missing values correctly toDomain `() {

        val dto = PropertyDto(
            id = "999",
            remoteViewing = false,
            listingType = null,
            listerBranding = null,
            listing = null,
            lister = null

        )

        val result = dto.toDomain()

        assertEquals("999", result.id)
        assertEquals("No title", result.title)
        assertEquals("", result.imageUrl)
        assertEquals(0L, result.price?.amount)
        assertEquals("", result.price?.currency)
        assertEquals("", result.address)
    }

    @Test
    fun `toDomain formats address without street`() {

        val dto = PropertyDto(
            id = "1",
            remoteViewing = false,
            listingType = null,
            listerBranding = null,
            listing = ListingDto(
                id = null,
                offerType = null,
                categories = null,
                prices = null,
                address = AddressDto(
                    country = "CH",
                    locality = "Bern",
                    postalCode = "8000",
                    region = "ZH",
                    street = "",
                    geoCoordinates = GeoCoordinatesDto(
                        latitude = 47.3769,
                        longitude = 8.5417
                    )
                ),
                characteristics = null,
                localization = null
            ),
            lister = null
        )

        val result = dto.toDomain()

        assertEquals("Bern", result.address)
    }

    @Test
    fun `toDomain selects first IMAGE attachment`() {

        val dto = PropertyDto(
            id = "1",
            remoteViewing = false,
            listingType = null,
            listerBranding = null,
            listing = ListingDto(
                id = "1234",
                offerType = "buy",
                categories = arrayListOf(),
                prices = PricesDto(
                    currency = "CHF",
                    buy = BuyPriceDto(
                        area = "all",
                        price = 1000000L,
                        interval = null
                    )
                ),
                address = AddressDto(
                    country = "CH",
                    locality = "Zurich",
                    postalCode = "8000",
                    region = "ZH",
                    street = "Main Street 1",
                    geoCoordinates = GeoCoordinatesDto(
                        latitude = 47.3769,
                        longitude = 8.5417
                    )
                ),
                characteristics = null,
                localization = LocalizationDto(
                    primary = "de",
                    de = LocalizationContentDto(
                        attachments = listOf(
                            AttachmentDto(
                                type = "DOCUMENT",
                                url = "file.pdf",
                                file = "201705241056461331496.pdf"
                            ),
                            AttachmentDto(
                                type = "IMAGE",
                                url = "image1.jpg",
                                file = "201705241056461331496.jpg"
                            ),
                            AttachmentDto(
                                type = "IMAGE",
                                url = "image2.jpg",
                                file = "201705241056461331496.jpg"
                            )
                        ),
                        text = TextDto(
                            title = "Luxury Villa"
                        ),
                        urls = listOf(
                            UrlDto(
                                type = "DETAILS"
                            )
                        )
                    )
                )
            ),
            lister = null
        )


            val result = dto.toDomain ()

        assertEquals("image1.jpg", result.imageUrl)
    }

}
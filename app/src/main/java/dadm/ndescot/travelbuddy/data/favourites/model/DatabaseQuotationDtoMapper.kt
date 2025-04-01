package dadm.ndescot.travelbuddy.data.favourites.model

import dadm.ndescot.travelbuddy.domain.model.Quotation

fun DatabaseQuotationDto.toDomain(): Quotation {
    return Quotation(
        id = this.id,
        quote = this.text,
        author = this.author
    )
}

fun Quotation.toDatabaseDto(): DatabaseQuotationDto {
    return DatabaseQuotationDto(
        id = this.id,
        text = this.quote,
        author = this.author
    )
}
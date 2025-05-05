package dadm.ndescot.travelbuddy.data.guide.domain

import connectors.default.AllSitesAsGuideQuery
import dadm.ndescot.travelbuddy.domain.model.Site

/*
 * Mapper to convert the GraphQL response to a domain model.
 */
fun AllSitesAsGuideQuery.Data.UsersItem.GuideSitesItem.toDomain(): Site {
    return Site(
        siteName = siteName,
        countryName = countryName
    )
}